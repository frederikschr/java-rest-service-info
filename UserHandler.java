
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.io.IOException;


public class UserHandler implements HttpHandler
{
    @Override
    public void handle(HttpExchange request) throws IOException {
         if ("GET".equals(request.getRequestMethod())) {
                String responseText = "Hello World!";
                request.sendResponseHeaders(200, responseText.getBytes().length);
                OutputStream output = request.getResponseBody();
                output.write(responseText.getBytes());
                output.flush();
            } else {
                request.sendResponseHeaders(405, -1);// 405 Method Not Allowed
            }
        request.close();
    }
}
