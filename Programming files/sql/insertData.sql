use KadZebrasDeg;
insert into lLogin (username, pass, accessType) values ('admin','admin', 1);
insert into ttable (noOfSeats, tableNo, isAvailable, tExists, tableOnTheNorth,tableOnTheEast,tableOnTheSouth,tableOnTheWest)
values (4, 1, 1, 1, 0, 2, 4, 0);
insert into ttable (noOfSeats, tableNo, isAvailable, tExists, tableOnTheNorth,tableOnTheEast,tableOnTheSouth,tableOnTheWest)
values (4, 2, 1, 1, 0, 3, 0, 1);
insert into ttable (noOfSeats, tableNo, isAvailable, tExists, tableOnTheNorth,tableOnTheEast,tableOnTheSouth,tableOnTheWest)
values (4, 3, 1, 1, 0, 0, 0, 2);
insert into ttable (noOfSeats, tableNo, isAvailable, tExists, tableOnTheNorth,tableOnTheEast,tableOnTheSouth,tableOnTheWest)
values (4, 4, 1, 1, 1, 0, 0, 1);



select * from lLogin;