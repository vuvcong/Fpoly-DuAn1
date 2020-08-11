/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.com.helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author PC
 */
public class Connect {
    protected Connection con=null;
    public Connect(){
        try
        {
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=DA1_QLTV";
            con=DriverManager.getConnection(url,"sa","songlong");
        }
           catch(Exception ex)
            {
                ex.printStackTrace();
            }   
    }
}
