package task2;

public class Book {
    public final String name;

    public Book(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
