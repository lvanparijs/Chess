public class Pair<X,Y> {

    private final X x;
    private final Y y;

    public Pair(X x, Y y) {
        this.y = y;
        this.x = x;
    }

    public X getX() { return x; }
    public Y getY() { return y; }

    @Override
    public int hashCode() { return x.hashCode() ^ y.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair pairo = (Pair) o;
        return this.x.equals(pairo.getX()) &&
                this.y.equals(pairo.getY());
    }

}