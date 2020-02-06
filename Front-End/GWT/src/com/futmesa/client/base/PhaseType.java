package com.futmesa.client.base;

public enum PhaseType {

	FREE_FOR_ALL(1, "Todos Contra Todos"), GROUPS(2, "Grupos"),
	DEATHMATCH(3, "Mata-Mata");

	private final int key;
	private final String label;

	private PhaseType(int key, String label) {
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
		} else if (GROUPS.getKey() == index) {
			result = GROUPS.getLabel();
		} else if (DEATHMATCH.getKey() == index) {
			result = DEATHMATCH.getLabel();
		}
		return result;
	}
}
