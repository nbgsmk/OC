package rs.node.oc.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.TreeMap;

import static org.testng.Assert.*;

public class ContractTest {
	
	Call call;
	Put put;
	
	@BeforeMethod
	public void setUp() {
		call = new Call(400);
		put = new Put(400);
	}
	
	@Test
	private void testComboKalkulator() {
		Combo combo;
		double a;
		TreeMap<Double, Double> karakteristicneTacke;
		
		Leg lc = new Leg(2, new Call(400), 1.5);
		combo = new Combo();
		combo.add(lc);
		a = lc.getAvgPx();
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
		a = lp.getAvgPx();
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
		karakteristicneTacke = combo.getExtendedPnLPoints();
		
		combo = new Combo();
		combo.add(new Leg(1, new Call(398), 1.5));
		combo.add(new Leg(-2, new Call(401), 0.5));
		combo.add(new Leg(1, new Call(402), 0.35));
		karakteristicneTacke = combo.getExtendedPnLPoints();
		
		a = combo.getComboOpenPrice();
		a = combo.getExpiredPriceAt(390);
		a = combo.getExpiredPriceAt(400);
		a = combo.getExpiredPriceAt(410);
		
		
		
	}
	
	
	@Test
	public void testIsITM() {
		assertFalse(call.isITMaAt(399));
		assertFalse(call.isITMaAt(400));
		assertTrue(call.isITMaAt(401));       // in the money
		
		assertTrue(put.isITMaAt(399));         // in the money
		assertFalse(put.isITMaAt(400));
		assertFalse(put.isITMaAt(401));
	}
}