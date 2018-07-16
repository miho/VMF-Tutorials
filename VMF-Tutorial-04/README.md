# VMF Tutorial 4

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-05/README.md)

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

### The Code

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

Okay, now it's time to clone the `root` instance so we can check later whether undoing changes has an effect:

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

To check how many changes were recorded, we just access the collection that holds all changes:

```java
// show number of changes
System.out.println("#changes: " + root.vmf().changes().all().size()+"\n");
```

To undo the changes we just have to revert the order of the change list

```java
// invert change order ...
List<Change> changesToRevert = new ArrayList<>(root.vmf().changes().all());
Collections.reverse(changesToRevert);
```
and undo the changes

```java
// ... and undo all changes
changesToRevert.stream().forEach(c->{
    System.out.println("-------- undo change: --------");c.undo();}
);
```
The change listener will recognize the undo actions as changes as well. So watch the output for those changes.

The `root` object should be empty. The `rootClone`, however, should contain all changes. Here's how to check that: 

```java
// after undo we compare the clone and the empty root (they are not equal)
// we expect the root to be empty (all changes are reverted)
System.out.println("--------");
System.out.println("root eq rootClone: " + Objects.equals(root,rootClone));
System.out.println(" -> root:          " + root);
System.out.println(" -> rootClone:     " + rootClone);
```

Congrats, you have successfully used the VMF undo/redo API. As allways, if you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-05).

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-05/README.md)




