# VMF Tutorial 3

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/edit/master/VMF-Tutorial-02/README.md)

## Containment References

### What you will learn

In this tutorial you will learn how to

- declare containment references

### Declaring Containment References

For this tutorial we use an updated version of the model definition. Instead of just a single interface, we use a `Parent` and a `Child` interface and declare a containment reference.

```java
package eu.mihosoft.vmf.tutorial03.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface Parent {

    @Contains(opposite = "parent")
    Child getChild();

    String getName();
}

interface Child {
    @Container(opposite="child")
    Parent getParent();

    int getValue();
}
```

In the above code sample the `Parent` interface declares that it contains a `Child` object. Thus, we use the `@Contains()` annotation and declare the `parent` property of the `Child` interface as the opposite. In the `Child` interface we use the `@Container` annotation to indicate that the containing property in `Parent` is the `child` property.

Now let's instantiate a `Parent`, a `Child` and add the child to the parent.

```java
Parent parent = Parent.newInstance();

parent.setName("Parent 1");

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

Child child1 = Child.newInstance();

// add the child to the parent
parent.setChild(child1);
```

Now we can check the `getParent()` method, i.e. the `parent`property:

```java
// containment references make it possible: the child automatically knows its parent
System.out.println("my parent: " + child1.getParent().getName());
```

If we make changes to `child1` we will get notified by the change listener even though we didn't add it to `child1` explicitly. 

```java
// cause a change by setting the value property of child 1
// child 1 is implicitly observed
child1.setValue(42);
```



Congrats, you have successfully created your first VMF model.

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/edit/master/VMF-Tutorial-02/README.md)



