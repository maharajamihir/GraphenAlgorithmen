package graphen;

public class GradFolgeElement {
    Graph.Knoten knoten;
    int grad;

    public GradFolgeElement(int idx, int grad) {
        this.grad = grad;
        this.knoten = new Graph.Knoten(idx);
    }

    public GradFolgeElement(Graph.Knoten v, int grad) {
        this.grad = grad;
        this.knoten = v;
    }

    @Override
    public String toString() {
        return String.format("%d", grad);
    }
}
