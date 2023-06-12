package TestPackage;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resourcesAndClasses.*;

public class SelectorDePreferenciasTest {
	
	User usuario;
	ArrayList<Sight> ofertasParaUsuario = new ArrayList<>();
	ArrayList<Sight> ordenadaCombate = new ArrayList<>();
	ArrayList<Sight> ordenadaAventura = new ArrayList<>();
	ArrayList<Sight> ordenadaBanquete = new ArrayList<>();
	
	ArrayList<OfferdableItem> ofertasParaUsuariosConPromos = new ArrayList<>();
	ArrayList<OfferdableItem> ordenadaConPromosCombate = new ArrayList<>();
	ArrayList<OfferdableItem> ordenadaConPromosAventura = new ArrayList<>();
	ArrayList<OfferdableItem> ordenadaConPromosBanquete = new ArrayList<>();
	
	@Before
	public void setup() {
		
		Sight attraction1 = new Sight("Namekusei",200,1,100,Preferency.COMBATE);
		Sight attraction2 = new Sight("Tierra",300,1,100,Preferency.AVENTURA);
		Sight attraction3 = new Sight("Universo6",500,1,100,Preferency.COMBATE);
		Sight attraction4 = new Sight("Vegita",250,1,100,Preferency.COMBATE);
		Sight attraction5 = new Sight("CapsulaCorp",350,1,100,Preferency.BANQUETE);
			
		
		
		ofertasParaUsuario.add(attraction1);
		ofertasParaUsuario.add(attraction2);
		ofertasParaUsuario.add(attraction3);
		ofertasParaUsuario.add(attraction4);
		ofertasParaUsuario.add(attraction5);
		
		
		ordenadaCombate.add(attraction3);
		ordenadaCombate.add(attraction4);
		ordenadaCombate.add(attraction1);
		ordenadaCombate.add(attraction5);
		ordenadaCombate.add(attraction2);
		
		ordenadaAventura.add(attraction2);
		ordenadaAventura.add(attraction3);
		ordenadaAventura.add(attraction5);
		ordenadaAventura.add(attraction4);
		ordenadaAventura.add(attraction1);
		
		ordenadaBanquete.add(attraction5);
		ordenadaBanquete.add(attraction3);
		ordenadaBanquete.add(attraction2);
		ordenadaBanquete.add(attraction4);
		ordenadaBanquete.add(attraction1);
		
		ArrayList<Sight> atracciones = new ArrayList<>();
		atracciones.addAll(ofertasParaUsuario);
		ofertasParaUsuariosConPromos.addAll(ofertasParaUsuario);
		
		atracciones.add(new Sight("UNLAM",300,1,100,Preferency.AVENTURA));
		atracciones.add(new Sight("Restaurante",300,1,100,Preferency.BANQUETE));
		atracciones.add(new Sight("Comida de Lujo",1500,1,100,Preferency.BANQUETE));
		
		PromotionPercentual PerAventurapromotion = new PromotionPercentual(Preferency.AVENTURA, 20);
		PerAventurapromotion.loadSight("Tierra", atracciones);
		PerAventurapromotion.loadSight("UNLAM", atracciones);
		ofertasParaUsuariosConPromos.add(PerAventurapromotion);
		
		PromotionAbsolute AbsCombatepromotion = new PromotionAbsolute(Preferency.COMBATE, 10);
		AbsCombatepromotion.loadSight("Namekusei", atracciones);
		AbsCombatepromotion.loadSight("Universo6", atracciones);
		ofertasParaUsuariosConPromos.add(AbsCombatepromotion);
		
		
		PromotionFreeSights FreeBanquetepromotion = new PromotionFreeSights(Preferency.BANQUETE);
		FreeBanquetepromotion.addFreeSight("CapsulaCorp", atracciones);
		FreeBanquetepromotion.loadSight("Restaurante", atracciones);
		FreeBanquetepromotion.loadSight("Comida de Lujo", atracciones);

		ofertasParaUsuariosConPromos.add(FreeBanquetepromotion);
		
		ordenadaConPromosCombate.add(AbsCombatepromotion);
		ordenadaConPromosCombate.add(attraction3);
		ordenadaConPromosCombate.add(attraction4);
		ordenadaConPromosCombate.add(attraction1);		
		ordenadaConPromosCombate.add(FreeBanquetepromotion);
		ordenadaConPromosCombate.add(PerAventurapromotion);
		ordenadaConPromosCombate.add(attraction5);
		ordenadaConPromosCombate.add(attraction2);
		
		
		ordenadaConPromosAventura.add(PerAventurapromotion);
		ordenadaConPromosAventura.add(attraction2);
		ordenadaConPromosAventura.add(FreeBanquetepromotion);	
		ordenadaConPromosAventura.add(AbsCombatepromotion);	
		ordenadaConPromosAventura.add(attraction3);
		ordenadaConPromosAventura.add(attraction5);
		ordenadaConPromosAventura.add(attraction4);
		ordenadaConPromosAventura.add(attraction1);
		
		ordenadaConPromosBanquete.add(FreeBanquetepromotion);
		ordenadaConPromosBanquete.add(attraction5);
		ordenadaConPromosBanquete.add(AbsCombatepromotion);
		ordenadaConPromosBanquete.add(PerAventurapromotion);		
		ordenadaConPromosBanquete.add(attraction3);
		ordenadaConPromosBanquete.add(attraction2);
		ordenadaConPromosBanquete.add(attraction4);
		ordenadaConPromosBanquete.add(attraction1);
	}
	
	@Test
	public void testeoUserCombate() {
		usuario = new User("Goku",100,2,Preferency.COMBATE);
		Collections.sort(ofertasParaUsuario,new ComparatorPrefCostTime(usuario.getPref()));
		Assert.assertEquals(ofertasParaUsuario, ordenadaCombate);
	}
	
	@Test
	public void testeoUserAventura() {
		usuario = new User("Whis",100,2,Preferency.AVENTURA);
		Collections.sort(ofertasParaUsuario,new ComparatorPrefCostTime(usuario.getPref()));
		Assert.assertEquals(ofertasParaUsuario, ordenadaAventura);
	}
	
	@Test
	public void testeoUserBanquete() {
		usuario = new User("Bills",100,2,Preferency.BANQUETE);
		Collections.sort(ofertasParaUsuario,new ComparatorPrefCostTime(usuario.getPref()));
		Assert.assertEquals(ofertasParaUsuario, ordenadaBanquete);
	}
	
	@Test
	public void testeoUserPromoCombate() {
		usuario = new User("Bardock",100,2,Preferency.COMBATE);
		Collections.sort(ofertasParaUsuariosConPromos,new ComparatorPrefCostTime(usuario.getPref()));
		Assert.assertEquals(ofertasParaUsuariosConPromos, ordenadaConPromosCombate);
	}
	
	@Test
	public void testeoUserPromoAventura() {
		usuario = new User("Gohan",100,2,Preferency.AVENTURA);
		Collections.sort(ofertasParaUsuariosConPromos,new ComparatorPrefCostTime(usuario.getPref()));
		Assert.assertEquals(ofertasParaUsuariosConPromos, ordenadaConPromosAventura);
	}
	
	@Test
	public void testeoUserPromoBanquete() {
		usuario = new User("Bulma",100,2,Preferency.BANQUETE);
		Collections.sort(ofertasParaUsuariosConPromos,new ComparatorPrefCostTime(usuario.getPref()));
		Assert.assertEquals(ofertasParaUsuariosConPromos, ordenadaConPromosBanquete);
	}
}
