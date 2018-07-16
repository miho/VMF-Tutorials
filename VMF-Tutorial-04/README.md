# VMF Tutorial 4

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/edit/master/VMF-Tutorial-05/README.md)

# TBD

## Undo/Redo

### What you will learn

In this tutorial you will learn how to

- clone an object graph
- use the undo/redo API

### The Model

We use a model that declares a simple tree structure:

```java
package eu.mihosoft.vmf.tutorial04.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface Node {

    String getName();

    @Contains(opposite = "parent")
    Node[] getChildren();

    @Container(opposite = "children")
    Node getParent();

}
```

First we create a `Node` instance and enable change recording for using the undo/redo API:

```java
// create a new parent instance
Node root = Node.newInstance();

// start change recorder for undo
root.vmf().changes().start();
```

As always, we can add a changeListener:

```java
// register change listener
root.vmf().changes().addListener(
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
```

Now we add a child node and introduce some changes:

```java
// cause a change by setting the name of parent
root.setName("#1");

// create a new child
Node child1 = Node.newInstance();

// add the child to the parent
root.getChildren().add(child1);

// cause a change by setting the value property of child 1
child1.setName("#2");
```

Okay, now it's time to clone the `root` instance so we can check whether undoing changes has an effect:

```java
// create a deep clone of root
Node rootClone = root.vmf().content().deepCopy();
```

We can check whether cloning was successful. `root`and `rootClone` should be equal but not identical:

```java
// ensure that rootClone and parent are equal ...
System.out.println("root eq rootClone: " + Objects.equals(root,rootClone));

// ... but not identical
System.out.println("root != rootclone: " + (root!=rootClone));

// use automatically generated toString() method
System.out.println(" -> root:      " + root);
System.out.println(" -> rootClone: " + rootClone);
```

### Running the Code Generator

After we created our first model definition we are ready to run the code generator via the `vmfGenModelSource`task, e.g. via

```
./gradlew vmfGenModelSources
```

VMF should show the following output:

```
> Task :vmfGenModelSources
 -> generating code for vmf model in package: eu/mihosoft/vmf/tutorial01/vmfmodel
```

### Using the Code

To use the code just use the generated code from your regular Java code, e.g, in `src/main/java`:

```java
package eu.mihosoft.vmf.tutorial02;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create a new parent instance
        Parent parent = Parent.newInstance();
        
        // set parent's name
        parent.setName("My Name");
        
        // check that name is set
        if("My Name".equals(parent.getName())) {
          System.out.println("name is correctly set");
        } else {
          System.out.println("something went wrong :(");
        }
        
    }
}
```

Congrats, you have successfully created your first VMF model.

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/edit/master/VMF-Tutorial-02/README.md)



