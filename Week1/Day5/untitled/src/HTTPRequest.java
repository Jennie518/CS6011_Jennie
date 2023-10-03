import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HTTPRequest {
    InputStream clientRequest;
    // 构造函数，用于初始化 HTTPRequest 对象，接收一个 InputStream 参数
    public HTTPRequest(InputStream clientRequest_) {
        this.clientRequest = clientRequest_;
    }

    // 从 HTTP 请求中获取文件名的方法
    public String getTheFileName() {
        Scanner sc = new Scanner(clientRequest);

        String fileName = ""; // 创建一个空字符串以存储文件名

        if (sc.hasNext()) {
            String line = sc.nextLine(); // 如果输入流中有下一行数据，获取该行数据
            System.out.println(line); // 打印该行数据，用于调试

            String[] lines = line.split(" "); // 将该行数据按空格分割成字符串数组

            if (!lines[1].isEmpty()) { // 如果数组的第二个元素不为空
                fileName = lines[1]; // 将文件名设置为该元素的值
            }
        }

        if (fileName.equals("/")) { // 如果文件名为 "/"，则返回 "day1.html"
            fileName = "/day1.html";
        }

        String filePath = "/Users/zhanyijun/Desktop/CS6011/Week1/Day1" + fileName;
        System.out.println(filePath); // 打印文件路径，用于调试（非必要）

        return filePath; // 返回文件路径
    }
}
