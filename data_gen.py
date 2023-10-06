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

if __name__ == "__main__":
    
    # SQL Translations 
    user = """
                    CREATE TABLE Users (
                        UserID SERIAL PRIMARY KEY,
                        Role VARCHAR(255),
                        FirstName VARCHAR(255),
                        LastName VARCHAR(255),
                        Email VARCHAR(255) UNIQUE,
                        "Phone#" VARCHAR(50)
                    );
                    """
    conn = connect_to_db("cse412project")
    
    if conn:
        print("connection successful")
        create_table(conn, "Users", user)
        print("Tables in the database:")
        show_all_tables(conn)
        # print("Deleting Created Tables")
        # delete_table(conn, "Users")
        # print("Tables in the database:")
        # show_all_tables(conn)
        
        conn.close()
