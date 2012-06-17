package ca.b02.a01.dbctrl;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ca.b02.a01.dbctrl.gui.MainFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting...");
		try {
			DbController ctrl = new DbController();
			MainFrame mf = new MainFrame(ctrl);
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
