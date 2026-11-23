package eu.mihosoft.vmf.tutorial07;

public class Main {
    public static void main(String[] args) {
        ImmutableObject immutable = ImmutableObject.newBuilder().withValue(12).build();
        System.out.println("Immutable value: " + immutable.getValue());

        MutableObject original = MutableObject.newBuilder().withValue(12).build();
        ReadOnlyMutableObject view = original.asReadOnly();
        original.setValue(24);
        System.out.println("Live view after editing original: " + view.getValue());

        MutableObject copy = view.asModifiable();
        copy.setValue(36);
        System.out.println("Original through view: " + view.getValue());
        System.out.println("Independent copy: " + copy.getValue());

        WithName immutableName = ImmutableNamed.newBuilder().withName("immutable").build();
        WithName mutableName = MutableNamed.newBuilder().withName("mutable").build();
        System.out.println("Shared interface: " + immutableName.getName() + ", " + mutableName.getName());
    }
}
