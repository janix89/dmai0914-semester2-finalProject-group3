create Database KadZebrasDeg
go
use KadZebrasDeg;


/* think of adding staffId to this table */
create Table lLogin(
id int identity(1,1) primary key(id),
username varchar(20) not null,
pass varchar(20) not null,
accessType int not null
);
/* Table to keep track of openning and closing times */
create Table tTime(
id int identity(1,1) primary key(id),
time varchar(30) not null
);
/* Table to same all the employees */
/* sExists insead of exists because it's a keyword */ 
create Table Staff(
id int identity(1,1) primary key(id),
name varchar(20) not null,
bankAccount varchar(20),
address varchar(20),
profession varchar(20),
phoneNo varchar(20),
cprNo varchar(11) not null,
sExists bit not null
);

create Table Merchandise(
mId int identity (1,1) primary key,
name varchar(20) not null,
price float not null,
mExists bit not null,
mType int not null
);

create Table SaleOrder(
id int identity(1,1) primary key(id),
totalPrice float  not null,
isPaid bit  not null,
isActive bit  not null,
wId int 
);

create Table tTable(
id int identity(1,1) primary key(id),
noOfSeats int  not null,
tableNo int  not null,
isAvailable bit  not null,
tExists bit  not null,
tableOnTheNorth int,
tableOnTheEast int,
tableOnTheSouth int,
tableOnTheWest int
);

create Table Reservation(
id int identity(1,1) primary key(id),
customerName varchar(30),
phoneNo varchar(20),
reservationDate varchar(20)  not null,
numberOfGuests int  not null,
registrationDate varchar(20)  not null,
reservationTime varchar(20)  not null,
oId int not null
);

create Table ReservedTable(
rId int foreign key (rId)  references Reservation(id) on delete no action on update no action  not null,
tId int foreign key (tId)  references tTable(id) on delete cascade on update cascade  not null
);
/*
create Table Merchandise(
cmdId int primary key(cmdId)  not null,
mType int  not null
);
*/
create Table OrderLine(
id int identity(1,1) primary key(id),
quantity int  not null,
oId int not null,
meId int not null,
/*mType int not null,*/
isDone bit not null
);

create Table Course(
id int  primary key (id) references Merchandise(mId) on delete cascade on update cascade not null,
/*
name varchar(20)  not null,
price float  not null,
*/
typeOfCourse varchar(10),
ingredients varchar(100)  not null,
isVegetarian bit  not null,
/*
mExists bit not null
*/
);

create Table Miscellaneous(
id int  primary key (id) references Merchandise(mId) on delete cascade on update cascade not null,
/*
name varchar(20)  not null,
price float  not null,
*/
quantityInStock int  not null,
minQuantityInStock int  not null
);

create Table Drink(
id int  primary key (id) references Merchandise(mId) on delete cascade on update cascade not null,
/*
name varchar(20)  not null,
price float  not null,
*/
quantityInStock int  not null,
alcoholConcentration float  not null,
minQuantityInStock int  not null
);

