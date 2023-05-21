package mainProcess;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
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
		//prueba pull
		
	}
}
