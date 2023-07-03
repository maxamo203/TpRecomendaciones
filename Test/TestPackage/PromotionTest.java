package TestPackage;

import static org.junit.Assert.*;

import java.util.LinkedList;

import resourcesAndClasses.*;

import org.junit.Before;
import org.junit.Test;

public class PromotionTest {

	LinkedList<Sight> miListaDeAtracciones;

	PromotionPercentual perAventurapromotion;
	PromotionAbsolute absAventuraPromotion;
	PromotionAbsolute absBanquetePromotion;
	PromotionFreeSights freeCombatePromotion;
	PromotionPercentual perCombatePromotion;

	@Before
	public void setup() {
		Sight miAtraccion = new Sight("Namekusei", 200, 1, 100, Preferency.COMBATE);
		Sight miAtraccion2 = new Sight("Tierra", 300, 1, 100, Preferency.AVENTURA);
		Sight miAtraccion3 = new Sight("UNLAM", 300, 1, 100, Preferency.AVENTURA);
		Sight miAtraccion4 = new Sight("Universo6", 500, 1, 100, Preferency.COMBATE);
		Sight miAtraccion5 = new Sight("TacosDelTioNacho", 200, 1, 100, Preferency.BANQUETE);
		Sight miAtraccion6 = new Sight("Vegita", 200, 1, 100, Preferency.BANQUETE);
		Sight miAtraccion7 = new Sight("Marte", 500, 1, 100, Preferency.COMBATE);
		Sight miAtraccion8 = new Sight("Inframundo", 700, 1, 100, Preferency.COMBATE);

		miListaDeAtracciones = new LinkedList<Sight>();

		miListaDeAtracciones.add(miAtraccion);
		miListaDeAtracciones.add(miAtraccion2);
		miListaDeAtracciones.add(miAtraccion3);
		miListaDeAtracciones.add(miAtraccion4);
		miListaDeAtracciones.add(miAtraccion5);
		miListaDeAtracciones.add(miAtraccion6);
		miListaDeAtracciones.add(miAtraccion7);
		miListaDeAtracciones.add(miAtraccion8);

		perAventurapromotion = new PromotionPercentual(Preferency.AVENTURA, 20);
		perAventurapromotion.loadSight("Tierra", miListaDeAtracciones);
		perAventurapromotion.loadSight("UNLAM", miListaDeAtracciones);

		absAventuraPromotion = new PromotionAbsolute(Preferency.AVENTURA, 300);
		absAventuraPromotion.loadSight("Tierra", miListaDeAtracciones);
		absAventuraPromotion.loadSight("UNLAM", miListaDeAtracciones);

		absBanquetePromotion = new PromotionAbsolute(Preferency.BANQUETE, 100);
		absBanquetePromotion.loadSight("TacosDelTioNacho", miListaDeAtracciones);
		absBanquetePromotion.loadSight("Vegita", miListaDeAtracciones);

		freeCombatePromotion = new PromotionFreeSights(Preferency.COMBATE);
		freeCombatePromotion.addFreeSight("Namekusei", miListaDeAtracciones);
		freeCombatePromotion.loadSight("Namekusei", miListaDeAtracciones);
		freeCombatePromotion.loadSight("Marte", miListaDeAtracciones);
		freeCombatePromotion.loadSight("Inframundo", miListaDeAtracciones);

		perCombatePromotion = new PromotionPercentual(Preferency.COMBATE, 20);
		perCombatePromotion.loadSight("Marte", miListaDeAtracciones);
		perCombatePromotion.loadSight("Namekusei", miListaDeAtracciones);

	}

	@Test
	public void Discount() {
		// GetDiscount
		perCombatePromotion = new PromotionPercentual(Preferency.COMBATE, 20);
		perCombatePromotion.loadSight("Marte", miListaDeAtracciones);
		perCombatePromotion.loadSight("Namekusei", miListaDeAtracciones);

		
		
		assertEquals(140.0, perCombatePromotion.getDiscount(), 0.1);
		assertEquals(120.0, perAventurapromotion.getDiscount(), 0.1);
		assertEquals(300.0, absAventuraPromotion.getDiscount(), 0.1);
		assertEquals(100.0, absBanquetePromotion.getDiscount(), 0.1);
		assertEquals(200.0, freeCombatePromotion.getDiscount(), 0.1);
	}

	@Test
	public void costWithDiscount() {
		assertEquals(560.0, perCombatePromotion.getCost(), 0.1);
		assertEquals(480.0, perAventurapromotion.getCost(), 0.1);
		assertEquals(300.0, absAventuraPromotion.getCost(), 0.1);
		assertEquals(300.0, absBanquetePromotion.getCost(), 0.1);
		assertEquals(1200.0, freeCombatePromotion.getCost(), 0.1);
	}

	@Test
	public void originalPrice() {
		assertEquals(700.0, perCombatePromotion.getCostWithoutDiscount(), 0.1);
		assertEquals(600.0, perAventurapromotion.getCostWithoutDiscount(), 0.1);
		assertEquals(600.0, absAventuraPromotion.getCostWithoutDiscount(), 0.1);
		assertEquals(400.0, absBanquetePromotion.getCostWithoutDiscount(), 0.1);
		assertEquals(1400.0, freeCombatePromotion.getCostWithoutDiscount(), 0.1);
	}

	@Test
	public void promotionTime() {
		assertEquals(2.0, perCombatePromotion.getTime(), 0.1);
		assertEquals(2.0, perAventurapromotion.getTime(), 0.1);
		assertEquals(2.0, absAventuraPromotion.getTime(), 0.1);
		assertEquals(2.0, absBanquetePromotion.getTime(), 0.1);
		assertEquals(3.0, freeCombatePromotion.getTime(), 0.1);
	}

}
