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
public class MovieDatabaseHandler {
    public MongoCollection<Document> MOVIES;
    public MongoClient mongoClient;
    public void setupAll(){
        mongoClient = MongoClients.create("mongodb+srv://shreyas:pranay@movies.eicjc.mongodb.net/dbname");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("IMDB");
        MOVIES = mongoDatabase.getCollection("movies");
    }
    @GetMapping(value = "/UploadMovie",params = {"title","year","rated","released","runtime","genre"})
    public String uploadmovie(
            @Param("title")String title,
            @Param("year")String year,
            @Param("rated")String rated,
            @Param("released")String released,
            @Param("runtime")String runtime,
            @Param("genre")String genre
    ){
        this.setupAll();
        Document newDocument = new Document("title",title)
                                    .append("year",year)
                                    .append("rated",rated)
                                    .append("released",released)
                                    .append("genre",genre);
        String id = MOVIES.insertOne(newDocument).getInsertedId().toString();
        closeAllCollection();
        return id;
    }
    public void closeAllCollection(){mongoClient.close();}
}
