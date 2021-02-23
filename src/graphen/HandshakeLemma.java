package graphen;

import java.util.Arrays;

public class HandshakeLemma {
    static public int getAnzKnotenFromGradfolge(int[] gradFolge) {
        return Arrays.stream(gradFolge).sum() / 2;
    }
}
