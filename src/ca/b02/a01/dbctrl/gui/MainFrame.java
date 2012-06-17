package ca.b02.a01.dbctrl.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.xml.sax.SAXException;

import ca.b02.a01.dbctrl.Bouquet;
import ca.b02.a01.dbctrl.DbController;
import ca.b02.a01.dbctrl.Service;
import ca.b02.a01.dbctrl.SignalThread;

public class MainFrame {

	private DbController dbc;

	private JFrame frame;
	private JList<Bouquet> listBouquets;
	private JScrollPane listBouquetsScroller;
	private JList<Service> listServices;
	private JScrollPane listServicesScroller;

	private JButton btnPower;
	private JButton btnPick;
	private JButton btnTune;
	private JButton btnView;
	private JButton btnGetServices;

	private JLabel lbDb;
	private JProgressBar progressBarDb;
	private JLabel lbSnr;
	private JProgressBar progressBarSnr;
	private JLabel lbBer;
	private JProgressBar progressBarBer;
	private JLabel lbAcg;
	private JProgressBar progressBarAcg;

	public MainFrame(DbController dbc) {
		this.dbc = dbc;
		frame = new JFrame("Dreambox Controller");
		frame.setSize(825, 500);
		frame.addWindowListener(new ExitListener());

		Container content = frame.getContentPane();
		content.setBackground(Color.black);
		content.setLayout(new FlowLayout());

		ButtonEventListener bel = new ButtonEventListener(this, dbc);

		btnPower = new JButton("Power");
		btnPower.addActionListener(bel);
		btnPower.setActionCommand("Power");
		content.add(btnPower);

		btnGetServices = new JButton("GetServices");
		btnGetServices.addActionListener(bel);
		btnGetServices.setActionCommand("GetServices");
		content.add(btnGetServices);

		listBouquets = new JList<Bouquet>(new DefaultListModel<Bouquet>());

		listBouquets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listBouquets.setLayoutOrientation(JList.VERTICAL);
		listBouquets.setVisibleRowCount(-1);

		listBouquetsScroller = new JScrollPane(listBouquets);
		listBouquetsScroller.setPreferredSize(new Dimension(200, 400));
		content.add(listBouquetsScroller);

		btnPick = new JButton("Pick");
		btnPick.addActionListener(bel);
		btnPick.setActionCommand("Pick");
		content.add(btnPick);

		listServices = new JList<Service>(new DefaultListModel<Service>());

		listServices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listServices.setLayoutOrientation(JList.VERTICAL);
		listServices.setVisibleRowCount(-1);

		listServicesScroller = new JScrollPane(listServices);
		listServicesScroller.setPreferredSize(new Dimension(200, 400));
		content.add(listServicesScroller);

		btnTune = new JButton("Tune");
		btnTune.addActionListener(bel);
		btnTune.setActionCommand("Tune");
		content.add(btnTune);

		btnView = new JButton("View");
		btnView.addActionListener(bel);
		btnView.setActionCommand("View");
		content.add(btnView);

		lbDb = new JLabel("DB:");
		content.add(lbDb);

		progressBarDb = new JProgressBar(0, 1600);
		progressBarDb.setValue(0);
		progressBarDb.setStringPainted(true);
		content.add(progressBarDb);

		lbSnr = new JLabel("SNR:");
		content.add(lbSnr);

		progressBarSnr = new JProgressBar(0, 100);
		progressBarSnr.setValue(0);
		progressBarSnr.setStringPainted(true);
		content.add(progressBarSnr);

		lbBer = new JLabel("BER:");
		content.add(lbBer);

		progressBarBer = new JProgressBar(0, 100);
		progressBarBer.setValue(0);
		progressBarBer.setStringPainted(true);
		content.add(progressBarBer);

		lbAcg = new JLabel("ACG:");
		content.add(lbAcg);

		progressBarAcg = new JProgressBar(0, 100);
		progressBarAcg.setValue(0);
		progressBarAcg.setStringPainted(true);
		content.add(progressBarAcg);

		try {
			SignalThread sig = new SignalThread(this);
			sig.start();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame.setVisible(true);
	}

	public JList<Bouquet> getListBouquets() {
		return listBouquets;
	}

	public JList<Service> getListServices() {
		return listServices;
	}

	public JProgressBar getProgressBarDb() {
		return progressBarDb;
	}

	public JProgressBar getProgressBarSnr() {
		return progressBarSnr;
	}

	public JProgressBar getProgressBarBer() {
		return progressBarBer;
	}

	public JProgressBar getProgressBarAcg() {
		return progressBarAcg;
	}
}
