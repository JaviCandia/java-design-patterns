package org.example.structural.flyweight;

import java.util.*;

interface Location {
    void display(Coordinates coordinates);
}

// Extrinsic state (context-dependent, not shared | always variable)
record Coordinates(int x, int y) {
}

// Flyweight (the heavy objects that you don't want to repeat across you app
class LocationIcon implements Location {
    private final String type;       // Intrinsic state
    private final String iconImage;  // Intrinsic state

    public LocationIcon(String type, String iconImage) {
        this.type = type;
        this.iconImage = iconImage;
    }

    @Override
    public void display(Coordinates coordinates) {
        // Combines intrinsic (shared) and extrinsic (context) state
        System.out.printf("""
                        %s Coordinates: (%d, %d)
                        Icon: [%s]
                        
                        """,
                type, coordinates.x(), coordinates.y(), iconImage
        );
    }
}

// Flyweight Factory (manages shared objects | stores the heavy objects)
class LocationFactory {
    private final Map<String, LocationIcon> icons = new HashMap<>();

    public LocationIcon getLocationIcon(String type) {
        if (!icons.containsKey(type)) {
            System.out.println("- Creating instance of this icon: " + type);
            String iconImage = "image_" + type.toLowerCase() + ".png";
            icons.put(type, new LocationIcon(type, iconImage));
        }
        return icons.get(type);
    }
}

// Context (stores extrinsic state + reference to Flyweight)
class MapLocation {
    private final Coordinates coordinates;
    private final LocationIcon icon;

    public MapLocation(int x, int y, LocationIcon icon) {
        this.coordinates = new Coordinates(x, y);
        this.icon = icon;
    }

    public void display() {
        icon.display(coordinates);
    }
}

public class FlyweightDemo {
    public static void main(String[] args) {
        LocationFactory factory = new LocationFactory();

        List<MapLocation> locations = Arrays.asList(
                new MapLocation(10, 20, factory.getLocationIcon("Hospital")),
                new MapLocation(20, 40, factory.getLocationIcon("Hospital")),

                new MapLocation(35, 65, factory.getLocationIcon("Park")),
                new MapLocation(35, 65, factory.getLocationIcon("Park")),

                new MapLocation(30, 60, factory.getLocationIcon("Hospital")),

                new MapLocation(30, 60, factory.getLocationIcon("School")),
                new MapLocation(30, 60, factory.getLocationIcon("School"))
        );

        locations.forEach(MapLocation::display);
    }
}