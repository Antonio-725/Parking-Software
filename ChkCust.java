/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author antonio
 */
public class ChkCust {
    public String name;
    public int contacts;
    public int status;
    public  int credit;
     public  String vehicle;
    
    public ChkCust(String id,int conta,int sta,int cre,String veh){
        this.name=id;
        this.contacts=conta;
        this.status=sta;
        this.credit=cre;
        this.vehicle=veh;
    }
    public String getID(){
        return this.name;
    }
    public int getConta(){
        return this.contacts;
    }
    public int getStatus(){
        return this.status;
    }
    public int getCredit(){
        return this.credit;
    }
    public String getVehicle(){
        return this.vehicle;
    }
    
}

