package mainProcess;

import java.util.Scanner;
import resourcesAndClasses.*;

//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
//import java.io.File;

//import java.io.PrintWriter;

public class InterfazUsuario {
	public static void mainProcess(String rutaArchUsuarios, String rutaArchAtracciones, String rutaArchPromociones, String rutaSalida) {
		
			//Archive archive = new Archive();
			ArrayList<User> misUsuarios = Archive.ReadUsers(rutaArchUsuarios);
			ArrayList<Sight> misAtracciones = Archive.ReadSights(rutaArchAtracciones);
			ArrayList<Promotion> misPromociones = Archive.ReadPromotion(rutaArchPromociones, misAtracciones);
			
			ArrayList<OfferdableItem> misOfferdablesCombate = new ArrayList<OfferdableItem>();
			misOfferdablesCombate.addAll(misPromociones);
			misOfferdablesCombate.addAll(misAtracciones);
			Collections.sort(misOfferdablesCombate, new ComparatorPrefCostTime(Preferency.COMBATE));
			
			
			ArrayList<OfferdableItem> misOfferdablesBanquete = new ArrayList<OfferdableItem>();
			misOfferdablesBanquete.addAll(misOfferdablesCombate);
			Collections.sort(misOfferdablesBanquete, new ComparatorPrefCostTime(Preferency.BANQUETE));
			
			
			ArrayList<OfferdableItem> misOfferdablesAventura = new ArrayList<OfferdableItem>();
			misOfferdablesAventura.addAll(misOfferdablesCombate);
			Collections.sort(misOfferdablesAventura, new ComparatorPrefCostTime(Preferency.ACADEMICO));
			
			
			ArrayList<OfferdableItem> misOfferdables = null;
			char opcion;
			//System.out.println(misOfferdables);
			String []salidas = new String[misUsuarios.size()];
			try (Scanner teclado = new Scanner(System.in)) {
				for(int i =0; i<misUsuarios.size();i++) {
					System.out.println("\n Nombre de visitante: "+misUsuarios.get(i).getName()+"\n");
					//Collections.sort(misOfferdables, new ComparatorPrefCostTime(misUsuarios.get(i).getPref()));
					switch(misUsuarios.get(i).getPref()) {
					case COMBATE: 
						misOfferdables = misOfferdablesCombate;
						break;
					case BANQUETE: 
						misOfferdables = misOfferdablesBanquete;
						break;
					case ACADEMICO: 
						misOfferdables = misOfferdablesAventura;
					default:
						break;
				}	
					for(OfferdableItem actualItem: misOfferdables) {
						if(actualItem.canBeBoughtBy(misUsuarios.get(i))){
							System.out.println(actualItem);
								
							opcion = leerTeclado(teclado);
										
							if(opcion == 'S') {
								actualItem.boughtBy(misUsuarios.get(i));
								System.out.println("Aceptada!");
							}
							else {
								System.out.println("Rechazada...");
							}
							System.out.println("----------------------------------------------------------------------------");
						}
					}
					//System.out.println("\n(!)Datos del usuario "+misUsuarios.get(i).getName()+" "+misUsuarios.get(i).getStrMySights() +" Dinero gastado: "+misUsuarios.get(i).getSpentMoney() +" Tiempo invertido: "+misUsuarios.get(i).getSpentTime() +"\n");
					String salida = generarStringUsuario(misUsuarios.get(i));
					System.out.println(salida);
					salidas[i] = salida;
				}
			}
			
			Archive.cargarArchivoSalida(salidas, rutaSalida);
		}
		private static String generarStringUsuario(User user) {
			String salida = user.getName() + " compró:\n";
			for(OfferdableItem i: user.getSights()) {
				salida += i.stringParaUsuario() + "\n";
			}
			salida += String.format("---------\nEn total gasto: $%.2f\n"
					+ "El tiempo total de las atracciones es: %.2f horas\n"
					+ "En total se ahorró $%.2f\n", user.getSpentMoney(),user.getSpentTime(),user.getSavedMoney());
			salida += "----------------------------------------------------------------------------\n";
			return salida;
			
		}
		private static char leerTeclado(Scanner teclado) {
			
			String opcion;
			do {
				System.out.println("\nAcepta sugerencia? Ingrese S o N\n");
				opcion = teclado.next().toUpperCase();
			}while(!opcion.equals("S") && !opcion.equals("N"));
			
			return opcion.charAt(0);
		}
		
}