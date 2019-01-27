# VMF Tutorial 6

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-07/README.md)

## Object Graph Traversal & Custom Property Order

### What you will learn

In this tutorial you will learn how to

- traverse object graphs via streams
- declare a property order that defines the order in which properties appear in the stream

### Declaring the Property Order

The model we are going to use consists of a `Node` entity with five properties. With the `@PropertyOrder(index = 0)` annotation we can define the index of each property, i.e., the order in which properties are processed. Here's the code of the full model:

```java
package eu.mihosoft.vmf.tutorial06.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface Node {

    @PropertyOrder(index = 0)
    String getName();

    @PropertyOrder(index = 1)
    Boolean getVisible();

    @PropertyOrder(index = 4)
    Node getChild1();
    @PropertyOrder(index = 3)
    Node getChild2();
    @PropertyOrder(index = 2)
    Node getChild3();
}
```

### Setting up the Object Graph

To set up the object graph, we create a root node and three children which we asssign after creating all four instances:

```java
// create a new Node instance (this is our root)
Node root = Node.newBuilder().withName("root").withVisible(true).build();

// create two children
Node child1 = Node.newBuilder().withName("child 1").withVisible(true).build();
Node child2 = Node.newBuilder().withName("child 2").withVisible(true).build();
Node child3 = Node.newBuilder().withName("child 3").withVisible(true).build();
        
// and add them to the root node
root.setChild1(child1);
root.setChild2(child2);
root.setChild3(child3);
```

Now we can traverse the object graph. As with all VMF related functionality, we do this by using the `vmf()` API. More specifically, we obtain a stream via `root.vmf().content().stream(Node.class)` which is equivalent to `stream().filter(e->type.isAssignableFrom(e.getClass())).map(e->(T)e)`. Printing the traversed node names can be done via

```java
root.vmf().content().stream(Node.class).forEach(
    (node)-> System.out.println("-> node: " + node.getName())
);
```

Usually, the expected output would be:

```
// -> node: root
// -> node: child 1
// -> node: child 2
// -> node: child 3
```

But with the custom property order, we get the following result:

```
// -> node: root
// -> node: child 3
// -> node: child 2
// -> node: child 1
```

Note, that only model-type properties are visited. That is why the `name` and `visible` properties are omitted. The same applies to collection properties. If the element type is a model-type, all elements are visited. The collection is omitted otherwise. The method `node.vmf().reflect().properties()` gives access to the complete list of properties of a node object. 

### Filter Nodes

We can easily declare nodes as invisible and use `filter()` to skip them during the object graph traversal:

```java
// -> child 2 should be invisible
child2.setVisible(false);

// we use the simple predicate `filter(Node::isVisible)` to filter invisible instances
root.vmf().content().stream(Node.class).filter(Node::isVisible).forEach(
    (node)-> System.out.println("-> node: " + node.getName())
);
```

This should give us the following output:

```
// we should see the following output:
// -> node: root
// -> node: child 3
// -> node: child 1
```

## Conclusion

Congrats, you have successfully declared a custom property order and used streams to traverse object graphs.  

If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-06). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).


[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-07/README.md)



