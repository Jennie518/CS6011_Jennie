package com.example.thread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyHttpServer {

    public static void main(String[] args) throws Exception {
        // 创建 ServerSocket 监听 8010 端口
        ServerSocket server = new ServerSocket(8010);
        while (true) { // 循环等待客户端连接
            Socket client = server.accept();

            // 为每个客户端连接创建一个新线程
            new Thread(new RequestHandler(client)).start();
        }
    }
}

class RequestHandler implements Runnable {
   Socket client_;
   RequestHandler(Socket client){client_ = client;}

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(client_.getInputStream());
            String fileName = "";
            if (sc.hasNext()) {
                String[] lines = sc.nextLine().split(" ");
                if (!lines[1].isEmpty()) {
                    fileName = lines[1];
                }
            }

            if (fileName.equals("/")) {
                fileName = "/day1.html";
            }
            String filePath = "/Users/zhanyijun/Desktop/CS6011/Week1/Day1" + fileName;
            System.out.println(filePath);

            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fileToRead = new FileInputStream(file);
                OutputStream outputStream = client_.getOutputStream();
                outputStream.write("HTTP/1.1 200\n".getBytes());
                outputStream.write("Content-Type: text/html\n".getBytes());
                outputStream.write("\n".getBytes());
//                fileToRead.transferTo(outputStream);
                for( int i = 0; i < file.length(); i++ ) {
                    outputStream.write( fileToRead.read() );
                    outputStream.flush();
                    Thread.sleep( 1 ); // Maybe add <- if images are still loading too quickly...
                }
                outputStream.flush();
                outputStream.close();
            } else {
                FileInputStream fileToRead = new FileInputStream("/Users/zhanyijun/Desktop/CS6011/Week1/Day1/error.html");
                OutputStream outputStream = client_.getOutputStream();
                outputStream.write("HTTP/1.1 404\n".getBytes());
                outputStream.write("Content-Type: text/html\n".getBytes());
                outputStream.write("\n".getBytes());
                fileToRead.transferTo(outputStream);

                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
