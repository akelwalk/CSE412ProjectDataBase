# CSE412ProjectDataBase

Database currently being edited by: **Arjun**.

How to use? 

1) Download the dump.sql File
2) Createa a new database named "cse412project" (Drop the previous database if you have it stored)
dropdb cse412project
createdb cse412project
3) THEN DO "psql cse412project < dump.sql". This will import everything from the dump.
4) After that is done you can connect to the database and make changes you want.
5) After you are done, do "pg_dump --no-owner cse412project > dump.sql" to get the dump.
6) Upload the dump to the GitHub.


Tutorial Video: https://www.youtube.com/watch?v=DA1Trq51JZs
