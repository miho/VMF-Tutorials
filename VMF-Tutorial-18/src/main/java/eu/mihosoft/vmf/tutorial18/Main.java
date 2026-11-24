package eu.mihosoft.vmf.tutorial18;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Workshop workshop = Workshop.newInstance();
        System.out.println(workshop.vmf().content().validate());
        if (workshop.vmf().content().validate().size() != 1) throw new AssertionError("An empty workshop needs a station");

        var first = Station.newBuilder().withName("Inspection").build();
        var second = Station.newBuilder().withName("Inspection").build();
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
