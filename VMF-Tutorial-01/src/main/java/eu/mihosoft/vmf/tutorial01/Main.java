package eu.mihosoft.vmf.tutorial01;

public class Main {
    public static void main(String[] args) {
        Parent parent = Parent.newInstance();
        parent.setName("My Name");
        System.out.println("Parent name: " + parent.getName());
    }
}
