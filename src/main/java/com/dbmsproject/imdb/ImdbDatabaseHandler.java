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
public class ImdbDatabaseHandler {
    /*
        * create movies
        * create actor
        * create supporting
        * update movies
        * updating supporting
        * update actor
        * get movies
        * get actors
        * get supporting
        * movies likes = name of the movie
        * movies dislike  = name of the movie
    */

    @GetMapping(value = "/")
    public String connectionToMongo() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://laukik:laukik20@movies.eicjc.mongodb.net/<dbname>?retryWrites=true&w=majority");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("IMDB");
        MongoCollection<Document> movies = mongoDatabase.getCollection("movies");
        return movies.find(new Document("hello", "world")).first().toString();
    }
}
