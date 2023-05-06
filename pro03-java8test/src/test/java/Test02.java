import lombok.*;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Description TODO
 * @Date 2023/5/4 12:19
 * @Author shungen
 */
public class Test02 {

    /**
     * 四大核心函数式接口
     * - Consumer接口
     */
    @Test
    public void test01() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("接收到传进来的字符串为:" + s);
            }
        };
        consumer.accept("hello");
        System.out.println("===================lambda表达式================");
        Consumer<Integer> consumer1 = x -> System.out.println("接收到的整数为:" + x);
        consumer1.accept(122);
    }

    @Test
    public void test02() {
        Supplier<String> pwdSupplier = new Supplier<String>() {
            @Override
            public String get() {
                return "111";
            }
        };
        System.out.println(pwdSupplier.get());

        Supplier<Integer> pwdSupplier02 = () -> 100;
        System.out.println(pwdSupplier02.get());
    }


    @Test
    public void test03() {
        Function<String, String> dealStrSpace = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.trim();
            }
        };
        System.out.println(dealStrSpace.apply("      dsfs          "));

        System.out.println("============================");
        Function<String, Integer> str2Int = (x) -> Integer.parseInt(x);
        System.out.println(str2Int.apply("1222"));
    }


    @Test
    public void test04(){
        //假设我想要实现输出传入的参数的动作
        Consumer<String> consumer = x-> System.out.println(x);
        consumer.accept("111");

        //我们可以转换成这样 对象::方法
        Consumer<String> consumer1 = System.out::println;//这里相当于是用已有的方法直接替代抽象方法，里面包含了参数定义矛动作执行
        consumer1.accept("1231");

        //比较字符串

    }

    //创建Stream
    @Test
    public void test05(){
        //方式一:可以通过Collection系列集合提供的stream()或者parallelStream()
        ArrayList<String> list = new ArrayList<>();
        list.add("admin01");
        list.add("admin02");
        list.add("admin03");
        list.add("admin04");
        list.add("admin05");
        Stream<String> stream1 = list.stream();


        //方式二:通过Arrays工具类的静态方法stream()获取数组流
        String[] arr1 = new String[]{"111","222","333"};
        Stream<String> stream2 = Arrays.stream(arr1);

        //方式三:通过Stream类中的静态方法of(...args)获取流对象
        Stream<String> stream3 = Stream.of(arr1);
        Stream<String> stream4 = Stream.of("111", "222", "333");

        //方式四:创建无限流:
        //迭代
        //参数一:初始值，参数二:Function函数式接口子接口，定义下一个元素的获得方法，同时当前元素作为参数传入（第一次当前元素就是seed初始化参数)
        Stream<Integer> iterateStream = Stream.iterate(1, x -> x + 2);

        System.out.println("===========迭代生成的无限流");
        iterateStream.limit(10).forEach(System.out::println);//limit中间操作,foreach终止操作

        //生成
        System.out.println("================直接生成的无限流");//1-3间的随机数
        Stream<Integer> generateStream = Stream.generate(() -> (int) (Math.random() * 3) + 1);
        generateStream.limit(10).forEach(System.out::println);

    }
    @Test
    public void testRandom(){
        for(int i = 0;i < 30;i++){
            System.out.println((int)(Math.random() * 3) + 1);
        }
    }



    //筛选与切片
    @Test
    public void test06(){
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        class Employee{
            private Integer id;
            private String name;
            private Integer age;
        }

        ArrayList<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1001,"test01",23));
        empList.add(new Employee(1003,"test02",16));
        empList.add(new Employee(1003,"test03",34));
        empList.add(new Employee(1004,"test04",12));
        empList.add(new Employee(1005,"test05",56));
        empList.add(new Employee(1006,"test06",34));
        empList.add(new Employee(1007,"test07",23));
        empList.add(new Employee(1008,"test08",45));
        empList.add(new Employee(1009,"test09",67));

        Stream<Employee> stream = empList.stream();
        stream.filter(x->x.age >= 20?true:false).forEach(System.out::println);//filter中间操作只要返回为true的

    }


    //映射：说白了就是对每个元素进行操作映射到新的集合中，返回一个stream流
    @Test
    public void test07(){
        List<String> list = Arrays.asList("AA","bb","ss","qq","tt");
        Stream<String> stream = list.stream();
        stream.map(String::toUpperCase).forEach(System.out::println);
    }


    @Test
    public void test08(){
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        class Employee implements Comparable<Employee> {
            private Integer id;
            private String name;
            private Integer age;

            @Override
            public int compareTo(Employee employee) {
                return Integer.compare(this.getAge(),employee.getAge());
            }
        }

        ArrayList<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1001,"test01",23));
        empList.add(new Employee(1003,"test02",16));
        empList.add(new Employee(1003,"test03",34));
        empList.add(new Employee(1004,"test04",12));
        empList.add(new Employee(1005,"test05",56));
        empList.add(new Employee(1006,"test06",34));
        empList.add(new Employee(1007,"test07",23));
        empList.add(new Employee(1008,"test08",45));
        empList.add(new Employee(1009,"test09",67));

        Stream<Employee> stream = empList.stream();
        stream.sorted().forEach(System.out::println);

        System.out.println("===========定制排序===========");
        //定制排序
        Stream<Employee> stream1 = empList.stream();
        stream1.sorted((x,y)->Integer.compare(y.getAge(),x.getAge())).forEach(System.out::println);


        Stream<Employee> stream2 = empList.stream();
        boolean flag = stream2.map(x -> {
            x.setAge(x.getAge() + 10);
            return x;
        }).allMatch(x -> x.getAge() > 20);
        System.out.println(flag);


    }


    @Test
    public void test09(){
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        class Employee implements Comparable<Employee> {
            private Integer id;
            private String name;
            private Integer age;

            @Override
            public int compareTo(Employee employee) {
                return Integer.compare(this.getAge(),employee.getAge());
            }
        }

        ArrayList<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1001,"test01",23));
        empList.add(new Employee(1003,"test02",16));
        empList.add(new Employee(1003,"test03",34));
        empList.add(new Employee(1004,"test04",12));
        empList.add(new Employee(1005,"test05",56));
        empList.add(new Employee(1006,"test06",34));
        empList.add(new Employee(1007,"test07",23));
        empList.add(new Employee(1008,"test08",45));
        empList.add(new Employee(1009,"test09",67));

        Stream<Employee> stream = empList.stream();
        //累积函数的标识值

        List<Integer> list = Arrays.asList(13, 24, 22, 56, 32, 12);
        System.out.println(list.stream().reduce((x, y) -> x + y).get());






    }


    @Test
    public void test10(){
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        class Employee implements Comparable<Employee> {
            private Integer id;
            private String name;
            private Integer age;

            @Override
            public int compareTo(Employee employee) {
                return Integer.compare(this.getAge(),employee.getAge());
            }
        }
        Optional<Employee> wsg = Optional.of(new Employee(1001, "王顺根", 23));
        System.out.println(wsg.get());//Employee(id=1001, name=王顺根, age=23)

//        Optional<Employee> op1 = Optional.of(null);
//        System.out.println(op1.get());//Employee(id=1001, name=王顺根, age=23)//会报空指针，到更好找

//        Optional<Object> empty = Optional.empty();//
//        System.out.println(empty.get());//java.util.NoSuchElementException: No value present
    }

    @Test
    public void test11(){
        //LocalDateTime本身不包含时区信息，它存储的是年、月、日、时分秒，纳秒这样的数字。获取的是当前的系统时间
        //因为Date有时区信息，所以构造Date也就需要时区信息。所以LocalDate不能直接转换为Date
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);//2023-05-05T10:22:40.719

        //用于“时间戳”的运算,就是带时区的时间
        //跟Date一样，它是以Unix元年(传统的设定为UTC时区1970年1月1日午夜时分)开始所经历的描述进行运算
        //所以可以直接转换为Date
        Instant ins = Instant.now();//默认获取UTC时区
        System.out.println(ins);//2023-05-05T02:22:40.720Z 与上面的相差八个小时

        //对ins添加偏移,带偏移的时间
        OffsetDateTime offsetDateTime = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2023-05-05T10:22:40.720+08:00


        //获取时间戳https://blog.csdn.net/u014044812/article/details/79231738
        long milli = offsetDateTime.toInstant().toEpochMilli();//获取毫秒数

        ZonedDateTime zoneTime1 = ZonedDateTime.now();
        System.out.println(zoneTime1);
        LocalDateTime localDateTime = zoneTime1.toLocalDateTime();
        System.out.println(localDateTime);

        //ZoneId是时区类
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(availableZoneIds);

        //从指定时区获取当前时间
        ZonedDateTime now1 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(now1);
        LocalDateTime localDateTime1 = now1.toLocalDateTime();
        System.out.println(localDateTime1);

    }

    @SneakyThrows
    @Test
    public void test12(){
        //• Duration:用于计算两个“时间”间隔 类
        LocalDateTime start = LocalDateTime.now();
        Thread.sleep(3000);
        LocalDateTime end = LocalDateTime.now();
        Duration between = Duration.between(start, end);
        System.out.println(between);
        System.out.println(between.getSeconds());//获取秒数
        System.out.println(between.toMillis());//获取毫秒数

        // Period 用于计算两个日期之间的间隔
        LocalDate date1 = LocalDate.of(2023,5,5);
        LocalDate date2 = LocalDate.of(2023,5,1);
        Period between1 = Period.between(date1, date2);
        System.out.println(between1);
        System.out.println(between1.getDays());

    }


    @Test
    public void test13(){
        Optional<String> shanghaiZoneIdOp = ZoneId.getAvailableZoneIds().stream().filter(x -> x.toLowerCase().contains("shanghai")).findFirst();
        System.out.println(shanghaiZoneIdOp.get());
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(shanghaiZoneIdOp.get()));
        LocalDateTime localDateTime = now.toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowTimeStr = formatter.format(localDateTime);
        System.out.println(nowTimeStr);

        LocalDateTime localDateTime1 = LocalDateTime.parse(nowTimeStr, formatter);
        System.out.println(localDateTime1);

    }
}
