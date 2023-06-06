package mainProcess;

public class Main {

	public static void main(String[] args) {
		String rutaArchUsuarios = "archiveTemporal\\Usuarios.in";
		String rutaArchAtracciones = "archiveTemporal\\Atracciones.in";
		String rutaArchPromociones = "archiveTemporal\\Paquetes.in";
		String rutaSalida = "archiveTemporal\\salidaUsuarios.out";
		InterfazUsuario.mainProcess(rutaArchUsuarios,rutaArchAtracciones, rutaArchPromociones,rutaSalida);
	}
}
