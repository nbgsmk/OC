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
	private void testGetIntrinsic() {
		Combo combo;
		double a;
		TreeMap<Double, Double> karakteristicneTacke;
		
		Pozicija pc = new Pozicija(1, new Call(398), 1.5);
		combo = new Combo();
		combo.add(pc);
		a = pc.getOpenPrice();
		a = pc.getExpirationPriceAt(399);
		a = pc.getExpirationPriceAt(400);
		a = pc.getExpirationPriceAt(403);
		a = combo.getOpenPrice();
		a = combo.getExpirationPriceAt(399);
		a = combo.getExpirationPriceAt(400);
		a = combo.getExpirationPriceAt(402.7);
		
		
		Pozicija pp = new Pozicija(-1, new Put(401), 0.5);
		combo = new Combo();
		combo.add(pp);
		a = pp.getOpenPrice();
		a = pp.getExpirationPriceAt(399);
		a = pp.getExpirationPriceAt(400);
		a = pp.getExpirationPriceAt(403);
		a = combo.getOpenPrice();
		a = combo.getExpirationPriceAt(399);
		a = combo.getExpirationPriceAt(400);
		a = combo.getExpirationPriceAt(402.7);
		
		combo.add(pc);
		a = combo.getOpenPrice();
		a = combo.getExpirationPriceAt(399);
		a = combo.getExpirationPriceAt(400);
		a = combo.getExpirationPriceAt(402.7);
		
		
		a = combo.getExpirationPriceAt(110);
		
		combo.add(new Pozicija(-1, new Put(399), 4.3));
		karakteristicneTacke = combo.getCharacteristicPoints();
		
		combo = new Combo();
		combo.add(new Pozicija(1, new Call(398), 1.5));
		combo.add(new Pozicija(-2, new Call(401), 0.5));
		combo.add(new Pozicija(1, new Call(402), 0.35));
		karakteristicneTacke = combo.getCharacteristicPoints();
		
		a = combo.getOpenPrice();
		a = combo.getExpirationPriceAt(390);
		a = combo.getExpirationPriceAt(400);
		a = combo.getExpirationPriceAt(410);
		
		
		
	}
	

}