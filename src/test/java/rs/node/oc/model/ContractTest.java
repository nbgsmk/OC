package rs.node.oc.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.TreeMap;

public class ContractTest {
	
	Call call;
	Put put;
	
	@BeforeMethod
	public void setUp() {
		call = new Call(100d);
		put = new Put(100d);
	}
	
	@Test
	private void testComboKalkulator() {
		Combo combo;
		double a;
		TreeMap<Double, Double> karakteristicneTacke;
		
		Leg lc = new Leg(2, new Call(400), 1.5);
		combo = new Combo();
		combo.add(lc);
		a = lc.getOpenPrice();
		a = lc.getExpirationPriceAt(398);
		a = lc.getExpirationPriceAt(399);
		a = lc.getExpirationPriceAt(400);
		a = lc.getExpirationPriceAt(403);
		a = combo.getComboOpenPrice();
		a = combo.getExpiredPriceAt(398);
		a = combo.getExpiredPriceAt(399);
		a = combo.getExpiredPriceAt(400);
		a = combo.getExpiredPriceAt(402.7);
		
		
		Leg lp = new Leg(-1, new Put(401), 0.5);
		combo = new Combo();
		combo.add(lp);
		a = lp.getOpenPrice();
		a = lp.getExpirationPriceAt(399);
		a = lp.getExpirationPriceAt(400);
		a = lp.getExpirationPriceAt(403);
		a = combo.getComboOpenPrice();
		a = combo.getExpiredPriceAt(399);
		a = combo.getExpiredPriceAt(400);
		a = combo.getExpiredPriceAt(402.7);
		
		combo.add(lc);
		a = combo.getComboOpenPrice();
		a = combo.getExpiredPriceAt(399);
		a = combo.getExpiredPriceAt(400);
		a = combo.getExpiredPriceAt(402.7);
		
		
		a = combo.getExpiredPriceAt(110);
		
		combo.add(new Leg(-1, new Put(399), 4.3));
		karakteristicneTacke = combo.getPnLPoints();
		
		combo = new Combo();
		combo.add(new Leg(1, new Call(398), 1.5));
		combo.add(new Leg(-2, new Call(401), 0.5));
		combo.add(new Leg(1, new Call(402), 0.35));
		karakteristicneTacke = combo.getPnLPoints();
		
		a = combo.getComboOpenPrice();
		a = combo.getExpiredPriceAt(390);
		a = combo.getExpiredPriceAt(400);
		a = combo.getExpiredPriceAt(410);
		
		
		
	}
	

}