package task3;

public class Request {
    public final int fromFloor;
    public final int toFloor;

    public Request(int fromFloor, int toFloor) {
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
    }

    @Override
    public String toString() {
        return "fromFloor =" + fromFloor + ", toFloor =" + toFloor;
    }
}
