
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
        
        if (exchange.getRequestMethod().equals("GET")) {
            
            this.userController.getUser(requestBody);            
        
            // exchange.sendResponseHeaders(200, responseText.getBytes().length);

            // output.write(responseText.getBytes());
            output.flush();    
        }
        
        else if (exchange.getRequestMethod().equals("POST")) {
              
            
            
            

            
            
        }
        
        else {
            exchange.sendResponseHeaders(405, -1);
        }
        
        exchange.close();
        
    }
}
