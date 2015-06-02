use KadZebrasDeg;
/*creating login admin */
insert into lLogin (username, pass, accessType) values ('admin','admin', 1);

/*creating tables */
insert into ttable (noOfSeats, tableNo, isAvailable, tExists, tableOnTheNorth,tableOnTheEast,tableOnTheSouth,tableOnTheWest)
values (4, 1, 1, 1, 0, 2, 4, 0);
insert into ttable (noOfSeats, tableNo, isAvailable, tExists, tableOnTheNorth,tableOnTheEast,tableOnTheSouth,tableOnTheWest)
values (4, 2, 1, 1, 0, 3, 0, 1);
insert into ttable (noOfSeats, tableNo, isAvailable, tExists, tableOnTheNorth,tableOnTheEast,tableOnTheSouth,tableOnTheWest)
values (4, 3, 1, 1, 0, 0, 0, 2);
insert into ttable (noOfSeats, tableNo, isAvailable, tExists, tableOnTheNorth,tableOnTheEast,tableOnTheSouth,tableOnTheWest)
values (4, 4, 1, 1, 1, 0, 0, 1);

/*adding staff*/
insert into staff(name, bankAccount, address, profession, phoneNo, cprNo, sExists)
Values ('Janis', 'DK123', 'Janis home', 'Waiter', '1234678', '1212901111' , 1);
insert into staff(name, bankAccount, address, profession, phoneNo, cprNo, sExists)
Values ('Nikita', 'DK321', 'Nikita', 'Waiter', 88888888, '0706902525' , 1);

/*adding merchandise*/
insert into Merchandise(name, price, mExists, mType)
values('Soup', 10, 1, 1);
insert into Course(id, typeOfCourse, ingredients, isVegetarian)
values(1, '1st', 'Ingrediends dor soup', 0);
insert into Merchandise(name, price, mExists, mType)
values('Coca cola', 10, 1, 2);
insert into Drink(id, quantityInStock, alcoholConcentration, minQuantityInStock)
values(1, 10, 0, 5);
insert into Merchandise(name, price, mExists, mType)
values('Something', 20, 1, 3);
insert into Miscellaneous(id, quantityInStock, minQuantityInStock)
values(1, 10, 5);