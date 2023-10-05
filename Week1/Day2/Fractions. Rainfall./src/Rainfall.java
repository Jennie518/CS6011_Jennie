import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Rainfall {
    public static void main(String[] args) {
        String inputFilename = "rainfall_data.txt";   // 输入数据文件名
        String outputFilename = "rainfall_results.txt"; // 输出结果文件名

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilename));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename))) {
            String line;
            Map<String, Double> monthlyData = new HashMap<>();
            Map<String, Integer> daysInMonth = new HashMap<>();

            while ((line = br.readLine()) != null)  {
                System.out.println("Reading line: " + line); // Debug statement

                // 使用空格分割每行数据
                String[] parts = line.split("\\s+");

                if (parts.length == 3) {
                    String month = parts[0]; // 月份信息
                    double value = Double.parseDouble(parts[2]); // 将字符串转换为 double

                    // 如果月份已经在映射中，则累加降雨量和天数
                    if (monthlyData.containsKey(month)) {
                        monthlyData.put(month, monthlyData.get(month) + value);
                        daysInMonth.put(month, daysInMonth.get(month) + 1);
                    } else {
                        monthlyData.put(month, value);
                        daysInMonth.put(month, 1);
                    }
                }
            }

            // 计算每个月的平均降雨量并写入输出文件
            for (Map.Entry<String, Double> entry : monthlyData.entrySet()) {
                System.out.println("Writing data for month: " + entry.getKey());
                String month = entry.getKey();
                double totalRainfall = entry.getValue();
                int days = daysInMonth.get(month);
                double averageRainfall = totalRainfall / days;

                // 将结果写入输出文件
                bw.write(String.format("The average rainfall amount for %s is %.2f inches.", month, averageRainfall));
                bw.newLine(); // 换行
            }
            bw.close();

            System.out.println("Data processed and written to " + outputFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}