package mainProcess;

import java.util.Scanner;
import resourcesAndClasses.*;

import java.util.ArrayList;
import java.util.Collections;

public class InterfazUsuario {
	public static void mainProcess(String rutaArchUsuarios, String rutaArchAtracciones, String rutaArchPromociones,
			String rutaSalida) {

		ArrayList<User> misUsuarios = FilesClass.ReadUsers(rutaArchUsuarios);
		ArrayList<Sight> misAtracciones = FilesClass.ReadSights(rutaArchAtracciones);
		ArrayList<Promotion> misPromociones = FilesClass.ReadPromotion(rutaArchPromociones, misAtracciones);

		ArrayList<OfferdableItem> misOfferdablesCombate = new ArrayList<OfferdableItem>();
		misOfferdablesCombate.addAll(misPromociones);
		misOfferdablesCombate.addAll(misAtracciones);
		Collections.sort(misOfferdablesCombate, new ComparatorPrefCostTime(Preferency.COMBATE));

		ArrayList<OfferdableItem> misOfferdablesBanquete = new ArrayList<OfferdableItem>();
		misOfferdablesBanquete.addAll(misOfferdablesCombate);
		Collections.sort(misOfferdablesBanquete, new ComparatorPrefCostTime(Preferency.BANQUETE));

		ArrayList<OfferdableItem> misOfferdablesAventura = new ArrayList<OfferdableItem>();
		misOfferdablesAventura.addAll(misOfferdablesCombate);
		Collections.sort(misOfferdablesAventura, new ComparatorPrefCostTime(Preferency.AVENTURA));

		ArrayList<OfferdableItem> misOfferdables = null;
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