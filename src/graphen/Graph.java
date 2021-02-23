package graphen;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Graph {
    public List<Knoten> knoten;
    public List<UngerichteteKante> kanten; // Mengen mit je zwei elementen

    public Graph(List<Knoten> v, List<UngerichteteKante> e) {
        knoten = v;
        kanten = e;
    }

    public static Graph emptyGraphOfNaturalNumbersUpTo(int n) {
        List<Knoten> knoten = IntStream.rangeClosed(1, n).boxed().map(Knoten::new).collect(Collectors.toList());
        List<UngerichteteKante> kanten = new ArrayList<>();
        return  new Graph(knoten, kanten);
    }

    public static Graph emptyGraphWithEdges(List<Knoten> knoten) {
        List<UngerichteteKante> kanten = new ArrayList<>();
        return new Graph(knoten, kanten);
    }

    static class Knoten {
        String name;
        public Knoten(String n) {
            name = n;
        }
        public Knoten(int i) {
            name = String.valueOf(i);
        }

        @Override
        public String toString() {
            return name;
        }
        public void setName(String n) {name = n;}
        public void setName(int n) {name = String.valueOf(n);}
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", knoten.toString(), kanten.toString());
    }
}
