package org.polytech.covid.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Or another appropriate strategy
    private int id;
    private String name;
    private String city;
    private String address;

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getCity(){
        return this.city;
    }

    public void setCity(String city){
        this.city=city;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address=address;
    }

}
