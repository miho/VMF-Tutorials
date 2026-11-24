# Tutorial 18 · Multiplicity, lists and sets

A workshop needs one to four stations. Its setup steps have an order and each step occurs once. A capability such as `"measure"` can be advertised once, in any order.

```java
@Contains(opposite = "workshop")
@Multiplicity(lower = 1, upper = 4, kind = Multiplicity.Kind.ORDERED_SET)
Station[] getStations();

Set<Station> getInspectionStations();

@Multiplicity(kind = Multiplicity.Kind.ORDERED_SET)
List<String> getSetupSteps();

Set<String> getCapabilities();
```

Build the graph as usual. Call `workshop.vmf().content().validate()` once it is ready to use. The returned list identifies each object and property outside its bounds, including contained objects. This lets you create a workshop before attaching its first station.

The example creates two stations named “Inspection” that compare equal by content. The ordered relationship keeps both. Adding the first station again returns `false`. Calling `move(1, 0)` changes their order, and `remove(second)` detaches exactly the second station. The unordered inspection group uses the same membership rule. Both kinds reject null.

Calling `add("measure")` a second time returns `false` and produces no change event. The example subscribes with `change.setChange()` and reads the added members.

Run `./gradlew run` (Windows: `.\gradlew.bat run`). This lesson uses the local VMF 0.4.1-SNAPSHOT feature build and VCollections 0.3.7-SNAPSHOT. Install the matching VCollections and VMF projects to Maven Local, in that order, with `./gradlew publishToMavenLocal` in each repository.
