import psycopg2
from faker import Faker
import random
from datetime import datetime, timedelta

fake = Faker()

# Constants
NUM_USERS = 100
NUM_PROPERTIES = 20
NUM_UNITS = 50
NUM_MAINTENANCE_REQS = 30

# Establish connection
conn = psycopg2.connect(
    dbname='cse412project'
)
cursor = conn.cursor()

user_ids = []
for _ in range(NUM_USERS):
    user_id = fake.unique.random_int()
    role = random.choice(['Customer', 'Customer', 'Customer', 'PropertyManager'])
    first_name = fake.first_name()
    last_name = fake.last_name()
    email = fake.email()
    password = fake.password()
    phone_number = fake.phone_number()
    
    user_ids.append(user_id)
    
    cursor.execute(
        "INSERT INTO Users VALUES (%s, %s, %s, %s, %s, %s, %s)",
        (user_id, role, first_name, last_name, email, password, phone_number)
    )

customer_ids = []

for user_id in user_ids:
    cursor.execute("SELECT Role FROM Users WHERE UserID = %s", (user_id,))
    role = cursor.fetchone()[0]
    if role == 'PropertyManager':
        ...
    elif role == 'Customer':
        ...
        customer_ids.append(user_id)  # Add user_id to the customer_ids list


# Generate Propert Data 
property_ids = []
for _ in range(NUM_PROPERTIES):
    # we can add or remove possible ammentities that will be randomly generated 
    possible_amenities = [
        "pool", 
        "park", 
        "tennis courts", 
        "dog park", 
        "gym", 
        "spa", 
        "playground", 
        "BBQ area", 
        "clubhouse", 
        "secured parking"
    ]
    amenities = random.sample(possible_amenities, random.randint(1, len(possible_amenities)))
    property_id = fake.unique.random_int()
    address = fake.address().replace('\n', ', ')
    rent_due_date = fake.date_between(start_date='-1y', end_date='+1y')
    name = fake.company()
    announcements = [fake.sentence() for _ in range(random.randint(1, 3))]

    property_ids.append(property_id)

    cursor.execute(
        "INSERT INTO Property (amenities, propertyID, address, rentDueDate, Name, communityAnnouncements) VALUES (%s, %s, %s, %s, %s, %s)",
        (amenities, property_id, address, rent_due_date, name, announcements)
    )

approved_users = []
approved_count = 0 
for user_id in user_ids:
    cursor.execute("SELECT Role FROM Users WHERE UserID = %s", (user_id,))
    role = cursor.fetchone()[0]
    if role == 'PropertyManager':
        is_owner = fake.boolean()
        property_id = random.choice(property_ids)  
        cursor.execute(
            "INSERT INTO PropertyManager VALUES (%s, %s, %s)",
            (user_id, is_owner, property_id)
        )
    elif role == 'Customer':
        payment_type = random.choice(['Credit', 'Debit', 'Cash'])
        is_approved = fake.boolean() 
        if (is_approved == True):
            lease_start = fake.date_between(start_date='-1y', end_date='+1y')
            lease_end = lease_start + timedelta(days=random.randint(180, 365))
            approved_users.append(user_id)
            approved_count += 1
            appr = True 
        else: 
            lease_start = None 
            lease_end = None
            appr = False
        cursor.execute(
            "INSERT INTO Customer(UserID, PaymentType, LeaseStart, LeaseEnd, IsApproved) VALUES (%s, %s, %s, %s, %s)",
            (user_id, payment_type, lease_start, lease_end, appr)
        )


user_unit_map = {}
unit_ids = []
unit_users = [] 

# assing units that are approved 
for _ in range(approved_count):
    unit_id = fake.unique.random_int()
    is_furnished = fake.boolean()
    rent_amount = fake.random_int(min=500, max=3000)
    floor_plans = ["Studio", "1 Bedroom", "2 Bedroom", "3 Bedroom", "Loft", "Penthouse"]
    floor_plan = random.choice(floor_plans)
    condition = random.choice(['New', 'Good', 'Used', 'Old'])
    is_rented = True # setting true since only approved users are being assigned here 
    appliance_list = ["Refrigerator", "Oven", "Microwave", "Washing Machine", "Dishwasher", "Dryer", "Toaster", "Blender", "Coffee Maker"]
    appliances = random.sample(appliance_list, random.randint(1, 4))
    property_id = random.choice(property_ids) # assigns a valid property ID to the unit from the number of properties generated 
    rent_paid = fake.boolean()
    rent_due = fake.date_between(start_date='-1m', end_date='+1m')
    user_id = random.choice(approved_users) # assigns a valid User ID to the unit from the number of Users approved for a lease 
    unit_users.append(user_id)
    user_unit_map[user_id] = unit_id
    approved_users.remove(user_id)
    unit_ids.append(unit_id)
    cursor.execute(
        "INSERT INTO Unit VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)",
        (unit_id, is_furnished, rent_amount, floor_plan, condition, is_rented,
         appliances, property_id, rent_paid, rent_due, user_id)
    )
    NUM_UNITS -= 1 # updating to loop through the rest of the units that are unused 
    
# fill in rest of the data for unused units 
for _ in range(NUM_UNITS):
    while True:
        unit_id = fake.unique.random_int() 
        if unit_id not in unit_ids:
            break
    is_furnished = fake.boolean()
    rent_amount = fake.random_int(min=500, max=3000)
    floor_plans = ["Studio", "1 Bedroom", "2 Bedroom", "3 Bedroom", "Loft", "Penthouse"]
    floor_plan = random.choice(floor_plans)
    condition = random.choice(['New', 'Good', 'Used', 'Old'])
    is_rented = False 
    appliance_list = ["Refrigerator", "Oven", "Microwave", "Washing Machine", "Dishwasher", "Dryer", "Toaster", "Blender", "Coffee Maker"]
    appliances = random.sample(appliance_list, random.randint(1, 4))
    property_id = random.choice(property_ids) # assigns a valid property ID to the unit from the number of properties generated 
    rent_paid = fake.boolean()
    rent_due = fake.date_between(start_date='-1m', end_date='+1m')
    user_id = None # no assigned userid
    unit_ids.append(unit_id)
    cursor.execute(
        "INSERT INTO Unit VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)",
        (unit_id, is_furnished, rent_amount, floor_plan, condition, is_rented,
         appliances, property_id, rent_paid, rent_due, user_id)
    ) 
    
for user in unit_users:
    unit_id = user_unit_map[user]
    cursor.execute(
        "UPDATE Customer SET unitid = %s WHERE UserID = %s",
        (unit_id, user)
    )


# Generate data for MaintenanceRequest from specified number of Requests to generate 
# fetch the user_id for the unit number from the database for the unit_id that was 
# randomly generated to ensure the database is mapped from unit to user 
cursor.execute("SELECT UnitId, UserID FROM Unit")
unit_user_mapping = {unit_id: user_id for unit_id, user_id in cursor.fetchall()}

for _ in range(NUM_MAINTENANCE_REQS):
    is_dealt_with = fake.boolean()
    request_id = fake.unique.random_int()
    timestamp = fake.date_this_decade()
    unit_id = random.choice(unit_ids)
    
    user_id = unit_user_mapping[unit_id]

    cursor.execute(
        "INSERT INTO MaintenanceRequest VALUES (%s, %s, %s, %s, %s)",
        (is_dealt_with, request_id, timestamp, unit_id, user_id)
    )


# Commit all the changes
conn.commit()
cursor.close()
conn.close()
