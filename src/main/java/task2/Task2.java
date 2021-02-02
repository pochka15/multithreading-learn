package task2;

import task2.reader.Reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Task2 {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("There must be given readers amount in the first argument");
            System.out.println("There must be given writers amount in the second argument");
            System.out.println("There must be given an amount of books to write");
            System.out.println("Exiting");
            return;
        }
        final int readersAmount = Integer.parseInt(args[0]);
        final int writersAmount = Integer.parseInt(args[1]);
        final int amountOfBooksToWrite = Integer.parseInt(args[2]);
        System.out.println("Args are: " + readersAmount + ", " + writersAmount + ", " + amountOfBooksToWrite);

        final int overallAmountOfBooks = writersAmount * amountOfBooksToWrite;
        final List<SynchronizedBookContainer> synchronizedContainers = Collections.synchronizedList(
                new ArrayList<>(overallAmountOfBooks));
        final ArrayList<Reader> readers = new ArrayList<>(readersAmount);
        final ArrayList<Writer> writers = new ArrayList<>(writersAmount);
//        Add readers
        for (int i = 0; i < readersAmount; i++) {
            readers.add(new Reader(synchronizedContainers, overallAmountOfBooks));
        }
//        Add writers
        for (int i = 0; i < writersAmount; i++) {
            writers.add(new Writer(readers, synchronizedContainers, amountOfBooksToWrite));
        }
//        Start readers and writers threads
        int i = 0;
        for (Reader reader : readers) {
            new Thread(reader, "Reader " + i++).start();
        }
        i = 0;
        for (Writer writer : writers) {
            new Thread(writer, "Writer " + i++).start();
        }
    }
}
