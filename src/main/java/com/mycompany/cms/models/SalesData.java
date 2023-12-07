/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cms.models;

import java.util.Date;

/**
 *
 * @author francisjamestolentino
 */
public class SalesData implements Comparable<SalesData>{
    
    private int sales;
    private Date date;
    private int tickets;
    
    public SalesData(int tickets, int sales, Date date) {
        this.date = date;
        this.tickets = tickets;
        this.sales = sales;
    }
    
    public void setSales(int sales){
        this.sales = sales;
    }
    
    public int getSales() {
        return this.sales;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setTickets(int tickets) {
        this.tickets = tickets;
    }
    
    public int getTickets() {
        return this.tickets;
    }
    
    @Override
    public int compareTo(SalesData o) {
        // usually toString should not be used,
        // instead one of the attributes or more in a comparator chain
        return this.date.compareTo(o.getDate());
    }
}
