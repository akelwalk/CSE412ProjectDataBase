build: 
	python3 data_gen.py

dump: 
	pg_dump --no-owner cse412project > dump.sql