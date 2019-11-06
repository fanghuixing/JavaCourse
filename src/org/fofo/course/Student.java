package org.fofo.course;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Student {
    private String name = "HuGe";
    public Student(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }

    private void modify(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Student stu1 = new Student("李世民");
        Student stu2 = new Student("李渊");
        Class CO_Stu2 = stu2.getClass();
        Class CO_Stu1 = stu1.getClass();
        //看看两个Class对象是否相同
        System.out.println(CO_Stu1 == CO_Stu2 ? "CO_Stu1 = CO_Stu2" : "CO_Stu1 != CO_Stu2");
        //看看Class对象的名字，与Student对象的名字比较一下，分别输出什么？
        System.out.println(CO_Stu1.getName() + " " + stu1.getName());
        System.out.println(CO_Stu2.getName() + " " + stu2.getName());

        //通过类名得到类对象
        String className = "org.fofo.course.Student";
        Class newClassObject = Class.forName(className);
        System.out.println(newClassObject.getCanonicalName());

        Class intClassObject = int.class;
        Class intArrayClassObject = int[].class;
        System.out.println(intClassObject.getCanonicalName());
        System.out.println(intArrayClassObject.getCanonicalName());


        //创建新对象
        Constructor cs = Class.forName("org.fofo.course.Student").getConstructor(String.class);
        Object bao =  cs.newInstance("包拯");


    }
}
