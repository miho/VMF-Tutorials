package eu.mihosoft.vmf.tutorial18;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import eu.mihosoft.vmf.jackson.VMFJacksonModule;
import eu.mihosoft.vmf.jackson.VMFJsonSchemaGenerator;
import eu.mihosoft.vmf.jackson.VMFValidationException;
import eu.mihosoft.vmf.runtime.core.ModelValidator;
import eu.mihosoft.vmf.runtime.core.ValidationIssue;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Workshop workshop = Workshop.newInstance();
        var empty = workshop.vmf().content().validate();
        if (empty.isValid() || empty.getMultiplicityViolations().size() != 1) {
            throw new AssertionError("An empty workshop needs a station");
        }
        empty.getIssues().forEach(System.out::println);

        var first = Station.newBuilder().withName("Inspection").withPowerWatts(25).build();
        var second = Station.newBuilder().withName("Inspection").withPowerWatts(25).build();
        var stations = workshop.getStations();
        stations.addAll(List.of(first, second));
        if (!first.equals(second) || stations.size() != 2) throw new AssertionError("Both stations belong here");
        if (stations.add(first)) throw new AssertionError("Already present");
        stations.move(1, 0);
        if (stations.get(0) != second || second.getWorkshop() != workshop) throw new AssertionError("Station order");

        workshop.getInspectionStations().add(first);
        stations.remove(second);
        if (stations.get(0) != first || second.getWorkshop() != null) throw new AssertionError("Wrong station removed");
        System.out.println("Inspection station present: " + workshop.getInspectionStations().contains(first));
        workshop.getSetupSteps().add("connect");

        var subscription = workshop.vmf().changes().addListener(change ->
                change.setChange().ifPresent(edit -> System.out.println("Added capabilities " + edit.added())));
        workshop.getCapabilities().add("measure");
        if (workshop.getCapabilities().add("measure")) throw new AssertionError("Repeated capability");
        subscription.unsubscribe();

        demonstrateDeclaredConstraints(workshop, first);
        demonstrateApplicationRule(workshop);
        System.out.println("Setup order: " + workshop.getSetupSteps());
    }

    private static void demonstrateDeclaredConstraints(Workshop workshop, Station station) {
        station.setName("!");
        station.setPowerWatts(0);
        var report = workshop.vmf().content().validate();
        var codes = report.getIssues().stream().map(ValidationIssue::getCode).collect(Collectors.toSet());
        if (report.isValid() || !codes.equals(Set.of("vmf.length", "vmf.pattern", "vmf.range"))) {
            throw new AssertionError(report.getIssues());
        }
        report.getIssues().forEach(System.out::println);
        station.setName("Inspection");
        station.setPowerWatts(25);
    }

    private static void demonstrateApplicationRule(Workshop workshop) throws IOException {
        var validator = ModelValidator.builder()
                .withRule(Workshop.class, (value, context) -> {
                    if (value.getCapabilities().contains("measure") && !value.getSetupSteps().contains("calibrate")) {
                        context.report(ValidationIssue.Severity.ERROR, "workshop.calibration", "setupSteps",
                                "A measuring workshop needs a calibration step");
                    }
                })
                .build();
        var report = validator.validate(workshop);
        if (!workshop.vmf().content().validate().isValid() || report.isValid()) {
            throw new AssertionError("The model meets its declarations and needs a calibration step");
        }
        report.getIssues().forEach(System.out::println);

        var mode = VMFJacksonModule.RUNTIME_TYPE.EXPERIMENTAL;
        var mapper = new ObjectMapper().registerModule(
                VMFJacksonModule.newInstance(mode).withReferences(true).withModelValidator(validator));
        var document = mapper.writeValueAsBytes(workshop);
        try {
            mapper.readValue(document, Workshop.class);
            throw new AssertionError("Expected model validation to reject the import");
        } catch (VMFValidationException exception) {
            if (exception.getReport().getIssues().stream()
                    .noneMatch(issue -> "workshop.calibration".equals(issue.getCode())
                            && "/setupSteps".equals(issue.getPath()))) {
                throw new AssertionError("Expected the calibration issue on /setupSteps", exception);
            }
        }

        var schema = VMFJsonSchemaGenerator.newInstance(mode)
                .withReferences(true)
                .withTypeConstraint(Workshop.class, Map.of(
                        "if", Map.of("required", List.of("capabilities"), "properties",
                                Map.of("capabilities", Map.of("contains", Map.of("const", "measure")))),
                        "then", Map.of("required", List.of("setupSteps"), "properties",
                                Map.of("setupSteps", Map.of("contains", Map.of("const", "calibrate"))))))
                .generateSchemaAsString(Workshop.class);
        var wireValidator = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7)
                .getSchema(mapper.readTree(schema));
        if (wireValidator.validate(mapper.readTree(document)).isEmpty()) {
            throw new AssertionError("Expected the schema to reject the document without a calibration step");
        }

        workshop.getSetupSteps().add("calibrate");
        var calibrated = mapper.writeValueAsBytes(workshop);
        var wireIssues = wireValidator.validate(mapper.readTree(calibrated));
        if (!wireIssues.isEmpty()) throw new AssertionError(wireIssues);
        mapper.readValue(calibrated, Workshop.class);
        System.out.println("The calibrated workshop passed schema validation and model validation on import.");
    }
}
