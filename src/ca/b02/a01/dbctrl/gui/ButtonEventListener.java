package ca.b02.a01.dbctrl.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import javax.swing.DefaultListModel;

import ca.b02.a01.dbctrl.Bouquet;
import ca.b02.a01.dbctrl.DbController;
import ca.b02.a01.dbctrl.Logger;
import ca.b02.a01.dbctrl.Service;

public class ButtonEventListener implements ActionListener {

	private DbController dbc;
	private MainFrame mainFrame;

	public ButtonEventListener(MainFrame mainFrame, DbController dbc) {
		this.mainFrame = mainFrame;
		this.dbc = dbc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equalsIgnoreCase("Power")) {
			handlePower();
		} else if (e.getActionCommand().equalsIgnoreCase("GetServices")) {
			handleGetServices();
		} else if (e.getActionCommand().equalsIgnoreCase("Pick")) {
			handlePick();
		} else if (e.getActionCommand().equalsIgnoreCase("Tune")) {
			handleTune();
		} else if (e.getActionCommand().equalsIgnoreCase("View")) {
			handleView();
		} else {
			Logger.debug(this.getClass(),
					"Got unhandled command: " + e.getActionCommand());
		}
	}

	private void handleGetServices() {
		LinkedList<Bouquet> bouquets = dbc.getBouquets();
		DefaultListModel<Bouquet> modelBouquets = (DefaultListModel<Bouquet>) mainFrame
				.getListBouquets().getModel();
		if (!modelBouquets.isEmpty())
			modelBouquets.removeAllElements();
		for (Bouquet b : bouquets) {
			modelBouquets.addElement(b);
		}
	}

	private void handlePick() {
		DefaultListModel<Bouquet> modelBouquets = (DefaultListModel<Bouquet>) mainFrame
				.getListBouquets().getModel();
		Bouquet bouquet = modelBouquets.get(mainFrame.getListBouquets()
				.getSelectedIndex());
		DefaultListModel<Service> modelServices = (DefaultListModel<Service>) mainFrame
				.getListServices().getModel();
		if (!modelServices.isEmpty())
			modelServices.removeAllElements();
		for (Service s : bouquet.getServices()) {
			modelServices.addElement(s);
		}
	}

	private void handleTune() {
		DefaultListModel<Service> modelServices = (DefaultListModel<Service>) mainFrame
				.getListServices().getModel();
		Service service = modelServices.get(mainFrame.getListServices()
				.getSelectedIndex());
		try {
			new URL("http://192.168.0.51/web/zap?sRef="
					+ service.getReference()).openStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handlePower() {
		try {
			new URL("http://192.168.0.51/web/remotecontrol?command=116")
					.openStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleView() {
		DefaultListModel<Service> modelServices = (DefaultListModel<Service>) mainFrame
				.getListServices().getModel();
		Service service = modelServices.get(mainFrame.getListServices()
				.getSelectedIndex());

		Runtime run = Runtime.getRuntime();
		try {
			Process pr = run.exec(System.getenv("ProgramFiles(x86)")
					+ "\\VideoLAN\\VLC\\vlc.exe " + "http://192.168.0.51:8001/"
					+ service.getReference());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
