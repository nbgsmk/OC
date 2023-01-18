package rs.node.oc.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.testng.Assert.*;

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
		
		Pozicija pc = new Pozicija(1, new Call(400), 5.11);
		combo = new Combo();
		combo.add(pc);
		a = pc.getPx();
		a = pc.getPxAtExpiration(399);
		a = pc.getPxAtExpiration(400);
		a = pc.getPxAtExpiration(403);
		a = combo.getPx();
		a = combo.getExpirationPxAt(399);
		a = combo.getExpirationPxAt(400);
		a = combo.getExpirationPxAt(402.7);
		
		
		Pozicija pp = new Pozicija(1, new Put(400), 2.45);
		combo = new Combo();
		combo.add(pp);
		a = pp.getPx();
		a = pp.getPxAtExpiration(399);
		a = pp.getPxAtExpiration(400);
		a = pp.getPxAtExpiration(403);
		a = combo.getPx();
		a = combo.getExpirationPxAt(399);
		a = combo.getExpirationPxAt(400);
		a = combo.getExpirationPxAt(402.7);
		
		combo.add(pc);
		a = combo.getPx();
		a = combo.getExpirationPxAt(399);
		a = combo.getExpirationPxAt(400);
		a = combo.getExpirationPxAt(402.7);
		
		
		a = combo.getExpirationPxAt(110);
		
		
		
	}
	
	@Test
	public void testGetExtrinsic() {
	}
}