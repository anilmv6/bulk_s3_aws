package com.gympro.app.organization.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gympro.app.base.db.EntityFactory;
import com.gympro.app.base.exception.BusinessException;
import com.gympro.app.organization.config.BulkUploadConfig;
import com.gympro.app.organization.domain.BulkUploadTemplate;
import com.gympro.app.organization.domain.Student;
import com.gympro.app.organization.dto.BulkUploadRequest;
import com.gympro.app.organization.dto.JsonDataDTO;
import com.gympro.app.organization.dto.StudentDTO;
import com.gympro.app.organization.repository.BulkUploadRepository;
import com.gympro.app.organization.util.Constants;
import com.gympro.app.organization.util.ConvertStringToJsonUtils;

@Service
public class BulkUploadService {

	@Autowired
	private BulkUploadConfig bulkUploadConfig;
	
	@Autowired
	BulkUploadRepository bulkUploadRepository;
	
	@Autowired
	private EntityFactory entityFactory;
	
	@Autowired
	@Qualifier("AsyncPool")
	private Executor executor;

	public BulkUploadTemplate uploadData(String xlsValues, MultipartFile file) throws BusinessException {

		
		BulkUploadRequest bulkUploadRequest = null;
		try {
			bulkUploadRequest = ConvertStringToJsonUtils.getObj(xlsValues);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BulkUploadTemplate bulkUploadTemplate = entityFactory.newEntity(BulkUploadTemplate.class);
		bulkUploadTemplate.setPosId(bulkUploadRequest.getPosId());
		bulkUploadTemplate.setCreatedBy("Anilkumar");
		bulkUploadTemplate.setStatus("PROCESSING");
		bulkUploadTemplate.setType(bulkUploadRequest.getFileType());
		BulkUploadTemplate bulkUploadTemplateObj = bulkUploadRepository.save(bulkUploadTemplate);
		Runnable processUploadAsync = () -> {
		try {
			JsonObject json = getExcelDataAsJsonObject(getUploadedFile(file));
			uploadJsonFileToS3(json, file, bulkUploadTemplate);

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		};
		CompletableFuture.runAsync(processUploadAsync, executor);
		return bulkUploadTemplateObj;
	}

	public static JsonObject getExcelDataAsJsonObject(File excelFile) throws InvalidFormatException {

		JsonObject sheetsJsonObject = new JsonObject();
		Workbook workbook = null;

		try {
			workbook = new XSSFWorkbook(excelFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

			JsonArray sheetArray = new JsonArray();
			ArrayList<String> columnNames = new ArrayList<String>();
			Sheet sheet = workbook.getSheetAt(i);
			Iterator<Row> sheetIterator = sheet.iterator();

			while (sheetIterator.hasNext()) {

				Row currentRow = sheetIterator.next();
				JsonObject jsonObject = new JsonObject();

				if (currentRow.getRowNum() != 0) {

					for (int j = 0; j < columnNames.size(); j++) {

						if (currentRow.getCell(j) != null) {
							if (currentRow.getCell(j).getCellType() == Cell.CELL_TYPE_STRING) {
								jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getStringCellValue());
							} else if (currentRow.getCell(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {
								jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getNumericCellValue());
							} else if (currentRow.getCell(j).getCellType() == Cell.CELL_TYPE_BOOLEAN) {
								jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getBooleanCellValue());
							} else if (currentRow.getCell(j).getCellType() == Cell.CELL_TYPE_BLANK) {
								jsonObject.addProperty(columnNames.get(j), "");
							}
						} else {
							jsonObject.addProperty(columnNames.get(j), "");
						}
					}
					sheetArray.add(jsonObject);
				} else {
					// store column names
					for (int k = 0; k < currentRow.getPhysicalNumberOfCells(); k++) {
						columnNames.add(currentRow.getCell(k).getStringCellValue());
					}
				}
			}
			sheetsJsonObject.add(workbook.getSheetName(i), sheetArray);
		}

		return sheetsJsonObject;

	}

	void uploadJsonFileToS3(JsonObject sheetsJsonObject, MultipartFile file,BulkUploadTemplate bulkUploadTemplate) {
		try {

			System.out.println(file.getOriginalFilename());
			File jsonFileName = createFileFromJson(file.getOriginalFilename().split("\\.")[0] + ".json", sheetsJsonObject);
			bulkUploadConfig.saveFileToAWS(jsonFileName);

			downloadFileFromAWS(jsonFileName, bulkUploadTemplate);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void prepareData(StudentDTO action, List<StudentDTO> listError,BulkUploadTemplate bulkUploadTemplate) {

		if (action.getName() != null) {
			
			Student student = entityFactory.newEntity(Student.class);
			BeanUtils.copyProperties(action, student);
			
			///BulkUploadTemplate bulkUploadTemplateObj = bulkUploadRepository.save(bulkUploadTemplate);
			System.out.println("save to system");
			// writeObjects2ExcelFile(List<Student> studentsList, String filePath);
		} else {
			listError.add(action);
			BulkUploadTemplate bulkUploadTemplateObj = bulkUploadRepository.save(bulkUploadTemplate);
		}
	}

	public File downloadFileFromAWS(File jsonFileName,BulkUploadTemplate bulkUploadTemplate) {

		List<StudentDTO> list = new ArrayList<>();
		List<StudentDTO> listError = new ArrayList<>();

		InputStream downloadJsonFile;
		File newFile = null;
		try {
			downloadJsonFile = bulkUploadConfig.downloadFileFromAWS(jsonFileName.getName());
			Reader reader = new InputStreamReader(downloadJsonFile, "UTF-8");
			list = new Gson().fromJson(reader, JsonDataDTO.class).getStudentList();
			String filePath = "C:\\Work\\personal\\downloadnewfile.xlsx";

			File myTempFile = new File(Files.createTempDir(), "downloadnewfile.xlsx");
			System.out.println(myTempFile.getAbsolutePath());
			 newFile = writeObjects2ExcelFile(list, myTempFile.getAbsolutePath());

			list.forEach(action -> prepareData(action, listError, bulkUploadTemplate));

			if (!CollectionUtils.isEmpty(listError)) {

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile;
	}

	private File writeObjects2ExcelFile(List<StudentDTO> studentsList, String filePath) throws IOException {

		File fileOutPut = createFile(Constants.FILE_NAME);

		String[] COLUMNs = { "Id", "name", "age", "cat", "lname" };

		Workbook workbook = new XSSFWorkbook();

		CreationHelper createHelper = workbook.getCreationHelper();

		Sheet sheet = workbook.createSheet("Customers");

		Font headerFont = workbook.createFont();
		// headerFont.setBold(true);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Row for Header
		Row headerRow = sheet.createRow(0);

		// Header
		for (int col = 0; col < COLUMNs.length; col++) {
			Cell cell = headerRow.createCell(col);
			cell.setCellValue(COLUMNs[col]);
			cell.setCellStyle(headerCellStyle);
		}

		// CellStyle for Age
		CellStyle ageCellStyle = workbook.createCellStyle();
		ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

		int rowIdx = 1;
		for (StudentDTO student : studentsList) {
			Row row = sheet.createRow(rowIdx++);
			row.createCell(0).setCellValue(student.getId());
			row.createCell(1).setCellValue(student.getName());
			row.createCell(2).setCellValue(student.getAge());
			row.createCell(3).setCellValue(student.getCat());
			row.createCell(4).setCellValue(student.getLname());
		}

		FileOutputStream fileOut = new FileOutputStream(filePath);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
		return fileOutPut;
	}

	public File getUploadedFile(MultipartFile file) {
		File convFile = new File(file.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convFile;
	}

	public File createFileFromJson(String fileName, JsonObject sheetsJsonObject) {
		File file = new File(fileName);
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(sheetsJsonObject.toString());
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

	public File createFileFromJson(String fileName, List<StudentDTO> Object) {

		File file = new File(fileName);
		String studentString = Object.stream().map(StudentDTO::toString).collect(Collectors.joining(","));
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(studentString);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	public File createFile(String fileName) {
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
}
