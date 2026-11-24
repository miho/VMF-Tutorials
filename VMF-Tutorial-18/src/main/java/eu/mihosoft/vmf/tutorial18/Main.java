package eu.mihosoft.vmf.tutorial18;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Workshop workshop = Workshop.newInstance();
        System.out.println(workshop.vmf().content().validate());
        if (workshop.vmf().content().validate().size() != 1) throw new AssertionError("An empty workshop needs a station");

        workshop.getStations().add(Station.newBuilder().withName("Inspection").build());
        workshop.getSetupSteps().addAll(List.of("connect", "calibrate"));

        var subscription = workshop.vmf().changes().addListener(change ->
                change.setChange().ifPresent(edit -> System.out.println("Added capabilities " + edit.added())));
        workshop.getCapabilities().add("measure");
        if (workshop.getCapabilities().add("measure")) throw new AssertionError("Repeated capability");
        subscription.unsubscribe();

        var issues = workshop.vmf().content().validate();
        if (!issues.isEmpty()) throw new AssertionError(issues);
        System.out.println("Setup order: " + workshop.getSetupSteps());
    }
}
