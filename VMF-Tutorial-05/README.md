# VMF Tutorial 1

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/edit/master/VMF-Tutorial-02/README.md)

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


Congrats, you have successfully created your first VMF model.

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/edit/master/VMF-Tutorial-02/README.md)



