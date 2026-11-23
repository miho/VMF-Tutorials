# Tutorial 03 · Containment references

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-02/README.md) · [Next](../VMF-Tutorial-03b/README.md)

Containment establishes ownership. A model object can belong to one container at a time, and moving it preserves its identity. [Model definition](src/main/vmf/eu/mihosoft/vmf/tutorial03/vmfmodel/Parent.java) · [Java example](src/main/java/eu/mihosoft/vmf/tutorial03/Main.java)

## Attach and move a child

Declare the owning property with `@Contains` and the back-reference with `@Container`. Opposite names are model property names such as `parent` and `child`.

```java
interface Parent {
    @Contains(opposite = "parent")
    Child getChild();

    String getName();
}

interface Child {
    @Container(opposite = "child")
    Parent getParent();

    int getValue();
}
```

Calling `firstParent.setChild(child)` makes `child.getParent()` return `firstParent`. Calling `secondParent.setChild(child)` then clears the original parent's child and sets both ends of the new link. Read the completed links after the setter returns.

The example subscribes to `value` with `Changes.Scope.RECURSIVE` on the first parent. Its callback sees the change to `42` while the child is contained. The later change to `43` belongs to the second parent's subtree.

## Contain a list of children

Declare a list with `TreeNode[] getChildren()` in the model definition. VMF generates a mutable list getter. Adding a child through that list also sets the child's `parent` link.

```java
TreeNode root = TreeNode.newBuilder().withName("Root").build();
TreeNode leaf = TreeNode.newBuilder().withName("Leaf").build();
root.getChildren().add(leaf);
```

The example attempts to add `root` under `leaf` and catches the `IllegalArgumentException` for the containment cycle. For a model with multiple possible containing properties, a `@Container` getter without a named opposite is read-only. A `@Contains` property can also own children without exposing a back-reference in the model.

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`.

```text
Initial parent: Parent 1
Observed child value: 0 -> 42
Original parent empty: true
New parent: Parent 2
Same child instance: true
Child value after the move: 43
List child's parent: Root
Containment cycle rejected
```
