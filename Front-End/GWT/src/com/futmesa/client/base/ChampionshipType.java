package com.futmesa.client.base;

public enum ChampionshipType {

	FREE_FOR_ALL(0, "Todos Contra Todos"), CLASSIFICATORY_GROUPS(1, "Classificatório Grupos"),
	CLASSIFICATORY_DEATHMATCH(2, "Classificatório Mata-Mata");

	private final int key;
	private final String label;

	private ChampionshipType(int key, String label) {
		this.key = key;
		this.label = label;
	}

	public int getKey() {
		return key;
	}

	public String getLabel() {
		return label;
	}

	public static String getLabelIndex(int index) {
		String result = "";
		if (FREE_FOR_ALL.getKey() == index) {
			result = FREE_FOR_ALL.getLabel();
		} else if (CLASSIFICATORY_GROUPS.getKey() == index) {
			result = CLASSIFICATORY_GROUPS.getLabel();
		} else if (CLASSIFICATORY_DEATHMATCH.getKey() == index) {
			result = CLASSIFICATORY_DEATHMATCH.getLabel();
		}
		return result;
	}
}
