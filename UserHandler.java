
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.io.IOException;


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
        
        
        if (exchange.getRequestMethod().equals("GET")) {
            
            response = this.userController.getUser(requestBody);            
           
        }
        
        else if (exchange.getRequestMethod().equals("POST")) {
            response = this.userController.createUser(requestBody);
        }
        
        else {
            exchange.sendResponseHeaders(405, -1);
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
