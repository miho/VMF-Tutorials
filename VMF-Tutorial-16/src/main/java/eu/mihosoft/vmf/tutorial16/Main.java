package eu.mihosoft.vmf.tutorial16;

public final class Main {
    public static void main(String[] args) {
        Greeting greeting = Greeting.newBuilder().withMessage("Hello from Maven!").build();
        System.out.println(greeting.getMessage());
    }
}
