package eu.mihosoft.vmf.tutorial11;

public class Main {
    public static void main(String[] args) {
        Node node = Node.newBuilder()
                .withId("node:n")
                .withA(Node.newBuilder().withId("node:a").build())
                .withB(Node.newBuilder().withId("node:b").build())
                .withC(Node.newBuilder().withId("node:c").build())
                .build();

        printPorts(node, "input");
        printPorts(node, "output");
    }

    private static void printPorts(Node node, String role) {
        node.vmf().reflect().properties().stream()
                .filter(property -> property.annotationByKey("api")
                        .map(annotation -> role.equals(annotation.getValue()))
                        .orElse(false))
                .forEach(property -> {
                    Node target = (Node) property.get();
                    System.out.printf("%s %s -> %s%n", role, property.getName(), target.getId());
                });
    }
}
