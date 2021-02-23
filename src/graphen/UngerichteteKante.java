package graphen;

public class UngerichteteKante {
    public final Graph.Knoten v;
    public final Graph.Knoten u;

    public UngerichteteKante(Graph.Knoten v, Graph.Knoten u) {
        this.v = v;
        this.u = u;
    }

    @Override
    public String toString() {
        return String.format("{%s, %s}", v.name, u.name);
    }
}
