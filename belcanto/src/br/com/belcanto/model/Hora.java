package br.com.belcanto.model;

public enum Hora {

	A("9:00"), 
	B("10:00"),
	C("11:00"),
	D("12:00"),
	E("13:00"),
	F("14:00"),
	G("15:00"),
	H("16:00"),
	I("17:00"),
	J("18:00"),
	L("19:00"),
	M("20:00"),
	N("21:00"),
	O("22:00");
	
	private String label;

	private Hora(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
