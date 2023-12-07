/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cms.models;

/**
 *
 * @author marks
 */
public class Movie {
    
    private final int id;
    private final String title;
    private final String rating;
    private final int released;
    private final String genre;
    private final int duration;
//    private final byte[] image;

    // Constructor
    public Movie(int id, String title, String rating, int released, String genre, int duration) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.released = released;
        this.genre = genre;
        this.duration = duration;
//        this.image = image;
    }

    // Getter methods
    public Movie getMovieById() {
        return this;
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public int getReleased() {
        return released;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

//    public byte[] getImage() {
//        return image;
//    }
}