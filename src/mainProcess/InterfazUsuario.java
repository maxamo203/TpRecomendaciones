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
			
			ArrayList<User> misUsuarios = procesoArchUsuarios(rutaArchUsuarios);
			ArrayList<Sight> misAtracciones = procesoArchAtracciones(rutaArchAtracciones);
			ArrayList<Promotion> misPromociones = procesoArchPromociones(rutaArchPromociones, misAtracciones);
			
			ArrayList<OfferdableItem> misOfferdables = new ArrayList<OfferdableItem>();
			misOfferdables.addAll(misPromociones);
			misOfferdables.addAll(misAtracciones);
			char opcion;
			
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
								System.out.println("¡Aceptada!");
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
	
		private static ArrayList<Sight> procesoArchAtracciones(String rutaArchAtracciones) {
			File archivo = new File(rutaArchAtracciones);
			ArrayList<Sight> ret = new ArrayList<Sight>();
			
			int prefInt,quota;
			Preferency pref = null;
			String name;
			double cost,time;
			
			try {
				Scanner entrada = new Scanner(archivo);
				
				while(entrada.hasNext()) {
					prefInt  = entrada.nextInt();
					
					
					switch(prefInt){
						case 1: pref = Preferency.ACADEMICO; break;
						case 2: pref = Preferency.AVENTURA; break;
						case 3: pref = Preferency.BANQUETE; break;
						case 4: pref = Preferency.COMBATE; break;
						case 5: pref = Preferency.CONQUISTA; break;
					}
					name = entrada.next();
					cost = entrada.nextDouble();
					time = entrada.nextDouble();
					quota = entrada.nextInt();
					
					
					Sight atracc = new Sight(name,cost,time,quota,pref);
					ret.add(atracc);
					
					
				}
				entrada.close();
				
			}catch(FileNotFoundException ex) {
				ex.printStackTrace();
			}
			
			
			return ret;
		}
	
		private static ArrayList<Promotion> procesoArchPromociones(String rutaArchPromociones, ArrayList<Sight> atracciones) {
			File archivo = new File(rutaArchPromociones);
			ArrayList<Promotion> ret = new ArrayList<Promotion>();
			
			int prefInt,claseProm,cantAtraccion;
			Preferency pref = null;
			double discount;
			String nombreAtraccion;
			Promotion miPromo = null;
			
			try {
				Scanner entrada = new Scanner(archivo);
				
				while(entrada.hasNext()) {
					prefInt  = entrada.nextInt();
					
					
					switch(prefInt){
						case 1: pref = Preferency.ACADEMICO; break;
						case 2: pref = Preferency.AVENTURA; break;
						case 3: pref = Preferency.BANQUETE; break;
						case 4: pref = Preferency.COMBATE; break;
						case 5: pref = Preferency.CONQUISTA; break;
					}
					
					claseProm = entrada.nextInt();
					
					if(claseProm == 1) {
						miPromo = new PromotionAbsolute(pref);
					}
					else if (claseProm == 2){
						miPromo = new PromotionPercentual(pref);
					}
					else {
						miPromo = new PromotionFreeSights(pref);
					}
					
					
					if(claseProm == 1 || claseProm == 2) {
						discount = entrada.nextDouble();
						cantAtraccion = entrada.nextInt();
						for(int i = 0; i<cantAtraccion;i++) {
							nombreAtraccion = entrada.next();
							miPromo.loadSight(nombreAtraccion,atracciones);
							
						}
						
						if(claseProm == 1) {
							((PromotionAbsolute)miPromo).setDiscount(discount);
						}
						else {
							((PromotionPercentual)miPromo).setPercentage(discount);
						}
					}
					else {
						cantAtraccion = entrada.nextInt();
						for(int i = 0; i<cantAtraccion;i++) {
							nombreAtraccion = entrada.next();
							miPromo.loadSight(nombreAtraccion, atracciones);
							((PromotionFreeSights)miPromo).addFreeSight(nombreAtraccion, atracciones);
						}
						cantAtraccion = entrada.nextInt();
						for(int i = 0; i<cantAtraccion;i++) {
							nombreAtraccion = entrada.next();
							miPromo.loadSight(nombreAtraccion, atracciones);
						}
					}
	
					
					
					ret.add(miPromo);
					
					
				}
				entrada.close();
				
			}catch(FileNotFoundException ex) {
				ex.printStackTrace();
			}
			
			
			return ret;
		}
	
		private static ArrayList<User> procesoArchUsuarios(String rutaArchUsuarios) {
			File archivo = new File(rutaArchUsuarios);
			ArrayList<User> ret = new ArrayList<User>();
			
			int prefInt;
			Preferency pref = null;
			String name;
			double money,time;
			
			try {
				Scanner entrada = new Scanner(archivo);
				
				while(entrada.hasNext()) {
					prefInt  = entrada.nextInt();
					
					
					switch(prefInt){
						case 1: pref = Preferency.ACADEMICO; break;
						case 2: pref = Preferency.AVENTURA; break;
						case 3: pref = Preferency.BANQUETE; break;
						case 4: pref = Preferency.COMBATE; break;
						case 5: pref = Preferency.CONQUISTA; break;
					}
					name = entrada.next();
					money = entrada.nextDouble();
					time = entrada.nextDouble();
					
					
					
					User us = new User(name,money,time,pref);
					ret.add(us);
					
					
				}
				entrada.close();
				
			}catch(FileNotFoundException ex) {
				ex.printStackTrace();
			}
			
			
			return ret;
		}
}
