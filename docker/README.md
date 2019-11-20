# Please refer to readme file inside employee-portal application for manually running application

## For postgres container and pg client container (Required for prod profile) (Not required for dev and test profile)
docker run --name employeedb -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=employeedb -d postgres<br>
docker run --name dbclient -p 80:80 -e PGADMIN_DEFAULT_EMAIL=jagan@gmail.com -e PGADMIN_DEFAULT_PASSWORD=a12345678 -d dpage/pgadmin4
