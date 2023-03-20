
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server {
    
    final private int PORT = 8080;
    
    private HttpServer httpServer;
    
    private API api;
    
    public Server() throws IOException {
        this.httpServer = HttpServer.create(new InetSocketAddress(this.PORT), 0);
        this.httpServer.setExecutor(null); // creates a default executor
        this.api = new API(this.httpServer);
    }
    
    public void start() {
        this.httpServer.start();
        System.out.println("Server started...");
    }
    
    public void stop() {
        System.out.println("Server will shut down in 5 seconds...");
        this.httpServer.stop(5); //5 seconds delay until shutdown
        System.out.println("Server shutdown...");
    }
}
