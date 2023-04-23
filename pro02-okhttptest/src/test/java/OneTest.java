import org.junit.Test;

import java.io.File;

/**
 * @Description
 * @Author shungen
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/4/22
 */
public class OneTest {

    @Test
    public void test01(){
        File file = new File("pro02-okhttptest\\src\\main\\resources\\dict");
        File[] files = file.listFiles();
        for(int i = 0;i < files.length;i++){
            System.out.println(files[i].getAbsolutePath());
        }
    }
}
