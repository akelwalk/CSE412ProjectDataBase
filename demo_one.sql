--------------------------------------------------------------------
-- INSERT statements 
--------------------------------------------------------------------
INSERT INTO Users VALUES ( (SELECT MAX(coalesce(UserID, -1)) FROM USERS) + 1, 
                            'Customer', 
                            'Arjun',
                            'Dadhwal', 
                            'adadhwal@asu.edu', 
                            'arjun', 
                            '123456789');


INSERT INTO Customer VALUES( (SELECT UserID FROM USERS WHERE USERS.EMAIL = 'adadhwal@asu.edu'), 
                              NULL, 
                              NULL, 
                              NULL, 
                              NULL, 
                              NULL);


--------------------------------------------------------------------
-- UPDATE statements 
--------------------------------------------------------------------
UPDATE MaintenanceRequest SET isDealtWith = true 
WHERE MaintenanceRequest.requestID =9819 
AND MaintenanceRequest.userID=8910 
AND MaintenanceRequest.unitID=9166 
AND MaintenanceRequest.propertyID=6054;

Update Unit SET isRented = false, rentPaid = false, rentDue = null, UserId = null 
WHERE Unit.UserID = 984;

--------------------------------------------------------------------
-- DELETE statements 
--------------------------------------------------------------------
DELETE FROM Customer 
WHERE UserID = 984;

DELETE FROM Users 
WHERE UserID = 984;

--------------------------------------------------------------------
-- SELECT statements 
--------------------------------------------------------------------
SELECT * FROM USERS, CUSTOMER 
WHERE USERS.ROLE = 'Customer' 
AND USERS.USERID = CUSTOMER.USERID
LIMIT 10;

Select Distinct(Property.name) 
FROM Property, (Select * From Unit Where Unit.isRented = false) as AvailUnits 
Where AvailUnits.PropertyID = Property.PropertyID
LIMIT 10;