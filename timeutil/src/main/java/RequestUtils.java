import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

/**
 * @Description
 * @Author shungen
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/4/17
 */
public class RequestUtils {


    private static String url = "https://pmc.test1.247tsa.dps.kone.cn/websocket/api/notifications/koneAlarm";

    /**
     * 从json文件中读取json字符串
     * @return
     * @throws IOException
     */
    public static String getJSONStrFromJSONFile() throws IOException {
        FileReader fr = new FileReader("pro01/src/main/java/com/shungen/request.json");
        char[] cbuf = new char[1024];
        StringBuilder sb = new StringBuilder();
        int len = 0;
        while((len = fr.read(cbuf)) != -1){
            sb.append(cbuf,0,len);
        }
        fr.close();
        return sb.toString();
    }




    public static void main(String[] args) throws IOException {
        System.out.println(getJSONStrFromJSONFile());
        JSONObject jsonObject = JSON.parseObject(getJSONStrFromJSONFile());

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    OkHttpClient client = new OkHttpClient();
//                    MediaType mediaType = MediaType.parse("application/json");
//                    JSONObject Json = JSON.parseObject(getJSONStrFromJSONFile());
//                    RequestBody requestBody = RequestBody.create(mediaType,Json.toString()+"");
//                    Request request = new Request.Builder()
//                            .url(url)
//                            .post(requestBody)
////                            .addHeader("请求头名称","值")
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    String responseData = response.body().string();
//                    JSONObject message = new JSONObject(JSON.parseObject(responseData));
//                    System.out.println(message);
//                }catch (Exception e){e.printStackTrace();}
//            }
//        }).start();
    }

    public void test11(){

    }
}
