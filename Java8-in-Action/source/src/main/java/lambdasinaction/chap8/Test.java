package lambdasinaction.chap8;



import lambdasinaction.chap6.Dish;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;


public class Test {
    public static void main(String[] args) {

        //8.1.2
        //익명클래스
//        Runnable r1 = new Runnable(){
//            public void run(){
//                System.out.println("Hello");
//            }
//        };
//
//        r1.run();

        //람다 표현식
//        Runnable r2 = () -> System.out.println("Hello2");
//
//        r2.run();

        //컴파일 에러
//        int a = 10;
//        Runnable  r3 = () -> {
//            int a = 2;
//            System.out.println(a);
//        };
//        r3.run();

        //정상 작동
//        Runnable r4 = new Runnable() {
//            @Override
//            public void run() {
//                int a = 2;
//                System.out.println(a);
//            }
//        };
//        r4.run();

//        doSomething(new Task() {
//            @Override
//            public void execute() {
//                System.out.println("Danger danger!!");
//            }
//        });

//        doSomething(() -> System.out.println("Danger danger!!"));

//        doSomething((Task)() -> System.out.println("Danger danger!!"));




        //8.1.3
//        System.out.println(dishesByCaloricLevel());


    }


    //8.1.2
    /*interface Task{
        public void execute();
    }

    public static void doSomething(Runnable r){ r.run();}
    public static void doSomething(Task r){ r.execute();}
    */



    //8.1.3
    enum CaloricLevel { DIET, NORMAL, FAT };
//    private static Map<CaloricLevel, List<Dish>> dishesByCaloricLevel() {
//        return lambdasinaction.chap6.Dish.menu.stream().collect(
//                groupingBy(dish -> {
//                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
//                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
//                    else return CaloricLevel.FAT;
//                }));
//    }

//    private static Map<CaloricLevel, List<Dish>> dishesByCaloricLevel()=
//        lambdasinaction.chap6.Dish.menu.str
}
