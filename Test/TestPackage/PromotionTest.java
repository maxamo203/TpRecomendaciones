package TestPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;

import resourcesAndClasses.*;

import org.junit.Before;
import org.junit.Test;

public class PromotionTest {
	
	Sight miAtraccion;
	Sight miAtraccion2;
	Sight miAtraccion3;
	Sight miAtraccion4;
	Sight miAtraccion5;
	Sight miAtraccion6;
	Sight miAtraccion7;
	Sight miAtraccion8;
	ArrayList<Sight> miListaDeAtracciones;
	ArrayList<Promotion> MyPromotionListOrigin;
	PromotionPercentual PerAcademicpromotion;
	PromotionAbsolute AbsAcademicpromotion;
	PromotionAbsolute AbsBanquetepromotion;
	PromotionFreeSights FreeCombatepromotion;
	PromotionPercentual PerCombatepromotion;
	
	@Before public void setup(){
		miAtraccion = new Sight("Namekusei",200,1,100,Preferency.COMBATE);
		miAtraccion2 = new Sight("Tierra",300,1,100,Preferency.AVENTURA);
		miAtraccion3 = new Sight("UNLAM",300,1,100,Preferency.AVENTURA);
		miAtraccion4 = new Sight("Universo6",500,1,100,Preferency.COMBATE);
		miAtraccion5 = new Sight("TacosDelTioNacho",200,1,100,Preferency.BANQUETE);
		miAtraccion6 = new Sight("Vegita",200,1,100,Preferency.BANQUETE);
		miAtraccion7 = new Sight("Marte",500,1,100,Preferency.COMBATE);
		miAtraccion8 = new Sight("Inframundo",700,1,100,Preferency.COMBATE);
		
		miListaDeAtracciones = new ArrayList<Sight>();
		MyPromotionListOrigin = new ArrayList<Promotion>();
		
		miListaDeAtracciones.add(miAtraccion);
		miListaDeAtracciones.add(miAtraccion2);
		miListaDeAtracciones.add(miAtraccion3);
		miListaDeAtracciones.add(miAtraccion4);
		miListaDeAtracciones.add(miAtraccion5);
		miListaDeAtracciones.add(miAtraccion6);
		miListaDeAtracciones.add(miAtraccion7);
		miListaDeAtracciones.add(miAtraccion8);
		
		PerAcademicpromotion = new PromotionPercentual(Preferency.AVENTURA, 20);
		PerAcademicpromotion.loadSight("Tierra", miListaDeAtracciones);
		PerAcademicpromotion.loadSight("UNLAM", miListaDeAtracciones);
		MyPromotionListOrigin.add(PerAcademicpromotion);
		
		AbsAcademicpromotion = new PromotionAbsolute(Preferency.AVENTURA, 300);
		AbsAcademicpromotion.loadSight("Tierra", miListaDeAtracciones);
		AbsAcademicpromotion.loadSight("UNLAM", miListaDeAtracciones);
		MyPromotionListOrigin.add(AbsAcademicpromotion);
		
		AbsBanquetepromotion = new PromotionAbsolute(Preferency.BANQUETE, 100);
		AbsBanquetepromotion.loadSight("TacosDelTioNacho", miListaDeAtracciones);
		AbsBanquetepromotion.loadSight("Vegita", miListaDeAtracciones);
		MyPromotionListOrigin.add(AbsBanquetepromotion);
		
		FreeCombatepromotion = new PromotionFreeSights(Preferency.COMBATE);
		FreeCombatepromotion.addFreeSight("Namekusei", miListaDeAtracciones);
		FreeCombatepromotion.loadSight("Marte", miListaDeAtracciones);
		FreeCombatepromotion.loadSight("Inframundo", miListaDeAtracciones);
		MyPromotionListOrigin.add(FreeCombatepromotion);
		
		PerCombatepromotion = new PromotionPercentual(Preferency.COMBATE, 20);
		PerCombatepromotion.loadSight("Marte", miListaDeAtracciones);
		PerCombatepromotion.loadSight("Namekusei", miListaDeAtracciones);
		MyPromotionListOrigin.add(PerCombatepromotion);
	}
	
		@Test
		public void Discount() {
	        //GetDiscount
			assertEquals(140.0,PerCombatepromotion.getDiscount(),0.1);
	        assertEquals(120.0,PerAcademicpromotion.getDiscount(),0.1);
	        assertEquals(300.0,AbsAcademicpromotion.getDiscount(),0.1);
	        assertEquals(100.0,AbsBanquetepromotion.getDiscount(),0.1);
	        assertEquals(200.0,FreeCombatepromotion.getDiscount(),0.1);
		}
		
		@Test
		public void costWithDiscount(){
			assertEquals(560.0,PerCombatepromotion.getCost(),0.1);
			assertEquals(480.0,PerAcademicpromotion.getCost(),0.1);
			assertEquals(300.0,AbsAcademicpromotion.getCost(),0.1);
			assertEquals(300.0,AbsBanquetepromotion.getCost(),0.1);
			assertEquals(1200.0,FreeCombatepromotion.getCost(),0.1);
		}
		@Test
		public void originalPrice(){
			assertEquals(700.0,PerCombatepromotion.getCostWithoutDiscount(),0.1);
			assertEquals(600.0,PerAcademicpromotion.getCostWithoutDiscount(),0.1);
			assertEquals(600.0,AbsAcademicpromotion.getCostWithoutDiscount(),0.1);
			assertEquals(400.0,AbsBanquetepromotion.getCostWithoutDiscount(),0.1);
			assertEquals(1400.0,FreeCombatepromotion.getCostWithoutDiscount(),0.1);
		}
		@Test
		public void promotionTime(){
			assertEquals(2.0,PerCombatepromotion.getTime(),0.1);
			assertEquals(2.0,PerAcademicpromotion.getTime(),0.1);
			assertEquals(2.0,AbsAcademicpromotion.getTime(),0.1);
			assertEquals(2.0,AbsBanquetepromotion.getTime(),0.1);
			assertEquals(3.0,FreeCombatepromotion.getTime(),0.1);
			System.out.println(FreeCombatepromotion.getTime());
		}
		
	}

