/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vrms.creation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vrms.connection.JDBCConnectionPool;
import com.vrms.dao.LocationObjects;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.xml.ws.Response;

/**
 *
 * @author acts
 */
public class MyClass {
    
     private JDBCConnectionPool pool;

    public MyClass() {
        pool = new JDBCConnectionPool();
    }
    
    
    
    
    public static void main(String args[]) throws SQLException
    {
         String str="{\n" +
"    loc:[{\n" +
"            buildingNo: '1', \n" +
"            buildingName: 'null', \n"+
"            streetName: 'tgdfgdf', \n" +
"            areaName: 'sdadsa', \n" +
"            city: 'asdasd', \n" +
"            state: 'sdfdsfs'\n" +
"        },{\n" +
"            buildingNo: '2', \n" +
"            buildingName: 'null', \n"+
"            streetName: 'tgdfgdf', \n" +
"            areaName: 'sdadsa', \n" +
"            city: 'asdasd', \n" +
"            state: 'sdfdsfs'\n" +
"        },{\n" +
"            buildingNo: '3', \n" +
"            buildingName: 'null', \n"+
"            streetName: 'tgdfgdf', \n" +
"            areaName: 'sdadsa', \n" +
"            city: 'asdasd', \n" +
"            state: 'sdfdsfs'\n" +
"        }],\n" +
"    person:[{\n" +
"            name: 'sdfsdf', \n" +
"            mobile: 'fgdfgdg'\n" +
"        }],\n" +
"    map:[{\n" +
"    _loc:0,_prsns:[{\n" +
"            _prsn:0,\n" +
"            type:'PICK'\n" +
"    }]\n" +
"    }]\n" +
"}";    
         
        
         JsonParser parser = new JsonParser();
         JsonObject o = parser.parse(str).getAsJsonObject();
        // System.out.println(""+ o.get("loc"));
        // System.out.println(""+ o.get("person"));
        // System.out.println(""+ o.get("map"));
	 //System.out.println(""+o.get("loc").getAsJsonArray());
         
        LocationObjects obj=new LocationObjects();
        ArrayList<Integer> locations=new ArrayList<>();
         JsonArray loc= o.getAsJsonArray("loc");
          JsonArray person= o.getAsJsonArray("person");
         for(JsonElement jo : loc)
          {
            String buildingNo= jo.getAsJsonObject().get("buildingNo").getAsString();
            String buildingName=jo.getAsJsonObject().get("buildingName").getAsString();
            String streetName=jo.getAsJsonObject().get("streetName").getAsString();
            String areaName=jo.getAsJsonObject().get("areaName").getAsString();
            String city=jo.getAsJsonObject().get("city").getAsString();
            String state=jo.getAsJsonObject().get("state").getAsString();
            int locationID = obj.fillLocation(buildingNo, buildingName, streetName, areaName, city, state);
           
            
            locations.add(locationID); 
         }
         System.out.println("LocationID "+locations.get(1));   
         for(JsonElement je : person)
         {
             String userName= je.getAsJsonObject().get("name").getAsString();
             String mobileNo=je.getAsJsonObject().get("mobile").getAsString();
             int requestUserID=obj.fillRequestUser(userName, mobileNo);
             System.out.println("RequestUserID "+ requestUserID);
         }
         
                
    }
    
    
}
