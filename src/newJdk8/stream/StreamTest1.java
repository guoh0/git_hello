package newJdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest1 {

    public static void main(String[] args) {
        Person person1 = new Person("zhangsan", 10);
        Person person2 = new Person("lisi", 20);
        Person person3 = new Person("wangwu", 30);
        List<Person> list = Arrays.asList(person1, person2, person3);
        List<Person> list1 = new StreamTest1().getPersonByName("zhangsan", list);
        list1.forEach(person -> System.out.println(person.getName()));
    }

    public List<Person> getPersonByName(String name, List<Person> prsons) {
        //遍历list 并根据条件获取结果 然后放到List里 在返回
        return prsons.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
    }


    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}

