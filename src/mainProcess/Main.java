package mainProcess;

public class Main {

	public static void main(String[] args) {
		String rutaArchUsuarios = "archives\\Usuarios.in";
		String rutaArchAtracciones = "archives\\Atracciones.in";
		String rutaArchPromociones = "archives\\Paquetes.in";
		String rutaSalida = "archives\\salidaUsuarios.out";
		InterfazUsuario.mainProcess(rutaArchUsuarios,rutaArchAtracciones, rutaArchPromociones,rutaSalida);
	}
}
