# VMF Tutorial 11

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-12/README.md)

## Annotation Support

### What you will learn

In this tutorial you will learn how to

- annotate models
- use annotations to query elements

### Annotate Models

For the following experiments, we use the model shown below:

```java
package eu.mihosoft.vmf.tutorial11.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface Node {
package eu.mihosoft.vmf.tutorial11.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface Node {

    String getId();

    @Annotation(key="api",value="input")
    Node getA();

    @Annotation(key="api",value="input")
    Node getB();

    @Annotation(key="api",value="output")
    Node getC();
}
```

The model consists of a `Node` entity with `id` and three additional node 
properties `a, b, c`. These properties are annotated with the `@Annotation`
annotation. Each annotation consists of a key and a value. The key defines the
category of the annotation, while the value contains the annotation information.
This can be a simple string, such as `input` or `output` but could also be more
structured information that can be parsed by an annotation analyzer.
Annotations allow us to define custom semantics, i.e., we can query
an object for logical inputs or outputs (just an example). This might be
relevant for a representative visualization of nodes and their relations to
other nodes.

First, we create four node instances and set the properties `a, b, c`:

```java
// create nodes
Node n = Node.newBuilder().withId("node:0").build();
Node a = Node.newBuilder().withId("node:a").build();
Node b = Node.newBuilder().withId("node:b").build();
Node c = Node.newBuilder().withId("node:c").build();

// set a,b,c
n.setA(a);
n.setB(b);
n.setC(c);
```

### Query Elements by Annotations

As an example we query properties of a node via annotations. To accomplish that,
we create the corresponding predicates which is our custom vocabulary we will
use in combination with the stream API:

```java
// predicate to filter inputs
Predicate<Property> isInput = (p) -> {
    return p.annotationByKey("api").
      map(ann->"input".equals(ann.getValue())).orElse(false);
};
// predicate to filter outputs
Predicate<Property> isOutput = (p) -> {
    return p.annotationByKey("api").
      map(ann->"output".equals(ann.getValue())).orElse(false);
};
```

Now we can easily query inputs and outputs:

```java
// query inputs:
n.vmf().reflect().properties().stream().filter(isInput).forEach(p->{
    System.out.println(
        "-> input  param '" + p.getName() + "' -> node: " + ((Node)p.get()).getId()
    );
});
// query outputs:
n.vmf().reflect().properties().stream().filter(isOutput).forEach(p->{
    System.out.println(
        "-> output param '" + p.getName() + "' -> node: " + ((Node)p.get()).getId()
    );
});
```

The expected output looks like this:

```
// Inputs and Outputs:
// -> input  param 'a' -> node: node:a
// -> input  param 'b' -> node: node:b
// -> output param 'c' -> node: node:c
```

## Conclusion

Congrats, you have successfully annotated properties and used them to query
inputs and outputs. 

If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-11). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).


[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-12/README.md)



