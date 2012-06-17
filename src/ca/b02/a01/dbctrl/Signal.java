package ca.b02.a01.dbctrl;

public class Signal {
	private float db;
	private int snr;
	private int ber;
	private int acg;

	public float getDb() {
		return db;
	}

	public void setDb(float db) {
		this.db = db;
	}

	public int getSnr() {
		return snr;
	}

	public void setSnr(int snr) {
		this.snr = snr;
	}

	public int getBer() {
		return ber;
	}

	public void setBer(int ber) {
		this.ber = ber;
	}

	public int getAcg() {
		return acg;
	}

	public void setAcg(int acg) {
		this.acg = acg;
	}
}
