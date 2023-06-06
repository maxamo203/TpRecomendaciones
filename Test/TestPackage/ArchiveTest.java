package TestPackage;

import resourcesAndClasses.*;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;


public class ArchiveTest {

	@Test
	public void testAtracciones() {
		Archive atracciones = new Archive();
		
		Sight miAtraccion = new Sight("Namekusei",200,1,100,Preferency.COMBATE);
		Sight miAtraccion2 = new Sight("Tierra",300,1,100,Preferency.ACADEMICO);
		Sight miAtraccion3 = new Sight("Universo6",500,1,100,Preferency.COMBATE);
		Sight miAtraccion4 = new Sight("Vegita",200,1,100,Preferency.BANQUETE);
		Sight miAtraccion5 = new Sight("TacosDelTioNacho",300,1,100,Preferency.BANQUETE);
		Sight miAtraccion6 = new Sight("Marte",500,1,100,Preferency.COMBATE);
		Sight miAtraccion7 = new Sight("Inframundo",700,1,100,Preferency.COMBATE);		
		Sight miAtraccion8 = new Sight("UNLAM",400,1,100,Preferency.ACADEMICO);
		
		ArrayList<Sight> miListaDeAtracciones = new ArrayList<Sight>();
		miListaDeAtracciones.add(miAtraccion);
		miListaDeAtracciones.add(miAtraccion2);
		miListaDeAtracciones.add(miAtraccion3);
		miListaDeAtracciones.add(miAtraccion4);
		miListaDeAtracciones.add(miAtraccion5);
		miListaDeAtracciones.add(miAtraccion6);
		miListaDeAtracciones.add(miAtraccion7);
		miListaDeAtracciones.add(miAtraccion8);
		
		ArrayList<Sight> myAtraccionList = atracciones.ReadSights("archiveTemporal/Atracciones.in");
		System.out.println(myAtraccionList);
		Assert.assertEquals(miListaDeAtracciones, myAtraccionList);	
	}
	
	@Test
	public void testUser() {
		Archive UserArchive = new Archive();
		
		User goku = new User("GOKU",1500, 9, Preferency.COMBATE);
		User veggeta = new User("VEGETTA",1700,10,Preferency.ACADEMICO);
		User trunks = new User("TRUNKS",1900,15,Preferency.BANQUETE);
		User piccolo = new User("PICCOLO",2300,23,Preferency.COMBATE);
		
		ArrayList<User> UserList = new ArrayList<User>();
		UserList.add(goku);
		UserList.add(veggeta);
		UserList.add(trunks);
		UserList.add(piccolo);
		ArrayList<User> ArchiveUserList = UserArchive.ReadUsers("archiveTemporal/Usuarios.in");		
		Assert.assertEquals(UserList, ArchiveUserList);
	}
	
	@Test
	public void testPromotion() {
		Archive PromotionArchive = new Archive();

		Sight miAtraccion = new Sight("Namekusei",200,1,100,Preferency.COMBATE);
		Sight miAtraccion2 = new Sight("Tierra",300,1,100,Preferency.ACADEMICO);
		Sight miAtraccion3 = new Sight("UNLAM",300,1,100,Preferency.ACADEMICO);
		Sight miAtraccion4 = new Sight("Universo6",500,1,100,Preferency.COMBATE);
		Sight miAtraccion5 = new Sight("TacosDelTioNacho",200,1,100,Preferency.BANQUETE);
		Sight miAtraccion6 = new Sight("Vegita",200,1,100,Preferency.BANQUETE);
		Sight miAtraccion7 = new Sight("Marte",500,1,100,Preferency.COMBATE);
		Sight miAtraccion8 = new Sight("Inframundo",700,1,100,Preferency.COMBATE);		
		
		ArrayList<Sight> miListaDeAtracciones = new ArrayList<Sight>();
		miListaDeAtracciones.add(miAtraccion);
		miListaDeAtracciones.add(miAtraccion2);
		miListaDeAtracciones.add(miAtraccion3);
		miListaDeAtracciones.add(miAtraccion4);
		miListaDeAtracciones.add(miAtraccion5);
		miListaDeAtracciones.add(miAtraccion6);
		miListaDeAtracciones.add(miAtraccion7);
		miListaDeAtracciones.add(miAtraccion8);
		
		ArrayList<Promotion> MyPromotionList = PromotionArchive.ReadPromotion("archiveTemporal/Paquetes.in", miListaDeAtracciones);
		Assert.assertEquals(miListaDeAtracciones, MyPromotionList);
		
	}
}
