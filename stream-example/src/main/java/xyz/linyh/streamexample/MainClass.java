package xyz.linyh.streamexample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainClass {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person {
        private String name;
        private String sex;
        private int age;
    }

    public static void main(String[] args) {
        List<Person> peoples = List.of(new Person("小 张", "男", 16), new Person("张 三", "男", 18), new Person("李 四", "女", 20), new Person("王 五", "女", 28), new Person("赵 四", "男", 30));
//        流在处理数据的时候，每一个元素都会经过所有的操作一遍后，再继续执行接下来的元素
        Map<Object, List<Person>> newPeoples = peoples.stream()
                .filter(p -> p.getAge() >= 18) // 进行过滤操作
                .peek(p -> System.out.println("peek:" + p)) // 在流式操作的时候进行打印
                .collect(Collectors.groupingBy(Person::getSex)); //groupBy 操作
        System.out.println(newPeoples);
        System.out.println("=========================================");

        // 取出所有大于18岁的所有数据，然后将person数据转换为一个String的map，然后将其中每一个map按照空格分隔开形成一个新的流，然后将里面所有的元素进行去重
        List<String> list = peoples.stream()
                .parallel() //开启并发处理
                .filter(p -> p.getAge() > 18)  //过滤出所有大于18岁的数据
                .peek(p -> System.out.println("peek1:" + p))
                .map(Person::getName) // 将原来的person数据 转换为String的数据集合
                .peek(s -> System.out.println("peek2:" + s))
                .flatMap(p -> Arrays.stream(p.split(" ")))//将里面的每一个流元素，按照空格拆开为2个流，然后替换原来的流
                .peek(s -> System.out.println("peek3:" + s))
                .distinct()  //去除相同的流元素
                .peek(s -> System.out.println("peek4:" + s))
                .toList();
        System.out.println(list);

        System.out.println("=================filter和takeWhile区别========================");
        var integers = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        integers.stream()
//                .filter(i->i>=2)
                .filter(i -> i < 2)
                .forEach(System.out::println);
        System.out.println("---------------------------");

        integers.stream()
//                .takeWhile(i->i>=2)   //他会在处理到如果不符合要求的地方，就停止流的处理，直接结束
                .takeWhile(i -> i < 2)   //他会在处理到如果不符合要求的地方，就停止流的处理，直接结束
                .forEach(System.out::println);


//                .toList();
//                .forEach(System.out::println);
//        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        integers.stream()
//                .filter(i -> i>=2)
//                .peek(i-> System.out.println("peek:"+i))
//                .forEach(i -> System.out.println("forEach:"+i));
    }
}
