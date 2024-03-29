package hash;

/**
 * @author j1997
 * @since 2021/8/24
 */
public class Student {

    private String name;
    private String sex;
    private int age;

    public Student() {

    }

    public Student(String name, String sex, int age) {
        super();
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", sex=" + sex + ", age=" + age + "]";
    }


}