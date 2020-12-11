package com.dbmsproject.imdb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorDatabaseHandler {
    public MongoCollection<Document> ACTORS;
    public  MongoClient mongoClient;
    public void setupAll() {
        mongoClient = MongoClients.create("mongodb+srv://shreyas:pranay@movies.eicjc.mongodb.net/dbname");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("IMDB");
        ACTORS = mongoDatabase.getCollection("actors");
    }

    @GetMapping(value = "/CreateActor", params = {"name", "dob","gender","age","img"})
    public String createActor(
            @Param("name") String name,
            @Param("dob") String dob,
            @Param("gender")String gender,
            @Param("age")String age,
            @Param("img")String img) {
            this.setupAll();
            System.out.println(name + " " + dob);
            Document newDocument = new Document("name", name)
                                        .append("dob", dob)
                                        .append("gender",gender)
                                        .append("age",age)
                                        .append("img",img);
            String id = ACTORS.insertOne(newDocument).getInsertedId().toString();
            closeAllconnections();
            return id;
    }

    public void closeAllconnections() {
        mongoClient.close();
    }

}
