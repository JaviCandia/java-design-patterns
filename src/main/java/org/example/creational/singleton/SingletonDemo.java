package org.example.creational.singleton;

// Singleton Pattern: ensures only one instance of DragonBalls exists in the entire application
class DragonBalls {

    // Holds the single instance (shared across all calls)
    private static DragonBalls instance;
    private int ballsCollected;

    private DragonBalls() {
        this.ballsCollected = 0;
    }

    // Singleton access point: creates the instance once and always returns the same object
    public static DragonBalls getInstance() {
        if (instance == null) {
            instance = new DragonBalls();
            System.out.println("The Dragon Balls have been created!");
        }
        return instance;
    }

    public void collectBall(String name) {
        if (this.ballsCollected < 7) {
            this.ballsCollected++;
            System.out.printf("Ball Collected by: %s. Total Dragon Balls: %d\n", name, this.ballsCollected);
            return;
        }

        System.out.println("All 7 Dragon Balls have been collected! Summon Shenlong.");

    }

    public void summonShenlong() {
        if (this.ballsCollected == 7) {
            System.out.println("Shenlong has been summoned. Make your wish!");
            this.ballsCollected = 0;
            System.out.println("The Dragon Balls have scattered again!");
            return;
        }

        System.out.printf("\nYou still need %d Dragon Balls to summon Shenlong\n", (7-this.ballsCollected));
    }
}


public class SingletonDemo {
    public static void main(String[] args) {
        DragonBalls gokuDragonBalls = DragonBalls.getInstance();

        gokuDragonBalls.collectBall("Goku");
        gokuDragonBalls.collectBall("Goku");
        gokuDragonBalls.collectBall("Goku");

        gokuDragonBalls.summonShenlong();

        DragonBalls vegetaDragonBalls = DragonBalls.getInstance();
        vegetaDragonBalls.collectBall("Vegeta");
        vegetaDragonBalls.collectBall("Vegeta");
        vegetaDragonBalls.collectBall("Vegeta");
        vegetaDragonBalls.collectBall("Vegeta");

        gokuDragonBalls.summonShenlong();

        // The count is restarted after the balls have been scattered.
        vegetaDragonBalls.summonShenlong();
    }
}