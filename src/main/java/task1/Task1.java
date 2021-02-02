package task1;

import java.util.Map;

public class Task1 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("There must be given a mode in the first argument (modes are: 1 or 2 or 3)");
            System.out.println("There must be given threads amount in the second argument");
            System.out.println("Exiting");
            return;
        }
        final String chosenMode = args[0];
        final int threadsAmount = Integer.parseInt(args[1]);
        final Map<String, Runnable> mappedRunnables = Map.of(
                "1", () -> printInLoop(1),
                "2", () -> printInLoop(20),
                "3", Task1::printInInfiniteLoop);
        final Runnable chosenRunnable = mappedRunnables.get(chosenMode);
        if (chosenRunnable == null) {
            System.out.println("Incorrect mode is chosen");
        } else {
            for (int i = 0; i < threadsAmount; ++i)
                new Thread(chosenRunnable, String.valueOf(i)).start();
        }
    }

    private static void printInLoop(int loopsAmount) {
        for (int i = 0; i < loopsAmount; i++) {
            System.out.println("Hello from the thread: " + Thread.currentThread().getName());
        }
    }

    private static void printInInfiniteLoop() {
        while (true) {
            System.out.println("Hello from the thread: " + Thread.currentThread().getName());
        }
    }
}
