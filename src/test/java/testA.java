import org.study.entity.Person;

public class testA {

    public static void main(String[] args) {
        Person person=new Person();
        person.setAge(18);
        person.setName("小明");
        person.setSex("男");
        System.out.println(person.getAge());
        System.out.println(person);
    }
}
