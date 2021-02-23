package graphen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import pgdp.MiniJava;

public class GaleShapley {

	Map<String, Mann> atzen = new TreeMap<>();
	Map<String, Frau> chicks = new TreeMap<>();
	Map<Mann, Frau> verlobungen = new HashMap<>();

	private void setup() {
		int anzFrauen = MiniJava.readInt("Wie viele Frauen gibts?");
		int anzMänner = MiniJava.readInt("Wie viele Männer gibts?");
		System.out.println("Frauen: ");
		for (int i = 0; i < anzFrauen; i++) {
			chicks.put("f" + i, new Frau("f" + i));
			System.out.print("f" + i + (i == anzFrauen - 1 ? "" : ", "));
		}
		System.out.println("");
		System.out.println("Männer: ");
		for (int i = 0; i < anzMänner; i++) {
			atzen.put("m" + i, new Mann("m" + i));
			System.out.print("m" + i + (i == anzMänner - 1 ? "" : ", "));
		}
		System.out.println("");
	}

	public void executeAlgorithm() {
		setup();
		System.out.println(atzen + "\n" + chicks);
		chicks.entrySet().stream().map(entry -> entry.getValue()).forEach(chick -> getPreferencesChick(chick));
		atzen.entrySet().stream().map(entry -> entry.getValue()).forEach(dude -> getPreferencesDude(dude));

		while (chicks.entrySet().stream().map(entry -> entry.getValue()).anyMatch(atze -> atze.isSingle())) {
			atzen.entrySet().stream().map(entry -> entry.getValue()).forEach(atze -> atze.proposal());
			System.out.println(verlobungen);
		}
		System.out.println(verlobungen);
	}

	public static void main(String args[]) {
		new GaleShapley().executeAlgorithm();
	}

	private void getPreferencesChick(Frau frau) {
		System.out.println("Bitte gebe die Präferenzen von " + frau.toString() + " als Tupel an");
		frau.setPreferences(Arrays
				.stream(MiniJava.readString(frau.toString()).replace("(", "").replace(")", "").split(","))
				.map(string -> atzen.get(string.trim())).filter(pref -> !(pref == null)).collect(Collectors.toList()));
	}

	private void getPreferencesDude(Mann dude) {
		System.out.println("Bitte gebe die Präferenzen von " + dude.toString() + " als Tupel an");
		dude.setPreferences(Arrays
				.stream(MiniJava.readString(dude.toString()).replace("(", "").replace(")", "").split(","))
				.map(string -> chicks.get(string.trim())).filter(pref -> !(pref == null)).collect(Collectors.toList()));
	}

	private abstract class Mensch {

		private final String name;

		Mensch(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

	}

	private class Frau extends Mensch {
		private List<Mann> preferences;
		private Mann verlobte;

		public Frau(String name) {
			super(name);
		}

		public List<Mann> getPreferences() {
			return preferences;
		}

		public void setPreferences(List<Mann> preferences) {
			this.preferences = preferences;
		}

		public boolean proposal(Mann dude) {
			System.out.println(dude.toString() + " proposing to " + this.toString());
			if (isSingle()) {
				setVerlobte(dude);
				verlobungen.put(dude, this);
				System.out.println(this.toString() + " accepted " + dude.toString()+" 's proposal :))");
				return true;
			}
			if (getPreferences().indexOf(dude) >= getPreferences().indexOf(getVerlobte())) {
				getPreferences().remove(getVerlobte());
				getVerlobte().breakMarriage();
				setVerlobte(dude);
				verlobungen.put(dude, this);
				System.out.println(this.toString() + " accepted " + dude.toString()+" 's proposal :))");
				return true;
			}
			System.out.println(this.toString() + " rejected " + dude.toString()+" 's proposal.");
			return false;
		}

		public Mann getVerlobte() {
			return verlobte;
		}

		public void setVerlobte(Mann verlobte) {
			this.verlobte = verlobte;
		}

		public boolean isSingle() {
			return verlobte == null;
		}

	}

	private class Mann extends Mensch {
		private List<Frau> preferences;
		private Frau verlobte;

		Mann(String name) {
			super(name);
		}

		public List<Frau> getPreferences() {
			return preferences;
		}

		public void setPreferences(List<Frau> preferences) {
			this.preferences = preferences;
		}

		public void breakMarriage() {
			System.out.println(
					"Verlobung between " + getVerlobte().toString() + " and " + this.toString() + " has been broken.");
			getPreferences().remove(getVerlobte());
			verlobungen.remove(this, getVerlobte());
			setVerlobte(null);
		}

		public Frau getVerlobte() {
			return verlobte;
		}

		public void setVerlobte(Frau verlobte) {
			this.verlobte = verlobte;
		}

		public boolean isSingle() {
			return verlobte == null;
		}

		public boolean proposal() {
			if (!isSingle())
				return false;
			if (getPreferences().isEmpty())
				return false;
			boolean accepted = getPreferences().get(0).proposal(this);
			if (accepted) {
				setVerlobte(getPreferences().get(0));
				return true;
			} else {
				getPreferences().remove(0);
				return false;
			}
		}
	}
}
