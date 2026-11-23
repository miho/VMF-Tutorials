package eu.mihosoft.vmf.tutorial12;

public class Main {
    public static void main(String[] args) {
        Item first = Item.newBuilder().withId("first").build();
        Item second = Item.newBuilder().withId("second").build();
        Store store = Store.newBuilder().withId("original").withItems(first, second).build();

        Store deepCopy = store.vmf().content().deepCopy();
        Store shallowCopy = store.vmf().content().shallowCopy();
        deepCopy.setId("deep copy");
        shallowCopy.setId("shallow copy");

        deepCopy.getItems().get(0).setId("deep edit");
        if (!"first".equals(first.getId()) || deepCopy.getItems().get(0) == first) {
            throw new IllegalStateException("The deep copy must have an independent first item");
        }
        shallowCopy.getItems().get(0).setId("shared edit");
        if (shallowCopy.getItems().get(0) != first || !"shared edit".equals(first.getId())) {
            throw new IllegalStateException("The shallow copy must reuse the first item");
        }

        System.out.println("Original:");
        System.out.print(store);
        System.out.println("Deep copy:");
        System.out.print(deepCopy);
        System.out.println("Shallow copy:");
        System.out.print(shallowCopy);
    }
}
