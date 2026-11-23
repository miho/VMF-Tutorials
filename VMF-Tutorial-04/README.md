# Tutorial 04 · Undo and redo on a live model

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-03b/README.md) · [Next](../VMF-Tutorial-05/README.md)

Record a rename and a child insertion as one edit. Both changes are then available for undo on the same working model. [Model definition](src/main/vmf/eu/mihosoft/vmf/tutorial04/vmfmodel/Node.java) · [Java example](src/main/java/eu/mihosoft/vmf/tutorial04/Main.java)

## Record and reverse an edit

```java
var changes = root.vmf().changes();
changes.start();
changes.startTransaction();
root.setName("Published");
root.getChildren().add(child);
changes.publishTransaction();
changes.stop();

var edit = changes.transactions().get(0);
if (edit.isUndoable()) {
    edit.undo();
}
```

The setters and list operations edit the live model immediately. Publishing groups the already-recorded changes. Stop recording before undo or redo so these operations do not add to this recording. Undo applies the changes in reverse order and checks the live values or list positions. A later write can make the recorded edit ineligible for undo.

## Reapply the changes

After undo, iterate over the recorded changes in their original order. Reapplying to `change.object()` uses the affected live object and preserves the identity of the inserted child.

```java
for (Change change : edit.changes()) {
    change.apply(change.object());
}
```

To save edits and replay them against an independent checkpoint, see [Portable changes](../VMF-Tutorial-17/README.md).

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`.

```text
After the edit: Published (children: 1)
After undo: Draft (children: 0)
After redo: Published (children: 1)
Same child instance: true
Undo allowed after a later edit: false
```
