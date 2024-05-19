Deploy database only need two steps.

1. Move to the backend program folder:

cd `your_path`/GenesianTheatreCompanyWeb

2. Replace `your_username` with the database's username, and `your_database` with the database's name:

psql -U `your_username` -d `your_database` -f capstone.sql
