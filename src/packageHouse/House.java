/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageHouse;

import packageClient.Client;
import packageOwner.Owner;

/**
 *
 * @author Rentex
 */
//hOUSE CLASS
public class House {
    private String adress;
    private String city;
    private Owner owner;
    private Client client;
    private int contratDuration;
    private int rentPrice;
    private int HouseCode;
    private int lenght;
    private int width;
    private int duration;

    
     public House(){
    }
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
   

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getContratDuration() {
        return contratDuration;
    }

    public void setContratDuration(int contratDuration) {
        this.contratDuration = contratDuration;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getHouseCode() {
        return HouseCode;
    }

    public void setHouseCode(int HouseCode) {
        if(HouseCode ==0){
           this.HouseCode = GenerateHouseCode();
        }else if(HouseCode > 0){
        this.HouseCode = HouseCode;
        }
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public int DetermineArea(){
        int area=0;
        area = getLenght() * getWidth();
        return area;
    }
    
    public int GenerateHouseCode(){
        int randomHouseCode=0;
        
       randomHouseCode = (int) (Math.random()* (9999999 -1000000))+ 1000000;
       
       return randomHouseCode;
    }
    
}
