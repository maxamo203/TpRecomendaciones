package TestPackage;

import resourcesAndClasses.*;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArchiveTest {
	ArrayList<Sight> myAtraccionListTest = new ArrayList<Sight>();
	ArrayList<Promotion> MyPromotionListTest = new ArrayList<Promotion>();
	ArrayList<User> UserListTest = new ArrayList<User>();

	@Before
	public void setup() {

		// ATTRACTION TEST
		Sight attraction1 = new Sight("Namekusei", 200, 1, 100, Preferency.COMBATE);
		Sight attraction2 = new Sight("Tierra", 300, 1, 100, Preferency.AVENTURA);
		Sight attraction3 = new Sight("Universo6", 500, 1, 100, Preferency.COMBATE);
		Sight attraction4 = new Sight("Vegita", 200, 1, 100, Preferency.COMBATE);
		Sight attraction5 = new Sight("CapsulaCorp", 300, 1, 100, Preferency.BANQUETE);
		Sight attraction6 = new Sight("Marte", 500, 1, 100, Preferency.AVENTURA);
		Sight attraction7 = new Sight("Inframundo", 700, 1, 100, Preferency.AVENTURA);
		Sight attraction8 = new Sight("Planeta_de_Bills", 400, 1, 100, Preferency.BANQUETE);
		Sight attraction9 = new Sight("Restaurante", 200, 1, 100, Preferency.BANQUETE);
		Sight attraction10 = new Sight("MajinBu", 100, 1, 100, Preferency.COMBATE);

		myAtraccionListTest.add(attraction1);
		myAtraccionListTest.add(attraction2);
		myAtraccionListTest.add(attraction3);
		myAtraccionListTest.add(attraction4);
		myAtraccionListTest.add(attraction5);
		myAtraccionListTest.add(attraction6);
		myAtraccionListTest.add(attraction7);
		myAtraccionListTest.add(attraction8);
		myAtraccionListTest.add(attraction9);
		myAtraccionListTest.add(attraction10);

		// USER TEST
		User goku = new User("GOKU", 1500, 9, Preferency.COMBATE);
		User veggeta = new User("VEGETTA", 1700, 10, Preferency.COMBATE);
		User trunks = new User("TRUNKS", 1900, 15, Preferency.BANQUETE);
		User piccolo = new User("PICCOLO", 2300, 23, Preferency.AVENTURA);
		UserListTest.add(goku);
		UserListTest.add(veggeta);
		UserListTest.add(trunks);
		UserListTest.add(piccolo);

		// PROMOTION TEST
		PromotionAbsolute AbsCombatepromotion = new PromotionAbsolute(Preferency.COMBATE, 250);
		AbsCombatepromotion.loadSight("Namekusei", myAtraccionListTest);
		AbsCombatepromotion.loadSight("Universo6", myAtraccionListTest);
		MyPromotionListTest.add(AbsCombatepromotion);

		PromotionPercentual PerAventurepromotion = new PromotionPercentual(Preferency.AVENTURA, 20);
		PerAventurepromotion.loadSight("Tierra", myAtraccionListTest);
		PerAventurepromotion.loadSight("Marte", myAtraccionListTest);
		MyPromotionListTest.add(PerAventurepromotion);

		PromotionFreeSights FreeBanquetepromotion = new PromotionFreeSights(Preferency.BANQUETE);
		FreeBanquetepromotion.addFreeSight("Restaurante", myAtraccionListTest);
		FreeBanquetepromotion.loadSight("Restaurante", myAtraccionListTest);
		FreeBanquetepromotion.loadSight("CapsulaCorp", myAtraccionListTest);
		FreeBanquetepromotion.loadSight("Planeta_de_Bills", myAtraccionListTest);
		MyPromotionListTest.add(FreeBanquetepromotion);

		PromotionFreeSights FreeCombatepromotion = new PromotionFreeSights(Preferency.BANQUETE);
		FreeCombatepromotion.addFreeSight("Namekusei", myAtraccionListTest);
		FreeCombatepromotion.addFreeSight("Universo6", myAtraccionListTest);
		FreeCombatepromotion.loadSight("Namekusei", myAtraccionListTest);
		FreeCombatepromotion.loadSight("Universo6", myAtraccionListTest);
		FreeCombatepromotion.loadSight("Vegita", myAtraccionListTest);
		FreeCombatepromotion.loadSight("MajinBu", myAtraccionListTest);
		MyPromotionListTest.add(FreeCombatepromotion);
	}

	@Test
	public void AttractionTest() {
		ArrayList<Sight> myAtraccionList = FilesClass.ReadSights("archivesTest/Atracciones.in");
		Assert.assertEquals(myAtraccionListTest, myAtraccionList);
	}

	@Test
	public void testUser() {
		ArrayList<User> UserList = FilesClass.ReadUsers("archivesTest/Usuarios.in");
		Assert.assertEquals(UserListTest, UserList);
	}

	@Test
	public void testPromotion() {
		ArrayList<Promotion> MyPromotionList = FilesClass.ReadPromotion("archivesTest/Paquetes.in",
				myAtraccionListTest);
		Assert.assertEquals(MyPromotionListTest, MyPromotionList);
	}
}