package graphen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HavelHakimiTest {

    @org.junit.jupiter.api.Test
    void generateGraph() {
        int[][] testSeq = new int[4][];
        testSeq[0] = new int[]{2, 2, 2, 2, 2, 2};
        testSeq[1] = new int[]{1, 1, 1, 1, 2, 3, 3};
        testSeq[2] = new int[]{1, 1, 2, 2, 2};
        testSeq[3] = new int[]{2, 3, 3, 4, 4};
        for (int[] folge: testSeq) {
            List<GradFolgeElement> gradFolge = new ArrayList<>();
            int i = 0;
            for (int deg: folge) {
                gradFolge.add(new GradFolgeElement(i+1, deg));
                i++;
            }
            Graph g = HavelHakimi.generateGraph(gradFolge, false);
            System.out.printf("%s\t -> %s\n", Arrays.toString(folge), g.toString());
        }
    }
}
