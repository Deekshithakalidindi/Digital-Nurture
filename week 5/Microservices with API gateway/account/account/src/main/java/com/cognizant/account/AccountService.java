import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class AccountService {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/accounts", new AccountHandler());
        server.setExecutor(null); // creates a default executor
        System.out.println("Account service started at http://localhost:8080/accounts");
        server.start();
    }

    static class AccountHandler implements HttpHandler {
        public void handle(HttpExchange exchange) {
            try {
                String path = exchange.getRequestURI().getPath();
                String[] parts = path.split("/");
                String accNumber = (parts.length > 2) ? parts[2] : "unknown";

                String response = "{ \"number\": \"" + accNumber + "\", \"type\": \"savings\", \"balance\": 234343 }";

                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.length());

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
