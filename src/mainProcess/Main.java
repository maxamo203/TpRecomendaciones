package mainProcess;

public class Main {

	public static void main(String[] args) {
		String rutaArchUsuarios="src\\mainProcess\\usuarios.in",
				rutaArchAtracciones="src\\mainProcess\\atracciones.in",
				rutaArchPromociones="src\\mainProcess\\promociones.in";
		String rutaSalida="src\\mainProcess\\salidaUsuarios.out";
		
		InterfazUsuario.mainProcess(rutaArchUsuarios,rutaArchAtracciones, rutaArchPromociones,rutaSalida);
	}
	}
