package com.shungen.gpt;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * @Description
 * @Author shungen
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/4/22
 */
public class GptWebPwdSearch {

    public static OkHttpClient client = new OkHttpClient();

    public static String BASE_URL = "http://144.34.166.190:8888";

    public static void main(String[] args) throws IOException {
        File file = new File("pro02-okhttptest\\src\\main\\resources\\dict");
        File[] files = file.listFiles();
        for(int i = 0;i < files.length;i++){
            System.out.println(files[i].getAbsolutePath());
        }

    }
}
