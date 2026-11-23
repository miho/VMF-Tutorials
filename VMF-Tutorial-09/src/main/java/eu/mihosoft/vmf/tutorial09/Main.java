package eu.mihosoft.vmf.tutorial09;

public class Main {
    public static void main(String[] args) {
        ObjectWithDefaultValues object = ObjectWithDefaultValues.newInstance();
        var reflection = object.vmf().reflect();
        var value = reflection.propertyByName("value").orElseThrow();
        var name = reflection.propertyByName("name").orElseThrow();

        System.out.printf("Initial value = %s, isSet = %s%n", object.getValue(), value.isSet());
        System.out.printf("Initial name = %s, isSet = %s%n", object.getName(), name.isSet());

        object.setName("another name");
        System.out.printf("After set: name = %s, isSet = %s%n", object.getName(), name.isSet());

        name.unset();
        System.out.printf("After unset: name = %s, isSet = %s%n", object.getName(), name.isSet());
    }
}
