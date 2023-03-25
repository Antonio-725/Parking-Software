
import com.mysql.cj.xdevapi.Statement;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;

//import java.sql.Statement;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
//import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author antonio santos
 */
public class DataBase {
    public static Connection setConnection() {
        Connection con=null;
        String url=("jdbc:mysql://localhost:3306/ParkingDatabase");
        try {
            con = (Connection)DriverManager.getConnection(url);
            System.out.println("Connection Established Successfully");
            Class.forName("com.mysql.cj.jdbc.Driver");
            //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ParkingDatabase","root","");
        }catch (SQLException sql){
            System.out.print(sql);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
        return con;
    }
   
    
     public int getID(String TableName)
       {
           Connection con = setConnection();
            int k = 0;
           try
           {
           java.sql.Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("SELECT * FROM "+TableName+"");
           while(rs.next())
           {
               k++;
           }
           rs.close();
           st.close();
           }
           catch (Exception e)
           {
               System.out.println("Error Occured");
           }
           return k+1;
       }
       public static int getRevenue(String TableName, String Date,String Type)
    {
        Connection con = setConnection();
        int count = 0;
        try{
        java.sql.Statement st = con.createStatement();
        ResultSet rs ;
        if (Date.equals(""))
        rs = st.executeQuery("SELECT * FROM VehicleDetails");
        else
            rs = st.executeQuery("(SELECT * FROM VehicleDetails Where Date = '"+Date+"' and Type='"+Type+"') ");
        while(rs.next())
        {
            count++;
        }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return count;
    }
       
       
    public void insertVehicle(String TableName, String id,String Reg , String Pos, String type ){
        DateFormat td=new SimpleDateFormat("HH:mm:ss a");
        Date time= new Date();
        String tm=(td.format(time)); 
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
        Date dat=new Date();
       
      
        java.sql.Date sqldate=new java.sql.Date(dat.getTime());
        java.sql.Time sqltime=new java.sql.Time(time.getTime());
        
        
        try{
            
            String ID= id;
            String reg=Reg;
            String Pozi=Pos;
            String typ=type;

            Connection con=setConnection();
           //PreparedStatement st=(PreparedStatement) con.createStatement();
           
          PreparedStatement st = con.prepareStatement("insert into VehicleDetails(id,RegNo,Position,Type,Date,TimeIn) values(?,?,?,?,?,?)");
          
           st.setString(1, id);
           st.setString(2, Reg);
           st.setString(3, Pos);
           st.setString(4,type);
           st.setDate(5, sqldate);
          st.setTime(6, sqltime);
       //   PreparedStatement st2 = con.prepareStatement("insert into TimeOut(id,Date,TimeIn) values(?,?,?,?,?,?)");
           

           //PreparesStatement st1=con.prepareStatement("update"+TableName+"position set RegNo='"Reg"' where position)
        
         //  st.executeUpdate("INSERT into VehicleDetails (id,RegNo,Position,Type,TimeIn)VALUES("+id+",'"+Reg+"','"+Pos+"','"+tm+"')");
           st.executeUpdate("UPDATE ParkSlots  set Status = 'Taken' ,Type ='"+type+"' Where Position = '"+Pos+"'");
           
          // st.executeUpdate("UPDATE "+TableName+"Position set ID = "+id+" Where Position = '"+Pos+"'");
          st.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Error Occurred");
            
            
        }
        
        
        
        
        
    }
     public String[] GetData(String TableName, String pos)
       {
           int id =0;
           String Reg = "";
           String position="";
           Connection con = setConnection();
           try{
             
               java.sql.Statement st1 = con.createStatement();
              
                
              ResultSet rs = st1.executeQuery("SELECT * FROM VehicleDetails WHERE Position ='"+pos+"' ");
             // ResultSet rs = st1.executeQuery("SELECT * FROM "+TableName+" WHERE Position = '"+pos+"'");
               System.out.println("Select Query worked");
               if(rs.next())
               {
                   
                  id = rs.getInt("id");
                  Reg = rs.getString("RegNo");
                  position=rs.getString("Position");
                  System.out.println("Id = "+id);
                  System.out.println("reg #"+Reg);
                  System.out.println("position#"+position);
                 
                 //  System.out.println("Data fetched");
               }
               rs.close();
               st1.close();
               con.close();
           }
           catch(Exception e)
           {
               System.out.println("Error Occured:"+e);
           }
         String[]Data = new String[ 1000];
         Data[0]= String.valueOf(id);
          Data[1] = Reg;
          Data[2]=position;
return Data;
       }
      public void UpdateTimeOut(String TableName, int id)
       {
           Connection con = setConnection();
         DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
        
	Date date = new Date();
        Date tme= new Date();
        String tiime=(dateFormat.format(tme)); 
        java.sql.Time sqltime=new java.sql.Time(tme.getTime());
	System.out.println(dateFormat.format(date)); 
        String Time = dateFormat.format(date);
           try{
           java.sql.Statement st = con.createStatement();
           st.executeUpdate("UPDATE VehicleDetails set TimeOut = '"+sqltime+"' WHERE ID = "+id+"");
           
           System.out.println("Select Query Run Successfully");
           st.close();
           con.close();
           }
           catch(Exception e)
           {
               System.out.println("Error:"+e);
           }
           
       }
       public void UpdateCarPosition(String TableName , String pos)
       {
           try
           {
               Connection con = setConnection();
               java.sql.Statement st = con.createStatement();
            
                st.executeUpdate("UPDATE ParkSlots set Status= '',Type='' Where Position = '"+pos+"'");
                st.executeUpdate("UPDATE VehicleDetails set Position= 0 Where Position = '"+pos+"'");
             
               System.out.println("Updated");
           st.close();
           con.close();
           }
           catch(Exception e)
           {
               System.out.println("Error:"+e);
           }
       }
       public  String  SelectCarPosition(String TableName)
       {
           String Pos = "N/A";
           try{
           Connection con = setConnection();
           java.sql.Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("SELECT * FROM VehicleDetails WHERE RegNo = '0'");
           while(rs.next())
           {
               Pos = rs.getString("Position");
               System.out.println("Position = "+Pos);
           }
           rs.close();
           st.close();
           con.close();
           }           catch(Exception e)
           {
           }
           return Pos;
       }
    public static void main(String args[]){
        DataBase db=new DataBase();
        db.setConnection();
         //db.getID("VehicleDetails");
         db.UpdateTimeOut("VehicleDetails", 5);
         db.insertVehicle("VehicleDetails", "9", "KCJ 860P", "9", "Van");
        
        // db.GetData("VehicleDetails","6");
         //System.out.println();
       // db.insertVehicle("Vehicle Details", "9", "KBR 764K", "9", "Lorry");
    }
    
}
