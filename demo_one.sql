--------------------------------------------------------------------
-- INSERT statement 1 
--------------------------------------------------------------------
INSERT INTO Users VALUES ( (SELECT MAX(coalesce(UserID, -1)) FROM USERS) + 1, 
                            'Customer', 
                            'Arjun',
                            'Dadhwal', 
                            'adadhwal@asu.edu', 
                            'arjun', 
                            '123456789');

--------------------------------------------------------------------
-- Result 
--------------------------------------------------------------------
SELECT * FROM Users WHERE userID = 9966; 

--------------------------------------------------------------------
-- INSERT statement 2  
--------------------------------------------------------------------
INSERT INTO Customer VALUES( (SELECT UserID FROM USERS WHERE USERS.EMAIL = 'adadhwal@asu.edu'), 
                              NULL, 
                              NULL, 
                              NULL, 
                              NULL, 
                              NULL);

--------------------------------------------------------------------
-- Result 
--------------------------------------------------------------------
SELECT * FROM Customer WHERE userID = 9966; 

--------------------------------------------------------------------
-- UPDATE statement 1
--------------------------------------------------------------------
-- select 
SELECT * FROM MaintenanceRequest 
WHERE requestID = 9819; 

-- update 
UPDATE MaintenanceRequest SET isDealtWith = true 
WHERE MaintenanceRequest.requestID =9819 
AND MaintenanceRequest.userID=8910 
AND MaintenanceRequest.unitID=9166 
AND MaintenanceRequest.propertyID=6054;

--------------------------------------------------------------------
-- Result 
--------------------------------------------------------------------
SELECT * FROM MaintenanceRequest 
WHERE requestID = 9819; 

--------------------------------------------------------------------
-- UPDATE statement 2
--------------------------------------------------------------------
-- select 
SELECT * FROM Unit 
WHERE userID = 984; 

-- update 
Update Unit SET isRented = false, rentPaid = false, rentDue = null, UserId = null 
WHERE Unit.UserID = 984;

--------------------------------------------------------------------
-- Result 
--------------------------------------------------------------------
SELECT * FROM Unit 
WHERE unitID = 1798; 

--------------------------------------------------------------------
-- DELETE statement 1 
--------------------------------------------------------------------
-- select 
SELECT userID FROM Customer
WHERE userID = 984; 

-- delete
DELETE FROM Customer 
WHERE UserID = 984;

--------------------------------------------------------------------
-- Result
--------------------------------------------------------------------
SELECT userID FROM Customer
WHERE userID = 984; 

--------------------------------------------------------------------
-- DELETE statement 2 
--------------------------------------------------------------------
-- select 
SELECT userID FROM Users
WHERE userID = 984; 

-- delete 
DELETE FROM Users 
WHERE UserID = 984;


--------------------------------------------------------------------
-- Result 
--------------------------------------------------------------------
SELECT userID FROM Users
WHERE userID = 984; 

--------------------------------------------------------------------
-- SELECT statement 1 
--------------------------------------------------------------------
SELECT * FROM USERS, CUSTOMER 
WHERE USERS.ROLE = 'Customer' 
AND USERS.USERID = CUSTOMER.USERID
LIMIT 10;

--------------------------------------------------------------------
-- SELECT statement 2 
--------------------------------------------------------------------
Select Distinct(Property.name) 
FROM Property, (Select * From Unit Where Unit.isRented = false) as AvailUnits 
Where AvailUnits.PropertyID = Property.PropertyID
LIMIT 10;