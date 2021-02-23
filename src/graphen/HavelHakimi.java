package graphen;

import java.util.Arrays;

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

	public static void main(String args[]) {
		executeAlgorithm(GeneralFacts.getGradFolge());
	}
}
