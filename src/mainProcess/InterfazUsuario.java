package mainProcess;

import java.util.Scanner;
import resourcesAndClasses.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;

import java.io.PrintWriter;

public class InterfazUsuario {
	public static void mainProcess(String rutaArchUsuarios, String rutaArchAtracciones, String rutaArchPromociones, String rutaSalida) {
		
			Archive archive = new Archive();
			ArrayList<User> misUsuarios = archive.ReadUsers(rutaArchUsuarios);
			ArrayList<Sight> misAtracciones = archive.ReadSights(rutaArchAtracciones);
			ArrayList<Promotion> misPromociones = archive.ReadPromotion(rutaArchPromociones, misAtracciones);
			
			ArrayList<OfferdableItem> misOfferdables = new ArrayList<OfferdableItem>();
			misOfferdables.addAll(misAtracciones);
			misOfferdables.addAll(misPromociones);
			char opcion;
			System.out.println(misOfferdables);
			
			try (Scanner teclado = new Scanner(System.in)) {
				for(int i =0; i<misUsuarios.size();i++) {
					System.out.println("\n Nombre de visitante: "+misUsuarios.get(i).getName()+"\n");
					Collections.sort(misOfferdables, new ComparatorPrefCostTime(misUsuarios.get(i).getPref()));
				
					for(OfferdableItem actualItem: misOfferdables) {
						if(actualItem.canBeBoughtBy(misUsuarios.get(i))){
							System.out.println(actualItem);
								
							do {
								System.out.println("\nAcepta sugerencia? Ingrese S o N\n");
								opcion = teclado.next().toUpperCase().charAt(0);
							}while(opcion != 'S' && opcion !='N');
										
							if(opcion == 'S') {
								actualItem.boughtBy(misUsuarios.get(i));
								System.out.println("�Aceptada!");
							}
							else {
								System.out.println("Rechazada...");
							}
							System.out.println("----------------------------------------------------------------------------");
						}
					}
					System.out.println("\n(!)Datos del usuario "+misUsuarios.get(i).getName()+" "+misUsuarios.get(i).getStrMySights() +" Dinero gastado: "+misUsuarios.get(i).getSpentMoney() +" Tiempo invertido: "+misUsuarios.get(i).getSpentTime() +"\n");
				}
			}
			
			cargarArchivoSalida(misUsuarios,rutaSalida);
		}

		private static void cargarArchivoSalida(ArrayList<User> misUsuarios,String rutaSalida) {
			File archivo = new File(rutaSalida);
			try {
				PrintWriter salida = new PrintWriter(archivo);
				
				for(int i = 0; i<misUsuarios.size();i++) {
					salida.print("\n(!)Datos del usuario "+misUsuarios.get(i).getName()+" "+misUsuarios.get(i).getStrMySights() +" Dinero gastado: "+misUsuarios.get(i).getSpentMoney() +" Tiempo invertido: "+misUsuarios.get(i).getSpentTime() +"\n");
					
				}
				
				salida.close();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}
}