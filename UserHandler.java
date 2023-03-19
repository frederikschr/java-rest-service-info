
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class UserHandler implements HttpHandler
{
    private UserController userController;
    
    public UserHandler(DatabaseController dbController) {
        this.userController = new UserController(dbController);    
    }
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        
        
        String requestBody = new String(exchange.getRequestBody().readAllBytes());
        
        OutputStream output = exchange.getResponseBody();
        
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        
        String[] response = new String[2];
        
        
        try {
            if (exchange.getRequestMethod().equals("POST")) {
                
                JSONObject json = new JSONObject(requestBody);
        
                String username = json.getString("username");
                String password = json.getString("password");
                
                response = this.userController.createUser(username, password);
              
            }
            
            else { // Method not allowed
                response[0] = "405";
                response[1] = "Method not allowed";
            }
        
            
        } catch (Exception e) { // Bad request --> JSON invalid
            if (e instanceof JSONException) {
                response[0] = "400";
                response[1] = e.getMessage();
            }
            
            else if (e instanceof SQLException) {
                response[0] = "500";
                response[1] = "Internal server error";
            }
            System.out.println("Error: " + e.getMessage());
        }
    
        
        if (response[0] != null && response[1] != null) {
            int responseStatusCode = Integer.parseInt(response[0]);
            String responseText = response[1];
            exchange.sendResponseHeaders(responseStatusCode, responseText.getBytes().length);
            output.write(responseText.getBytes());
            output.flush();
        }
        
        exchange.close();
    }
}
