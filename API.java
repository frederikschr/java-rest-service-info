
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class API {
    
    HttpServer server;
    DatabaseController dbController;
    
    public API(HttpServer server) {
        this.server = server;
        this.dbController = new DatabaseController();
        this.registerResources();
    }
    
    public void registerResources() {
        this.server.createContext("/api/user", new UserHandler(dbController));
        this.server.createContext("/api/note", new NoteHandler(dbController));
    }
}
