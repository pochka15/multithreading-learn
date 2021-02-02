package task2.reader;

import task2.Book;
import task2.BookHandler;

public class SynchronizedBookContainer {
    private final Book book;

    public SynchronizedBookContainer(Book book) {
        this.book = book;
    }

    public synchronized void useBook(BookHandler handler) {
        handler.handle(book);
    }
}
