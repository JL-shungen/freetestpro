package com.shungen.gpt;


import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GptSearch02 {
    public static void main(String[] args) {
        System.out.println(check("xiaoming"));
    }

    public static boolean check(String pwd){
        try {
            // 设置请求的URL
            URL url = new URL("http://144.34.166.190:8888/login");

            // 创建一个 HttpURLConnection 实例
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求的方法
            connection.setRequestMethod("POST");

            // 设置请求的头部信息
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setRequestProperty("username","xiaoming");
            connection.setRequestProperty("password",pwd);


            // 设置请求体
//            String requestBody = "{\"username\":\"John Doe\", \"email\":\"johndoe@example.com\"}";
            connection.setDoOutput(true);
//            OutputStream outputStream = connection.getOutputStream();
//            outputStream.write(requestBody.getBytes());
//            outputStream.flush();
//            outputStream.close();

            // 发送请求并获取响应
            int responseCode = connection.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            // 打印响应
//            System.out.println("Response Code: " + responseCode);
//            System.out.println("Response Body: " + response.toString());
            if(response.toString().indexOf("用户名或者密码错误") != -1){
//                System.out.println(response.toString().indexOf("用户名或者密码错误"));
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
