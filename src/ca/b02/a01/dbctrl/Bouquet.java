package ca.b02.a01.dbctrl;

import java.util.LinkedList;

public class Bouquet {

	private LinkedList<Service> services = new LinkedList<Service>();
	private String name;

	public void addService(String name, String reference) {
		services.add(new Service(name, reference));
	}

	public void addService(Service service) {
		services.add(service);
	}

	public void removeService(String name) {
		services.remove(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Service> getServices() {
		return services;
	}

	public String toString() {
		return name;
	}
}
