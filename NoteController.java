
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteController
{
    private DatabaseController dbController;

    /**
     * Konstruktor für Objekte der Klasse NotesController
     */
    public NoteController(DatabaseController dbController)
    {
        this.dbController = dbController;
    }
    
    public String[] createNote(String username, String password, String date, String content) throws SQLException {
        String[] response = new String[2];
        
        String get_user_sql = "SELECT User.ID FROM User WHERE User.name = '" + username + "' AND User.password = '" + password + "'";
        
        ResultSet rs = this.dbController.query(get_user_sql);
        
        if (!rs.isBeforeFirst()) { // No user found --> does not exist or password is incorrect
            response[0] = "404";
            response[1] = "User does not exist or password is wrong";
        }
        
        else {
            String user_id = rs.getString(1);
            System.out.println("User found: " + user_id);
            String create_note_sql = "INSERT INTO Note (Date, Content, User_ID) VALUES ('" + date + "', '" + content + "', " + user_id + ");";
            this.dbController.execute(create_note_sql);
            response[0] = "200";
            response[1] = "Successfully created note!";
        }
        
        return response;
        
    }

}
