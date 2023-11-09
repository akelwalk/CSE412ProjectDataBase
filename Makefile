build: 
	python3 data_gen.py 0 1

dump: 
	pg_dump --no-owner cse412project > dump.sql

# if running make import for the first time you will get an error for Role postgres not existing
# to solve this run the following command prior to running the make import 
# - psql -U postgres -c "CREATE ROLE postgres WITH LOGIN SUPERUSER;"
import:
	@echo "Creating 'postgres' role if it doesn't exist..."
	@psql -U postgres -tc "SELECT 1 FROM pg_roles WHERE rolname='postgres'" | grep -q 1 || psql -U postgres -c "CREATE ROLE postgres WITH LOGIN SUPERUSER;"
	@echo "Dropping 'cse412project' database if it exists..."
	@echo "Dropping 'cse412project' database if it exists..."
	@dropdb --if-exists -U postgres cse412project
	@echo "Creating 'cse412project' database..."
	@createdb -U postgres cse412project
	@echo "Importing 'dump.sql' into 'cse412project' database..."
	@psql -U postgres cse412project < dump.sql
	@echo "Import completed."

clean: 
	python3 data_gen.py 1 0 

data_gen: 
	python3 utility.py

demo_one: 
	psql -d cse412project -a -f demo_one.sql > demo_one_out.txt