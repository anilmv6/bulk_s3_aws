use `auth`;
INSERT INTO `auth_user` VALUES (3343,'586298580505034752','586298580500840448','ksaleh','2018-12-26 00:00:00','','ab-req',NULL,NULL,1,'msnishan@gmail.com','GP001','{bcrypt}$2a$10$kJA/pVWk3V3HVT5ZNHnBD.NE9ObJkgZX20Kwbu3tr73jIQRDTv742','RECEPTIONIST'),(3344,NULL,'1','ksaleh','2018-12-26 00:00:00','','ab-req',NULL,NULL,1,'ksaleh@gmail.com','GP002','{bcrypt}$2a$10$kJA/pVWk3V3HVT5ZNHnBD.NE9ObJkgZX20Kwbu3tr73jIQRDTv742','OWNER');

INSERT INTO `feature_access` VALUES (3345,'ksaleh','2018-12-26 00:00:00','','ab-req',NULL,NULL,1,'TRAINER_ADD','Add a New Trainer','/company/api/trainer','POST'),(3346,'ksaleh','2018-12-26 00:00:00','','ab-req',NULL,NULL,1,'RECEPTIONIST_ADD','Add a New Receptionist','/company/api/receptionist','POST'),(586493845199486976,'msnishan@gmail.com','2019-06-07 09:52:23','','74b035bc-4a33-45e1-a91e-fbb537a17e5f','msnishan@gmail.com','2019-06-07 09:52:23',1,'TRAINER_DELETE','Delete Trainer','/api/organiation/trainer','DELETE'),(586493973582938112,'msnishan@gmail.com','2019-06-07 09:52:23','','5f043682-e0dd-4856-8e4a-a99cf831a5de','msnishan@gmail.com','2019-06-07 09:52:23',1,'TRAINER_VIEW','View Trainer','/api/organiation/trainer','GET'),(586494236234448896,'msnishan@gmail.com','2019-06-07 09:52:23','','585f5c21-f145-4c34-8cc7-d7c94ad06867','msnishan@gmail.com','2019-06-07 09:52:23',1,'CUSTOMER_VIEW','View Customer','/api/organiation/customer','GET'),(586494293348286464,'msnishan@gmail.com','2019-06-07 09:52:23','','81e1e24b-b52a-4cff-8f70-f43ee276c886','msnishan@gmail.com','2019-06-07 09:52:23',1,'CUSTOMER_ADD','Add Customer','/api/organiation/customer','POST'),(586542826080272384,'msnishan@gmail.com','2019-06-07 13:08:33','','2c486867-3634-490d-82bd-367b827fadd2','msnishan@gmail.com','2019-06-07 13:08:33',1,'LEAD_ADD','Add Lead','/api/organization/lead','POST'),(586593526801858560,'msnishan@gmail.com','2019-06-07 13:08:33','','71611031-ab58-47db-9c3e-27a010229c1a','msnishan@gmail.com','2019-06-07 13:08:33',1,'LEAD_DELETE','Delete a Lead','/api/organization/lead','DELETE');


INSERT INTO `feature_access_employee` VALUES (3349,'1','1','ksaleh','2018-12-26 00:00:00','','ab-req',NULL,NULL,1,'TRAINER_ADD','GP001'),(3350,'1','1','ksaleh','2018-12-26 00:00:00','','ab-req',NULL,NULL,1,'RECEPTIONIST_ADD','GP001'),(586502083278110720,'586298580505034752','586298580500840448',NULL,NULL,'','0e62d0ae-1aa8-424b-bc0b-af367f4b28be',NULL,NULL,0,'TRAINER_VIEW','GP001'),(586507188245200896,'586298580505034752','586298580500840448',NULL,NULL,'','cecf23b8-cd5a-4ca7-a245-40dc82f50cc1',NULL,NULL,0,'CUSTOMER_VIEW','GP001'),(586595450401947648,'586298580505034752','586298580500840448',NULL,NULL,'','fcad1360-d803-43c3-96b7-9bb9ee75e4b6',NULL,NULL,0,'LEAD_DELETE','GP001'),(586596001151811584,'586298580505034752','586298580500840448',NULL,NULL,'','7cd8d3f4-7160-4cc3-883b-0bbbfb522e7e',NULL,NULL,0,'LEAD_ADD','GP001');


INSERT INTO `oauth_client_details` VALUES ('service-gym','rest-api-user','{bcrypt}$2a$04$xl95D9bpcbIMqNyfHZnGYOYBUQma27Dsr.x/qAaYVompAR9man5Fu','write,read','password,authorization_code,refresh_token,implicit','http://localhost:8080/dashboard','USER',10800,2592000,NULL,'');


INSERT INTO `org_company` VALUES (586298580500840448,'msnishan@gmail.com','2019-06-07 02:31:14','','0b48a8f8-831b-4595-89fc-b9bff529e10a','msnishan@gmail.com','2019-06-07 02:31:14',1,'new@gmail.com','New gym');

INSERT INTO `org_point_of_sale` VALUES (586298580505034752,'msnishan@gmail.com','2019-06-07 02:31:14','','0b48a8f8-831b-4595-89fc-b9bff529e10a','msnishan@gmail.com','2019-06-07 02:31:14',1,'pos1@gmail.com','pos1',586298580500840448),(586298580505034753,'msnishan@gmail.com','2019-06-07 02:31:14','','0b48a8f8-831b-4595-89fc-b9bff529e10a','msnishan@gmail.com','2019-06-07 02:31:14',1,'pos2@gmail.com','pos2',586298580500840448);



INSERT INTO `org_customer` VALUES (586300745940303872,'msnishan@gmail.com','2019-06-07 02:39:51','','4d6dbffb-a920-4b7f-8ad9-97104810952b','msnishan@gmail.com','2019-06-07 02:39:51',1,'2019-06-06 21:06:41',NULL,NULL,'Khandekar',NULL,NULL,'Saleh',NULL,NULL),(586305627556511744,'msnishan@gmail.com','2019-06-07 02:59:16','','2d3f792c-b81d-471d-80d3-cccb5452e059','msnishan@gmail.com','2019-06-07 02:59:16',1,'2019-06-06 21:06:41',NULL,NULL,'Khandekar1',NULL,NULL,'Saleh',NULL,NULL),(586569121157709824,'msnishan@gmail.com','2019-06-07 20:26:20','','a2c3fd9c-fe13-48b0-9909-7baa9dd25b63','msnishan@gmail.com','2019-06-07 20:26:20',1,NULL,NULL,NULL,'sample',NULL,NULL,NULL,NULL, NULL),(586596046446100480,'msnishan@gmail.com','2019-06-07 22:13:16','','84ecd45a-f620-4d69-9f63-7765e8894e93','msnishan@gmail.com','2019-06-07 22:13:16',1,NULL,NULL,NULL,'sample',NULL,NULL,NULL,NULL, NULL);


INSERT INTO `org_address` VALUES (586300745957081088,'msnishan@gmail.com','2019-06-06 21:06:41','','4d6dbffb-a920-4b7f-8ad9-97104810952b','msnishan@gmail.com','2019-06-06 21:06:41',1,'Home','BLR','IN','line1','560093','','KA',586300745940303872),(586305632770031616,'msnishan@gmail.com','2019-06-06 21:06:41','','2d3f792c-b81d-471d-80d3-cccb5452e059','msnishan@gmail.com','2019-06-06 21:06:41',1,'Home','BLR','IN','line1','560093','','KA',586305627556511744);

INSERT INTO `org_pos_customer` VALUES (586305638981795840,'msnishan@gmail.com','2019-06-07 02:59:19','','2d3f792c-b81d-471d-80d3-cccb5452e059','msnishan@gmail.com','2019-06-07 02:59:19',0,NULL,586305627556511744,586298580505034752),(586569161523691520,'msnishan@gmail.com','2019-06-07 20:26:26','','a2c3fd9c-fe13-48b0-9909-7baa9dd25b63','msnishan@gmail.com','2019-06-07 20:26:26',0,NULL,586569121157709824,586298580505034752),(586596046450294784,'msnishan@gmail.com','2019-06-07 22:13:16','','84ecd45a-f620-4d69-9f63-7765e8894e93','msnishan@gmail.com','2019-06-07 22:13:16',0,NULL,586596046446100480,586298580505034752);

INSERT INTO `auth`.`org_employee`
    (`employee_type`,
    `id`,
    `created_by`,
    `created_datetime`,
    `is_enabled`,
    `request_id`,
    `updated_by`,
    `updated_datetime`,
    `version`,
    `company_id`,
    `birth_date`,
    `designation`,
    `email`,
    `employee_id`,
    `first_name`,
    `gender`,
    `last_name`,
    `martial_status`,
    `rating`,
    `receptionist_type`,
    `manager_id`)
    VALUES
    ('manager',
    12242434,
    'ksaleh',
    NULL,
    b'1',
    'req',
    NULL,
    NULL,
    b'1',
    586298580500840448,
    NULL,
    'MANAGER',
    'man@gmail.com',
    'GymMan01',
    'fname',
    'MALE',
    'lname',
    NULL,
    1.6,
    NULL,
    NULL),
    ('receptionist',
    12242435,
    'ksaleh',
    NULL,
    b'1',
    'req',
    NULL,
    NULL,
    b'1',
    586298580500840448,
    NULL,
    'RECEPTIONIST',
    'rep@gmail.com',
    'GymRep01',
    'fname',
    'MALE',
    'lanem',
    NULL,
    1.6,
    'receptionist',
    12242434);
    
    
    INSERT INTO `org_sms_template` VALUES (7001,"Discount Vouchers","Coupons",
"OFFERS:Take an extra 10% of all your purchases. show code 1102YD22",
"6000",200,"anilkumar","2019-05-26",true,"333","Anilkumar","2019-05-26",1);
INSERT INTO `org_sms_template` VALUES (7002,"Discount Vouchers","Coupons",
"OFFERS:Take an extra 10% of all your purchases. show code 1102YD22",
"6000",200,"anilkumar","2019-05-26",true,"333","Anilkumar","2019-05-26",1);