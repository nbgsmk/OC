package rs.node.oc.data;

import rs.node.oc.model.Call;
import rs.node.oc.model.Combo;
import rs.node.oc.model.Pozicija;
import rs.node.oc.model.Put;

import java.util.TreeMap;

public class DemoCombo {

	public Combo getDemoCombo() {

		Combo combo = new Combo();
		TreeMap<Double, Double> karakteristicneTacke;
		double a;

		Pozicija pcall = new Pozicija(1, new Call(398), 1.5);
		Pozicija pput = new Pozicija(1, new Put(401), 0.5);
		combo.add(pcall);
		combo.add(pput);
		// combo.add(new Pozicija(-1, new Put(399), 4.3));
		karakteristicneTacke = combo.getPnLPoints();

		combo = new Combo();
		combo.add(new Pozicija(1, new Put(397), 1.5));
		combo.add(new Pozicija(-1, new Put(398), 1));
		combo.add(new Pozicija(-1, new Call(402), 1.5));
		combo.add(new Pozicija(1, new Call(403), 1));
		// combo.add(new Pozicija(1, new Call(400), 1.3));
		// combo.add(new Pozicija(1, new Put(400), 1.3));
		karakteristicneTacke = combo.getPnLPoints();

		a = combo.getComboOpenPrice();
		a = combo.getExpirationPriceAt(390);
		a = combo.getExpirationPriceAt(400);
		a = combo.getExpirationPriceAt(410);

		a = combo.getPnLAt(390);
		a = combo.getPnLAt(398);
		a = combo.getPnLAt(400);
		a = combo.getPnLAt(401);
		a = combo.getPnLAt(402);
		a = combo.getPnLAt(410);

		System.out.println("do yaya");

		return combo;
	}


}
