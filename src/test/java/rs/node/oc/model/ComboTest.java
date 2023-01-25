package rs.node.oc.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.TreeMap;
import java.util.concurrent.locks.Condition;

import static org.testng.Assert.*;

public class ComboTest {
	private final double err = 0.000000001;
	
	Combo long_call;
	Combo long_put;
	Combo short_call;
	Combo short_put;
	Combo call_vertical;
	Combo put_vertical;
	
	private final double co = 1.5d;
	private final double po = 1.2d;
	private final double dd = 0.35d;

	@BeforeMethod
	public void setUp() {
		
		long_call = new Combo();
		long_put = new Combo();
		short_call = new Combo();
		short_put = new Combo();
		call_vertical = new Combo();
		put_vertical = new Combo();
		
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
		long_call = new Combo();
		long_put = new Combo();
		short_call = new Combo();
		short_put = new Combo();
		call_vertical = new Combo();
		put_vertical = new Combo();
		
		long_call.add(amt, new Call(400), co);
		long_put.add(amt, new Put(400), po);
		
		short_call.add(-amt, new Call(400), co);
		short_put.add(-amt, new Put(400), po);
		
		call_vertical.add(-amt, new Call(399), co);
		call_vertical.add(amt, new Call(400), dd);
		
		put_vertical.add(-amt, new Put(400), po);
		put_vertical.add(amt, new Put(399), dd);
		
		// long call i put
		assertEquals(long_call.getComboOpenPrice(), amt*co, err);
		assertEquals(long_put.getComboOpenPrice(), amt*po, err);
		// short call i put
		assertEquals(short_call.getComboOpenPrice(), (-amt)*co, err);
		assertEquals(short_put.getComboOpenPrice(), (-amt)*po, err);
		// call i put vertical
		assertEquals(call_vertical.getComboOpenPrice(), amt*(-co+dd), err);
		assertEquals(put_vertical.getComboOpenPrice(), amt*(-po+dd), err);
	}
	
	
	@Test
	public void testGetComboOpenPrice() {
		assertEquals(long_call.getComboOpenPrice(), co, err);
		assertEquals(long_put.getComboOpenPrice(), po, err);

		assertEquals(short_call.getComboOpenPrice(), (-1)*co, err);
		assertEquals(short_put.getComboOpenPrice(), (-1)*po, err);

		assertEquals(call_vertical.getComboOpenPrice(), (-co)+dd, err);
		assertEquals(put_vertical.getComboOpenPrice(), (-po)+dd, err);
	}
	
	@Test
	public void testGetExpirationPriceAt() {
		// price of combo at expiration, at given underlying
		assertEquals(long_call.getExpiredPriceAt(399), 0, err);
		assertEquals(long_call.getExpiredPriceAt(400), 0, err);
		assertEquals(long_call.getExpiredPriceAt(401), 1, err);

		assertEquals(long_put.getExpiredPriceAt(399), 1, err);
		assertEquals(long_put.getExpiredPriceAt(400), 0, err);
		assertEquals(long_put.getExpiredPriceAt(401), 0, err);

		assertEquals(short_call.getExpiredPriceAt(399), 0, err);
		assertEquals(short_call.getExpiredPriceAt(400), 0, err);
		assertEquals(short_call.getExpiredPriceAt(401), -1, err);

		assertEquals(short_put.getExpiredPriceAt(399), -1, err);
		assertEquals(short_put.getExpiredPriceAt(400), 0, err);
		assertEquals(short_put.getExpiredPriceAt(401), 0, err);

		assertEquals(call_vertical.getExpiredPriceAt(399), 0, err);
		assertEquals(call_vertical.getExpiredPriceAt(400), 0, err);
		assertEquals(call_vertical.getExpiredPriceAt(401), -1, err);
		assertEquals(call_vertical.getExpiredPriceAt(402), -1, err);
		assertEquals(call_vertical.getExpiredPriceAt(403), -1, err);

		assertEquals(put_vertical.getExpiredPriceAt(398), -1, err);
		assertEquals(put_vertical.getExpiredPriceAt(399), -1, err);
		assertEquals(put_vertical.getExpiredPriceAt(400), -1, err);
		assertEquals(put_vertical.getExpiredPriceAt(401), 0, err);
		assertEquals(put_vertical.getExpiredPriceAt(402), 0, err);

	}
	
	@Test
	public void testGetPnlAt() {
		assertEquals(long_call.getPnlAt(399), -co, err);
		assertEquals(long_call.getPnlAt(400), -co, err);
		assertEquals(long_call.getPnlAt(401), 1-co, err);

		assertEquals(long_put.getPnlAt(399), 1-po, err);
		assertEquals(long_put.getPnlAt(400), -po, err);
		assertEquals(long_put.getPnlAt(401), -po, err);

		assertEquals(short_call.getPnlAt(399), co, err);
		assertEquals(short_call.getPnlAt(400), co, err);
		assertEquals(short_call.getPnlAt(401), -1+co, err);

		assertEquals(short_put.getPnlAt(399), -1+po, err);
		assertEquals(short_put.getPnlAt(400), po, err);
		assertEquals(short_put.getPnlAt(401), po, err);

		assertEquals(call_vertical.getPnlAt(399), (co-dd), err);
		assertEquals(call_vertical.getPnlAt(400), (co-dd), err);
		assertEquals(call_vertical.getPnlAt(401), -1+(co-dd), err);
		assertEquals(call_vertical.getPnlAt(402), -2+1+(co-dd), err);
		assertEquals(call_vertical.getPnlAt(403), -3+2+(co-dd), err);

		assertEquals(put_vertical.getPnlAt(398), -3+2+(po-dd), err);
		assertEquals(put_vertical.getPnlAt(399), -2+1+(po-dd), err);
		assertEquals(put_vertical.getPnlAt(400), -1+(po-dd), err);
		assertEquals(put_vertical.getPnlAt(401), (po-dd), err);
		assertEquals(put_vertical.getPnlAt(402), (po-dd), err);

	}
	
	@Test
	public void testGetPnLPoints() {
		assertEquals(long_call.getPnLPoints().size(), 1, err);
		assertEquals(long_put.getPnLPoints().size(), 1, err);
		
		assertEquals(call_vertical.getPnLPoints().size(), 2, err);
		assertEquals(put_vertical.getPnLPoints().size(), 2, err);
		
		TreeMap<Double, Double> pnlp = new TreeMap<>();
		
		pnlp = long_call.getPnLPoints();
		
		Combo vert = new Combo();
		vert.add(-1, new Call(400), 1.5);
		vert.add(1, new Call(401), 1.5);
		pnlp = vert.getPnLPoints();
		
		Combo condor = new Combo();
		condor.add(1, new Put(398), 1.5);
		condor.add(-1, new Put(399), 1);
		condor.add(-1, new Call(401), 1);
		condor.add(1, new Call(402), 1);
		pnlp = vert.getPnLPoints();
		
		
	}
	
	@Test
	public void testGetLegs() {
		assertNotNull(long_call.getLegs());
		assertEquals(long_call.getLegs().size(), 1, err);
		assertEquals(call_vertical.getLegs().size(), 2, err);
	}
	
	@Test
	public void testGetDelta() {
		Combo combo = new Combo();
		Leg c = new Leg(1, new Call(400), 1.5);
		Leg p = new Leg(1, new Call(400), 1.5);
		c.setDelta(0.5);
		p.setDelta(0.5);
		combo.add(c);
		combo.add(p);
	
		// TODO kako se racuna delta od celog comba?
	}
	
	@Test
	public void testGetMaxProfit() {
	}
	
	@Test
	public void testGetMaxLoss() {
	}
}