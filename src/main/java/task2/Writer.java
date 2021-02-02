package task2;

import task2.reader.SynchronizedBookContainer;

import java.util.List;

import static java.lang.Thread.sleep;

class Writer implements Runnable {
    private final List<? extends BookHandler> handlers;
    private final List<SynchronizedBookContainer> containers;
    private final int amountOfBooksToWrite;

    public Writer(List<? extends BookHandler> handlers,
                  List<SynchronizedBookContainer> containers,
                  int amountOfBooksToWrite) {
        this.handlers = handlers;
        this.containers = containers;
        this.amountOfBooksToWrite = amountOfBooksToWrite;
    }

    @Override
    public void run() {
        for (int i = 0; i < amountOfBooksToWrite; ++i) {
            sleepRandomAmountOfTime(100);
            final Book book = new Book(Thread.currentThread().getName() + " " + (i + 1));
            containers.add(new SynchronizedBookContainer(book));
            System.out.println("+ " + book);
            for (BookHandler handler : handlers) {
                handler.handle(book);
            }
        }
    }

    private void sleepRandomAmountOfTime(long t) {
        try {
            sleep((long) (t * Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
