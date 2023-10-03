import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.*;

public class HTTPResponse {
    String filePath;         // 存储要发送的文件路径
    OutputStream clientStream; // 用于向客户端发送数据的输出流

    public HTTPResponse(String filePath, OutputStream clientStream) {
        this.clientStream = clientStream;
        this.filePath = filePath;
    }

    // 发送现有文件的方法
    public void sendTheExistingFile(String filePath) {
        try {
            FileInputStream fileToRead = new FileInputStream(filePath); // 创建一个用于读取文件的FileInputStream
            clientStream.write("HTTP/1.1 200\n".getBytes()); // 发送HTTP响应头部信息，表示成功
            clientStream.write("Content-Type: text/html\n".getBytes()); // 指定响应内容类型为文本/html
            clientStream.write("\n".getBytes()); // 响应头部和内容之间的空行
            fileToRead.transferTo(clientStream); // 将文件内容传输到客户端输出流
            clientStream.flush(); // 刷新输出流
            clientStream.close(); // 关闭输出流
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
            String errorFilePath = "/Users/zhanyijun/Desktop/CS6011/Week1/Day1/error.html";
            sendErrorFile(errorFilePath); // 如果文件不存在，则发送错误页面
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }

    // 发送错误文件的方法
    public void sendErrorFile(String errorFilePath) {
        try {
            FileInputStream fileToRead = new FileInputStream(errorFilePath); // 创建一个用于读取错误文件的FileInputStream
            clientStream.write("HTTP/1.1 404\n".getBytes()); // 发送HTTP响应头部信息，表示文件未找到
            clientStream.write("Content-Type: text/html\n".getBytes()); // 指定响应内容类型为文本/html
            clientStream.write("\n".getBytes()); // 响应头部和内容之间的空行
            fileToRead.transferTo(clientStream); // 将错误文件内容传输到客户端输出流
            clientStream.flush(); // 刷新输出流
            clientStream.close(); // 关闭输出流
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
}
