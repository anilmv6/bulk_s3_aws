package com.gympro.app.base.sms.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.gympro.app.organization.dto.SendSMSDTO;

@Configuration
@Component
public class SMSConfig {

    public String sendSMS(SendSMSDTO sendSms,StringBuilder postdata) {
		boolean isAppend = false;
		URL myURL = null;
		BufferedReader reader = null;
		URLConnection myURLConnection = null;
		StringBuilder buildContacts = new StringBuilder();
		
		String mainUrl = "http://api.msg91.com/api/sendhttp.php?";
		
		System.out.println(buildContacts.toString());

		String authkey = "277617AgMCmr35Kq5ce39928";
		String senderId = "GYMPRO";
		String route = "4";
		
		StringBuilder postdataBuild = new StringBuilder(mainUrl);
		postdataBuild.append("authkey=" + authkey);
		postdataBuild.append(postdata);
		postdataBuild.append("&route=" + route);
		postdataBuild.append("&sender=" + senderId);

		mainUrl = postdataBuild.toString();
		
		  try { 
		      String response; myURL = new URL(mainUrl); myURLConnection =
		  
		  myURL.openConnection(); myURLConnection.connect(); reader = new
		  BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		  //while ((response = reader.readLine()) != null) //reader.close(); 
		  } catch(IOException e) 
		  { 
		      e.printStackTrace(); 
		}
		return mainUrl;
	}
}
