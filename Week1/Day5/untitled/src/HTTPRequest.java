import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class HTTPRequest {
    InputStream clientRequest;
    public HTTPRequest(InputStream clientRequest_)  {
        this.clientRequest = clientRequest_;
    }
    public  String getTheFileName(){
        Scanner sc = new Scanner(clientRequest);
        String fileName = "";
        if (sc.hasNext()) {
            String line = sc.nextLine() ; //if sc do had next. get the next line and split the blank get each string
            System.out.println(line);
            String[] lines = line.split(" ");

            if(!lines[1].isEmpty()){ //if the lines 1 is not empty,then the filename = to lines 1
                fileName = lines[1];
            }
        }
        if(fileName.equals("/")){// if client input "/" just return to the index.html
            fileName = "/index.html";
        }
        String filePath = "/Users/zhanyijun/Desktop/CS6011/Week1/Day1"+fileName;
        System.out.println(filePath); //cout the file path we can see how many web page shows,Not important though
        return  filePath;
    }
}
