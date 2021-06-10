package com.jobreadyprogrammer.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Application {
	
	public static void main(String args[]) throws InterruptedException {
		
		// Every spark application you write, the very first step is to connect to the spark master node
		SparkSession spark = new SparkSession.Builder()
				.appName("CSB to DB")
				.master("local")	// In our case the master node is in local
				.getOrCreate();
		
		// Get the data 
		// Typically in a production we load the file from a distributed environment, dataframe is a distributed data structure 
		Dataset<Row> df = spark.read().format("csv")
		.option("header", true)
		.load("src/main/resources/name_and_comments.txt");
		
		df.show();
		
//		// Create a session
//		SparkSession spark = new SparkSession.Builder()
//				.appName("CSV to DB")
//				.master("local")
//				.getOrCreate();
//
//		// get data
//		Dataset<Row> df = spark.read().format("csv")
//			.option("header", true)
//			.load("src/main/resources/name_and_comments.txt");
//		
////		df.show(3);
//		
//		// Transformation
//		df = df.withColumn("full_name", 
//				concat(df.col("last_name"), lit(", "), df.col("first_name")))
//				.filter(df.col("comment").rlike("\\d+"))
//				.orderBy(df.col("last_name").asc());
//		
//		// Write to destination
//		String dbConnectionUrl = "jdbc:postgresql://localhost/course_data"; // <<- You need to create this database
//		Properties prop = new Properties();
//	    prop.setProperty("driver", "org.postgresql.Driver");
//	    prop.setProperty("user", "postgres");
//	    prop.setProperty("password", "password"); // <- The password you used while installing Postgres
//	    
//	    df.write()
//	    	.mode(SaveMode.Overwrite)
//	    	.jdbc(dbConnectionUrl, "project1", prop);
	}
}