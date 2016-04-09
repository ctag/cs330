/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartoguration.a.tron;

/**
 *
 * @author Luc
 */
import java.sql.*;

public class StateDB {
    //This will be used to interact with the DB
    public static String StoryChainElements = "CREATE TABLE `Elements_SS` (\n" +
                                            "	`Genotype`	TEXT NOT NULL UNIQUE," +
                                            "	`Field2`	TEXT NOT NULL," +
                                            "	`StoryBlockID`	TEXT NOT NULL," +
                                            "	PRIMARY KEY(Genotype)\n" +
                                            ");"; 
    public static String StoryChainModifiers = "CREATE TABLE `Modifiers_SS` (\n" +
                                               " `Trait`	TEXT NOT NULL UNIQUE,\n" +
                                               " `Value`	TEXT NOT NULL,\n" +
                                               " StoryBlockID`	TEXT NOT NULL,\n" +
                                               " Description`	TEXT NOT NULL\n" +
                                               ");"; 
    public static void createDB()
    {
        Connection c = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:StroryData.db");
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Opened database successfully");
        createTables(StoryChainElements);
        createTables(StoryChainModifiers);
        
    }
    //public static void 
    private static void createTables(String sql)
    {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:StroryData.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}
