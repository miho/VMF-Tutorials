# Tutorial 15 · Use an external Java type

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-14/README.md) · [Next](../VMF-Tutorial-16/README.md)

Declare an external Java type in a model with `@ExternalType`. This lesson uses a VVecMath vector as the location in a [model object](src/main/vmf/eu/mihosoft/vmf/tutorial15/vmfmodel/ModelWithExternalTypes.java).

```java
@ExternalType(pkgName = "eu.mihosoft.vvecmath")
interface Vector3d {
}

interface MyModel {
    Vector3d getLocation();
}
```

The empty interface gives the model definition a local type name. VMF uses `pkgName` and that name to put `eu.mihosoft.vvecmath.Vector3d` into the generated getter, setter, and builder signatures. That lets the model definition compile with VMF's annotations while the application's normal dependency supplies the real vector class to the generated code and at runtime.

The [example](src/main/java/eu/mihosoft/vmf/tutorial15/Main.java) uses the library directly to construct the value.

```java
MyModel model = MyModel.newInstance();
model.setLocation(Vector3d.xyz(1, 2, 3));
Vector3d stored = model.getLocation();
```

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`.

```text
Location: 1.0, 2.0, 3.0
```
