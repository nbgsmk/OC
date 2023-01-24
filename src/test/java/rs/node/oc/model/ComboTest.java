package rs.node.oc.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ComboTest {
	Combo long_call = new Combo();
	Combo long_put = new Combo();
	Combo short_call = new Combo();
	Combo short_put = new Combo();
	Combo call_vertical = new Combo();
	Combo put_vertical = new Combo();

	private final double co = 1.5d;
	private final double po = 1.2d;
	private final double dd = 0.35d;

	@BeforeMethod
	public void setUp() {
		// 1 x long call
		long_call.add(1, new Call(400), co);

		// 1 x long put
		long_put.add(1, new Put(400), po);

		// 1 x short call call
		short_call.add(-1, new Call(400), co);

		// 1 x short call put
		short_put.add(-1, new Put(400), po);

		// call vertical (long call, short call)
		call_vertical.add(-1, new Call(400), co);
		call_vertical.add(1, new Call(401), dd);

		// call vertical (long call, short call)
		put_vertical.add(-1, new Put(401), po);
		put_vertical.add(1, new Put(400), dd);
		

	}
	
	@Test
	public void testAmount(){
		// Da li AMOUNT mnozi pravilno?
		final int amt = 3;
		
		// priprema
		long_call.add(amt, new Call(400), co);
		long_put.add(amt, new Put(400), po);
		
		short_call.add(-amt, new Call(400), co);
		short_put.add(-amt, new Put(400), po);
		
		call_vertical.add(-amt, new Call(399), co);
		call_vertical.add(amt, new Call(400), dd);
		
		put_vertical.add(-amt, new Put(400), po);
		put_vertical.add(amt, new Put(399), dd);
		
		// long call i put
		assertEquals(long_call.getComboOpenPrice(), amt*co);
		assertEquals(long_put.getComboOpenPrice(), amt*po);
		// short call i put
		assertEquals(short_call.getComboOpenPrice(), (-amt)*co);
		assertEquals(short_put.getComboOpenPrice(), (-amt)*po);
		// call i put vertical
		assertEquals(call_vertical.getComboOpenPrice(), amt*(-co+dd));
		assertEquals(put_vertical.getComboOpenPrice(), amt*(-po+dd));
	}
	
	
	@Test
	public void testGetComboOpenPrice() {
		assertEquals(long_call.getComboOpenPrice(), co);
		assertEquals(long_put.getComboOpenPrice(), po);

		assertEquals(short_call.getComboOpenPrice(), (-1)*co);
		assertEquals(short_put.getComboOpenPrice(), (-1)*po);

		assertEquals(call_vertical.getComboOpenPrice(), (-co)+dd);
		assertEquals(put_vertical.getComboOpenPrice(), (-po)+dd);
	}
	
	@Test
	public void testGetExpirationPriceAt() {
		// price of combo at expiration, at given underlying
		assertEquals(long_call.getExpiredPriceAt(399), 1);
		assertEquals(long_call.getExpiredPriceAt(400), 0);
		assertEquals(long_call.getExpiredPriceAt(401), 0);

		assertEquals(long_put.getExpiredPriceAt(399), 1);
		assertEquals(long_put.getExpiredPriceAt(400), 0);
		assertEquals(long_put.getExpiredPriceAt(401), 0);

		assertEquals(short_call.getExpiredPriceAt(399), 0);
		assertEquals(short_call.getExpiredPriceAt(400), 0);
		assertEquals(short_call.getExpiredPriceAt(401), -1);

		assertEquals(short_put.getExpiredPriceAt(399), -1);
		assertEquals(short_put.getExpiredPriceAt(400), 0);
		assertEquals(short_put.getExpiredPriceAt(401), 0);

		assertEquals(call_vertical.getExpiredPriceAt(399), 0);
		assertEquals(call_vertical.getExpiredPriceAt(400), 0);
		assertEquals(call_vertical.getExpiredPriceAt(401), -1);
		assertEquals(call_vertical.getExpiredPriceAt(402), -1);
		assertEquals(call_vertical.getExpiredPriceAt(403), -1);

		assertEquals(put_vertical.getExpiredPriceAt(398), -1);
		assertEquals(put_vertical.getExpiredPriceAt(399), -1);
		assertEquals(put_vertical.getExpiredPriceAt(400), -1);
		assertEquals(put_vertical.getExpiredPriceAt(401), 0);
		assertEquals(put_vertical.getExpiredPriceAt(402), 0);

	}
	
	@Test
	public void testGetPnlAt() {
		assertEquals(long_call.getPnlAt(399), -co);
		assertEquals(long_call.getPnlAt(400), -co);
		assertEquals(long_call.getPnlAt(401), 1-co);

		assertEquals(long_put.getPnlAt(399), 1-po);
		assertEquals(long_put.getPnlAt(400), -po);
		assertEquals(long_put.getPnlAt(401), -po);

		assertEquals(short_call.getPnlAt(399), co);
		assertEquals(short_call.getPnlAt(400), co);
		assertEquals(short_call.getPnlAt(401), -1+co);

		assertEquals(short_put.getPnlAt(399), -1+po);
		assertEquals(short_put.getPnlAt(400), po);
		assertEquals(short_put.getPnlAt(401), po);

		assertEquals(call_vertical.getPnlAt(399), (co-dd));
		assertEquals(call_vertical.getPnlAt(400), (co-dd));
		assertEquals(call_vertical.getPnlAt(401), -1+(co-dd));
		assertEquals(call_vertical.getPnlAt(402), -2+1-(co-dd));
		assertEquals(call_vertical.getPnlAt(403), -3+2-(co-dd));

		assertEquals(put_vertical.getPnlAt(398), -3+2+(po-dd));
		assertEquals(put_vertical.getPnlAt(399), -2+1-(po-dd));
		assertEquals(put_vertical.getPnlAt(400), -1+(po-dd));
		assertEquals(put_vertical.getPnlAt(401), (po-dd));
		assertEquals(put_vertical.getPnlAt(402), (po-dd));

	}
	
	@Test
	public void testGetPnLPoints() {
	}
	
	@Test
	public void testGetLegs() {
	}
	
	@Test
	public void testGetDelta() {
	}
	
	@Test
	public void testGetMaxProfit() {
	}
	
	@Test
	public void testGetMaxLoss() {
	}
}