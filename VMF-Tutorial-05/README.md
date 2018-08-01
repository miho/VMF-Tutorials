# VMF Tutorial 5

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-06/README.md)

## Using the Builder API

### What you will learn

In this tutorial you will learn how to

- create instances with the Builder API
- copy state via the Builder API

### Instantiating Objects

for this tutorial we are going to use the following model:

```java
package eu.mihosoft.vmf.tutorial05.vmfmodel;

interface WithName {
    String getName();
}

interface WithId {
    int getId();
}

interface Node extends WithName, WithId {}
```
Instead of just instantiating objects via `newInstance()` we can create builder, set the desired properties and build the instance:

```java
// create a new node instance
Node node1 = Node.newBuilder().
               withName("my node"). // set the name
               withId(3).           // set the id
               build();             // create the instance
```

This is a convenient and compact way to declare properties before the actual instantiation. But there are even more applications of the builder API. It can selectively apply state (properties) defined in a class we inherit from to our instance. In this example, we could apply state from `node1` that is defined in `WithName` and apply it to another `Node` instance `node2`:

```java 
// instantiate another node
Node node2 = Node.newInstance();

// reference properties defined in 'WithName' from 'node1'
// and apply it to 'node2':
// - using the builder of a class we inherit from allows to selectively
//   apply state defined in this class to another instance
WithName.newBuilder().applyFrom(node1).applyTo(node2);

// check that the 'name' property of 'node2' is equal to 'node1'
System.out.println("> node1.name == node2.name: " + node1.getName().equals(node2.getName()));
```

Congrats, you have successfully used the Builder API to instantiate your domain objects.

As always, if you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-05). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-06/README.md)



