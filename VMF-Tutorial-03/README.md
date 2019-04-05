# VMF Tutorial 3

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-03b/README.md)

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

Since a containment reference cannot be shared between multiple containers, adding the `child1` instance to another `Parent` should remove `child1`from `parent`. Let's try this:

```java
// now we create a second parent
Parent parent2 = Parent.newInstance();
parent2.setName("Parent 2");

// adding child 1 to parent2 has several interesting effects
// 1. child1 is removed from parent1 (check change notification output)
// 2. parent of child1 is now parent2
parent2.setChild(child1);
```
If we check the output from the change listener we can clearly see that `parent.getChild()` has been updated automatically. Instead of `child1` it returns `null` since `child1` belongs to `parent2` now.

`child1.getParent()` should now return `Parent 2` as its parent:

```java
// containment references make it possible: the child automatically knows its new parent
System.out.println("my new parent: " + child1.getParent().getName());
```

## Conclusion

Congrats, you have successfully declared your first containment reference.  

If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-03). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-03b/README.md)



