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
		Sight miAtraccion4 = new Sight("Vegita",200,1,100,Preferency.COMBATE);
		Sight miAtraccion5 = new Sight("CapsulaCorp",300,1,100,Preferency.BANQUETE);
		Sight miAtraccion6 = new Sight("Marte",500,1,100,Preferency.AVENTURA);
		Sight miAtraccion7 = new Sight("Inframundo",700,1,100,Preferency.AVENTURA);
		Sight miAtraccion8 = new Sight("Restaurante",200,1,100,Preferency.BANQUETE);		
		Sight miAtraccion9 = new Sight("MajinBu",100,1,100,Preferency.COMBATE);
		
		miListaDeAtracciones.add(miAtraccion);
		miListaDeAtracciones.add(miAtraccion2);
		miListaDeAtracciones.add(miAtraccion3);
		miListaDeAtracciones.add(miAtraccion4);
		miListaDeAtracciones.add(miAtraccion5);
		miListaDeAtracciones.add(miAtraccion6);
		miListaDeAtracciones.add(miAtraccion7);
		miListaDeAtracciones.add(miAtraccion8);
		miListaDeAtracciones.add(miAtraccion9);
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
		User piccolo = new User("PICCOLO",2300,23,Preferency.AVENTURA);
		
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
		
		PromotionAbsolute AbsCombatePromotion = new PromotionAbsolute(Preferency.COMBATE, 250);
		AbsCombatePromotion.loadSight("Namekusei", miListaDeAtracciones);
		AbsCombatePromotion.loadSight("Universo6", miListaDeAtracciones);
		MyPromotionListOrigin.add(AbsCombatePromotion);

		PromotionPercentual PerAventuraPromotion = new PromotionPercentual(Preferency.AVENTURA, 20);
		PerAventuraPromotion.loadSight("Tierra", miListaDeAtracciones);
		PerAventuraPromotion.loadSight("Marte", miListaDeAtracciones);
		MyPromotionListOrigin.add(PerAventuraPromotion);
		
		PromotionFreeSights FreeBanquetePromotion = new PromotionFreeSights(Preferency.BANQUETE);
		FreeBanquetePromotion.loadSight("Restaurante", miListaDeAtracciones);
		FreeBanquetePromotion.addFreeSight("Restaurante", miListaDeAtracciones);
		FreeBanquetePromotion.loadSight("CapsulaCorp", miListaDeAtracciones);
		FreeBanquetePromotion.loadSight("Planeta_de_Bills", miListaDeAtracciones);
		MyPromotionListOrigin.add(FreeBanquetePromotion);
		
		PromotionFreeSights FreeCombatePromotion = new PromotionFreeSights(Preferency.COMBATE);
		FreeCombatePromotion.loadSight("Namekusei", miListaDeAtracciones);
		FreeCombatePromotion.addFreeSight("Namekusei", miListaDeAtracciones);
		FreeCombatePromotion.loadSight("Universo6", miListaDeAtracciones);
		FreeCombatePromotion.addFreeSight("Universo6", miListaDeAtracciones);
		FreeCombatePromotion.loadSight("Vegita", miListaDeAtracciones);
		FreeCombatePromotion.loadSight("MajinBu", miListaDeAtracciones);
		MyPromotionListOrigin.add(FreeCombatePromotion);
		
		ArrayList<Promotion> MyPromotionList = FilesClass.ReadPromotion("archivesTest/Paquetes.in", miListaDeAtracciones);
		Assert.assertEquals(MyPromotionListOrigin, MyPromotionList);
	}
}