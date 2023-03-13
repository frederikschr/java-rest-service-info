
/**
 * Beschreiben Sie hier die Klasse UserController.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */



public class UserController
{
    private DatabaseController dbController;
    
    public UserController(DatabaseController dbController) {
        this.dbController = dbController;
    }
    
    
    public String[] createUser(String username, String password) {
        
        System.out.println("User signed up with username: " + username + " and password: " + password); 
        
        String[] response = {"200", "Successfully created user!"};
        return response;
    }
    
    public String[] getUser(String userData) {
        String[] response = {"200", "Hello there!"};
        return response;
    }
}
