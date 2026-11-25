# Tutorial 18 · Multiplicity, model validation, lists and sets

**VMF 0.4.1 and later.** The example uses the matching `0.4.1-SNAPSHOT` builds of VMF and VMF Jackson. [Version guide](../README.md#vmf-versions)

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

The example creates two stations named “Inspection” that compare equal by content. The ordered relationship keeps both. Adding the first station again returns `false`. Calling `move(1, 0)` changes their order, and `remove(second)` detaches exactly the second station. The unordered inspection group uses the same membership rule. Both kinds reject null.

Calling `add("measure")` a second time returns `false` and produces no change event. The example subscribes with `change.setChange()` and reads the added members.

## Check the model's declarations

The workshop starts empty so you can assemble it in several edits. Call `workshop.vmf().content().validate()` when you want to check the workshop and its contained stations. The default `ValidationReport` includes multiplicity and declared scalar constraints. Use `isValid()` to check for errors, `getIssues()` to inspect diagnostics, and `getMultiplicityViolations()` to select cardinality violations.

```java
@Multiplicity(lower = 1)
@Length(min = 2, max = 24)
@Pattern("^[A-Z][A-Za-z ]*$")
String getName();

@Range(min = "1", max = "100")
int getPowerWatts();
```

An empty workshop reports a missing station at `/stations`. After inserting the first station, the example gives it the name `"!"` and power `0`. One validation call reports issues for length, pattern, and range in the contained station.

## Add a workshop rule

A workshop that advertises `measure` needs a `calibrate` setup step. Build a validator with a rule for `Workshop` and report the issue on the property the caller can fix.

```java
var validator = ModelValidator.builder()
        .withRule(Workshop.class, (workshop, context) -> {
            if (workshop.getCapabilities().contains("measure")
                    && !workshop.getSetupSteps().contains("calibrate")) {
                context.report(ValidationIssue.Severity.ERROR, "workshop.calibration", "setupSteps",
                        "A measuring workshop needs a calibration step");
            }
        })
        .build();
```

The workshop passes `workshop.vmf().content().validate()` once the station is repaired. Calling `validator.validate(workshop)` then reports `workshop.calibration` at `/setupSteps`. The configured validator also runs all the declared checks.

## Validate a JSON import and generate a schema

Pass the same validator to `VMFJacksonModule.withModelValidator(validator)` to check the completed model on import. The example enables `withReferences(true)` to preserve the workshop's inspection-station reference. The invalid import throws `VMFValidationException` with the calibration issue in `getReport()`.

Generate a Draft-07 document schema with `VMFJsonSchemaGenerator`. It projects the portable getter constraints. For the calibration rule, the example calls `withTypeConstraint(Workshop.class, assertion)` with a separate `if` / `then` assertion. It uses the serialized field names `capabilities` and `setupSteps` and checks whether their arrays contain `measure` and `calibrate`. The program runs a JSON Schema validator on both documents. The uncalibrated document fails, and the updated document passes both JSON Schema validation and VMF validation on import.

## Run the example

With JDK 21, open `VMF-Tutorial-18` and run `./gradlew run` on macOS or Linux, or `.\gradlew.bat run` on Windows. Add `--refresh-dependencies` if Gradle has cached an older snapshot, or `-PvmfLocal` to use your own build from Maven Local. [Runnable example](src/main/java/eu/mihosoft/vmf/tutorial18/Main.java)
