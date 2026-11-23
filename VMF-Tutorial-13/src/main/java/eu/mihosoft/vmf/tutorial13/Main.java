package eu.mihosoft.vmf.tutorial13;

import eu.mihosoft.vmf.runtime.core.Property;
import eu.mihosoft.vmf.runtime.core.Reflect;

public class Main {
    public static void main(String[] args) {
        Reflect type = A.type().reflect();
        System.out.println("Properties of A:");
        for (Property property : type.properties()) {
            System.out.printf("%s: %s (model type: %s)%n", property.getName(),
                    property.getType().getName(), property.getType().isModelType());
        }

        A a = A.newInstance();
        Property name = a.vmf().reflect().propertyByName("name").orElseThrow();
        var listener = name.addChangeListener(change ->
                System.out.println("name changed to " + a.getName()));
        name.set("my new name");
        listener.unsubscribe();

        System.out.println("Supertypes of B:");
        B.type().superTypes().forEach(parent -> System.out.println(parent.getName()));

        String description = type.annotationByKey("my-key").orElseThrow().getValue();
        System.out.println("Annotation on A: " + description);
    }
}
