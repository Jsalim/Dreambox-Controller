package ca.b02.a01.dbctrl.xmlhandler;

import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ca.b02.a01.dbctrl.Bouquet;
import ca.b02.a01.dbctrl.Service;

public class GetAllServicesHandler extends DefaultHandler {

	private String currentElement;
	private Bouquet currentBouquet;
	private Service currentService;
	private boolean isService = false;

	public LinkedList<Bouquet> bouquets = new LinkedList<Bouquet>();

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("e2service")) {
			isService = true;
			if (currentService != null)
				currentBouquet.addService(currentService);
			currentService = new Service();
		}
		currentElement = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (isService) {
			if (currentElement.equalsIgnoreCase("e2servicereference")) {
				currentService.setReference(new String(ch, start, length));
			}
			if (currentElement.equalsIgnoreCase("e2servicename")) {
				currentService.setName(new String(ch, start, length));
			}
		}

		if (!isService && currentElement.equalsIgnoreCase("e2servicename")) {
			if (currentBouquet != null)
				bouquets.add(currentBouquet);
			currentBouquet = new Bouquet();
			currentBouquet.setName(new String(ch, start, length));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("e2service")) {
			isService = false;
		}
		currentElement = "";
	}

	@Override
	public void endDocument() throws SAXException {
		if (currentBouquet != null)
			bouquets.add(currentBouquet);
	}
}
