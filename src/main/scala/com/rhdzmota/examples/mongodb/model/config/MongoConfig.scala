package com.rhdzmota.examples.mongodb.model.config

case class MongoConfig(user: String, userPassword: String, clusterName: String, database: String) {
  def getUri: String = {
    val cluster0 = clusterName + "-shard-00-00-r8anq.mongodb.net:27017"
    val cluster1 = clusterName + "-shard-00-01-r8anq.mongodb.net:27017"
    val cluster2 = clusterName + "-shard-00-02-r8anq.mongodb.net:27017"
    s"mongodb://$user:$userPassword@$cluster0,$cluster1,$cluster2/admin?ssl=true&replicaSet=$clusterName-shard-0&authSource=admin"
  }
}

object MongoConfig {
  object Labels {
    val mongoUser = "mongoUser"
    val mongoUserPassword = "mongoUserPassword"
    val mongoClusterName  = "mongoClusterName"
    val mongoDatabaseName = "mongoDatabaseName"
  }
  def fromEnv: Option[MongoConfig] = for {
    user <- sys.env.get(Labels.mongoUser)
    userPassword <- sys.env.get(Labels.mongoUserPassword)
    clusterName  <- sys.env.get(Labels.mongoClusterName)
    database     <- sys.env.get(Labels.mongoDatabaseName)
  } yield MongoConfig(user, userPassword, clusterName, database)
}