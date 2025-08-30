    package org.example.behavioral.visitor;

    interface Visitor {
        void visitRollerCoaster(RollerCoaster rollerCoaster);

        void visitHauntedHouse(HauntedHouse hauntedHouse);

        void visitFerrisWheel(FerrisWheel ferrisWheel);
    }

    // Element (Visitable)
    interface Attraction {
        void accept(Visitor visitor);

        double getPrice();
    }

    // ConcreteElement
    class RollerCoaster implements Attraction {

        @Override
        public double getPrice() {
            return 50.0;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitRollerCoaster(this);
        }
    }

    // ConcreteElement
    class HauntedHouse implements Attraction {

        @Override
        public double getPrice() {
            return 40.0;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitHauntedHouse(this);
        }
    }

    // ConcreteElement
    class FerrisWheel implements Attraction {

        @Override
        public double getPrice() {
            return 30.0;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitFerrisWheel(this);
        }
    }

    // ConcreteVisitor
    class ChildVisitor implements Visitor {
        @Override
        public void visitRollerCoaster(RollerCoaster rollerCoaster) {
            System.out.printf("Child on Roller Coaster: Discounted price $%.2f%n",
                    rollerCoaster.getPrice() * 0.50);
        }

        @Override
        public void visitHauntedHouse(HauntedHouse hauntedHouse) {
            System.out.printf("Child in Haunted House: Discounted price $%.2f%n",
                    hauntedHouse.getPrice() * 0.70);
        }

        @Override
        public void visitFerrisWheel(FerrisWheel ferrisWheel) {
            System.out.printf("Child on Ferris Wheel: Discounted price $%.2f%n",
                    ferrisWheel.getPrice() * 0.60);
        }
    }

    // ConcreteVisitor
    class AdultVisitor implements Visitor {
        @Override
        public void visitRollerCoaster(RollerCoaster rollerCoaster) {
            System.out.printf("Adult on Roller Coaster: Price $%.2f%n",
                    rollerCoaster.getPrice());
        }

        @Override
        public void visitHauntedHouse(HauntedHouse hauntedHouse) {
            System.out.printf("Adult in Haunted House: Price $%.2f%n",
                    hauntedHouse.getPrice());
        }

        @Override
        public void visitFerrisWheel(FerrisWheel ferrisWheel) {
            System.out.printf("Adult on Ferris Wheel: Price $%.2f%n",
                    ferrisWheel.getPrice());
        }
    }

    // ConcreteVisitor
    class SeniorVisitor implements Visitor {
        @Override
        public void visitRollerCoaster(RollerCoaster rollerCoaster) {
            System.out.printf("Senior on Roller Coaster: Discounted price $%.2f%n",
                    rollerCoaster.getPrice() * 0.85);
        }

        @Override
        public void visitHauntedHouse(HauntedHouse hauntedHouse) {
            System.out.printf("Senior in Haunted House: Discounted price $%.2f%n",
                    hauntedHouse.getPrice() * 0.85);
        }

        @Override
        public void visitFerrisWheel(FerrisWheel ferrisWheel) {
            System.out.printf("Senior on Ferris Wheel: Discounted price $%.2f%n",
                    ferrisWheel.getPrice() * 0.85);
        }
    }

    // Client (+ ObjectStructure: a collection applying visitors)
    public class VisitorDemo {
        public static void main(String[] args) {
            Attraction[] attractions = new Attraction[]{
                    new RollerCoaster(),
                    new HauntedHouse(),
                    new FerrisWheel()
            };

            System.out.printf("Roller Coaster: $%.2f\n", new RollerCoaster().getPrice());
            System.out.printf("Haunted House: $%.2f\n", new HauntedHouse().getPrice());
            System.out.printf("Ferris Wheel:  $%.2f\n\n", new FerrisWheel().getPrice());

            System.out.println("Child Visitor");
            Visitor child = new ChildVisitor();
            for (Attraction attraction : attractions)
                attraction.accept(child);

            System.out.println("\nAdult Visitor");
            Visitor adult = new AdultVisitor();
            for (Attraction attraction : attractions)
                attraction.accept(adult);

            System.out.println("\nSenior Visitor");
            Visitor senior = new SeniorVisitor();
            for (Attraction attraction : attractions)
                attraction.accept(senior);
        }
    }