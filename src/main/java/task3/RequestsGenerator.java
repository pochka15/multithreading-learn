package task3;

import java.util.Random;

import static java.lang.Thread.sleep;

public class RequestsGenerator implements Runnable {
    private final int maxFloor;
    private final Lift lift;

    public RequestsGenerator(int maxFloor, Lift lift) {
        this.maxFloor = maxFloor;
        this.lift = lift;
    }

    @Override
    public void run() {
        final Random random = new Random();
        while (true) {
            lift.handleRequest(new Request(random.nextInt(maxFloor) + 1, 0));
            sleepRandomTime(1000);
        }
    }

    private void sleepRandomTime(long t) {
        try {
            sleep((long) (t * Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
