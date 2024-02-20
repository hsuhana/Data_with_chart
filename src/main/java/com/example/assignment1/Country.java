package com.example.assignment1;

public class Country {

    private int table_rank;
    private String table_country;
    private int table_visitors;

    public Country(int table_rank, String table_country, int table_visitors){
        this.table_rank = table_rank;
        this.table_country = table_country;
        this.table_visitors = table_visitors;
    }

    public Country(String table_country, int table_visitors){
        this.table_country = table_country;
        this.table_visitors = table_visitors;
    }

    public int getTable_rank() {
        return table_rank;
    }

    public void setTable_rank(int table_rank) {
        this.table_rank = table_rank;
    }

    public String getTable_country() {
        return table_country;
    }

    public void setTable_country(String table_country) {
        this.table_country = table_country;
    }

    public int getTable_visitors() {
        return table_visitors;
    }

    public void setTable_visitors(int table_visitors) {
        this.table_visitors = table_visitors;
    }
}
