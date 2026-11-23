# Tutorial 06 · Graph traversal and property order

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-05/README.md) · [Next](../VMF-Tutorial-07/README.md)

The model assigns traversal positions with `@PropertyOrder`. The example uses that order to visit and filter nodes. [Model](src/main/vmf/eu/mihosoft/vmf/tutorial06/vmfmodel/Node.java) · [Runnable example](src/main/java/eu/mihosoft/vmf/tutorial06/Main.java)

## Define the order

When you specify a custom order, annotate every property of the type with a unique index. Lower indices are processed first. The `Node` declarations place `child3` before `child2` and `child1`.

```java
interface Node {
    @PropertyOrder(index = 0) String getName();
    @PropertyOrder(index = 1) boolean isVisible();
    @PropertyOrder(index = 4) Node getChild1();
    @PropertyOrder(index = 3) Node getChild2();
    @PropertyOrder(index = 2) Node getChild3();
}
```

## Traverse and filter

```java
root.vmf().content().stream(Node.class)
        .forEach(node -> System.out.println(node.getName()));
```

The stream starts with `root` and follows the model references in order, printing `child 3`, `child 2`, and `child 1`. Lists of model objects are also traversed. Use `root.vmf().reflect().properties()` to inspect all five properties in the same declared order.

After `child2.setVisible(false)`, insert `.filter(Node::isVisible)` before `forEach` to omit that node from the output. The filter applies to the stream's results, so traversal can still reach descendants of a filtered node.

## Choose a strategy

- `UNIQUE_NODE` visits each object instance once and is the default.
- `UNIQUE_PROPERTY` walks each node's properties once and can return the same object through different references.
- `CONTAINMENT_TREE` follows only containment edges. This model uses ordinary references, so the containment traversal prints just `root`.

Pass a strategy to `content().iterator(strategy)` or `content().stream(Node.class, strategy)`.

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`.

```text
Object graph
root
child 3
child 2
child 1

Root properties
name
visible
child3
child2
child1

Visible nodes
root
child 3
child 1

Containment tree
root
```
