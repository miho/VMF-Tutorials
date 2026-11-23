package eu.mihosoft.vmf.tutorial15;

import eu.mihosoft.vvecmath.Vector3d;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        MyModel model = MyModel.newInstance();
        Vector3d location = Vector3d.xyz(1, 2, 3);
        model.setLocation(location);

        Vector3d stored = model.getLocation();
        System.out.printf(Locale.ROOT, "Location: %.1f, %.1f, %.1f%n", stored.x(), stored.y(), stored.z());
    }
}
