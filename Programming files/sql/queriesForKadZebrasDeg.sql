create Database KadZebrasDeg
go
use KadZebrasDeg;


/* think of adding staffId to this table */
create Table lLogin(
id int identity(1,1) primary key(id),
username varchar(20),
pass varchar(20),
accessType int
);
/* Table to keep track of openning and closing times */
create Table tTime(
id int identity(1,1) primary key(id),
hours varchar(20),
minutes varchar(20)
);
/* Table to same all the employees */
/* sExists insead of exists because it's a keyword */ 
create Table Staff(
id int identity(1,1) primary key(id),
name varchar(20),
bankAccount varchar(20),
address varchar(20),
profession varchar(20),
phoneNo varchar(20),
cprNo varchar(11),
sExists bit
);
/* Table to keep track of the waiters */
create Table Waiter(
id int identity(1,1) primary key(id),
workTable int,
ssId int foreign key(ssId) references Staff(id) on delete cascade on update cascade
);

create Table SaleOrder(
id int identity(1,1) primary key(id),
totalPrice float,
isPaid bit,
isActive bit,
wId int foreign key (wId) references Waiter(id) on delete cascade on update cascade
);

create Table tTable(
id int identity(1,1) primary key(id),
noOfSeats int,
tableNo int,
isAvailable bit,
tExists bit,
tableOnTheNorth int,
tableOnTheEast int,
tableOnTheSouth int,
tableOnTheWest int,
wId int foreign key (wId) references Waiter(id) on delete cascade on update cascade
);

create Table Reservation(
id int identity(1,1) primary key(id),
customerName varchar(30),
phoneNo varchar(20),
reservationDate varchar(20),
numberOfGuests int,
registrationDate varchar(20),
reservationTime varchar(20),
oId int foreign key (oId) references SaleOrder(id) on delete cascade on update cascade
);

create Table ReservedTable(
rId int foreign key (rId)  references Reservation(id) on delete no action on update no action,
tId int foreign key (tId)  references tTable(id) on delete cascade on update cascade
);

create Table Merchandise(
id int identity(1,1) primary key(id),
name varchar(20),
price float,
mExists bit
);

create Table OrderLine(
id int identity(1,1) primary key(id),
quantity int,
oId int foreign key (oId) references SaleOrder(id) on delete cascade on update cascade,
meId int foreign key (meID) references Merchandise(id) on delete cascade on update cascade
);

create Table Course(
id int identity(1,1) primary key (id),
ingredients varchar(100),
isVegetarian bit,
meId int foreign key (meID) references Merchandise(id) on delete cascade on update cascade
);

create Table Miscellaneous(
id int identity(1,1) primary key (id),
quantityInStock int,
minQuantityInStock int,
meId int foreign key (meID) references Merchandise(id) on delete cascade on update cascade
);

create Table Drink(
id int identity(1,1) primary key (id),
quantityInStock int,
alcoholConcentration float,
minQuantityInStock int,
meId int foreign key (meID) references Merchandise(id) on delete cascade on update cascade
);
