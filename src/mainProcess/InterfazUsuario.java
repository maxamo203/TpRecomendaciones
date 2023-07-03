package mainProcess;

import java.util.Scanner;
import resourcesAndClasses.*;

import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

public class InterfazUsuario {
	public static void mainProcess(String rutaArchUsuarios, String rutaArchAtracciones, String rutaArchPromociones,
			String rutaSalida) {

		List<User> misUsuarios = FilesClass.ReadUsers(rutaArchUsuarios);
		List<Sight> misAtracciones = FilesClass.ReadSights(rutaArchAtracciones);
		List<Promotion> misPromociones = FilesClass.ReadPromotion(rutaArchPromociones, misAtracciones);

		LinkedList<OfferdableItem> misOfferdablesCombate = new LinkedList<OfferdableItem>();
		misOfferdablesCombate.addAll(misPromociones);
		misOfferdablesCombate.addAll(misAtracciones);
		Collections.sort(misOfferdablesCombate, new ComparatorPrefCostTime(Preferency.COMBATE));

		LinkedList<OfferdableItem> misOfferdablesBanquete = new LinkedList<OfferdableItem>();
		misOfferdablesBanquete.addAll(misOfferdablesCombate);
		Collections.sort(misOfferdablesBanquete, new ComparatorPrefCostTime(Preferency.BANQUETE));

		LinkedList<OfferdableItem> misOfferdablesAventura = new LinkedList<OfferdableItem>();
		misOfferdablesAventura.addAll(misOfferdablesCombate);
		Collections.sort(misOfferdablesAventura, new ComparatorPrefCostTime(Preferency.AVENTURA));

		LinkedList<OfferdableItem> misOfferdables = null;
		System.out.println("	Bienvenido/a al Dragon World! ");
		System.out.println("----------------------------------------------------------------------------");
		try (Scanner teclado = new Scanner(System.in)) {

			for (int i = 0; i < misUsuarios.size(); i++) {

				switch (misUsuarios.get(i).getPref()) {
				case COMBATE:
					misOfferdables = misOfferdablesCombate;
					break;
				case BANQUETE:
					misOfferdables = misOfferdablesBanquete;
					break;
				case AVENTURA:
					misOfferdables = misOfferdablesAventura;
				default:
					break;
				}

				System.out.println("\nNombre de visitante: " + misUsuarios.get(i).getName() + "\n");

				for (OfferdableItem actualItem : misOfferdables) {
					if (actualItem.canBeBoughtBy(misUsuarios.get(i))) {
						System.out.println(actualItem);

						if (leerOpcion(teclado) == 'S') {
							actualItem.boughtBy(misUsuarios.get(i));
							System.out.println("Aceptada!");
						} else {
							System.out.println("Rechazada...");
						}
						System.out.println(
								"----------------------------------------------------------------------------");
					}
				}
				System.out.println(misUsuarios.get(i));
			}
		}

		FilesClass.cargarArchivoSalida(misUsuarios, rutaSalida);
	}

	private static char leerOpcion(Scanner teclado) {

		String opcion;
		do {
			System.out.println("\nAcepta sugerencia? Ingrese S o N\n");
			opcion = teclado.next().toUpperCase();
		} while (!opcion.equals("S") && !opcion.equals("N"));

		return opcion.charAt(0);
	}

}