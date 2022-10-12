# api-cloud-parking
 
## Run database
docker run --name parking-db -p 5432:5432 -e POSTRES_08=parking -e POSTGRES_USERNAME=   POSTGRES_PASSWORD=1234 -d postgres:10-alpine -->

## Stop database
docker stop parking-db

## Start database
docker start parking-db
