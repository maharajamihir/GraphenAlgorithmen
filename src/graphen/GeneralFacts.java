package graphen;

import java.util.Arrays;
import java.util.List;

import pgdp.MiniJava;

public class GeneralFacts {

	List<String> gegeben;
	int[] gradFolge;
	int anzKnoten;
	int anzKanten;

	public GeneralFacts() {
		gegeben = List.of("Gradfolge", "Anzahl Knoten", "Anzahl Kanten", "Anzahl Knoten und Kanten");
	}

	static int[] getGradFolge() {
		return Arrays
				.stream(MiniJava.readString("Bitte gebe eine Gradfolge in folgender Form ein: (x1,x2,x3,x4,...)")
						.replace("(", "").replace(")", "").split(","))
				.mapToInt(string -> Integer.parseInt(string)).toArray();
	}

	public static void main(String args[]) {
		GeneralFacts g = new GeneralFacts();
		System.out.println("Was aus den folgenden Optionen ist gegeben? Gebe die jeweilige Zahl ein.");
		for (String option : g.gegeben)
			System.out.println("[" + g.gegeben.indexOf(option) + "] " + option);
		switch (MiniJava.readInt()) {
		case 0 -> {
			g.gradFolge = getGradFolge();
			g.gradFolgenOperationen();
		}
		case 1 -> {
			g.anzKnoten = MiniJava.readInt("Bitte gebe die Anzahl der Knoten ein.");
			g.knotenOperationen();
		}
		case 2 -> {
			g.anzKanten = MiniJava.readInt("Bitte gebe die Anzahl der Kanten ein.");
			g.kantenOperationen();
		}
		case 3 -> {
			g.anzKnoten = MiniJava.readInt("Bitte gebe die Anzahl der Knoten ein.");
			g.anzKanten = MiniJava.readInt("Bitte gebe die Anzahl der Kanten ein.");
			g.kantenUndKnotenOperationen();
		}
		}
	}

	// Gebe alle Fakten aus, die man über die Gradfolge rausfinden kann
	void gradFolgenOperationen() {
		//TODO
	}

	// Gebe alle Fakten aus, die man über die anzahl der Kanten rausfinden kann
	void kantenOperationen() {
		//TODO
	}

	// Gebe alle Fakten aus, die man über die anzahl der Knoten rausfinden kann
	void knotenOperationen() {
		//TODO
	}

	// Gebe alle Fakten aus, die man über die anzahl der Knoten und der Kanten
	// rausfinden kann
	void kantenUndKnotenOperationen() {
		kantenOperationen();
		knotenOperationen();
		//TODO (wenn man beides weiß, kann probably noch mehr rausfinden)
	}
}
