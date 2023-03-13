
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
    
    
    public String[] createUser(String userData) {
        String[] response = {"200", "Successfully created user!"};
        
        String[] data = userData.split(":");//.replaceAll("}", "").split(":");
        
        System.out.println("Received user data: " + data[0] + " " + data[1]);
        
        return response;
    }
    
    public String[] getUser(String userData) {
        String[] response = {"200", "Hello there!"};
        return response;
    }
}
