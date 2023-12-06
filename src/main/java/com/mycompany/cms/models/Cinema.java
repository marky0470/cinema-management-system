/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cms.models;

/**
 *
 * @author marks
 */
public class Cinema {
    final private int id;
    final private String name;
    final private String type;
    
    public Cinema(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getType() {
        return this.type;
    }
}

//public class Ticket {
//    
//    final private Time time;
//    final private Date date;
//    final private int screeningId;
//    final private String seat;
//    final private float price;
//    
//    public Ticket(Time time, Date date, int screeningId, String seat, float price) {
//        this.time = time;
//        this.date = date;
//        this.screeningId = screeningId;
//        this.seat = seat;
//        this.price = price;
//    }
//    
//    public Time getTime() {
//        return this.time;
//    }
//    
//    public Date getDate() {
//        return this.date;
//    }
//    
//    public int getScreeningId() {
//        return this.screeningId;
//    }
//    
//    public String getSeat() {
//        return this.seat;
//    }
//    
//    public float getPrice() {
//        return this.price;
//    }
//}
