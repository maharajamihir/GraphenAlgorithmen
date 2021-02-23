package graphen;

import pgdp.MiniJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HavelHakimi {

    public static void executeAlgorithm(int[] gradFolge) {
        if (Arrays.stream(gradFolge).asDoubleStream().sum() % 2 != 0) {
            System.out.println(
                    "Der Graph ist nicht realisierbar, da die Summe aller Knotengrade ungerade ist!\n-> Handschlagslemma!");
            return;
        }
        try {
            if (istRealisierbar(gradFolge))
                System.out.println("Die gegebene Gradfolge ist realisierbar :)");
            else
                System.out.println("Die Gradfolge ist leider nicht realisierbar!");
        } catch (Exception e) {
            System.out.println("Die Gradfolge ist leider nicht realisierbar!");
        }
    }

    private static boolean istRealisierbar(int[] gradFolge) {
        Arrays.sort(gradFolge);
        System.out.println(Arrays.toString(gradFolge));
        if (gradFolge.length == 0)
            return true;
        if (Arrays.stream(gradFolge).allMatch(x -> x == 0))
            return true;
        if (Arrays.stream(gradFolge).anyMatch(x -> x < 0))
            return false;
        int[] newGradFolge = Arrays.stream(gradFolge).limit(gradFolge.length - 1).toArray();
        for (int i = 0; i < gradFolge[gradFolge.length - 1]; i++)
            newGradFolge[newGradFolge.length - 1 - i]--;
        return istRealisierbar(newGradFolge);
    }

    public static Graph generateGraph(int[] gradFolge, boolean printSteps) {
        List<GradFolgeElement> gradFolgeList = new ArrayList<>();
        int i = 0;
        for (int deg : gradFolge) {
            gradFolgeList.add(new GradFolgeElement(i, deg));
            i++;
        }
        return generateGraph(gradFolgeList, printSteps);
    }

    public static Graph generateGraph(List<GradFolgeElement> gradFolge) {
        return generateGraph(gradFolge, false);
    }

    public static Graph generateGraph(List<GradFolgeElement> gradFolge, boolean printSteps) {
        if (gradFolge == null) {
            // Abbruch, es existiert kein entsprechender Graph.
            return null;
        }
        List<GradFolgeElement> gradFolgeSorted = gradFolge.stream().sorted(Comparator.comparingInt(el -> el.grad)).collect(Collectors.toList());
        if (gradFolgeSorted.get(0).grad < 0) return null;
        int n = gradFolgeSorted.size();
        GradFolgeElement last = gradFolgeSorted.get(n - 1);

        if (printSteps)
            System.out.printf("Top-Down: %s\t%s\n", gradFolge.toString(), gradFolgeSorted.toString());
        if (last.grad > n - 1) return null;
        if (gradFolgeSorted.get(n - 1).grad == 0) {
            return Graph.emptyGraphWithEdges(gradFolge.stream().map(el -> el.knoten).collect(Collectors.toList()));
        }


        // Letzten Knoten entfernen, Grad von letzten last.grad Knoten reduzieren
        List<GradFolgeElement> newFolge = new ArrayList<>();
        for (GradFolgeElement el : gradFolgeSorted) {
            // Neue Elemente mit selbem knoten-reference.
            newFolge.add(new GradFolgeElement(el.knoten, el.grad));
        }
        last = newFolge.remove(n - 1);
        for (int i = 1; i <= last.grad; i++) {
            newFolge.get(n - 1 - i).grad--;
        }

        // Rekursiv G' generieren.
        Graph G = generateGraph(newFolge, printSteps);
        if (printSteps)
            System.out.printf("Bottom up: %s\n", G.toString());


        // Letzten knoten hinzufügen.
        List<Graph.Knoten> newKnoten = new ArrayList<>(G.knoten);
        newKnoten.add(last.knoten);
        List<UngerichteteKante> newKanten = new ArrayList<>(G.kanten);
        for (int i = 1; i <= last.grad; i++) {
            newKanten.add(new UngerichteteKante(last.knoten, newFolge.get(n - 1 - i).knoten));
        }

        return new Graph(newKnoten, newKanten);
    }

    // Source: Skript S.46 #138
    public static Graph generateGraph(int[] gradFolge) {
        return generateGraph(gradFolge, false);
    }

    public static void main(String[] args) {
        System.out.println("Havel-Hakimi!");
        System.out.println("[0] Realisierbarkeit prüfen");
        System.out.println("[1] Kanten- und Knotenmenge generieren");
        int option = -1;
        do {
            option = MiniJava.readInt("Bitte gebe die jeweilige Zahl ein.");
        } while (option != 0 && option != 1);
        switch (option) {
            case 0 -> executeAlgorithm(GeneralFacts.getGradFolge());
            case 1 -> {
                Graph g = generateGraph(GeneralFacts.getGradFolge(), true);
                System.out.println(g);
            }
        }

    }
}
