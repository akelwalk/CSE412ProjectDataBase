build: 
	python3 data_gen.py 0 1

dump: 
	pg_dump --no-owner cse412project > dump.sql

import:
	dropdb cse412project
	createdb cse412project 
	psql cse412project < dump.sql

clean: 
	python3 data_gen.py 1 0 

data_gen: 
	python3 utility.py

demo_one: 
	psql -d cse412project -a -f demo_one.sql > demo_one_out.txt