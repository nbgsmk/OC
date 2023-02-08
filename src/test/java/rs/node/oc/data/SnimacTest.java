package rs.node.oc.data;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rs.node.oc.io.Snimac;
import rs.node.oc.model.Combo;
import rs.node.oc.model.Put;

public class SnimacTest {
	
	Snimac snimac;
	Combo combo = new Combo("combo snimac test", "combo snimac dugacki opis");
	
	@BeforeMethod
	public void setUp() {
		snimac = new Snimac();
		combo.add(1, new Put(400), 1.5);
		combo.add(-1, new Put(401), 0.5);
	}
	
	@Test
	public void testSaveObj() {
		snimac.writeGenericObj("rajl", combo);
	}
	
	@Test
	public void testReadObj() {
		Combo getted = new Combo();
		Object i = snimac.readGenericObj("rajl");
		getted = (Combo) i;
	}
	
	
	@Test
	public void testWriteXml() {
		snimac.writeXml("rajl", combo);
	}
	
	@Test
	public void testReadXml() {
		Object c = snimac.readXml("rajl");
		combo = (Combo) c;
	}
}