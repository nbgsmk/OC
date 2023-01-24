package rs.node.oc.model;

import org.testng.Assert;
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

	// private final int amount = 1;
	private final double c_op = 1.5d;
	private final double p_op = 1.2d;
	private final double dx = 0.35d;

	@BeforeMethod
	public void setUp() {
		// 1 x long call
		long_call.add(1, new Call(400d), c_op);

		// 1 x long put
		long_put.add(1, new Put(400d), p_op);

		// 1 x short call call
		short_call.add(-1, new Call(400d), c_op);

		// 1 x short call put
		short_put.add(-1, new Put(400d), p_op);

		// call vertical (long call, short call)
		call_vertical.add(-1, new Call(400d), c_op);
		call_vertical.add(1, new Call(401d), c_op - dx);

		// call vertical (long call, short call)
		put_vertical.add(-1, new Put(401d), p_op);
		put_vertical.add(1, new Put(400d), p_op - dx);

	}
	
	@Test
	public void testGetComboOpenPrice() {
		assertEquals(long_call.getComboOpenPrice(), c_op);
		assertEquals(long_put.getComboOpenPrice(), p_op);

		assertEquals(short_call.getComboOpenPrice(), - 1 * c_op);
		assertEquals(short_put.getComboOpenPrice(), -1 * p_op);

		assertEquals(call_vertical.getComboOpenPrice(), (-1*(c_op)) + (1*(c_op-dx)));
		assertEquals(put_vertical.getComboOpenPrice(), (-1*(p_op)) + 1*(p_op-dx));
	}
	
	@Test
	public void testGetExpirationPriceAt() {
		// price of combo at expiration, at given underlying
		assertEquals(long_call.getExpirationPriceAt(399), 1);
		assertEquals(long_call.getExpirationPriceAt(400), 0);
		assertEquals(long_call.getExpirationPriceAt(401), 0);

		assertEquals(long_put.getExpirationPriceAt(399), 1);
		assertEquals(long_put.getExpirationPriceAt(400), 0);
		assertEquals(long_put.getExpirationPriceAt(401), 0);

		assertEquals(short_call.getExpirationPriceAt(399), 0);
		assertEquals(short_call.getExpirationPriceAt(400), 0);
		assertEquals(short_call.getExpirationPriceAt(401), -1);

		assertEquals(short_put.getExpirationPriceAt(399), -1);
		assertEquals(short_put.getExpirationPriceAt(400), 0);
		assertEquals(short_put.getExpirationPriceAt(401), 0);

		assertEquals(call_vertical.getExpirationPriceAt(399), 0);
		assertEquals(call_vertical.getExpirationPriceAt(400), 0);
		assertEquals(call_vertical.getExpirationPriceAt(401), 1);
		assertEquals(call_vertical.getExpirationPriceAt(402), 1);
		assertEquals(call_vertical.getExpirationPriceAt(403), 1);

		assertEquals(put_vertical.getExpirationPriceAt(398), -1);
		assertEquals(put_vertical.getExpirationPriceAt(399), -1);
		assertEquals(put_vertical.getExpirationPriceAt(400), -1);
		assertEquals(put_vertical.getExpirationPriceAt(401), 0);
		assertEquals(put_vertical.getExpirationPriceAt(402), 0);

	}
	
	@Test
	public void testGetPnlAt() {
		assertEquals(long_call.getPnlAt(399), (-1*c_op));
		assertEquals(long_call.getPnlAt(400), (-1*c_op));
		assertEquals(long_call.getPnlAt(401), (1-c_op));

		assertEquals(long_put.getPnlAt(399), 1-p_op);
		assertEquals(long_put.getPnlAt(400), -1*p_op);
		assertEquals(long_put.getPnlAt(401), -1*p_op);

		assertEquals(short_call.getPnlAt(399), c_op);
		assertEquals(short_call.getPnlAt(400), c_op);
		assertEquals(short_call.getPnlAt(401), -1+c_op);

		assertEquals(short_put.getPnlAt(399), 1-p_op);
		assertEquals(short_put.getPnlAt(400), p_op);
		assertEquals(short_put.getPnlAt(401), p_op);

		assertEquals(call_vertical.getPnlAt(399), (c_op-(c_op-dx)));
		assertEquals(call_vertical.getPnlAt(400), (c_op-(c_op-dx)));
		assertEquals(call_vertical.getPnlAt(401), -1+c_op-(c_op-dx));
		assertEquals(call_vertical.getPnlAt(402), (-2+c_op)+(c_op-dx));
		assertEquals(call_vertical.getPnlAt(403), (-3+c_op)+(2*(c_op-dx)));

		assertEquals(put_vertical.getPnlAt(398), (-3+p_op)-(2*(p_op-dx)));
		assertEquals(put_vertical.getPnlAt(399), (-2+p_op)-(p_op-dx));
		assertEquals(put_vertical.getPnlAt(400), (-1+p_op)-(p_op-dx));
		assertEquals(put_vertical.getPnlAt(401), (p_op)-(p_op-dx));
		assertEquals(put_vertical.getPnlAt(402), (p_op)-(p_op-dx));

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