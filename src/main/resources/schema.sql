CREATE TABLE IF NOT EXISTS contact_msg (
   contact_id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   mobile_num VARCHAR(10) NOT NULL,
   mail_id VARCHAR(20) NOT NULL,
   subject VARCHAR(50) NOT NULL,
   message VARCHAR(100) NOT NULL,
   status VARCHAR(10) NOT NULL,
   created_at TIMESTAMP NOT NULL,
   created_by VARCHAR(50) NOT NULL,
   update_at TIMESTAMP DEFAULT NULL,
   update_by VARCHAR(50) DEFAULT NULL
);


create TABLE IF NOT EXISTS holidays(
    holidayDate varchar(20) NOT NULL,
    holidayName varchar(100) PRIMARY KEY,
    type varchar(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by varchar(50) NOT NULL,
    update_at TIMESTAMP DEFAULT NULL,
    update_by varchar(50) DEFAULT NULL
);

create TABLE IF NOT EXISTS roles(
    role_id int not null auto_increment,
    role_name varchar(100) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by varchar(50) NOT NULL,
    update_at TIMESTAMP DEFAULT NULL,
    update_by varchar(50) DEFAULT NULL,
    PRIMARY KEY (role_id)
);

create TABLE IF NOT EXISTS address(
    address_id int not null auto_increment,
    address1 varchar(100) NOT NULL,
    address2 varchar(100) defaul NULL,
    city varchar(100) NOT NULL,
    state varchar(100) NOT NULL,
    zip_code int NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by varchar(50) NOT NULL,
    update_at TIMESTAMP DEFAULT NULL,
    update_by varchar(50) DEFAULT NULL,
    PRIMARY KEY (address_id)
);