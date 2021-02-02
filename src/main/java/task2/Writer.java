package task2;

import java.util.List;

import static java.lang.Thread.sleep;

class Writer implements Runnable {
    private final List<? extends BookHandler> handlers;
    private final List<SynchronizedBookContainer> containers;
    private final int amountOfBooksToWrite;

    /**
     * ctor
     * @param handlers all the handlers which will be called when the writer adds a new book
     * @param containers a list of containers. There will be added new containers with books into this list
     * @param amountOfBooksToWrite non-negative number of books that will be produced
     */
    public Writer(List<? extends BookHandler> handlers,
                  List<SynchronizedBookContainer> containers,
                  int amountOfBooksToWrite) {
        this.handlers = handlers;
        this.containers = containers;
        this.amountOfBooksToWrite = amountOfBooksToWrite;
    }

    /**
     * add the new containers in a loop and call the handlers to handle a new book
     */
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
