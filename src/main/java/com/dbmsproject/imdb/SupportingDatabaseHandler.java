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
public class SupportingDatabaseHandler {
    public MongoCollection<Document> SUPPORT;
    public MongoClient mongoClient;
    public void setupAll() {
        mongoClient = MongoClients.create("mongodb+srv://shreyas:pranay@movies.eicjc.mongodb.net/dbname");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("IMDB");
        SUPPORT = mongoDatabase.getCollection("cast");
    }

    @GetMapping(value = "/Support",params = {"writer","director"})
    public String Supporting(
            @Param("writer")String writer,
            @Param("Director")String director
    ){
        this.setupAll();
        Document newDocument = new Document("writer",writer)
                                    .append("Director",director);
        String id = SUPPORT.insertOne(newDocument).getInsertedId().toString();
        closeAllCollections();
        return id;
    }
    public void closeAllCollections(){mongoClient.close();}
}
