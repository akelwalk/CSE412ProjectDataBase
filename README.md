# CSE412ProjectDataBase

Project currently being edited by: **Arjun**.

## How to use? 

1) Download the dump.sql File
2) Createa a new database named "cse412project" (Drop the previous database if you have it stored)
dropdb cse412project
createdb cse412project
3) THEN DO "psql cse412project < dump.sql". This will import everything from the dump.
4) After that is done you can connect to the database and make changes you want.
5) After you are done, do "pg_dump --no-owner cse412project > dump.sql" to get the dump.
6) Upload the dump to the GitHub.


Tutorial Video: https://www.youtube.com/watch?v=DA1Trq51JZs

## Using Automation Tools 

### Pythons Scripts 

* `data_gen.py` <br>
This script generates the tables for our database with the associated columns. No data is added to the tables using this script so disregard the name. Automation with make clean and make build uses this script to delete or add the tables in the database. 

* `utility.py` <br>
This script generates the data into the tables of our database. This script is automated with the make data_gen command. 

### MakeFile Commands 

* `make import` <br>
Imports the current database dump file currently in the repo to your local instance. 

* `make dump` <br>
Dumps that changes that you have made to the database into the dump.sql file. 

* `make build` <br>
This command builds the database tables with the data_gen.py file. 

* `make clean` <br>
This command deletes the tables in the database essentially emptying the database. 

* `make data_gen` <br>
This command inserts the data into the database usin the utility.py script. 
