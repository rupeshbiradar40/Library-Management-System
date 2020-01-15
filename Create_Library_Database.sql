CREATE TABLE `Library`.`Resources` (
`Barcode No` INT NOT NULL UNIQUE,
`Description` VARCHAR(250) NOT NULL,
`Type` VARCHAR(50) NOT NULL,
`Location` VARCHAR(50) ,
CONSTRAINT Primary_Resources primary key (`Barcode No`));

CREATE TABLE `Library`.`Author` (
`Author Id` INT NOT NULL UNIQUE AUTO_INCREMENT,
`Author Name` VARCHAR(20) NOT NULL,
CONSTRAINT Primary_Author primary Key (`Author Id`));

CREATE TABLE `Library`.`Publisher` (
`Publisher Id` INT NOT NULL UNIQUE AUTO_INCREMENT,
`Publisher Name` VARCHAR(20) NOT NULL,
constraint Primary_Publisher primary Key (`Publisher Id`));

CREATE TABLE `Library`.`Media Reserves` (
`Barcode No` INT NOT NULL UNIQUE,
`Media Id` INT NOT NULL UNIQUE,
`Language` VARCHAR (50) NOT NULL,
`Size` INT NOT NULL,
`No of Copies` INT NOT NULL,
CONSTRAINT Primary_Media_Reserves primary Key (`Media Id`),
CONSTRAINT Foreign_Media_Reserves foreign Key (`Barcode No`) References `Resources` (`Barcode No`));

CREATE TABLE `Library`.`Publications` (
`LCCN` INT NOT NULL UNIQUE,
`Barcode No` INT NOT NULL UNIQUE,
`Department` VARCHAR(50) NOT NULL,
`Type` VARCHAR (50) NOT NULL,
`Edition` VARCHAR(50) NOT NULL,
`Title` VARCHAR (100) NOT NULL,
`Subject` VArchar(50) NOT NULL,
`Pages` INT NOT NULL,
`Copies` INT NOT NULL,
`Publication Date` Date NOT NULL,
`Author Id` INT NOT NULL UNIQUE,
`Publisher Id` INT NOT NULL UNIQUE,
CONSTRAINT Primary_Publications primary Key (`LCCN`),
CONSTRAINT Foreign_Publications foreign Key (`Barcode No`) References `Resources` (`Barcode No`),
CONSTRAINT Foreign_Publications_Author foreign Key (`Author Id`) References `Author` (`Author Id`),
CONSTRAINT Foreign_Publications_Publisher foreign Key (`Publisher Id`) References `Publisher` (`Publisher Id`));

CREATE TABLE `Library`.`Journals` (
`LCCN` INT NOT NULL UNIQUE,
`ISSN NO` INT NOT NULL UNIQUE,
constraint Primary_Journals primary Key (`LCCN`),
constraint Foreign_Journals foreign Key (`LCCN`) References `Publications` (`LCCN`));

CREATE TABLE `Library`.`Periodicals` (
`LCCN` INT NOT NULL UNIQUE,
`BIPAD NO` INT NOT NULL UNIQUE,
constraint Primary_Magazins primary Key (`LCCN`),
constraint Foreign_Magazins foreign Key (`LCCN`) References `Publications` (`LCCN`));

CREATE TABLE `Library`.`Books` (
`LCCN` INT NOT NULL UNIQUE,
`DDSN` INT UNIQUE,
`ISBN` INT NOT NULL UNIQUE,
`Type` VARCHAR(25) NOT NULL,
constraint Primary_Books primary Key (`ISBN`),
constraint Foreign_Books foreign Key (`LCCN`) References `Publications` (`LCCN`));

CREATE TABLE `Library`.`Rare Manuscripts` (
`LCCN` INT NOT NULL UNIQUE,
`SERIAL NO` INT NOT NULL UNIQUE,
constraint Primary_RareManuscripts primary Key (`LCCN`),
constraint Foreign_RareManuscripts foreign Key (`LCCN`) References `Publications` (`LCCN`));

CREATE TABLE `Library`.`User` (
`UTD Id` VARCHAR(50) NOT NULL UNIQUE,
`NET Id` VARCHAR(50) NOT NULL UNIQUE,
`User type` VARCHAR(20) NOT NULL,
`First Name` Varchar(20) NOT NULL,
`MInit` Varchar(1) NOT NULL,
`Last Name` Varchar (20) NOT NULL,
`Department` VARCHAR(50) NOT NULL,
`Courses` VARCHAR(20) NOT NULL,
`Gender` VARCHAR(50) NOT NULL,
`Contact_Personal_No` VARCHAR(10) NOT NULL UNIQUE,
`Contact_Work_No` VARCHAR(10) NOT NULL,
`Personal Email ID` VARCHAR(20) NOT NULL,
`Work Email ID` VARCHAR(20) NOT NULL,
`Membership Status` BOOLEAN NOT NULL,
constraint Primary_User primary Key (`UTD Id`));

CREATE TABLE `Library`.`Borrows` (
`Barcode No` INT NOT NULL UNIQUE,
`UTD Id` VARCHAR(50) NOT NULL UNIQUE,
`Borrowed Date` DATE NOT NULL,
`Due Date` DATE NOT NULL,
`Return Date` DATE NOT NULL,
`Max Fine` INT NOT NULL,
`Recept Id` INT NOT NULL UNIQUE,
constraint Primary_borrows primary Key (`Recept Id`),
CONSTRAINT Foreign_BorrowsResources foreign Key (`Barcode No`) References `Resources` (`Barcode No`),
CONSTRAINT Foreign_BorrowsUser foreign Key (`UTD Id`) References `User` (`UTD Id`));

CREATE TABLE `Library`.`Address` (
`Address Number` INT NOT NULL UNIQUE AUTO_INCREMENT,
`UTD Id` VARCHAR(50) NOT NULL UNIQUE,
`Street Name` VARCHAR(20) NOT NULL,
`Country` VARCHAR(20) NOT NULL,
`ZIP` VARCHAR(10) NOT NULL,
`City` VARCHAR(20) NOT NULL,
constraint Primary_Address primary Key (`Address Number`, `UTD Id`),
constraint Foreign_Address foreign Key (`UTD Id`) References `User` (`UTD Id`) ON DELETE CASCADE);


ALTER TABLE `LIBRARY`.`Borrows` Modify `Borrowed Date` Date Check(`Borrowed Date`<= curdate());
ALTER TABLE `LIBRARY`.`Borrows` Modify `Return Date` Date Check(`Return Date`<= curdate());
ALTER TABLE `LIBRARY`.`Borrows` Modify `Due Date` Date Check(`Due Date`<= curdate());
ALTER TABLE `Library`.`Borrows` ADD CONSTRAINT Max_fine_value CHECK (`Max Fine`>=0);
ALTER TABLE `Library`.`Resources` Modify `Location` int NOT NULL;
ALTER TABLE `Library`.`Borrows` ADD CONSTRAINT DueDateValidation CHECK (`Borrowed Date`<=`Due Date`);
ALTER TABLE `Library`.`Borrows` ADD CONSTRAINT BorrowedDateValidation CHECK (`Borrowed Date`<=`Return Date`);
ALTER TABLE `LIBRARY`.`Publications` Modify `Publication Date` Date Check(`Publication Date`<= curdate());


ALTER TABLE `LIBRARY`.`Author`  ADD UNIQUE `IndexForAuthor`(`Author Name`);
ALTER TABLE `LIBRARY`.`Publisher`  ADD UNIQUE `IndexForPublisher`(`Publisher Name`);
ALTER TABLE `LIBRARY`.`Publications`  ADD UNIQUE `IndexForPublications`(`Title`);
