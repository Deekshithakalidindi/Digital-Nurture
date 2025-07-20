import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class LoanService {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        server.createContext("/loans", new LoanHandler());
        server.setExecutor(null);
        System.out.println("Loan service started at http://localhost:8081/loans");
        server.start();
    }

    static class LoanHandler implements HttpHandler {
        public void handle(HttpExchange exchange) {
            try {
                String path = exchange.getRequestURI().getPath();
                String[] parts = path.split("/");
                String loanNumber = (parts.length > 2) ? parts[2] : "unknown";

                String response = "{ \"number\": \"" + loanNumber + "\", \"type\": \"car\", \"loan\": 400000, \"emi\": 3258, \"tenure\": 18 }";

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
