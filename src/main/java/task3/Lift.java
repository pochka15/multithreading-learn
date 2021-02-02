package task3;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Thread.sleep;

public class Lift implements Runnable {
    private final int ticksPeriod;
    private final Queue<Request> requests;

    private int currentFloor;
    private Request currentlyExecutingRequest;
    private boolean isTakingPassenger = false;

    public Lift(int ticksPeriod) {
        this.ticksPeriod = ticksPeriod;
        requests = new LinkedList<>();
    }

    int calcTargetFloor() {
//        if I'm on the targetFloor => get the target toFloor from the request
//        if I'm on the other floor => go on
//        If
        if (isTakingPassenger) {
            if (currentFloor == currentlyExecutingRequest.fromFloor) {
//                goal is to go to the currentlyExecutingRequest.toFloor
            }
//            go on to the passenger
        } else {
//            if (currentlyExecutingRequest.)
        }
        return 0;
    }

    synchronized void handleRequest(Request request) {
        requests.add(request);
        notify();
    }

    @Override
    public void run() {
        while (true) {
            boolean shouldMove = true;
            if (shouldMove) {
                try {
                    sleep(ticksPeriod);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                synchronized (this) {
                    while (requests.size() == 0){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }                }
            }
        }
    }
}
