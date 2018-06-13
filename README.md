# MongoDB with the Scala Driver

This is a simple implementation of a CRUD app using the official [Scala Driver](https://github.com/mongodb/mongo-scala-driver) 
for Mongodb. In particular, this example was created for the [MongoDB ATLAS](https://www.mongodb.com/cloud/atlas) database as a service.  

## Initial Configuration

You will need to create a `.env` file with the configuration variables in order to run the code. 
There is an example in the base directory of this repo called `.env.example`.

To create your own file run `cp .env.example .env` and edit the `.env` with your custom variables (see your ATLAS account).

## Usage

There are 3 main apps in this example: `Populate`, `Querying`, `Delete`. The `Populate` app creates the collection 
with 100 random documents (person objects). The `Querying` app performs simple queries in the collection. Finally, 
the `Delete` app deletes all the documents in the collection. 

1. Create your `.env` file: `cp .env.example .env`
2. Export the environment variables: `source .env`
3. Run the app with `sbt run` and select one of the following:
    * `Populate`: Creates the database, collection and initial objects (100).
    * `Querying`: Queries the existing database.
    * `Delete`  : Deletes all objects (documents) in the collection. 
    