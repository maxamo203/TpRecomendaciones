package mainProcess;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//cargamos los arraysList con los archivos
		String rutaUser = "";
		String rutaProm = "";
		String rutaAtracc = "";
		String rutaSalida = ""; 
		ArrayList<Usuario> usuarios;
		ArrayList<Recomendacion> recomendaciones;
		try {
			
		
			usuarios = Archivo.cargarArchUsuarios(rutaUser); //metodo estatico clase Archivo
			recomendaciones = Archivo.cargarRecomendaciones(rutaAtracc, rutaProm);
			
			File f2 = new FileWriter(rutaSalida);
			PrintWritter printerWriter = new PrintWriter(f2);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		int cantidadAOrdenar = recomendaciones.size()/4;
		int ultimaPosOrdenada;
		
		System.out.println("\t Bienvenido/a al universo de Dragon Ball Z");
		System.out.println("-----------------------------------------------------------------------------");
		
		for(Usuario usuarioActual: usuarios) {
			ultimaPosOrdenada = 0;
			System.out.println("Nombre de visitante: "+ usuarioActual.getNombre());
			
			
			for(int i = 0;i< recomendaciones.size();i++) {
				if(i == ultimaPosOrdenada) {
					Recomendacion.ordenar(recomendaciones,ultimaPosOrdenada, ultimaPosOrdenada + cantidadAOrdenar, usuarioActual.getPreferencia()); //no ordena todo la lista, sino desde 0(inclusivo) hasta la "n"(exclusivo)
					ultimaPosOrdenada+= cantidadAOrdenar;
				}
				
				if(usuarioActual.puedeComprar(recomendaciones.get(i))) {
					System.out.println(recomendaciones.get(i)); //usando to_string
					
					String respuesta = ObtenerRespuestaValida();
					
					if(respuesta.equals("S")) {
						usuarioActual.comprar(recomendaciones.get(i)); //entre otras cosas, comprar deberia agregar a un set el nombre de las atracciones compradas, decrementar el saldo disponible e incrementar el saldo gastado
					}
					System.out.println("-----------------------------------------------------------------------------");
					
				}
				
			}
			ActualizarCupos(recomendaciones, usuarioActual.getAtraccionesCompradas());
			
			//muestro por consola
			System.out.println("Gasto total: ", usuarioActual.getGastoTotal());
			System.out.println("Atracciones compradas: ", usuarioActual.mostrarAtraccionesComapradas());
			
			//cargo en el archivo .out
			printerWriter.println("Gasto total: ", usuarioActual.getGastoTotal());
			printerWriter.println("Atracciones compradas: ", usuarioActual.mostrarAtraccionesComapradas());
				
				
			
			
		}
		
		printerWriter.close();
		
		
	}
}
