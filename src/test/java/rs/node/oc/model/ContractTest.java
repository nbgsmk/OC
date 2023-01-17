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
		assertEquals(call.getIntrinsic(102.3d), 2.3d);
		assertEquals(call.getIntrinsic(99d), 0d);
		assertEquals(call.getIntrinsic(100d), 0d);
		
		assertEquals(put.getIntrinsic(99.7d), 0.3d);
		assertEquals(put.getIntrinsic(103.7d), 0d);
		assertEquals(put.getIntrinsic(100d), 90d);
		
	}
	
	@Test
	public void testGetExtrinsic() {
	}
}