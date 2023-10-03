import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Rainfall {
    private String cityName;
    private ArrayList<Double> rainfallData = new ArrayList<>();
    private ArrayList<String> monthsData = new ArrayList<>();
    private static final String[] MONTHS = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };
    public Rainfall(String filename) throws FileNotFoundException {
        Scanner fileReader = new Scanner(new FileInputStream(filename));
        cityName = fileReader.nextLine().trim();
        while (fileReader.hasNext()) {
            fileReader.next(); // skip month
            fileReader.nextInt(); // skip year
            rainfallData.add(fileReader.nextDouble()); // get all the rainfall data
        }
    }
    public double computeOverallAverage() {//calculate average data rainfall
        double sum = 0;
        for (double rainfall : rainfallData) {
            sum += rainfall;
        }
        return sum / rainfallData.size();
    }

    public double computeMonthlyAverage(int month) { // calculate average rainfall of every month
        double sum = 0;
        for (int i = month - 1; i < rainfallData.size(); i += 12) {
            sum += rainfallData.get(i);
        }// 算有多少行，用year的size，因为不同文件多少年会改变
        return sum/20 ; // as there are 240 data points over 20 years
    }

    public void generateReport() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream("rainfall_results.txt"));
        DecimalFormat df = new DecimalFormat("#.##");

        pw.println("The overall average rainfall amount is " + df.format(computeOverallAverage()) + " inches.");
        for (int i = 0; i < 12; i++) {
            pw.println("The average rainfall amount for " + MONTHS[i] + " is " + df.format(computeMonthlyAverage(i + 1)) + " inches.");
        }
        pw.close();
    }
}
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Rainfall {
//    private ArrayList<String> month =  new ArrayList<>();
//    private ArrayList<Double> rainfallData = new ArrayList<>();
//
//    // 添加一个构造函数，用于传递文件名
//    public Rainfall(String filename) {
//        readDataFromFile(filename);
//    }
//
//    public void readDataFromFile(String filename) {
//        try {
//            Scanner fileReader = new Scanner(new FileInputStream(filename));
//
//            // 读取城市名
//            if (fileReader.hasNextLine()) {
//                String city = fileReader.nextLine();
//            }
//            while (fileReader.hasNextLine()) {
//                String line = fileReader.nextLine();
//                if (!line.isEmpty()) {
//                    // 解析行并提取降雨数据
//                    String[] parts = line.split(" ");
//                    double rainfall = Double.parseDouble(parts[parts.length - 1]);
//                    rainfallData.add(rainfall);
//                }
//            }
//            fileReader.close();
//        } catch (FileNotFoundException e) {
//            System.err.println("File not found: " + filename);
//        }
//    }
//
//    public ArrayList<Double> calculateMonthlyAverages() {
//        ArrayList<Double> monthlyAverages = new ArrayList<>();
//
//        // 计算每个月的平均降雨量
//        for (int i = 0; i < 12; i++) {
//            double totalRainfall = 0.0;
//            int count = 0;
//            for (int j = i; j < rainfallData.size(); j += 12) {
//                totalRainfall += rainfallData.get(j);
//                count++;
//            }
//            if (count > 0) {
//                double average = totalRainfall / count;
//                monthlyAverages.add(average);
//            }
//        }
//
//        return monthlyAverages;
//    }
//
//    public void writeResultsToFile(String outputFilename, ArrayList<Double> monthlyAverages) {
//        try {
//            PrintWriter pw = new PrintWriter(new FileOutputStream(outputFilename));
//
//            // 写入每个月的平均降雨量
//            String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
//            for (int i = 0; i < monthlyAverages.size(); i++) {
//                pw.println("The average rainfall amount for " + monthNames[i] + " is " + String.format("%.2f", monthlyAverages.get(i)) + " inches.");
//            }
//            pw.close();
//        } catch (FileNotFoundException e) {
//            System.err.println("Error writing to file: " + outputFilename);
//        }
//    }
//
//    public double calculateOverallAverage() {
//        double totalRainfall = 0.0;
//        for (double rainfall : rainfallData) {
//            totalRainfall += rainfall;
//        }
//        return totalRainfall / rainfallData.size();
//    }
//
//    public static void main(String[] args) {
//        // 创建 Rainfall 对象并指定数据文件名
//        Rainfall rainfall = new Rainfall("rainfall_data.txt");
//
//        // 计算每个月的平均降雨量
//        ArrayList<Double> monthlyAverages = rainfall.calculateMonthlyAverages();
//
//        // 计算总体平均降雨量
//        double overallAverage = rainfall.calculateOverallAverage();
//
//        // 将结果写入文件
//        rainfall.writeResultsToFile("rainfall_results.txt", monthlyAverages);
//
//        // 打印总体平均降雨量
//        System.out.println("The overall average rainfall amount is " + String.format("%.2f", overallAverage) + " inches.");
//    }
//}
