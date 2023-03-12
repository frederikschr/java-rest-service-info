
import java.io.File;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseController
{
    final private String DATABASE_NAME = "database";
    final private String DB_URL = "jdbc:sqlite:" + this.DATABASE_NAME + ".db";
    
    final private String USER_TABLE_SQL = "CREATE TABLE User "     +
                       "(ID INT PRIMARY KEY     NOT NULL,"         +
                       " Name           TEXT    NOT NULL,"         + 
                       " Password       TEXT    NOT NULL)";
                       
                       
    final private String NOTES_TABLE_SQL = "CREATE TABLE Notes "   +
                        "(ID INT PRIMARY KEY    NOT NULL,"         +
                        "Date            TEXT   NOT NULL,"         +
                        "Content         TEXT   NOT NULL,"         +
                        "User_ID         INT    NOT NULL,"         +
                        "FOREIGN KEY(User_ID) REFERENCES User(ID))";
        
    Connection conn;
    
    public DatabaseController()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            boolean dbAlreadyExists = this.checkIfDatabaseAlreadyExists();
            this.conn = DriverManager.getConnection(this.DB_URL); //Create DB if not already exists and establish connection
            
            if (this.conn != null) {
                if (!dbAlreadyExists) {
                    this.createTables();
                } else {
                    System.out.println("Working with existing database.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private boolean checkIfDatabaseAlreadyExists() {
        File file = new File(DATABASE_NAME + ".db");
        if (file.exists()) {
            return true;
        }
        return false;
    }
    
    private void createTables() throws SQLException {
        this.execute(USER_TABLE_SQL);
        this.execute(NOTES_TABLE_SQL);
        System.out.println("Created new database tables.");
    }
    
    private void execute(String sql) throws SQLException {
        Statement stmt = this.conn.createStatement();
        System.out.println("Executing: " + sql);
        stmt.execute(sql);
    }

}
