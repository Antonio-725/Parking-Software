/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author antonio
 */
public class ShowSlots {
    public String id;
    public int position;
    public String status;
    public String vehicle;
    public ShowSlots(String id,int pozi,String sta,String veh){
        this.id=id;
        this.position=pozi;
        this.status=sta;
        this.vehicle=veh;
    }
    public String getID(){
        return this.id;
    }
    public int getPozi(){
        return this.position;
    }
    public String getStatus(){
        return this.status;
    }
    public String getVehicle(){
        return this.vehicle;
    }
    
}
