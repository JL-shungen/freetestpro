import org.junit.Test;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/**
 * @Description TODO
 * @Date 2023/4/28 15:44
 * @Author shungen
 */
public class Test01 {

    /**
     * 语法格式一：无参，无返回值，Lambda体只需一条语句
     */
    @Test
    public void test01(){
        //正常写法
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world!");
            }
        });
        t.start();
        //lambda
        Thread t1 = new Thread(()->{
            System.out.println("hello lambda!");
        });
        t1.start();
    }



    /**
     * 语法格式二：Lambda需要一个参数,只需要一个参数时，参数外面的括号可以省略
     * 这里引入一个经典的函数式接口:Consumer
     */
    @Test
    public void test02(){

        //正常的
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("hello world");

        //lambda
        Consumer<String> con1 = (str)->{
            System.out.println(str);
        };
        con1.accept("hello lambda!");


        Consumer<String> con2 = str->{
            System.out.println(str);
        };
        con1.accept("hello lambda!");
    }


    /**
     * 语法格式三：Lambda需要两个参数，并且有返回值
     * - 当 Lambda 体只有一条语句时，return 与大括号可以省略
     * 涉及到函数式接口
     */
    @Test
    public void test03(){
        BinaryOperator<Integer> bo = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer in1, Integer in2) {
                return in1 + in2;
            }
        };
        System.out.println(bo.apply(12, 12));

        //修改
        BinaryOperator<Integer> bo1 = (x,y)->{
            return x + y;
        };
        System.out.println(bo1.apply(24, 24));

        //只有一条语句时，{}与return可以省略
        BinaryOperator<Integer> bo2 = (x,y)->x + y;
        System.out.println(bo2.apply(2,2));
    }

    @FunctionalInterface
    interface Fun{
        void show(Integer x,Integer y);
    }
    /**
     * 多个参数,但没有返回值
     */
    @Test
    public void test04(){
        Fun fun = new Fun() {
            @Override
            public void show(Integer x, Integer y) {
                System.out.println(x + y);
            }
        };
        fun.show(10,10);

//        Fun fun1 = (x,y)-> x + y;//报错，因为原接口里面并返回值设置为void，这里这样写却是要返回x + y，所以报错!
        Fun fun1 = (x,y) -> System.out.println(x + y);
        fun1.show(12,12);
    }


    abstract class  TestA{
        abstract void show(Integer x,Integer y);
    }
    @Test
    public void test05(){
        TestA t = new TestA() {
            @Override
            void show(Integer x, Integer y) {
                System.out.println(x + y);
            }
        };
        t.show(12,12);
//        TestA t1 = (x,y)->{
//            System.out.println(x + y);//编译报错
//        };
//        t1.show(12,12);

    }

    ////从这里可以看出Lambda为函数式接口的匿名对象，抽象类不行
}
