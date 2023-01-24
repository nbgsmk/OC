package rs.node.oc.data;

import rs.node.oc.model.Call;
import rs.node.oc.model.Combo;
import rs.node.oc.model.Leg;
import rs.node.oc.model.Put;

import java.util.TreeMap;

public class DemoCombo {

	public Combo getDemoCombo() {

		Combo combo = new Combo();
		TreeMap<Double, Double> karakteristicneTacke;
		double a;

		Leg pcall = new Leg(1, new Call(398), 1.5);
		Leg pput = new Leg(1, new Put(401), 0.5);
		combo.add(pcall);
		combo.add(pput);
		// combo.add(new Leg(-1, new Put(399), 4.3));
		karakteristicneTacke = combo.getPnLPoints();

		combo = new Combo();
		combo.add(new Leg(1, new Put(397), 1.5));
		combo.add(new Leg(-1, new Put(398), 1));
		combo.add(new Leg(-1, new Call(402), 1.5));
		combo.add(new Leg(1, new Call(403), 1));
		// combo.add(new Leg(1, new Call(400), 1.3));
		// combo.add(new Leg(1, new Put(400), 1.3));
		karakteristicneTacke = combo.getPnLPoints();

		a = combo.getComboOpenPrice();
		a = combo.getExpiredPriceAt(390);
		a = combo.getExpiredPriceAt(400);
		a = combo.getExpiredPriceAt(410);

		a = combo.getPnlAt(390);
		a = combo.getPnlAt(398);
		a = combo.getPnlAt(400);
		a = combo.getPnlAt(401);
		a = combo.getPnlAt(402);
		a = combo.getPnlAt(410);

		System.out.println(getClass().getSimpleName() + ": do yaya");

		return combo;
	}


}
