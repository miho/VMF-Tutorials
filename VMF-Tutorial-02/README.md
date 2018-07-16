# VMF Tutorial 2

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/edit/master/VMF-Tutorial-03/README.md)

## Using the Change Notification API

### What you will learn

In this tutorial you will learn how to

- use the change notification API to listen to property changes

### Defining the Model

For this tutorial we reuse the model from [Tutorial 1](https://github.com/miho/VMF-Tutorials/edit/master/VMF-Tutorial-01/README.md)

```java
package eu.mihosoft.vmf.tutorial02.vmfmodel;

interface Parent {
    String getName();
}
```

### Listening to Changes

First, we create a new instance of `Parent`. Now we can register a change listener. Notice, how all VMF related API can be accessed via the `vmf()` method. Here's the code:

```java
package eu.mihosoft.vmf.tutorial02;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create a new parent instance
        Parent parent = Parent.newInstance();

        // register change listener
        parent.vmf().changes().addListener(
                (evt)-> {
                    System.out.println("evt: " + evt.propertyName());

                    if(evt.propertyChange().isPresent()) {
                        System.out.println("  -> oldValue: " + evt.propertyChange().get().oldValue());
                        System.out.println("  -> newValue: " + evt.propertyChange().get().newValue());
                    } else if (evt.listChange().isPresent()) {
                        System.out.println("  -> " + evt.listChange().get().toStringWithDetails());
                    }
                }
        );

        // cause a change by setting the name of parent
        parent.setName("Parent 1");

        // cause another  change
        parent.setName("Parent 2");
        
    }
}
```

After registering the listener, we can make some changes to the name property (see `parent.setName(...)` calls). The output will look like this:

```
> Task :run
evt: name
  -> oldValue: null
  -> newValue: Parent 1
evt: name
  -> oldValue: Parent 1
  -> newValue: Parent 2
```

This is exactly what we expected. The first change indicates that property `name` was previously `null` and is now set to "Parent 1". The second change indicates that the `name` property was previously set to "Parent 1" and is now set to "Parent 2".

Congrats, you have learned how to use the change notification API.

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/edit/master/VMF-Tutorial-03/README.md)



