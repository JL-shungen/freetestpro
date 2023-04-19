import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

/**
 * @Description
 * @Author shungen
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/4/13
 */
public class TimeGetUtil{

    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//        String inputTime = "2022-01-01 00:00:00";
//        System.out.println("请输入时间(格式:2022-01-01 00:00:00):");
//        inputTime = in.nextLine();
//        long timestamp = generateTimestamp(inputTime);
//        System.out.println(timestamp); // 输出：1640995200000
//        in.close();30321056



//        long time  =  1652441832000L;
//        System.out.println(timestampToDateString(time));


        System.out.println(getCurrentTimeStrSub8H());
    }



    public static long generateTimestamp(String inputTime) {
        try {
            inputTime = inputTime.trim();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = dateFormat.parse(inputTime);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // 返回-1表示解析失败
        }
    }
    public static String timestampToDateString(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }


    /**
     * 获取当前时间并往前退8h,再加1min
     * @return
     */
    public static String getCurrentTimeStrSub8H(){
        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDateTime = now.format(formatter);
        now = now.minusHours(8);
        now = now.plusSeconds(15);
        return now.toString();
    }
}
