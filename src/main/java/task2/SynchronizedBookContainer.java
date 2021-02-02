package task2;

public class SynchronizedBookContainer {
    private final Book book;

    public SynchronizedBookContainer(Book book) {
        this.book = book;
    }

    public synchronized void useBook(BookHandler handler) {
        handler.handle(book);
    }
}
