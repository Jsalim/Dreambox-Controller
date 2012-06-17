package ca.b02.a01.dbctrl;

public class Service {

	private String reference;
	private String name;

	public Service() {

	}

	public Service(String name, String reference) {
		this.name = name;
		this.reference = reference;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
