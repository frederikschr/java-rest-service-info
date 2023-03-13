

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.io.IOException;


public class NoteHandler implements HttpHandler
{
    private NoteController noteController;
    
    public NoteHandler(DatabaseController dbController)
    {
        this.noteController = new NoteController(dbController); 
    }

    @Override
    public void handle(HttpExchange exchange) {
        ;
    }
}
