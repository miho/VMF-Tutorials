# Tutorial 08 · Custom behavior and delegation

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-07/README.md) · [Next](../VMF-Tutorial-09/README.md)

Declare custom methods in the model and implement them in an ordinary Java delegate. Declare properties as getters and give custom methods names distinct from the generated accessors. [Model](src/main/vmf/eu/mihosoft/vmf/tutorial08/vmfmodel/CustomBehavior.java) · [Java delegate](src/main/java/eu/mihosoft/vmf/tutorial08/CustomBehavior.java)

## Declare a delegated method

```java
@DelegateTo(className = "eu.mihosoft.vmf.tutorial08.CustomBehavior")
interface ObjectWithCustomBehavior {
    int getA();
    int getB();

    @DelegateTo(className = "eu.mihosoft.vmf.tutorial08.CustomBehavior")
    int computeSum();
}
```

The method annotation sends `computeSum()` calls to the Java delegate. Implement `DelegatedBehavior<ObjectWithCustomBehavior>` to receive the model object in `setCaller()`.

```java
public class CustomBehavior implements DelegatedBehavior<ObjectWithCustomBehavior> {
    private ObjectWithCustomBehavior caller;

    @Override
    public void setCaller(ObjectWithCustomBehavior caller) {
        this.caller = caller;
    }

    public int computeSum() {
        return caller.getA() + caller.getB();
    }

    public void onObjectWithCustomBehaviorInstantiated() {
        System.out.println("Object instantiated");
    }
}
```

The annotation on the type also runs the instantiation hook. VMF looks for a no-argument method named `on<TypeName>Instantiated()` and calls it after supplying the caller.

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`. The example creates the object, sets `a` and `b` to 2 and 3, and calls `computeSum()`. [Runnable example](src/main/java/eu/mihosoft/vmf/tutorial08/Main.java)

```text
Object instantiated
Sum: 5
```
