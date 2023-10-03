import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyHttpServer {// use try to see if 8020 is not working then the client can try 8090.but both is working

    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8020);
        } catch (IOException e) {
            try {
                server = new ServerSocket(8090);
            } catch (IOException e1) {// if 8090 and 8020 both not working catch an error
                throw new RuntimeException("sorry server is busy" + e1.getMessage());
            }
        }
//
//        try {
//            ServerSocket server = new ServerSocket(8020);
        while (true) {//use while to wait the client to accept
            Socket client = server.accept();
            InputStream clientRequestStream = client.getInputStream();
            OutputStream clientResponseStream = client.getOutputStream();
            HTTPRequest hct = new HTTPRequest(clientRequestStream);
            String filePath = hct.getTheFileName();
            HTTPResponse hr1 = new HTTPResponse(filePath, clientResponseStream);
            hr1.sendTheExistingFile(filePath);

        }
    }
}