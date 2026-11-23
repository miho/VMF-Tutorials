package eu.mihosoft.vmf.tutorial10;

public class Main {
    public static void main(String[] args) {
        ObjectToCompare first = ObjectToCompare.newBuilder().withId("item").withData(7).build();
        ObjectToCompare same = ObjectToCompare.newBuilder().withId("item").withData(7).build();
        ObjectToCompare differentData = ObjectToCompare.newBuilder().withId("item").withData(8).build();

        System.out.println("Distinct instances: " + (first != same));
        System.out.println("Same ID and data: " + first.equals(same));
        System.out.println("Same ID, different data: " + first.equals(differentData));

        ObjectToCompareId byId = ObjectToCompareId.newBuilder().withId("item").withData(7).build();
        ObjectToCompareId byIdDifferentData = ObjectToCompareId.newBuilder().withId("item").withData(8).build();

        System.out.println("Ignoring data, same ID: " + byId.equals(byIdDifferentData));
        System.out.println("Ignoring data, same hash: " + (byId.hashCode() == byIdDifferentData.hashCode()));
    }
}
