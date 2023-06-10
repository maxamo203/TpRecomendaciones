package resourcesAndClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;


public class FilesClass {
	static Scanner scanner = null;
	
	
	//READ THE ATTRACTION LIST
	public static ArrayList<Sight> ReadSights(String path){
		ArrayList<Sight> MyAtraccionList = new ArrayList<Sight>();
		try {
			File file = new File(path);
			scanner = new Scanner(file);

			while(scanner.hasNext()) {
				String name = scanner.next();
				Double price = scanner.nextDouble();
				Double time = scanner.nextDouble();
				int quota = scanner.nextInt();
				int intPreferency = scanner.nextInt();
				Preferency preferency;
				
				//TURN THE NUMBER INTO A PREFERENCY
				switch(intPreferency) {
					case 1:  preferency = Preferency.COMBATE; break;
					case 2:  preferency = Preferency.BANQUETE; break;
					case 3:  preferency = Preferency.AVENTURA; break;
					default: preferency = null;
				}
				
				Sight Data = new Sight(name, price, time, quota, preferency);
				MyAtraccionList.add(Data);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			scanner.close();
		}
		return MyAtraccionList;
	}
	
	public static ArrayList<User> ReadUsers(String path){
		ArrayList<User> MyUserList = new ArrayList<User>();
		
		try {
			File file = new File(path);
			scanner = new Scanner(file);

			while(scanner.hasNext()) {
				String name = scanner.next();
				Double totalMount = scanner.nextDouble();
				Double totalTime = scanner.nextDouble();
				int intPreferency = scanner.nextInt();
				Preferency preferency;
				
				//TURN THE NUMBER INTO A PREFERENCY
				switch(intPreferency) {
					case 1:  preferency = Preferency.COMBATE; break;
					case 2:  preferency = Preferency.BANQUETE; break;
					case 3:  preferency = Preferency.AVENTURA; break;
					default: preferency = null;
				}
				
				User Data = new User(name, totalMount, totalTime, preferency);
				
				MyUserList.add(Data);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			scanner.close();
		}
		return MyUserList;
	}

	public static ArrayList<Promotion> ReadPromotion(String path, ArrayList<Sight> MyAtraccionList){
		ArrayList<Promotion> MyPromotionList = new ArrayList<Promotion>();
		
		try {
			File file = new File(path);
			scanner = new Scanner(file);
			String Attraction = null;
			Promotion promotion = null;
			

			while(scanner.hasNext()) {
				
				int intPreferency = scanner.nextInt();
				Preferency preferency;
				
				//TURN THE NUMBER INTO A PREFERENCY
				switch(intPreferency) {
					case 1:  preferency = Preferency.COMBATE; break;
					case 2:  preferency = Preferency.BANQUETE; break;
					case 3:  preferency = Preferency.AVENTURA; break;
					default: preferency = null;
				}
				
				
				String type = scanner.next();
				char charType = type.charAt(0);
				
				if(charType == 'A') {
					double percentage = scanner.nextDouble();				
					promotion = new PromotionPercentual(preferency, percentage);
					scanner.next();
				}
				
				else if(charType == 'B') {
					double price = scanner.nextInt();	
					promotion = new PromotionAbsolute(preferency, price);
					scanner.next();
					
				}
				
				else if(charType == 'C') {
					promotion = new PromotionFreeSights(preferency);
					
					int cantPromocionesGratuitas = scanner.nextInt();
					
					scanner.next();
					
					for(int i = 0; i<cantPromocionesGratuitas;i++) {
						Attraction = scanner.next();
						((PromotionFreeSights)promotion).addFreeSight(Attraction, MyAtraccionList);
						promotion.loadSight(Attraction, MyAtraccionList);
					}
				}
				
				Attraction = scanner.next();

				//chequeo que no es el corchete final
				while(Attraction.charAt(0) != ']') {
					promotion.loadSight(Attraction, MyAtraccionList);
					Attraction = scanner.next();
				}
				MyPromotionList.add(promotion);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			scanner.close();
		}
		return MyPromotionList;
	}
	
	public static void cargarArchivoSalida(ArrayList<User> misUsuarios,String rutaSalida) {
		File archivo = new File(rutaSalida);
		try {
			PrintWriter salida = new PrintWriter(archivo);
			
			for(int i = 0; i<misUsuarios.size();i++) {
				salida.print(misUsuarios.get(i));
			}
			
			salida.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
