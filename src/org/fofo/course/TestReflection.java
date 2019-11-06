package org.fofo.course;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Student stu = new Student("小馒头");
        //stu.Modify("三毛");//无法调用私有方法

        //获取Student定义的私有方法modify
        Method method = Student.class.getDeclaredMethod("modify", String.class);
        method.setAccessible(true);//设置该方法可以被调用
        method.invoke(stu,"三毛");//执行方法调用
        System.out.println(stu.getName());

        //获取Student定义的私有成员
        Field field = Student.class.getDeclaredField("name");
        field.setAccessible(true);
        field.set(stu, "毛毛");
        System.out.println(stu.getName());
    }
}
