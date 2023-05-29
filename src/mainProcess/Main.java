package mainProcess;

import java.util.Scanner;
import resourcesAndClasses.*;
import java.util.ArrayList;
import resourcesAndClasses.Preferency;

public class Main {

	public static void main(String[] args) {
		
		
		User goku = new User("Goku",5000,10,Preferency.COMBATE);
		
		
		Sight miAtraccion = new Sight("Namekusei",200,1,100,Preferency.COMBATE);
		Sight miAtraccion2 = new Sight("Tierra",300,1,100,Preferency.ACADEMICO);
		
		ArrayList<Sight> myList = new ArrayList<Sight>();
		
		myList.add(miAtraccion);
		myList.add(miAtraccion2);
		
		Promotion miPromocion = new Promotion(Preferency.COMBATE);
		miPromocion.loadSight("Namekusei",myList );
		miPromocion.loadSight("Tierra", myList);
		miPromocion.setAbsolute(450);
		char opcion;
		
		
		if(miAtraccion.canBuy(goku)) {
			try (Scanner teclado = new Scanner(System.in)) {
				do {
					System.out.println("1.Desea comprar esta atraccion? (SI/NO)");
					
					opcion = teclado.next().toLowerCase().charAt(0);
				}while(opcion != 's' && opcion !='n');
				
				if(opcion == 's') {
					miAtraccion.buy(goku);
				}
			}
		}
		
		
		if(miPromocion.canBuy(goku)) {
			try (Scanner teclado2 = new Scanner(System.in)) {
				do {
					System.out.println("2.Desea comprar esta atraccion? (SI/NO)");
					opcion = 's';
					
				}while(opcion != 's' && opcion !='n');
				
				if(opcion == 's') {
					miPromocion.buy(goku);
					System.out.println("estoy comprando la promocion");
				}
			}
		}
		
		System.out.println(goku.getMySights() +" Dinero gastado: "+goku.getSpentMoney() +" Tiempo invertido: "+goku.getSpentTime());
		
		
		
		/*
		//cargamos los arraysList con los archivos
		String rutaUser = "";
		String rutaProm = "";
		String rutaAtracc = "";
		String rutaSalida = ""; 
		
		try {
			
		
			ArrayList<Usuario> usuarios = Archivo.cargarArchUsuarios(rutaUser); //metodo estatico clase Archivo
			ArrayList<Atraccion> atracciones = Archivo.cargarArchAtracciones(rutaAtracc);
			ArrayList<Promocion> promociones = Archivo.cargarArcPromociones(rutaProm);
			
			File f2 = new FileWriter(rutaSalida);
			PrintWritter printerWriter = new PrintWriter(f2);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		
		
		System.out.println("\t Bienvenido/a al universo de Dragon Ball Z");
		System.out.println("-----------------------------------------------------------------------------");
		
		for(Usuario usuarioActual: usuarios) {
			
			System.out.println("Nombre de visitante: "+ usuarioActual.getNombre());
			
			
			promociones.ordenar(usuarioActual.getPref());
			atracciones.ordenar(usuarioActual.getPref());
			
			
			for(Promocion promActual: promociones) {
				if(usuarioActual.puedeComprar(promActual,atracciones)) {
					System.out.println(promoActual); //sobreescribimos metodo to_string
					
					do {
						
						System.out.println("Acepta sugerencia? Ingrese S o N: ");
						
						Scanner entradaTecl = new Scanner(System.in);
						String opcion = entradaTecl.nextLine();
						opcion = opcion.toUpperCase();
						
					}while(!opcion.equals("S") && !opcion.equals("N")); 
					
					if(opcion.equals("S")) {
						usuarioActual.comprar(promoActual,atracciones); // actualiza atracciones y el usuarioActual
					}
					System.out.println("-----------------------------------------------------------------------------");
				}
				
			}
			
			for(int i =0; i<atracciones.size();i++) { // hay que actualizar constantemente la lista de atracciones
				if(usuarioActual.puedeComprar(atracciones.get(i))) {
					System.out.println(atracciones.get(i));
					
					do {
						System.out.println("Acepta sugerencia? Ingrese S o N: ");
						
						Scanner entradaTecl = new Scanner(System.in);
						String opcion = entradaTecl.nextLine();
						opcion = opcion.toUpperCase();
						
					}while(!opcion.equals("S") && !opcion.equals("N")); 
					
					if(opcion.equals("S")) {
						atracciones.get(i) = usuarioActual.comprar(atracciones.get(i)); // actualiza atracciones y el usuarioActual
					}	
					
					System.out.println("-----------------------------------------------------------------------------");
				}
				
				
			}
			
			//muestro por consola
			System.out.println("Gasto total: ", usuarioActual.getGastoTotal);
			System.out.println("Atracciones compradas: ", usuarioActual.mostrarAtraccionesComapradas));
			
			//cargo en el archivo .out
			printerWriter.println("Gasto total: ", usuarioActual.getGastoTotal);
			printerWriter.println("Atracciones compradas: ", usuarioActual.mostrarAtraccionesComapradas));
				
				
			
			
		}
		
		printerWriter.close();
		
*/		
	}
}
