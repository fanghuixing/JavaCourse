package org.fofo.course;

public class Son extends Father{
    String name = "babe";

    public void sleep() {
        System.out.println("爆裂出击！");
    }

    public static void main(String[] args) {
        Father son = new Son();
        son.speak();
        son.sleep();
        System.out.println(son.name);
        son = (Son)son;
        System.out.println(son.name);

    }
}
