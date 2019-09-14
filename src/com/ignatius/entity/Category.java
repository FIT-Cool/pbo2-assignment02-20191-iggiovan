package com.ignatius.entity;

public class Category {
    private String nameCat;
    public String getNameCat() {
        return nameCat;
    }
    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }
    @Override
    public String toString(){
        return this.nameCat;
    }
}
