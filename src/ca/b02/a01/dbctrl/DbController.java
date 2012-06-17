package ca.b02.a01.dbctrl;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import ca.b02.a01.dbctrl.xmlhandler.GetAllServicesHandler;

public class DbController {

	private XMLReader saxReader;
	private LinkedList<Bouquet> bouquets = new LinkedList<Bouquet>();

	public DbController() throws ParserConfigurationException, SAXException {
		saxReader = XMLReaderFactory.createXMLReader();
	}

	private void refreshAllServices() {
		try {
			GetAllServicesHandler handler = new GetAllServicesHandler();
			saxReader.setContentHandler(handler);
			saxReader.parse(new InputSource(new URL(
					"http://192.168.0.51/web/getallservices").openStream()));
			bouquets = handler.bouquets;
			Logger.debug(this.getClass(), "Found " + bouquets.size()
					+ " bouquets");
		} catch (IOException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public LinkedList<Bouquet> getBouquets() {
		refreshAllServices();
		return bouquets;
	}
}
