build: 
	python3 data_gen.py

dump: 
	pg_dump --no-owner cse412project > dump.sql

import:
	dropdb cse412project
	createdb cse412project 
	psql cse412project < dump.sql