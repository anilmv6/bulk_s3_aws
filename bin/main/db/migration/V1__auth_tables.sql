create table if not exists oauth_client_details
(
    client_id               VARCHAR(255) PRIMARY KEY,
    resource_ids            VARCHAR(255),
    client_secret           VARCHAR(255),
    scope                   VARCHAR(255),
    authorized_grant_types  VARCHAR(255),
    web_server_redirect_uri VARCHAR(255),
    authorities             VARCHAR(255),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(255)
);

create table if not exists oauth_client_token
(
    token_id          VARCHAR(255),
    token             LONG VARBINARY,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name         VARCHAR(255),
    client_id         VARCHAR(255)
);

create table if not exists oauth_access_token
(
    token_id          VARCHAR(255),
    token             LONG VARBINARY,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name         VARCHAR(255),
    client_id         VARCHAR(255),
    authentication    LONG VARBINARY,
    refresh_token     VARCHAR(255)
);

create table if not exists oauth_refresh_token
(
    token_id       VARCHAR(255),
    token          LONG VARBINARY,
    authentication LONG VARBINARY
);

create table if not exists oauth_code
(
    code           VARCHAR(255),
    authentication LONG VARBINARY
);

create table if not exists oauth_approvals
(
    userId         VARCHAR(255),
    clientId       VARCHAR(255),
    scope          VARCHAR(255),
    status         VARCHAR(10),
    expiresAt      TIMESTAMP,
    lastModifiedAt TIMESTAMP
);

create table if not exists ClientDetails
(
    appId                  VARCHAR(255) PRIMARY KEY,
    resourceIds            VARCHAR(255),
    appSecret              VARCHAR(255),
    scope                  VARCHAR(255),
    grantTypes             VARCHAR(255),
    redirectUrl            VARCHAR(255),
    authorities            VARCHAR(255),
    access_token_validity  INTEGER,
    refresh_token_validity INTEGER,
    additionalInformation  VARCHAR(4096),
    autoApproveScopes      VARCHAR(255)
);

CREATE TABLE if not exists auth_user
(
    `id`               bigint(20)   NOT NULL,
    `pos_id`           varchar(255) DEFAULT NULL,
    `company_id`       varchar(255) NOT NULL,
    `created_by`       varchar(255) DEFAULT NULL,
    `created_datetime` datetime     DEFAULT NULL,
    `is_enabled`       bit(1)       NOT NULL,
    `request_id`       varchar(255) NOT NULL,
    `updated_by`       varchar(255) DEFAULT NULL,
    `updated_datetime` datetime     DEFAULT NULL,
    `version`          bigint(20)   NOT NULL,
    `email`            varchar(255) NOT NULL,
    `employee_id`      varchar(255) NOT NULL,
    `password`         varchar(255) NOT NULL,
    `designation`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_klvc3dss72qnlrjp2bai055mw` (`employee_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists auth_address
(
    `id`               bigint(20)   NOT NULL,
    `pos_id`           varchar(255) NOT NULL,
    `created_by`       varchar(255) DEFAULT NULL,
    `created_datetime` datetime     DEFAULT NULL,
    `is_enabled`       bit(1)       NOT NULL,
    `request_id`       varchar(255) NOT NULL,
    `updated_by`       varchar(255) DEFAULT NULL,
    `updated_datetime` datetime     DEFAULT NULL,
    `version`          bigint(20)   NOT NULL,
    `address_line_1`   varchar(255) NOT NULL,
    `address_line_2`   varchar(255) DEFAULT NULL,
    `city`             varchar(255) DEFAULT NULL,
    `country`          varchar(255) NOT NULL,
    `county`           varchar(255) DEFAULT NULL,
    `iso_country`      varchar(255) NOT NULL,
    `pin_code`         bigint(20)   DEFAULT NULL,
    `state`            varchar(255) DEFAULT NULL,
    `type`             varchar(255) NOT NULL,
    `employee_id`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK6tq8i9j835mjfl8xuarb72316` (`employee_id`),
    CONSTRAINT `FK6tq8i9j835mjfl8xuarb72316` FOREIGN KEY (`employee_id`) REFERENCES `auth_user` (`employee_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

CREATE table if not exists feature_access
(
    `id`               bigint(20)   NOT NULL,
    `created_by`       varchar(255) DEFAULT NULL,
    `created_datetime` datetime     DEFAULT NULL,
    `is_enabled`       bit(1)       NOT NULL,
    `request_id`       varchar(255) NOT NULL,
    `updated_by`       varchar(255) DEFAULT NULL,
    `updated_datetime` datetime     DEFAULT NULL,
    `version`          bigint(20)   NOT NULL,
    `feature_id`       varchar(255) NOT NULL,
    `feature_name`     varchar(255) NOT NULL,
    `feature_url`      varchar(255) NOT NULL,
    `feature_method`   varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_feature_access` (`feature_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists feature_access_employee
(
    `id`               bigint(20)   NOT NULL,
    `pos_id`           varchar(255) NOT NULL,
    `company_id` varchar(255) NOT NULL,
    `created_by`       varchar(255) DEFAULT NULL,
    `created_datetime` datetime     DEFAULT NULL,
    `is_enabled`       bit(1)       NOT NULL,
    `request_id`       varchar(255) NOT NULL,
    `updated_by`       varchar(255) DEFAULT NULL,
    `updated_datetime` datetime     DEFAULT NULL,
    `version`          bigint(20)   NOT NULL,
    `feature_id`       varchar(255) DEFAULT NULL,
    `employee_id`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKh2osps5bpt6u2jif7wt7h0v5h` (`employee_id`, `feature_id`),
    KEY `feature_access_key` (`feature_id`),
    CONSTRAINT `FKf8n47xg27r0kjqnawdwq42ppm` FOREIGN KEY (`employee_id`) REFERENCES `auth_user` (`employee_id`),
    CONSTRAINT `FKhxpwy3j19mjcn392t4tkpxcy1` FOREIGN KEY (`feature_id`) REFERENCES `feature_access` (`feature_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

-- Oganization Tables

create table if not exists `org_customer` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `request_id` varchar(255) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `birth_date` datetime DEFAULT NULL,
  `customer_email` varchar(255) DEFAULT NULL,
  `customer_type` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `martial_status` varchar(255) DEFAULT NULL,
  `receptionist_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl78lv8lqf4g4k72ubdhiayduq` (`receptionist_id`)
) ENGINE=InnoDB;

create table if not exists org_address
(
    id               bigint       not null,
    created_by       varchar(255),
    created_datetime datetime,
    is_enabled       bit          not null,
    request_id       varchar(255) not null,
    updated_by       varchar(255),
    updated_datetime datetime,
    version          bigint       not null,
    address_type     varchar(255),
    city             varchar(255),
    country          varchar(255),
    line1            varchar(255),
    postal_code      varchar(255),
    preferred        bit,
    state            varchar(255),
    customer_id      bigint       not null,
    primary key (id)
) engine = InnoDB;

create table if not exists org_company
(
    id               bigint       not null,
    created_by       varchar(255),
    created_datetime datetime,
    is_enabled       bit          not null,
    request_id       varchar(255) not null,
    updated_by       varchar(255),
    updated_datetime datetime,
    version          bigint       not null,
    company_email    varchar(255),
    company_name     varchar(255),
    primary key (id),
    UNIQUE KEY `UK_org_company` (`company_email`)
) engine = InnoDB;

create table if not exists org_phone
(
    id               bigint       not null,
    created_by       varchar(255),
    created_datetime datetime,
    is_enabled       bit          not null,
    request_id       varchar(255) not null,
    updated_by       varchar(255),
    updated_datetime datetime,
    version          bigint       not null,
    extension        decimal(19, 2),
    phone_number     decimal(19, 2),
    phone_type       varchar(255),
    preferred        bit,
    customer_id      bigint       not null,
    primary key (id)
) engine = InnoDB;

create table if not exists org_point_of_sale
(
    id               bigint       not null,
    created_by       varchar(255),
    created_datetime datetime,
    is_enabled       bit          not null,
    request_id       varchar(255) not null,
    updated_by       varchar(255),
    updated_datetime datetime,
    version          bigint       not null,
    pos_email        varchar(255),
    pos_name         varchar(255),
    company_id       bigint,
    primary key (id),
    UNIQUE KEY `UK_org_point_of_sale` (`pos_email`)
) engine = InnoDB;

create table if not exists org_pos_customer
(
    id               bigint       not null,
    created_by       varchar(255),
    created_datetime datetime,
    is_enabled       bit          not null,
    request_id       varchar(255) not null,
    updated_by       varchar(255),
    updated_datetime datetime,
    version          bigint       not null,
    company_id       bigint,
    customer_id      bigint,
    pos_id           bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists org_pos_employee
 (
    id bigint not null,
    created_by varchar(255),
    created_datetime datetime,
    is_enabled bit not null,
    request_id varchar(255) not null,
    updated_by varchar(255),
    updated_datetime datetime,
    version bigint not null,
    employee_id bigint,
    pos_id bigint,
    primary key (id)
) engine=InnoDB;


CREATE TABLE if not exists `org_employee` (
  `employee_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `request_id` varchar(255) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `company_id` varchar(255) NOT NULL,
  `birth_date` datetime DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employee_id` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `martial_status` varchar(255) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `receptionist_type` varchar(255) DEFAULT 'receptionist',
  `manager_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKlc1l26efi8me0l40r4vwxksss` (`employee_id`),
  KEY `FKnos82tlitxanel3od0uwvbf0i` (`manager_id`),
  CONSTRAINT `FKnos82tlitxanel3od0uwvbf0i` FOREIGN KEY (`manager_id`) REFERENCES `org_employee` (id)
) ENGINE=InnoDB;

create table if not exists org_customer_settings
(
    id                     bigint       not null,
    created_by             varchar(255),
    created_datetime       datetime,
    is_enabled             bit          not null,
    request_id             varchar(255) not null,
    updated_by             varchar(255),
    updated_datetime       datetime,
    version                bigint       not null,
    freeclasses            bit,
    joined_date            datetime,
    member_expiration_date datetime,
    registration_date      datetime,
    customer_id            bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists org_health
(
    id               bigint       not null,
    created_by       varchar(255),
    created_datetime datetime,
    is_enabled       bit          not null,
    request_id       varchar(255) not null,
    updated_by       varchar(255),
    updated_datetime datetime,
    version          bigint       not null,
    allergies        varchar(255),
    diet_plan        varchar(255),
    health_issue     varchar(255),
    customer_id      bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists org_more_details
(
    id               bigint       not null,
    created_by       varchar(255),
    created_datetime datetime,
    is_enabled       bit          not null,
    request_id       varchar(255) not null,
    updated_by       varchar(255),
    updated_datetime datetime,
    version          bigint       not null,
    comments         varchar(255),
    referral         bit,
    customer_id      bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists org_payment
(
    id               bigint       not null,
    created_by       varchar(255),
    created_datetime datetime,
    is_enabled       bit          not null,
    request_id       varchar(255) not null,
    updated_by       varchar(255),
    updated_datetime datetime,
    version          bigint       not null,
    amount_pending   decimal(19, 2),
    full_amount_paid bit,
    part_payment     bit,
    total_amount_to_be_paid decimal(19, 2),
    amount_paid decimal(19, 2),
    customer_id      bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists org_service
(
    id               bigint       not null,
    created_by       varchar(255),
    created_datetime datetime,
    is_enabled       bit          not null,
    request_id       varchar(255) not null,
    updated_by       varchar(255),
    updated_datetime datetime,
    version          bigint       not null,
    amount           decimal(19, 2),
    service_name     varchar(255),
    customer_id      bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists org_social
(
    id               bigint       not null,
    created_by       varchar(255),
    created_datetime datetime,
    is_enabled       bit          not null,
    request_id       varchar(255) not null,
    updated_by       varchar(255),
    updated_datetime datetime,
    version          bigint       not null,
    facebook         varchar(255),
    google           varchar(255),
    twitter          varchar(255),
    customer_id      bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists org_work
(
    id               bigint       not null,
    created_by       varchar(255),
    created_datetime datetime,
    is_enabled       bit          not null,
    request_id       varchar(255) not null,
    updated_by       varchar(255),
    updated_datetime datetime,
    version          bigint       not null,
    company          varchar(255),
    company_email    varchar(255),
    customer_id      bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists org_message
(
    id bigint not null,
    created_by varchar(255),
    created_datetime datetime,
    is_enabled bit not null,
    request_id varchar(255) not null,
    updated_by varchar(255),
    updated_datetime datetime,
    company_id varchar(255) NOT NULL,
    version bigint not null,
    message_content longblob,
    customer_id bigint,
    primary key (id)
) engine=InnoDB;

create table if not exists org_sms_template
(
    id bigint not null,
    type varchar(255),
    category varchar(255),
    content longblob,
    pos_id bigint,
    customer_id bigint,
created_by varchar(255),
    created_datetime datetime,
    is_enabled bit not null,
    request_id varchar(255) not null,
    updated_by varchar(255),
    updated_datetime datetime,
    version bigint not null,
    primary key (id)
) engine=InnoDB;

alter table org_address
    add constraint FKnvolhuvbv9ixyyer0e96b7p7n foreign key (customer_id) references org_customer (id);
alter table org_phone
    add constraint FKlpffn5w3exxn5o282cihoe68x foreign key (customer_id) references org_customer (id);
alter table org_point_of_sale
    add constraint FK6puhwqkjktki4lh1o28j84ucw foreign key (company_id) references org_company (id);
alter table org_pos_customer
    add constraint FK4ksd5fey93g60a1w6hd2yom8u foreign key (customer_id) references org_customer (id);
alter table org_pos_customer
    add constraint FKs1t49xarvlxwfuy8hd7v4teb7 foreign key (pos_id) references org_point_of_sale (id);
alter table org_customer_settings
    add constraint FKaw17k34lmdtqdld68hsp22onj foreign key (customer_id) references org_customer (id);
alter table org_health
    add constraint FK75v255212c70av2at5ppqpxd0 foreign key (customer_id) references org_customer (id);
alter table org_more_details
    add constraint FK26n67o6cgsan0ulci3mrvi9 foreign key (customer_id) references org_customer (id);
alter table org_payment
    add constraint FKa2avv72tvv3gt4p7j985rmkcj foreign key (customer_id) references org_customer (id);
alter table org_service
    add constraint FK238fogxf998cmkfhvqki5gj99 foreign key (customer_id) references org_customer (id);
alter table org_social
    add constraint FK3oatn4u4fgh7ggg0fvw3o1rap foreign key (customer_id) references org_customer (id);
alter table org_work
    add constraint FK7scy3clq0ug41p172avukuoc1 foreign key (customer_id) references org_customer (id);
alter table org_pos_customer
    add constraint UKa1lwk26xnrisg4iuo2lhn29ll unique (pos_id, customer_id);
alter table org_pos_employee
    add constraint UKlc1l26efi8me0l40r4vwxksox unique (pos_id, employee_id);
alter table org_customer
    add constraint FKl78lv8lqf4g4k72ubdhiayduq foreign key (receptionist_id) references org_employee (id);
alter table org_pos_employee
    add constraint FKeb0cl2v3dppjcmsfaxeb4jlhf foreign key (employee_id) references org_employee (id);
alter table org_pos_employee
    add constraint FK3joxowxfr4asd8dww13haayy3 foreign key (pos_id) references org_point_of_sale (id);
alter table org_message
    add constraint FKccji95yxg7xgds7eu5boragpl foreign key (customer_id) references org_customer (id);
