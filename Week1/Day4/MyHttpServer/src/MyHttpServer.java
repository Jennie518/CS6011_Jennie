import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyHttpServer {
    public static void main(String[] args) throws Exception {
        //creat the ServerSocket 8010
        ServerSocket server = new ServerSocket(8010);
        while (true) {//use while to wait the client to accept
            Socket client = server.accept();
            Scanner sc = new Scanner(client.getInputStream()); // get the client input
            String fileName = ""; //creat a string failname 空的
            if (sc.hasNext()) {//if sc do had next. get the next line and split the blank get each string
                String[] lines = sc.nextLine().split(" ");
                if(!lines[1].isEmpty()){ //if the lines 1 is not empty,then the filename = to lines 1
                    fileName = lines[1];
                }

            }

            if(fileName.equals("/")){// if client input "/" just return to the index.html
                fileName = "/index.html";
            }
            String filePath =  "/Users/zhanyijun/Desktop/CS6011/Week1/Day1"+fileName;
            System.out.println(filePath);

            File file = new File(filePath);
            if(file.exists()){
                //we creat a file and put the file path inside,
                // if the file are exists,creat the fileToRead and put the file path in
                // use getOutputStream send it to the client.and the HTTP and text/html is the header
                //use the transfer 实现文件传输，and don't forget at the end we need to use flush and close
                FileInputStream fileToRead = new FileInputStream(file);
                OutputStream outputStream = client.getOutputStream();
                outputStream.write("HTTP/1.1 200\n".getBytes());
                outputStream.write("Content-Type: text/html\n".getBytes());
                outputStream.write("\n".getBytes());
                fileToRead.transferTo(outputStream);
                outputStream.flush();
                outputStream.close();
            }

            else{// if not exists,go to the webpage 404 error.
                FileInputStream fileToRead = new FileInputStream("/Users/zhanyijun/Desktop/CS6011/Week1/Day1");
                OutputStream outputStream = client.getOutputStream();
                outputStream.write("HTTP/1.1 404\n".getBytes());
                outputStream.write("Content-Type: text/html\n".getBytes());
                outputStream.write("\n".getBytes());
                fileToRead.transferTo(outputStream);
                outputStream.flush();
                outputStream.close();
            }
        }
    }
}
