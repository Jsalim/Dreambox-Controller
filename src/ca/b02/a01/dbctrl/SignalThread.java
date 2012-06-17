package ca.b02.a01.dbctrl;

import java.io.IOException;
import java.net.URL;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import ca.b02.a01.dbctrl.gui.MainFrame;
import ca.b02.a01.dbctrl.xmlhandler.SignalHandler;

public class SignalThread extends Thread {

	private MainFrame mainFrame;
	private XMLReader saxReader;
	private SignalHandler handler;
	private boolean isRunning = true;

	public SignalThread(MainFrame mainFrame) throws SAXException {
		this.mainFrame = mainFrame;
		saxReader = XMLReaderFactory.createXMLReader();
		handler = new SignalHandler();
		saxReader.setContentHandler(handler);
	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				saxReader.parse(new InputSource(new URL(
						"http://192.168.0.51/web/signal").openStream()));
				Signal sig = handler.signal;
				mainFrame.getProgressBarDb()
						.setValue((int) (sig.getDb() * 100));
				mainFrame.getProgressBarSnr().setValue(sig.getSnr());
				mainFrame.getProgressBarBer().setValue(sig.getBer());
				mainFrame.getProgressBarAcg().setValue(sig.getAcg());

				Thread.sleep(500);
			} catch (IOException | SAXException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}
