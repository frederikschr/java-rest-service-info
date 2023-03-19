
/**
 * Beschreiben Sie hier die Klasse UserController.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController
{
    private DatabaseController dbController;
    
    public UserController(DatabaseController dbController) {
        this.dbController = dbController;
    }
    
    
    public String[] createUser(String username, String password) throws SQLException {
        
        String[] response = new String[2];
        
        String check_for_existing_user_sql = "SELECT User.ID FROM User WHERE User.name = '" + username + "'";
        String create_user_sql = "INSERT INTO User (Name,Password) VALUES('" +  username + "', '" + password + "');";
        
                
        ResultSet rs = this.dbController.query(check_for_existing_user_sql);
        
        if (rs.isBeforeFirst()) { //Data set is not empty --> user already exists
            response[0] = "409";
            response[1] = "Username already exists";
        }
        
        else {
            dbController.execute(create_user_sql);
            response[0] = "200";
            response[1] = "Successfully created user!";
            System.out.println("User signed up with username: " + username + " and password: " + password); 
        }
      
        return response;
    }
}
