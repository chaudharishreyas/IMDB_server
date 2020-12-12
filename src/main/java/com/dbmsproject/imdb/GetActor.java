package com.dbmsproject.imdb;

import com.mongodb.Block;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.util.function.Consumer;


@RestController
public class GetActor {
    public MongoCollection<Document> ACTORS;
    public MongoClient mongoClient;
    public void setupAll() {
        mongoClient = MongoClients.create("mongodb+srv://shreyas:pranay@movies.eicjc.mongodb.net/dbname");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("IMDB");
        ACTORS = mongoDatabase.getCollection("actors");
}
 @GetMapping(value = "/GetActor")
public String getActor(){
        this.setupAll();
        MongoDatabase mongoDB = mongoClient.getDatabase("IMDB");
        MongoCollection<Document> collection = mongoDB.getCollection("actors");
     FindIterable<Document> findIterable = collection.find();
     Consumer<Document> printConsumer = new Consumer<Document>() {
         public void accept(final Document doc) {
             System.out.println(doc.toJson());
         }
     };
     findIterable.forEach(printConsumer);
        closeAllconnetions();
        return "done";
 }
    public void closeAllconnetions(){
        mongoClient.close();
    }
}