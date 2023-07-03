package TestPackage;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Collections;

import org.junit.Before;

import resourcesAndClasses.*;

import org.junit.Test;

public class TotalizadorComprasTest {

	User usuario;
	LinkedList<Sight> atracciones = new LinkedList<>();

	@Before
	public void setup() {
		Sight attraction1 = new Sight("Namekusei", 200, 1, 100, Preferency.COMBATE);
		Sight attraction2 = new Sight("Tierra", 300, 1, 100, Preferency.AVENTURA);
		Sight attraction3 = new Sight("Universo6", 500, 1, 100, Preferency.COMBATE);
		Sight attraction4 = new Sight("Vegita", 250, 1, 100, Preferency.COMBATE);
		Sight attraction5 = new Sight("CapsulaCorp", 350, 1, 100, Preferency.BANQUETE);
		atracciones.add(attraction1);
		atracciones.add(attraction2);
		atracciones.add(attraction3);
		atracciones.add(attraction4);
		atracciones.add(attraction5);

	}

	@Test
	public void testUserMillonario() {

		usuario = new User("Millonario", 999999, 999, Preferency.AVENTURA);
		for (Sight s : atracciones) {
			if (s.canBeBoughtBy(usuario)) {
				s.boughtBy(usuario);
			}
		}

		boolean banderita = true;
		for (Sight s : atracciones) {
			if (!usuario.alreadyBought(s)) {
				banderita = false;
			}
		}
		assertTrue(banderita);
	}

	@Test
	public void testUserBancarota() {

		usuario = new User("Bancarota", 0, 999, Preferency.AVENTURA);
		for (Sight s : atracciones) {
			if (s.canBeBoughtBy(usuario)) {
				s.boughtBy(usuario);
			}
		}

		boolean banderita = true;
		for (Sight s : atracciones) {
			if (usuario.alreadyBought(s)) {
				banderita = false;
			}
		}
		assertTrue(banderita);

	}

	@Test
	public void soloPreferenciasAventurero() {
		usuario = new User("Aventurero", 1000, 100, Preferency.AVENTURA);
		for (Sight s : atracciones) {
			if (s.canBeBoughtBy(usuario) && s.getType() == usuario.getPref()) {
				s.boughtBy(usuario);
			}
		}

		boolean banderita = true;
		for (Sight s : atracciones) {
			if (usuario.alreadyBought(s) && s.getType() != usuario.getPref()) {
				banderita = false;
			}
		}
		assertTrue(banderita);

	}

	@Test
	public void soloPreferenciasCombativo() {
		usuario = new User("Combativo", 1000, 100, Preferency.COMBATE);
		for (Sight s : atracciones) {
			if (s.canBeBoughtBy(usuario) && s.getType() == usuario.getPref()) {
				s.boughtBy(usuario);
			}
		}

		boolean banderita = true;
		for (Sight s : atracciones) {
			if (usuario.alreadyBought(s) && s.getType() != usuario.getPref()) {
				banderita = false;
			}
		}
		assertTrue(banderita);

	}

	@Test
	public void soloPreferenciasBanquetero() {
		usuario = new User("Banquetero", 1000, 100, Preferency.BANQUETE);
		for (Sight s : atracciones) {
			if (s.canBeBoughtBy(usuario) && s.getType() == usuario.getPref()) {
				s.boughtBy(usuario);
			}
		}

		boolean banderita = true;
		for (Sight s : atracciones) {
			if (usuario.alreadyBought(s) && s.getType() != usuario.getPref()) {
				banderita = false;
			}
		}
		assertTrue(banderita);

	}

	@Test
	public void todoMenosPreferencias() {
		usuario = new User("ElNoPreferenciero", 1000, 100, Preferency.BANQUETE);
		for (Sight s : atracciones) {
			if (s.canBeBoughtBy(usuario) && s.getType() != usuario.getPref()) {
				s.boughtBy(usuario);
			}
		}

		boolean banderita = true;
		for (Sight s : atracciones) {
			if (usuario.alreadyBought(s) && s.getType() == usuario.getPref()) {
				banderita = false;
			}
		}
		assertTrue(banderita);

	}

	@Test
	public void sinCupones() {
		Sight atraccionSinCupo = new Sight("Planeta Kaiosama", 100, 1, 0, Preferency.AVENTURA);
		usuario = new User("Vegetta777", 100, 100, Preferency.AVENTURA);
		assertFalse(atraccionSinCupo.canBeBoughtBy(usuario));
	}

	@Test
	public void intentoComprarDosVecesLaMisma() {
		Sight atraccion = new Sight("Planeta Kaiosama", 100, 1, 0, Preferency.AVENTURA);
		usuario = new User("Vegetta777", 100, 100, Preferency.AVENTURA);

		atraccion.boughtBy(usuario);

		assertFalse(atraccion.canBeBoughtBy(usuario));

	}

	@Test
	public void gastoLaAtraccion() {
		Sight atraccion = new Sight("Planeta Kaiosama", 1, 1, 99, Preferency.AVENTURA);
		usuario = new User("Vegetta777", 100, 100, Preferency.AVENTURA);

		for (int i = 0; i < 99; i++) {
			atraccion.boughtBy(usuario);
			// no tiene sentido ejecutar boughtBy sin preguntar antes si canBeBoughtBy ==
			// True, hacer esto rompe la logica del programa
			// pero esto solo va a pasar en los testeos no en lo que le sera proporcionado
			// al usuario.
		}

		assertFalse(atraccion.canBeBoughtBy(usuario));

	}

	@Test

	public void usuarioRealPares() {
		// comportamiento de un usuario real que por capricho solo compra las
		// atracciones enumeradas con indice par en el catalogo
		usuario = new User("Persona real", 1000, 50, Preferency.COMBATE);
		Collections.sort(atracciones, new ComparatorPrefCostTime(usuario.getPref()));
		for (int i = 0; i < atracciones.size(); i++) {
			if (i % 2 == 0 && atracciones.get(i).canBeBoughtBy(usuario)) {
				atracciones.get(i).boughtBy(usuario);
			}
		}

		boolean bandera = true;

		for (int i = 0; i < atracciones.size(); i++) {
			if (i % 2 != 0 && usuario.alreadyBought(atracciones.get(i))) {
				bandera = false;
				break;
			}
		}
		assertTrue(bandera);
	}

	@Test

	public void compraPromocionAfectaAtraccion() {
		usuario = new User("Goku", 2000, 100, Preferency.COMBATE);
		PromotionAbsolute promocionDePrueba = new PromotionAbsolute(Preferency.COMBATE, 300);
		promocionDePrueba.loadSight("Namekusei", atracciones);
		promocionDePrueba.loadSight("Vegita", atracciones);

		promocionDePrueba.boughtBy(usuario);

		assertEquals(99, atracciones.get(0).getQuota());
		assertEquals(99, atracciones.get(3).getQuota());

	}

	@Test

	public void compraPromocionAfectaAtraccion2() {
		usuario = new User("Goku", 2000, 100, Preferency.COMBATE);
		PromotionAbsolute promocionDePrueba = new PromotionAbsolute(Preferency.COMBATE, 300);
		promocionDePrueba.loadSight("Namekusei", atracciones);
		promocionDePrueba.loadSight("Vegita", atracciones);

		for (int i = 0; i < 10; i++) {
			promocionDePrueba.boughtBy(usuario);
		}

		assertEquals(90, atracciones.get(0).getQuota());
		assertEquals(90, atracciones.get(3).getQuota());

	}

	@Test
	public void compraPromocionAfectaAtraccion3() {
		usuario = new User("Goku", 2000, 100, Preferency.COMBATE);
		PromotionAbsolute promocionDePrueba = new PromotionAbsolute(Preferency.COMBATE, 300);
		promocionDePrueba.loadSight("Namekusei", atracciones);
		promocionDePrueba.loadSight("Vegita", atracciones);

		for (int i = 0; i < 100; i++) {
			promocionDePrueba.boughtBy(usuario);
		}

		assertEquals(0, atracciones.get(0).getQuota());
		assertEquals(0, atracciones.get(3).getQuota());

	}

}
