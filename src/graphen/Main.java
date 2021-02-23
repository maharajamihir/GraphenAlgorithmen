package graphen;

import pgdp.MiniJava;

public class Main {
	public static void main(String[] args) {

		System.out.println("Hallo, was willst du über Graphen wissen?");
		System.out.println("[0] Algorithmen");
		System.out.println("[1] Generelle Fakten");
		int option = -1;
		do {
			option = MiniJava.readInt("Bitte gebe die jeweilige Zahl ein.");
		} while (option != 0 && option != 1);
		switch (option) {
		case 0 -> chooseAlgorithm();
		case 1 -> GeneralFacts.main(null);
		}

	}

	static void chooseAlgorithm() {
		System.out.println("Graphen-Algorithmen! Wähle einen Algorithmus aus.");
		System.out.println("[0] Havel-Hakimi");
		System.out.println("[1] Gale-Shapley");
		int option = -1;
		do {
			option = MiniJava.readInt("Bitte gebe die jeweilige Zahl ein.");
		} while (option != 0 && option != 1);
		switch (option) {
		case 0 -> HavelHakimi.main(null);
		case 1 -> GaleShapley.main(null);
		}
	}
}
