DROP DATABASE IF EXISTS restorisedb1;
CREATE DATABASE IF NOT EXISTS restorisedb1;
SHOW DATABASES;
USE restorisedb1;

CREATE TABLE user (
                      email VARCHAR(50)NOT NULL UNIQUE,
                      username VARCHAR(30) PRIMARY KEY,
                      password VARCHAR(30) NOT NULL,
                      question VARCHAR(30) NOT NULL,
                      answer VARCHAR(30) NOT NULL
);
DROP TABLE user;


CREATE TABLE employeedetails (
                                 EMPLOYEE_ID INT PRIMARY KEY,
                                 NAME VARCHAR(255) NOT NULL,
                                 MOBILE_NO VARCHAR(20) NOT NULL,
                                 EMAIL VARCHAR(255) NOT NULL,
                                 DATE_OF_BIRTH DATE NOT NULL,
                                 JOB_POST VARCHAR(255) NOT NULL
);

DROP TABLE employeedetails;

CREATE TABLE attendancedetails (
                                   ATTENDANCE_ID INT AUTO_INCREMENT PRIMARY KEY,
                                   EMPLOYEE_ID INT NOT NULL,
                                   EMPLOYEE_NAME VARCHAR(255) NOT NULL,
                                   ATTENDANCE_DATE DATE NOT NULL,
                                   STATUS VARCHAR(50) NOT NULL,
                                   FOREIGN KEY (EMPLOYEE_ID) REFERENCES employeedetails(EMPLOYEE_ID)
);
DROP TABLE attendancedetails;

INSERT INTO user VALUES
                     (2001, 'user', '1234'),
                     (2002, 'user1', '2345');

DESC user;
SELECT *FROM user;

CREATE TABLE supplierdetails(
    SUPPLIER_ID VARCHAR(10) PRIMARY KEY,
    NAME VARCHAR(200) NOT NULL,
    MOBILE_NO INT(20) NOT NULL ,
    EMAIL VARCHAR(50) NOT NULL ,
    ITEM_ID VARCHAR(50) ,
    SUPPLY_ITEM VARCHAR(50) NOT NULL ,
    SUPPLY_DATE DATE NOT NULL,
    SUPPLY_ITEMSTOCKQTY INT (10)

);

Drop table supplierdetails;


CREATE TABLE tableofrestorise(
    table_id INT PRIMARY KEY,
    tables_chairs int NOT NULL
);

CREATE TABLE customer_details(
    customer_id VARCHAR (50) PRIMARY KEY ,
    customer_name VARCHAR(50) DEFAULT 'Login as Guest',
    customer_mobile INT (10)NOT NULL ,
    order_ID VARCHAR(10) NOT NULL ,
    order_date DATE NOT NULL
   );

drop table customer_details;

CREATE TABLE table_RestoRise(
    table_id VARCHAR (10) PRIMARY KEY,
    no_of_Chairs INT(10) NOT NULL
);

drop table table_restorise;

CREATE TABLE itemstock (
                           item_Type VARCHAR(30) NOT NULL,
                           itemID VARCHAR(35) PRIMARY KEY,
                           item_Name VARCHAR(50) NOT NULL,
                           unit_price DOUBLE NOT NULL,
                           itemStock INT NOT NULL
);

drop table itemstock;

create table customer(
    customer_id varchar(30) primary key ,
    customer_name varchar(30) default 'Guest',
    customer_mobileNo int (10) not null unique

);

drop table customer;

create table orders(
                       order_id varchar(35) primary key,
                       cus_id varchar(35) not null,
                        cus
                       date date not null,
                       constraint foreign key (cus_id) references customer(customer_id)
                           on delete cascade on update cascade
);

CREATE TABLE order_detail (
                              order_id VARCHAR(35) NOT NULL,
                              item_code VARCHAR(35) NOT NULL,
                              qty INT NOT NULL,
                              unit_price DOUBLE NOT NULL,
                              CONSTRAINT fk_order_detail_order_id
                                  FOREIGN KEY (order_id) REFERENCES orders(order_id)
                                      ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT fk_order_detail_item_code
                                  FOREIGN KEY (item_code) REFERENCES itemstock(itemID)
                                      ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE addtocart (
                           itemName VARCHAR(30) NOT NULL,
                           unitPrice DOUBLE NOT NULL,
                           qty INT NOT NULL,
                           total DOUBLE NOT NULL,
                           itemType VARCHAR(30)
);

drop table Addtocart;


drop table order_detail;

CREATE TABLE stockdetails AS
SELECT
    SUPPLIER_ID AS STOCK_SUPPLIER_ID ,
    NAME AS STOCK_SUPPLIER_NAME,
    ITEM_ID AS STOCK_ITEM_ID,
    SUPPLY_ITEM AS STOCK_SUPPLY_ITEM,
    SUPPLY_ITEMSTOCKQTY AS STOCK_SUPPLY_ITEMSTOCKQTY,
    SUPPLY_DATE AS STOCK_SUPPLY_DATE

FROM supplierdetails;
CREATE TABLE stockdetails (
                              STOCK_SUPPLIER_ID VARCHAR(255),
                              STOCK_SUPPLIER_NAME VARCHAR(255),
                              STOCK_ITEM_ID VARCHAR(255),
                              STOCK_SUPPLY_ITEM VARCHAR(255),
                              STOCK_SUPPLY_ITEMSTOCKQTY INT,
                              STOCK_SUPPLY_DATE VARCHAR(255)
);

CREATE TABLE addtocart (
                           itemName VARCHAR(30) NOT NULL,
                           unitPrice DOUBLE NOT NULL,
                           qty INT NOT NULL,
                           total DOUBLE NOT NULL,
                           itemType VARCHAR(30)
);


drop table stockdetails;

CREATE TABLE orders (
                        itemType VARCHAR(255),
                        itemName VARCHAR(255),
                        unitPrice DOUBLE,
                        qty INT,
                        total DOUBLE
);
CREATE TABLE orders (
                        itemName VARCHAR(30) NOT NULL,
                        unitPrice DOUBLE NOT NULL,
                        qty INT NOT NULL,
                        total DOUBLE NOT NULL,
                        itemType VARCHAR(30)
);

drop table orders;
