package resourcesAndClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.LinkedList;

import java.util.List;

public class FilesClass {
	 
	// READ THE ATTRACTION LIST
	public static List<Sight> ReadSights(String path) {
		LinkedList<Sight> myAtraccionList = new LinkedList<Sight>();
		Scanner scanner = null;
		try {
			File file = new File(path);
			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				String name = scanner.next();
				Double price = scanner.nextDouble();
				Double time = scanner.nextDouble();
				int quota = scanner.nextInt();
				int intPreferency = scanner.nextInt();
				Preferency preferency = null;

				// TURN THE NUMBER INTO A PREFERENCY
				if(Preferency.asignacionPref.containsKey(intPreferency)){
					preferency = Preferency.asignacionPref.get(intPreferency);
				}
				
				
				/*
				 * 			switch (intPreferency) {
				case 1:
					preferency = Preferency.COMBATE;
					break;
				case 2:
					preferency = Preferency.BANQUETE;
					break;
				case 3:
					preferency = Preferency.AVENTURA;
					break;
				default:
					preferency = null;
				}
				 * */
				

				Sight data = new Sight(name, price, time, quota, preferency);
				myAtraccionList.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			scanner.close();
		}
		return myAtraccionList;
	}

	public static List<User> ReadUsers(String path) {
		LinkedList<User> myUserList = new LinkedList<User>();
		Scanner scanner = null;
		try {
			File file = new File(path);
			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				String name = scanner.next();
				Double totalMount = scanner.nextDouble();
				Double totalTime = scanner.nextDouble();
				int intPreferency = scanner.nextInt();
				Preferency preferency = null;

				// TURN THE NUMBER INTO A PREFERENCY
				if(Preferency.asignacionPref.containsKey(intPreferency)){
					preferency = Preferency.asignacionPref.get(intPreferency);
				}

				User data = new User(name, totalMount, totalTime, preferency);

				myUserList.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			scanner.close();
		}
		return myUserList;
	}

	public static List<Promotion> ReadPromotion(String path, List<Sight> MyAtraccionList) {
		LinkedList<Promotion> myPromotionList = new LinkedList<Promotion>();
		Scanner scanner = null;
		try {
			File file = new File(path);
			scanner = new Scanner(file);
			String attraction = null;
			Promotion promotion = null;

			while (scanner.hasNext()) {

				int intPreferency = scanner.nextInt();
				Preferency preferency = null;

				// TURN THE NUMBER INTO A PREFERENCY
				if(Preferency.asignacionPref.containsKey(intPreferency)){
					preferency = Preferency.asignacionPref.get(intPreferency);
				}

				String type = scanner.next();
				char charType = type.charAt(0);

				if (charType == 'A') {
					double percentage = scanner.nextDouble();
					promotion = new PromotionPercentual(preferency, percentage);
					scanner.next();
				}

				else if (charType == 'B') {
					double price = scanner.nextInt();
					promotion = new PromotionAbsolute(preferency, price);
					scanner.next();

				}

				else if (charType == 'C') {
					promotion = new PromotionFreeSights(preferency);

					int cantPromocionesGratuitas = scanner.nextInt();

					scanner.next();

					for (int i = 0; i < cantPromocionesGratuitas; i++) {
						attraction = scanner.next();
						((PromotionFreeSights) promotion).addFreeSight(attraction, MyAtraccionList);
						promotion.loadSight(attraction, MyAtraccionList);
					}
				}

				attraction = scanner.next();

				// chequeo que no es el corchete final
				while (attraction.charAt(0) != ']') {
					promotion.loadSight(attraction, MyAtraccionList);
					attraction = scanner.next();
				}
				myPromotionList.add(promotion);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			scanner.close();
		}
		return myPromotionList;
	}

	public static void cargarArchivoSalida(List<User> misUsuarios, String rutaSalida) {
		File archivo = new File(rutaSalida);
		try {
			PrintWriter salida = new PrintWriter(archivo);

			for (int i = 0; i < misUsuarios.size(); i++) {
				salida.print(misUsuarios.get(i));
			}

			salida.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
