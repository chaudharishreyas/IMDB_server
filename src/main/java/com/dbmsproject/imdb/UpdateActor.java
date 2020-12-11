package com.dbmsproject.imdb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.currentDate;


@RestController
public class UpdateActor {
    public MongoCollection<Document> ACTORS;
    public MongoClient mongoClient;
    public void setupAll() {
        mongoClient = MongoClients.create("mongodb+srv://shreyas:pranay@movies.eicjc.mongodb.net/dbname");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("IMDB");
        ACTORS = mongoDatabase.getCollection("actors");
    }

    @GetMapping(value = "/UpdateActor", params = {"name", "dob","gender","age","img"})
    public void createActor(
            @Param("name")String name,
            @Param("dob")String dob
    ){
        this.setupAll();
        MongoDatabase mongoDB = mongoClient.getDatabase("IMDB");
        MongoCollection<Document> collection = mongoDB.getCollection("actors");
        collection.updateOne (eq("name","shreyas"),
                    combine(Updates.set("dob","12312321"),currentDate("last modified")));
        //String id = ACTORS.updateOne().getInsertedId().toString();
        closeAllconnections();
       // return id;
    }
    public void closeAllconnections() {
        mongoClient.close();
    }
}
