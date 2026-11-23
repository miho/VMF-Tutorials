# Tutorial 14 · Document a model

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-13/README.md) · [Next](../VMF-Tutorial-15/README.md)

Use `@Doc` to carry domain documentation from the model definition into the generated Java API. Put it on a model interface to describe the type or on a getter to describe a property.

```java
@Doc("Represents a finite state machine.")
interface FSM {
    @Doc("The name of this state machine.")
    String getName();

    @Doc("The states owned by this state machine.")
    @Contains(opposite = "owningFSM")
    State[] getStates();
}
```

In the [full model](src/main/vmf/eu/mihosoft/vmf/tutorial14/vmfmodel/DocumentedModel.java), the state machine owns its states and transitions. A transition refers to its source and target states. Each transition has one owner even though it connects two states.

## Run the lesson

With JDK 21, run `./gradlew run javadoc` from this directory. On Windows, use `.\gradlew.bat run javadoc`.

The [example](src/main/java/eu/mihosoft/vmf/tutorial14/Main.java) prints the transition.

```text
Light switch: Off -> On (Press switch)
```

Open `build/docs/javadoc/index.html` and choose `FSM` or `FSM.Builder` to see the generated documentation. You can also inspect the generated Java sources under `build/generated-src/vmf-main`.

VMF 0.4.0 emits some invalid cross-references in generated Javadoc. This tutorial relaxes Javadoc's doclint checks so it can generate the HTML and surface those references as warnings.
