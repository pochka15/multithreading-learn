package task2.reader;

import task2.Book;
import task2.BookHandler;

import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Reader implements BookHandler, Runnable {
    private final List<SynchronizedBookContainer> containers;
    private final int booksToReadAmount;
    private final StateMachine<State, Event> stm;
    private int alreadyReadBooksAmount;
    private int availableBooksAmount;


    public Reader(List<SynchronizedBookContainer> containers, int booksToReadAmount) {
        this.containers = containers;
        this.booksToReadAmount = booksToReadAmount;
        stm = new StateMachine<>(State.WAITING, Map.of(
                new Transition<>(State.WAITING, Event.NEW_BOOK_ADDED, State.READING),
                ((Action) this::readNextBook).then(this::afterReadingAction),

                new Transition<>(State.READING, Event.READ_MORE, State.READING),
                ((Action) this::readNextBook).then(this::afterReadingAction),

                new Transition<>(State.READING, Event.WAIT, State.WAITING),
                ((Action) this::waitUntilBookIsAdded).then(() -> handle(Event.NEW_BOOK_ADDED)),

                new Transition<>(State.READING, Event.FINISH_READING, State.FINISHED_READING),
                () -> System.out.println(Thread.currentThread().getName() + " is existing")));
    }

    private void handle(Event ev) {
        stm.handleEvent(ev);
    }

    private boolean shouldWait() {
        return availableBooksAmount <= alreadyReadBooksAmount &&
                booksToReadAmount > alreadyReadBooksAmount;
    }

    synchronized private void waitUntilBookIsAdded() {
        do {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (shouldWait());
    }

    private void afterReadingAction() {
        synchronized (this) {
            if (shouldWait()) {
                handle(Event.WAIT);
            }
        }
        if (availableBooksAmount > alreadyReadBooksAmount)
            handle(Event.READ_MORE);
        else if (alreadyReadBooksAmount == booksToReadAmount)
            handle(Event.FINISH_READING);
    }

    private void readNextBook() {
        containers.get(alreadyReadBooksAmount).useBook(book -> {
            sleepRandomTime(100);
            System.out.format("%40s\n", Thread.currentThread().getName() + ": " + book);
        });
        alreadyReadBooksAmount++;
    }

    @Override
    synchronized public void handle(Book book) {
        availableBooksAmount++;
        notify();
    }

    @Override
    public void run() {
        synchronized (this) {
            availableBooksAmount = containers.size();
            if (shouldWait()) waitUntilBookIsAdded();
        }
        handle(Event.NEW_BOOK_ADDED);
    }

    private void sleepRandomTime(long t) {
        try {
            sleep((long) (t * Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}