package task3;


public class Task3 {
    public static void main(String[] args) {
        final int maxFloor = 10;
        final int ticksPeriod = 1000;
        final Lift lift = new Lift(ticksPeriod);
        new Thread(lift).start();
        final Thread requestsThread = new Thread(new RequestsGenerator(maxFloor, lift));
        requestsThread.setPriority(Thread.MAX_PRIORITY);
        requestsThread.start();
    }
}
