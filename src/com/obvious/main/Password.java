/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obvious.main;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.obvious.Fenetre.Fenetre;

/**
 *
 * @author Obvious
 */
public class Password {

    private static Connection conn;
    public static void main(String[] args) throws IOException, Exception {
        
        verifyDatabase();
        Fenetre f = new Fenetre();
        f.setVisible(true); 
    }

    private static void verifyDatabase() throws Exception {
    	
    	new File(System.getProperty("user.dir").replace("\\", "/")+"/PasswordSaver/").mkdirs();
    	System.out.println(System.getProperty("user.dir").replace("\\", "/")+"/PasswordSaver/data.db");
        String url = "jdbc:sqlite:/"+System.getProperty("user.dir").replace("\\", "/")+"/PasswordSaver/data.db";
        Class.forName("org.sqlite.JDBC").newInstance();
        String sql = "CREATE TABLE IF NOT EXISTS `password` (\n" +
        "  `id` integer PRIMARY KEY AUTOINCREMENT,\n" +
        "  `url` varchar(1000) NOT NULL,\n" +
        "  `utilisateur` varchar(1000) NOT NULL,\n" +
        "  `mdp` varchar(1000) NOT NULL\n" +
        ");";
        conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement(); 
        stmt.execute(sql);
        stmt.close();
        
        
    }
    public static Connection getConn(){
        return conn;
    }

    
}
