package TestPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import resourcesAndClasses.Preferency;
import resourcesAndClasses.Sight;
import resourcesAndClasses.User;

public class UserTest {
	
	private User user;
	private User user2;
	private User user3;
	private Sight atraccion;
	private Sight atraccion2;
	@Before
    public void setUp() {
		user = new User("GOKU",1500, 9, Preferency.COMBATE);
		user2 = new User("GOKU",1500, 9, Preferency.COMBATE);
		user3 = new User("VEGETTA",1700,10,Preferency.ACADEMICO);
		atraccion = new Sight("Namekusei",200,1,100,Preferency.COMBATE);
		atraccion2 = new Sight("Tierra",300,1,100,Preferency.ACADEMICO);
    }

	@Test
	public void testEqualsIguales() {

		assertEquals(user,user2);
	}
	
	@Test
	public void testEqualsNoIguales() {
		assertNotEquals(user,user3);
	}
	
	@Test
	public void testEqualsNoIgualesNon() {
		User veggeta = null;
		assertNotEquals(user,veggeta);
	}
	
	@Test
	public void testUpdate() {
		user = new User("GOKU",1500, 9, Preferency.COMBATE);
		user2 = new User("GOKU",1300, 8, Preferency.COMBATE);
		user2.setSpentMoney(200);
		user2.setSpentTime(1);
		user2.addAttraction(atraccion);
		user.update(atraccion);
		assertEquals(user,user2);
	}
	
	@Test
	public void testGetStrMySightsCON() {
		user.addAttraction(atraccion);
		user.addAttraction(atraccion2);
		String ListaNombres="Atracciones Compradas: "+atraccion.getName()+" "+atraccion2.getName()+" ";
		assertEquals(ListaNombres,user.getStrMySights());
	}
	
	@Test
	public void testGetStrMySightsSIN() {
		String ListaNombres="Atracciones Compradas: ";
		assertEquals(ListaNombres,user.getStrMySights());
	}
	
	@Test
	public void testMakeDiscount() {
		user = new User("GOKU",1500, 9, Preferency.COMBATE);
		user2 = new User("GOKU",1600, 9, Preferency.COMBATE);
		user2.setSpentMoney(-100);
		user.makeDiscount(100);
		assertEquals(user,user2);	
	}
	
	@Test
	public void testAlreadyBoughtSI() {
		user.update(atraccion);
		assertEquals(true,user.alreadyBought(atraccion));
	}
	
	@Test
	public void testAlreadyBoughtNO() {
		user.update(atraccion);
		assertEquals(false,user.alreadyBought(atraccion2));
	}

}
