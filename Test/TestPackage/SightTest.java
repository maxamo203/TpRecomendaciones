package TestPackage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import resourcesAndClasses.Preferency;
import resourcesAndClasses.Sight;
import resourcesAndClasses.User;

public class SightTest {
	private User user;
	private Sight atraccion;
	private Sight atraccion2;

	@Before
	public void setUp() {
		user = new User("VEGETTA", 1700, 10, Preferency.COMBATE);
		atraccion = new Sight("Namekusei", 200, 1, 100, Preferency.COMBATE);
		atraccion2 = new Sight("Namekusei", 200, 1, 99, Preferency.COMBATE);
	}

	@Test
	public void testBoughtBy() {
		atraccion.boughtBy(user);
		assertEquals(atraccion2, atraccion);
	}

	@Test
	public void testCanBeBoughtBySI() {
		assertEquals(true, atraccion.canBeBoughtBy(user));

	}

	@Test
	public void testCanBeBoughtByNO() {
		user = new User("VEGETTA", 0, 0, Preferency.COMBATE);
		assertEquals(false, atraccion.canBeBoughtBy(user));
	}

	@Test
	public void testCanBeBoughtByNO2() {
		user = new User("Goku", 10000, 10, Preferency.COMBATE);
		atraccion = new Sight("Namekusei", 200, 1, 0, Preferency.AVENTURA);
		assertEquals(false, atraccion.canBeBoughtBy(user));
	}

	@Test
	public void sightsTestAsesores() {
		atraccion = new Sight("Namekusei", 200, 1, 100, Preferency.AVENTURA);
		assertEquals("Namekusei", atraccion.getName());
		assertEquals(200.0, atraccion.getCost(), 0.1);
		assertEquals(1.0, atraccion.getTime(), 0.1);
		assertEquals(100, atraccion.getQuota());
		assertEquals(Preferency.AVENTURA, atraccion.getType());
	}

}
