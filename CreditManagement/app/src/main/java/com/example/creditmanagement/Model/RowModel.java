package com.example.creditmanagement.Model;

public class RowModel {
    String name,phone;
    int id;
    public RowModel(String name,String phone,int id){
        this.name=name;
        this.phone=phone;
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }


}
