package TestPackage;

import resourcesAndClasses.*;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ArchiveTest {

	ArrayList<Sight> miListaDeAtracciones = new ArrayList<Sight>();
	
	@Before
	public void setup() {

		
		Sight miAtraccion = new Sight("Namekusei",200,1,100,Preferency.COMBATE);
		Sight miAtraccion2 = new Sight("Tierra",300,1,100,Preferency.AVENTURA);
		Sight miAtraccion3 = new Sight("Universo6",500,1,100,Preferency.COMBATE);
		Sight miAtraccion4 = new Sight("Vegita",200,1,100,Preferency.BANQUETE);
		Sight miAtraccion5 = new Sight("TacosDelTioNacho",300,1,100,Preferency.BANQUETE);
		Sight miAtraccion6 = new Sight("Marte",500,1,100,Preferency.COMBATE);
		Sight miAtraccion7 = new Sight("Inframundo",700,1,100,Preferency.COMBATE);		
		Sight miAtraccion8 = new Sight("UNLAM",400,1,100,Preferency.AVENTURA);
		
		miListaDeAtracciones.add(miAtraccion);
		miListaDeAtracciones.add(miAtraccion2);
		miListaDeAtracciones.add(miAtraccion3);
		miListaDeAtracciones.add(miAtraccion4);
		miListaDeAtracciones.add(miAtraccion5);
		miListaDeAtracciones.add(miAtraccion6);
		miListaDeAtracciones.add(miAtraccion7);
		miListaDeAtracciones.add(miAtraccion8);
	}
	
	@Test
	public void testAtracciones() {		
		ArrayList<Sight> myAtraccionList = FilesClass.ReadSights("archivesTest/Atracciones.in");
		
		Assert.assertEquals(miListaDeAtracciones, myAtraccionList);	
	}
	
	@Test
	public void testUser() {

		User goku = new User("GOKU",1500, 9, Preferency.COMBATE);
		User veggeta = new User("VEGETTA",1700,10,Preferency.COMBATE);
		User trunks = new User("TRUNKS",1900,15,Preferency.BANQUETE);
		User piccolo = new User("PICCOLO",2300,23,Preferency.COMBATE);
		
		ArrayList<User> UserList = new ArrayList<User>();
		UserList.add(goku);
		UserList.add(veggeta);
		UserList.add(trunks);
		UserList.add(piccolo);
		ArrayList<User> ArchiveUserList = FilesClass.ReadUsers("archivesTest/Usuarios.in");		
		Assert.assertEquals(UserList, ArchiveUserList);
	}

	@Test
	public void testPromotion() {
		ArrayList<Promotion> MyPromotionListOrigin = new ArrayList<Promotion>();
		
		PromotionPercentual PerCombatePromotion = new PromotionPercentual(Preferency.COMBATE, 20);
		PerCombatePromotion.loadSight("Vegita", miListaDeAtracciones);
		PerCombatePromotion.loadSight("Marte", miListaDeAtracciones);
		MyPromotionListOrigin.add(PerCombatePromotion);
		
		PromotionAbsolute AbsAventuraPromotion = new PromotionAbsolute(Preferency.AVENTURA, 300);
		AbsAventuraPromotion.loadSight("Tierra", miListaDeAtracciones);
		AbsAventuraPromotion.loadSight("UNLAM", miListaDeAtracciones);
		MyPromotionListOrigin.add(AbsAventuraPromotion);
		
		PromotionAbsolute AbsBanquetepromotion = new PromotionAbsolute(Preferency.BANQUETE, 100);
		AbsBanquetepromotion.loadSight("TacosDelTioNacho", miListaDeAtracciones);
		AbsBanquetepromotion.loadSight("Vegita", miListaDeAtracciones);
		MyPromotionListOrigin.add(AbsBanquetepromotion);
		
		PromotionFreeSights FreeCombatepromotion = new PromotionFreeSights(Preferency.COMBATE);
		FreeCombatepromotion.loadSight("Marte", miListaDeAtracciones);
		FreeCombatepromotion.loadSight("Inframundo", miListaDeAtracciones);
		FreeCombatepromotion.addFreeSight("Namekusei", miListaDeAtracciones);
		MyPromotionListOrigin.add(FreeCombatepromotion);
		
		PromotionPercentual PerCombatepromotion = new PromotionPercentual(Preferency.COMBATE, 20);
		PerCombatepromotion.loadSight("Marte", miListaDeAtracciones);
		PerCombatepromotion.loadSight("Namekusei", miListaDeAtracciones);
		MyPromotionListOrigin.add(PerCombatepromotion);
		
		ArrayList<Promotion> MyPromotionList = FilesClass.ReadPromotion("archivesTest/Paquetes.in", miListaDeAtracciones);
		Assert.assertEquals(MyPromotionListOrigin, MyPromotionList);
	}
}