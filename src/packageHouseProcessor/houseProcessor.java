/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageHouseProcessor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import packageClient.Client;
import packageHouse.House;
import packageOwner.Owner;

/**
 *
 * @author Rentex
 */
public class houseProcessor {
    
      String USER = "root";
      String PASS_WORD = "*********";
      String DB_URL = "jdbc:mysql://localhost:3306/housingsys";
         
    public houseProcessor(){
    }
    
    // this method help to create house and store it in the database
    public void populateHouseInTheDataBase(List<House> newHouse, int houseCode,String adress, String city,
                                           int length, int width, String ownerName, 
                                           String ownerEmail, String clientName,
                                           String clientEmail ,int duration, int rentPrice){
    
  
      try{
    // connection
    Connection conn = DriverManager.getConnection(DB_URL,USER, PASS_WORD);
    
    PreparedStatement insertValue = conn.prepareStatement("insert into houses values(?,?,?,?,?,?,?,?,?,?,?)");
    Statement stmnt = conn.createStatement();
      
    // creating the house by setting all field   
   
    House house = new House();
    Owner owner = new Owner();
    Client client = new Client();
    owner.setName(ownerName);
    owner.setEmail(ownerEmail);
    
    house.setHouseCode(0);
    house.setAdress(adress);
    house.setCity(city);
    house.setLenght(length);
    house.setWidth(width);
    house.setOwner(owner);
    house.setClient(client);
    house.setDuration(duration);
    house.setRentPrice(rentPrice);
    
    // here we store the house in a list so we can store it a the database
    newHouse.add(house);
    
    // inserting values to the correct column that will constitute a record
    insertValue.setInt(1,newHouse.get(0).getHouseCode());
    insertValue.setString(2,newHouse.get(0).getAdress());
    insertValue.setString(3,newHouse.get(0).getCity());
    insertValue.setInt(4,newHouse.get(0).getLenght());
    insertValue.setInt(5, newHouse.get(0).getWidth());
    insertValue.setString(6, newHouse.get(0).getOwner().getName());
    insertValue.setString(7, newHouse.get(0).getOwner().getEmail());
    insertValue.setString(8, newHouse.get(0).getClient().getName());
    insertValue.setString(9, newHouse.get(0).getClient().getEmail());
    insertValue.setInt(10, newHouse.get(0).getDuration());
    insertValue.setInt(11, newHouse.get(0).getRentPrice()); 
    
    // closing all sql objects
    insertValue.executeUpdate();
    stmnt.close();
    conn.close();
    insertValue.close();
      
      }catch(Exception ex){
          System.out.print(ex.getMessage());
      }
        
        
    }
    
  
    
    public void getHousesFromDataBase(List<House>houses){
        
        int houseCode;
        String adress;
        String city;
        int length;
        int width;
        String ownerName;
        String ownerEmail;
        String clientName;
        String clientEmail;
        int duration,rentPrice;
        
    try{
            Connection conn = DriverManager.getConnection(DB_URL,USER, PASS_WORD);
          
            Statement stmnt = conn.createStatement();
    String sql = "select house_code, adress, city,length,width,owner_name,"
            + "owner_email, client_name, client_email,duration, rent_price  from houses";        
            
    ResultSet rs = stmnt.executeQuery(sql);
    
    
    while(rs.next() != false){
       houseCode = rs.getInt("house_code");
       adress = rs.getString("adress");
       city = rs.getString("city");
       length = rs.getInt("length");
       width = rs.getInt("width");
       ownerName = rs.getString("owner_name");
       ownerEmail = rs.getString("owner_email");
       clientName = rs.getString("client_name");
       clientEmail = rs.getString("client_email");
       duration = rs.getInt("duration");
       rentPrice = rs.getInt("rent_price");
       
       House house = new House();
      Owner owner = new Owner();
      Client client = new Client();
       
       owner.setName(ownerName);
       owner.setName(ownerEmail);
       client.setName(clientName);
       client.setName(clientEmail);
       
       house.setHouseCode(houseCode);
       house.setAdress(adress);
       house.setCity(city);
       house.setLenght(length);
       house.setWidth(width);
       house.setOwner(owner);
       house.setClient(client);
       house.setDuration(duration);
       house.setRentPrice(rentPrice);
       
       houses.add(house);
    }
    
     
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        
        
    }
    
    public int GetHouseIndex(int specificHouseCode, List<House>houses){
        int isFound=-1;
        int x =0;
        while(x< houses.size() && specificHouseCode != houses.get(x).getHouseCode()){
            x++;
        }
        if(x == houses.size()){
            isFound = -1;
        }else{
            isFound = x;
        }
        
        return isFound;
    }
    
    public void StorRentHouseInDataBase( int isFound,List<House>houses,String clientName,
                                           String clientEmail ,int duration){
   
    Client client = new Client();
    
   try{
    
    // connection
    Connection conn = DriverManager.getConnection(DB_URL,USER, PASS_WORD);
    
    PreparedStatement insert = conn.prepareStatement("insert into rented_houses values(?,?,?,?,?,?,?,?,?,?,?)");
    client.setName(clientName);
    client.setEmail(clientEmail);
    houses.get(isFound).setClient(client);
    houses.get(isFound).setDuration(duration);
    
    
    
    insert.setInt(1,houses.get(isFound).getHouseCode());
    insert.setString(2,houses.get(isFound).getAdress());
    insert.setString(3,houses.get(isFound).getCity());
    insert.setInt(4,houses.get(isFound).getLenght());
    insert.setInt(5,houses.get(isFound).getWidth());
    insert.setString(6,houses.get(isFound).getOwner().getName());
    insert.setString(7, houses.get(isFound).getOwner().getEmail());
    insert.setString(8,houses.get(isFound).getClient().getName());
    insert.setString(9, houses.get(isFound).getClient().getEmail());
    insert.setInt(10, houses.get(isFound).getDuration());
    insert.setInt(11, houses.get(isFound).getRentPrice()); 
 
    
    insert.executeUpdate();
    
    conn.close();
    insert.close();
    
   }catch(Exception ex){ 
       System.out.print(ex.getMessage());
   }
    }
    
     public void getRentedHousesFromDataBase( List<House>rentedHouses){
        
        int houseCode;
        String adress;
        String city;
        int length;
        int width;
        String ownerName;
        String ownerEmail;
        String clientName;
        String clientEmail;
        int duration,rentPrice;
        
    try{
            Connection conn = DriverManager.getConnection(DB_URL,USER, PASS_WORD);
            //PreparedStatement dailyValue = conn.prepareStatement("insert into summary values(?,?,?,?,?,?)");
            Statement stmnt = conn.createStatement();
    String sql = "select house_code, adress, city,length,width,owner_name,"
            + "owner_email, client_name, client_email,duration, rent_price  from rented_houses";        
            
    ResultSet rs = stmnt.executeQuery(sql);
    
    
    while(rs.next() != false){
       houseCode = rs.getInt("house_code");
       adress = rs.getString("adress");
       city = rs.getString("city");
       length = rs.getInt("length");
       width = rs.getInt("width");
       ownerName = rs.getString("owner_name");
       ownerEmail = rs.getString("owner_email");
       clientName = rs.getString("client_name");
       clientEmail = rs.getString("client_email");
       duration = rs.getInt("duration");
       rentPrice = rs.getInt("rent_price");
       
       House house = new House();
      Owner owner = new Owner();
      Client client = new Client();
       
       owner.setName(ownerName);
       owner.setName(ownerEmail);
       client.setName(clientName);
       client.setName(clientEmail);
       
       house.setHouseCode(houseCode);
       house.setAdress(adress);
       house.setCity(city);
       house.setLenght(length);
       house.setWidth(width);
       house.setOwner(owner);
       house.setClient(client);
       house.setDuration(duration);
       house.setRentPrice(rentPrice);
       
       rentedHouses.add(house);
       
       
    }
    conn.close();
    stmnt.close();
    rs.close();
     
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        
        
    }
     
     public void StoreEditedHouseInDataBase( int houseIndex,List<House>houses,int newPrice){
   
    Client client = new Client();
    
   try{
    
    // connection
    Connection conn = DriverManager.getConnection(DB_URL,USER, PASS_WORD);
    
    PreparedStatement insert = conn.prepareStatement("insert into houses values(?,?,?,?,?,?,?,?,?,?,?)");
   // Statement statmnt = conn.createStatement();
    int houseCod = houses.get(houseIndex).getHouseCode();
    PreparedStatement delete = conn.prepareStatement("delete from houses where house_code ='"+ houseCod+"'");
      delete.executeUpdate();
     delete.close();
    houses.get(houseIndex).setRentPrice(newPrice);
    
    
    insert.setInt(1,houses.get(houseIndex).getHouseCode());
    insert.setString(2,houses.get(houseIndex).getAdress());
    insert.setString(3,houses.get(houseIndex).getCity());
    insert.setInt(4,houses.get(houseIndex).getLenght());
    insert.setInt(5,houses.get(houseIndex).getWidth());
    insert.setString(6,houses.get(houseIndex).getOwner().getName());
    insert.setString(7, houses.get(houseIndex).getOwner().getEmail());
    insert.setString(8,houses.get(houseIndex).getClient().getName());
    insert.setString(9, houses.get(houseIndex).getClient().getEmail());
    insert.setInt(10, houses.get(houseIndex).getDuration());
    insert.setInt(11, houses.get(houseIndex).getRentPrice()); 
 
    
    insert.executeUpdate();
    
    conn.close();
    insert.close();
    //statmnt.clearBatch();
   
   }catch(Exception ex){ 
       System.out.print(ex.getMessage());
   }
    }
    
     public void SearchHousesFromDataBase(String city,List<House>cityHouses){
        
        int houseCode;
        String adress;
        
        int length;
        int width;
        String ownerName;
        String ownerEmail;
        String clientName;
        String clientEmail;
        int duration,rentPrice;
        
    try{
        
            Connection conn = DriverManager.getConnection(DB_URL,USER, PASS_WORD);
            //PreparedStatement dailyValue = conn.prepareStatement("insert into summary values(?,?,?,?,?,?)");
            Statement stmnt = conn.createStatement();
    String sql = "select house_code, adress, city,length,width,owner_name,"
            + "owner_email, client_name, client_email,duration, rent_price  from houses where city ='"+city+"'";        
            
    ResultSet rs = stmnt.executeQuery(sql);
    
    
    while(rs.next() != false){
       houseCode = rs.getInt("house_code");
       adress = rs.getString("adress");
       city = rs.getString("city");
       length = rs.getInt("length");
       width = rs.getInt("width");
       ownerName = rs.getString("owner_name");
       ownerEmail = rs.getString("owner_email");
       clientName = rs.getString("client_name");
       clientEmail = rs.getString("client_email");
       duration = rs.getInt("duration");
       rentPrice = rs.getInt("rent_price");
       
       House house = new House();
      Owner owner = new Owner();
      Client client = new Client();
       
       owner.setName(ownerName);
       owner.setName(ownerEmail);
       client.setName(clientName);
       client.setName(clientEmail);
       
       house.setHouseCode(houseCode);
       house.setAdress(adress);
       house.setCity(city);
       house.setLenght(length);
       house.setWidth(width);
       house.setOwner(owner);
       house.setClient(client);
       house.setDuration(duration);
       house.setRentPrice(rentPrice);
       
       cityHouses.add(house);
    }
    
     
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        
        
    }
    
    
}
