import psycopg2

def connect_to_db(database_name):
    """Connect to PostgreSQL database."""
    try:
        conn = psycopg2.connect(dbname=database_name)
        return conn
    except Exception as e:
        print(f"Error: {e}")
        return None
    
def show_all_tables(conn):
    """Display names of all tables in the database."""
    try:
        cursor = conn.cursor()
        
        sql = """
        SELECT tablename
        FROM pg_tables
        WHERE schemaname = 'public';  
        """
        
        cursor.execute(sql)
        
        tables = cursor.fetchall()
        for table in tables:
            print(table[0])
            
        cursor.close()
    except Exception as e:
        print(f"Error: {e}")
        
def delete_table(conn, table_name):
    """Delete a table from the database given its name."""
    try:
        cursor = conn.cursor()

        sql = f"DROP TABLE IF EXISTS {table_name} CASCADE;"
        
        cursor.execute(sql)
        conn.commit()
        cursor.close()
        
        print(f"Table '{table_name}' deleted successfully.")
    except Exception as e:
        print(f"Error: {e}")
        
def create_table(conn, table_name, sql_statement):
    """Create a table if it doesn't exist."""
    try:
        cursor = conn.cursor()
        
        # Check if the table already exists to avoid compile errors 
        cursor.execute(f"SELECT to_regclass('{table_name}');")
        table_exists = cursor.fetchone()[0]
        
        if table_exists:
            print(f"Table {table_name} already exists in the database.. skipping creation.")
        else:
            cursor.execute(sql_statement)
            conn.commit()
            print(f"Table {table_name} created successfully.")
            
        cursor.close()
    except Exception as e:
        print(f"Error: {e}")
        conn.rollback()  # Rollback the transaction in case of errors


def insert_into_table(conn, table_name, data):
    # need to define at some point 
    return 0 

def read_from_table(conn, table_name):
    """Read and print data from specified table."""
    try:
        cursor = conn.cursor()
        cursor.execute(f"SELECT * FROM {table_name}")
        
        rows = cursor.fetchall()
        for row in rows:
            print(row)
            
        cursor.close()
    except Exception as e:
        print(f"Error: {e}")

# Circular contraint between Unit and Customer causes an issue when trying to add a foreign key UserId into 
# unit, this method adds that to the table after the creation so tables are all inserted as initially designed. 
def add_foreign_key(conn, table_name, column_name, referenced_table, referenced_column, on_delete_action):
    """Add a foreign key constraint to a table"""
    try:
        cursor = conn.cursor()
        
        # Define the constraint name
        constraint_name = f"fk_{table_name}_{referenced_table}"
        
        # Check if the foreign key constraint already exists
        cursor.execute(f"SELECT 1 FROM pg_constraint WHERE conname = '{constraint_name}';")
        constraint_exists = cursor.fetchone()
        
        if constraint_exists:
            print(f"Foreign key constraint '{constraint_name}' already exists in '{table_name}' table.")
        else:
            sql = f"""
            ALTER TABLE {table_name}
            ADD CONSTRAINT {constraint_name}
            FOREIGN KEY ({column_name}) REFERENCES {referenced_table}({referenced_column}) {on_delete_action};
            """
            
            cursor.execute(sql)
            conn.commit()
            print(f"Foreign key constraint '{constraint_name}' added to {table_name}.")
        
        cursor.close()
    except Exception as e:
        print(f"Error: {e}")

        
if __name__ == "__main__":
    
    user = """
    CREATE TABLE Users(
        UserID INT PRIMARY KEY, 
        Role VARCHAR(255),
        firstName VARCHAR(255),
        lastName VARCHAR(255),
        Email VARCHAR(255),
        Password VARCHAR(255),
        phoneNumber VARCHAR(255)
    );
    """

    property = """
    CREATE TABLE Property(
        amenities TEXT[],
        propertyID INT PRIMARY KEY,
        address VARCHAR(255),
        rentDueDate DATE,
        Name VARCHAR(255),
        communityAnnouncements TEXT[]	
    );
    """

    unit = """
    CREATE TABLE Unit(
        UnitId INT PRIMARY KEY,
        isFurnished boolean,
        rentAmount FLOAT,
        floorPlan VARCHAR(255),
        Condition VARCHAR(255),
        isRented boolean,
        appliances TEXT[],
        PropertyID INT,
        rentPaid boolean,
        rentDue DATE,
        UserID INT,
        FOREIGN KEY (PropertyID) REFERENCES Property(propertyID) ON DELETE CASCADE
    );
    """

    property_manager = """
    CREATE TABLE PropertyManager (
        UserID INT PRIMARY KEY,
        isOwner boolean,
        propertyID INT,
        FOREIGN KEY(propertyID) REFERENCES Property(propertyID) ON DELETE SET NULL 
    );
    """

    customer = """
    CREATE TABLE Customer(
        UserID INT PRIMARY KEY,
        PaymentType VARCHAR(255),
        LeaseStart DATE,
        LeaseEnd DATE,
        IsApproved boolean,
        UnitID INT,
        FOREIGN KEY(UnitID) REFERENCES Unit(UnitId) ON DELETE SET NULL
    );
    """

    maintenance_req = """
    CREATE TABLE MaintenanceRequest(
        isDealtWith boolean,
        requestID INT PRIMARY KEY,
        timeStamp DATE,
        UnitID INT,
        userID INT,
        FOREIGN KEY (UnitID) REFERENCES Unit(UnitId) ON DELETE CASCADE,
        FOREIGN KEY (userID) REFERENCES Customer(UserID) ON DELETE SET NULL
    );
    """

    
    conn = connect_to_db("cse412project")
    
    if conn:
        print("connection successful")
        # create_table(conn, "Users", user)
        # create_table(conn, "Property", property)
        # create_table(conn, "Unit", unit) 
        # create_table(conn, "Customer", customer) 
        # create_table(conn, "PropertyManager", property_manager)
        # create_table(conn, "MaintenanceRequest", maintenance_req)

        # add FK UserID to Unit 
        # add_foreign_key(conn, "Unit", "UserID", "Customer", "UserID", "ON DELETE SET NULL")
        
        print("Tables in the database:")
        show_all_tables(conn)
        
        # print("Deleting Created Tables")
        
        # uncomment to delete a table
        # delete_table(conn, "Users")
        # delete_table(conn, "Property")
        # delete_table(conn, "Unit")
        # delete_table(conn, "Customer")
        # delete_table(conn, "PropertyManager")
        # delete_table(conn, "MaintenanceRequest")
        
        # view current tables in the database 
        # print("Tables in the database:")
        # show_all_tables(conn)
        
        conn.close()
