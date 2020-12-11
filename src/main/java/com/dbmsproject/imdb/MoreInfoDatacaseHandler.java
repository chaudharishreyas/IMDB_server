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
public class MoreInfoDatacaseHandler {
    public MongoCollection<Document> MORE;
    public MongoClient mongoClient;
    public void setupAll() {
        mongoClient = MongoClients.create("mongodb+srv://shreyas:pranay@movies.eicjc.mongodb.net/dbname");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("IMDB");
        MORE = mongoDatabase.getCollection("MoreInfo");
    }
    @GetMapping(value = "/MORE", params = {"plot","language","country","awards","poster","production"})
    public String MORE(
            @Param("plot")String plot,
            @Param("language")String language,
            @Param("country")String country,
            @Param("award")String award,
            @Param("poster")String poster,
            @Param("production")String production){
        this.setupAll();
        Document newDocument=new Document("plot",plot)
                                .append("language",language)
                                .append("country",country)
                                .append("award",award)
                                .append("poster",poster)
                                .append("production",production);
        String id = MORE.insertOne(newDocument).getInsertedId().toString();
        closeAllconnections();
        return id;
    }
    public void closeAllconnections(){mongoClient.close();}
}
