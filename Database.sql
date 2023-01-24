create database BookStoreDB_CTOL35;

use BookStoreDB_Ctol35;

create table book ( bookId int primary key auto_increment, bookName varchar(100) ,description varchar(500), author varchar(100), publisher varchar(200), category varchar(100), isbn varchar(20), price double, quantityOfAvailableBooks int default 0, bookRating double(2,2) default 0.0, bookImage varchar(500));

desc book;

create table customer (customerId int auto_increment,
firstName varchar(50), lastName varchar(50),address text, address2 text,
contact varchar(20),emailId varchar(100), password varchar(50), 
constraint pk_customer primary key (customerId,emailId));

create table cart (cartId int auto_increment, bookId int, quantity int, emailId varchar(100), constraint pk_cart primary key (cartId), constraint  fk_book_cart Foreign key (bookId) references book(bookId));

alter table cart add customerId int;

alter table cart add constraint fk_customer_Cart foreign key (customerId, emailId) references Customer(customerId,emailId);

create table Ordertbl(orderId int auto_increment,emailid varchar(100),customerid int,
 OrderDate varchar(50), status varchar(30), ShippingAddress text, totalCost double,
constraint Pk_Order Primary key(orderId));

alter table Ordertbl add constraint fk_customer_Ordertbl foreign key (customerId, emailId) references Customer(customerId,emailId);

create table orderDetails(cartId int, bookId int, quantity int, orderId int, Constraint fk_order_orderDetails Foreign key (orderId) references Ordertbl(orderId) );

 alter table Orderdetails add constraint fk_book_order foreign key (bookId) references Book(bookId);
 
