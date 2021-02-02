package task2;

/**
 * This interface is primarily used to handle the books which will be produced by the writers
 */
@FunctionalInterface
public interface BookHandler {
    void handle(Book book);
}
