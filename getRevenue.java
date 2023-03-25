
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author antonio
 */
public class getRevenue extends DataBase{
     public static int getRevenue(String TableName, String Date,String Type)
    {
        Connection con = setConnection();
        int count = 0;
        try{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("(SELECT * FROM VehicleDetails Where Date = '"+Date+"' and Type='"+Type+"')");
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
    public static void main(String[]args)
    {
    }
}
    

    

