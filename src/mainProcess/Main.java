package mainProcess;


public class Main {

	public static void main(String[] args) {
		String base = "C:\\Users\\maxam\\Downloads\\Trabajo_Tierra_Media\\src\\mainProcess\\";
		String rutaArchUsuarios= base + "usuarios.in",
			rutaArchAtracciones=base + "atracciones.in", 
			rutaArchPromociones=base + "promociones.in";
		String rutaSalida=base + "salidaUsuarios.out";
		
		InterfazUsuario.mainProcess(rutaArchUsuarios,rutaArchAtracciones, rutaArchPromociones,rutaSalida);
		
	
	}

	
}
