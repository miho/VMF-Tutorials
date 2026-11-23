package eu.mihosoft.vmf.tutorial08;

public class Main {
    public static void main(String[] args) {
        ObjectWithCustomBehavior object = ObjectWithCustomBehavior.newInstance();
        object.setA(2);
        object.setB(3);
        System.out.println("Sum: " + object.computeSum());
    }
}
