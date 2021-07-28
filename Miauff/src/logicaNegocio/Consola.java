package logicaNegocio;

import java.util.*;
import dataStructures.MyArrayList;
import dataStructures.QueueDoubleLinkedList;
import datosMascotas.Mascotas;
import datosUsuarios.Administrador;
import datosUsuarios.Natural;
import dataStructures.*;
import logicaNegocio.*;
public class Consola {
	public static Scanner sc = new Scanner(System.in);
	public static EscribirArchivo ea;
	public static Lector l ;
	public static Manipular m ;
	private static ArrayList<Object> datos;
	private Natural es ;
	public static int nin = 10;
	public static List<String> valida = List.of("s","si","no","n");
	
	//******** the sign in and register *************
	public static void consolaInicial() {

			boolean variablePrueba= false;
			String respuesta = null ;
			while(!variablePrueba){
				System.out.println("¿Que desea hacer?");
				System.out.println("1) Ingresar");
				System.out.println("2) Registrarse");
				System.out.println("3) Cerrar programa");
				respuesta = sc.nextLine();
				variablePrueba=isNumeric(respuesta);	
			}
			int respuesta1= Integer.parseInt(respuesta);
			if(respuesta1==1){
				consolaEntrarUsuario();
			}else if(respuesta1==2){
				String tipo = consola1();
				if(tipo.equals("a")||tipo.equals("a)")) {
					consolaCrearUsuario("natural");
				}else if (tipo.equals("b")||tipo.equals("b)")) {
					consolaCrearUsuario("administrador");
				}
			}else if(respuesta1==3){
				// **********salida de miauff******************
				System.out.println(" -------------------------------------");
				System.out.println("|      ¡Te esperamos en MIAUFF!       |");
				System.out.println(" -------------------------------------");
				System.out.println("       ¡Vuelve pronto!       ");
				System.exit(0);
			}else {
				System.out.println("Gracias por preferirnos ;)");
			}
	}

	public static boolean isNumeric(String cadena){
		try{
			Integer.parseInt(cadena);
			return true;
		}catch(NumberFormatException nfe){
			System.out.println("Solo entrada numerica, intentalo nuevamente");
			return false;
		}
	}
	public static ListaSimplementeEnlazada<String> lista() {
		ListaSimplementeEnlazada<String> natura = new ListaSimplementeEnlazada<String>();
		natura.pushFront("Mascotas");
		natura.pushFront("Telefono");
		natura.pushFront("Descripcion");
		natura.pushFront("Nombre");
		natura.pushFront("Contraseña");
		natura.pushFront("Usuario");		
		return natura;
	}
//*************** creacion de nuevo usuario******************
	public static String consola1() {
		String respuesta= null;
		while(true) {
			System.out.println("Que tipo de usuario es usted: ");
			System.out.println("a) Soy un amante de los animales");
			System.out.println("b) Somos una fundacion amante de los animales");
			respuesta = sc.nextLine();
			if(respuesta.equals("a")|| respuesta.equals("b")) {
				break;
			}
		}
		return respuesta;	
	}
// ***********validacion de respuestas booleanas en español**********
	public static String tryIsValid() {
		String respo = sc.nextLine().toLowerCase();
		while(!valida.contains(respo)) {
			System.out.println("Respuesta invalida, opciones: s, n ,si ,no");
			System.out.println("Digite una entrada valida");
			respo=sc.nextLine().toLowerCase();
		}return respo;
	}
	public static boolean siNo(String respo) {
		if(valida.indexOf(respo)<=1) {
			return true ;
		}return false;
	}
		
	public static void consolaEntrarUsuario(){
		System.out.println("Escribe tu usuario:");
		String idUsuario = sc.nextLine();
		System.out.println("Escribe tu contraseña:");
		String contrasenia = sc.nextLine();
		String respo=null;
		m = new Manipular(idUsuario,contrasenia);
		boolean esta= m.esta();
		if(!esta) {
			while(!esta) {
				System.out.println("Usuario no se encuentra");
				System.out.println("¿Desea volver a intentarlo? s/n");
				respo = tryIsValid();
				if(siNo(respo)) {
					System.out.println("Escribe tu usuario:");
					idUsuario = sc.nextLine();
					System.out.println("Escribe tu contraseña:");
					contrasenia = sc.nextLine();
					m.setUsuario(idUsuario);
					m.setContrasenia(contrasenia);
					esta= m.esta();
				}else if(!siNo(respo)) {
					esta=true;
				}
			}
			
		}if(m.getEs()!=null) {
			editarPerfil();
		}else if(m.getEst()!=null){
			editarPerfil1();
		}else {
			consolaInicial();
		}
	}
	//*************edicion de perfil para admins********************
	public static void editarPerfil1() {
		System.out.println();
		System.out.println(" -----------------------------------------------");
		System.out.println("| #######   Editar perfil administrador ####### |");
		System.out.println(" -----------------------------------------------");
		
		System.out.println("1) Ver los datos");
		System.out.println("2) Modificar los datos");
		System.out.println("3) Eliminar perfil");
		System.out.println("4) Ir al menu de la fundacion");
		System.out.println("5) Salir");
		int opcion = pedirnumero();
		if(opcion== 1) {
		System.out.println(m.getEst().toString().replace("Administrador","").replace("%", System.lineSeparator()).replace("=", ": "));
		
		editarPerfil1();
		}else if(opcion ==2) {
			Administrador n = m.getEst();
			System.out.println("¿Que desea cambiar?");
			int a =0;
			ListaSimplementeEnlazada<String> list = lista();
			while(a++<4) {
				System.out.println(a + ") " + list.popFront());	
			}
			m.eliminarPersona(n);
			int op = pedirnumero();
			System.out.println("Introduzca el nuevo dato");
			String nuevodato =sc.nextLine();
			if(op==1) {
				n.setIdUsuario(nuevodato);
			}else if(op==2) {
				n.setContraseña(nuevodato);			
			}else if(op==3) {
				n.setNombre(nuevodato);
			}else if(op==4) {
				n.setDescripcion(nuevodato);
			}
			else {
				while(op<1 || op>6){
					System.out.println("No se encuentra en el intervalo");
					op = pedirnumero();
				}
			}
			m.setEst(n);
			m.actualizarAdministrador(n);
			editarPerfil1();
		}else if(opcion==3) {
			m.eliminarPersona(m.getEst());
			System.out.println("Se ha eliminado a " + m.getUsuario());
			consolaInicial();
			
		}else if(opcion==4) {
			consolaOpcionesAdministrador();
		}
		else if(opcion ==5) {
			consolaInicial();
		}
		else {
			System.out.println("La entrada no se encuentra dentro de las opciones");
			editarPerfil1();
			}
		
		
	}
	//*************edicion de perfil para naturales********************
	public static void editarPerfil() {
		System.out.println();
		System.out.println(" -------------------------------------------------");
		System.out.println("| #######   Editar perfil persona natural ####### |");
		System.out.println(" -------------------------------------------------");
		
		System.out.println("1) Ver los datos de perfil");
		System.out.println("2) Ver los datos de mascota");
		System.out.println("3) Modificar los datos");
		System.out.println("4) Ver mascotas para adoptar");
		System.out.println("5) Eliminar perfil");
		System.out.println("6) Salir");
		int opcion = pedirnumero();
		if(opcion== 1) {
				String imprimeme = m.getEs().toString().substring(16, m.getEs().toString().indexOf("#"));
				System.out.println(imprimeme.replace("%", System.lineSeparator()).replace("=", ": "));
		editarPerfil();
		}else if(opcion==2) {
			m.getEs().getMascotas().printList();
			editarPerfil();
		}else if(opcion ==3){
			Natural n = m.getEs();
			System.out.println("¿Que desea cambiar?");
			int a =0;
			ListaSimplementeEnlazada<String> list = lista();
			while(a++<6) {
				System.out.println(a + ") " + list.popFront());	
			}
			int op = pedirnumero();
			if(op==6) {
				int opt = 0 ; 
				while(true) {
					System.out.println();
					System.out.println(" ----------------------------------");
					System.out.println("| #######   Menu Mascotas  ####### |");
					System.out.println(" ----------------------------------");

					System.out.println("1) Eliminar mascota");
					System.out.println("2) Agregar mascota");
					System.out.println("3) Nueva lista de mascotas");
					opt = pedirnumero();
					if(opt==1 || opt==2 || opt==3) {
						break;
					}
				}
				if(opt==1) {
					System.out.println("¿Que mascota quiere eliminar?");
					n.getMascotas().printList(); 
					int size= n.getMascotas().size;
					int opci = pedirnumero();
					if(size==1) {
						Mascotas defecto = new Mascotas("paco","perro","pekines",2,"-url");
						n.getMascotas().popFront();
						n.getMascotas().pushFront(defecto);
					}else {
						n.getMascotas().popIndex(opci);
					}
					editarPerfil();
				}else if(opt==2) {
					System.out.println("¿Cuantas mascotas desea crear?");
					int opci = pedirnumero();
					for(int i = 1;i<= opci;i++) {
						Mascotas animal = nuevasMascotas(i);
						System.out.println(animal.toString());
						n.getMascotas().pushFront(animal);
					}
					n.setMascotas(n.getMascotas());
 
				}else if(opt==3) {
					System.out.println("¿Cuantas mascotas desea crear?");
					int opci = pedirnumero();
					MyArrayList<Mascotas> ma = creacionMascotas(opci);
					n.setMascotas(ma);
					
				}
							
			}else {
				System.out.println("Introduzca el nuevo dato");
				String nuevodato =sc.nextLine();
				if(op==1) {
					n.setIdUsuario(nuevodato);
				}else if(op==2) {
					n.setContraseña(nuevodato);			
				}else if(op==3) {
					n.setNombre(nuevodato);
				}else if(op==4) {
					n.setDescripcion(nuevodato);
				}else {
					n.setTelefono(nuevodato);
				}
				
			}
			while(op<1 || op>6){
				System.out.println("No se encuentra en el intervalo");
				op = pedirnumero();
			}
			m.setEs(n);
			m.actualizarNatural(n);
			editarPerfil();
		}else if(opcion==4) {
			consolaAdoptarMascota();
			editarPerfil();
		}else if(opcion==5) {
			m.eliminarPersona(m.getEs());
			System.out.println("Se ha eliminado a " + m.getUsuario());
			consolaInicial();
		} else if (opcion==6) {
			consolaInicial();
		}
		else {
			System.out.println("La entrada no se encuentra dentro de las opciones");
			editarPerfil();
		}
	
	}
	public static int pedirnumero(){
		String respuesta = sc.nextLine();
		while(!isNumeric(respuesta)){
			respuesta =sc.nextLine();
		}return Integer.parseInt(respuesta);
	}
	public static boolean passwordVerification(String contra1, String contra2) {
		if (!contra1.equals(contra2)) {
			return true;			
		}
		return false;
		
	}
//*************crear nuevas mascotas**********
	public static Mascotas nuevasMascotas(int i) {
		    datos = new ArrayList<Object>();
			System.out.println("");
			System.out.println(" ----------------------------------");
			System.out.println("| #######   Crear mascota  ####### |");
			System.out.println(" ----------------------------------");
			System.out.println("Escribe el nombre de tu mascota #"+i+" :");
			String nombreMascota = sc.nextLine();		
			System.out.println("¿Que tipo de mascota desea agregar?");
			System.out.println("1.gato");
			System.out.println("2.perro");
			System.out.println("3.conejo");
			System.out.println("4.pez");
			int res = pedirnumero();
			while(res<1 || res>4){
				System.out.println("fuera de rango, intentalo nuevamente");
				res = pedirnumero();
				}
			List<String> animales = List.of("gato","perro","conejo","pez"); 
			System.out.println("Escribe la raza de tu mascota #"+i+" :");
			String razaMascota = sc.nextLine();
			System.out.println("Escribe la edad de tu mascota #"+i+" :");
			int edadMascota = pedirnumero();
			System.out.println("Ingrese la direccion de la foto de tu mascota #"+i+" :");
			String fotoMascota = sc.nextLine();
			try {
				if(m.getEs()==null) {
				Administrador admin= new Administrador();
				datos.add(nombreMascota);
				datos.add(animales.get(res-1));
				datos.add(razaMascota);
				datos.add(edadMascota);
				datos.add(fotoMascota);
				admin.crearMascota(datos);
			}
			}catch(NullPointerException e) {
				System.out.print("");
			}
			return new Mascotas(nombreMascota,animales.get(res-1),razaMascota,edadMascota,fotoMascota);
	}
	public static MyArrayList<Mascotas> creacionMascotas(int masnum) {
		MyArrayList<Mascotas> mascotas = new MyArrayList<Mascotas>();

		for(int i = 1;i<= masnum;i++){
			Mascotas animalito = nuevasMascotas(i);
			mascotas.pushFront(animalito);
		}	
		System.out.println("la mascota se agrego correctamente...");
		return mascotas;
	}
	//*********crar nuevo usuario********
	public static void consolaCrearUsuario(String tipo){
		System.out.println("Escribe tu usuario:");
		String idUsuario = sc.nextLine();
		System.out.println("Escribe tu contraseña:");
		String contrasenia = sc.nextLine();
		System.out.println("Vuelva a escribir su contraseña:");
		String contrasenia2 = sc.nextLine();
		boolean bucle=passwordVerification(contrasenia,contrasenia2);
		while(bucle){
			System.out.println("Las contraseñas no coinciden");
			System.out.println("Escribe tu contraseña:");
			contrasenia = sc.nextLine();
			System.out.println("Vuelva a escribir su contraseña:");
			contrasenia2 = sc.nextLine();		
			bucle=passwordVerification(contrasenia,contrasenia2);
		}
		System.out.println("Escriba su nombre (es el que aparece publica):");
		String nombre = sc.nextLine();
		System.out.println("Cuentanos sobre ti, añade una pequeña descripcion ;)");
		String descripcion = sc.nextLine();
		if(tipo.equals("natural")) {
			System.out.println("Escribe tu telefono para que te encuentren:");
			String telefono = sc.nextLine();
			System.out.println("¿Cuantas mascotas tienes?");
			int masnum = 0;
			try {
			    masnum = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
			    e.printStackTrace();
			}
			MyArrayList<Mascotas> mascotas = creacionMascotas(masnum);
			boolean quiereAdopcion= false;
			Natural nuevo = new Natural(idUsuario,contrasenia, nombre, descripcion, telefono, mascotas, quiereAdopcion,masnum);
			ea = new EscribirArchivo(nuevo);
			ea.escribirArchivo("usuarios.txt","usuario", true);
			editarPerfil();		
		}else {
			Administrador admin = new Administrador(idUsuario, contrasenia, nombre, descripcion);
			ea = new EscribirArchivo(admin);
			ea.escribirArchivo("usuarios.txt","usuario", true);
			editarPerfil1();
		}
			
		}

	//Consola Dedicada a Usuario Administrador 
	
	
	public void consolaInformacionMascotas(String tipo,String nombre){
		datos = new ArrayList<Object>();
		
		System.out.println("raza:");
		String raza= sc.nextLine();
		
		System.out.println("edad:");
		Integer edad=pedirnumero();
		datos.add(nombre);
		datos.add(tipo);
		datos.add(raza);
		datos.add(edad);
		System.out.println("foto (direccion):");
		String foto= sc.nextLine();
		datos.add(foto);
		Administrador admin= new Administrador();
		admin.crearMascota(datos);
				
	}
	//*************Editar informacion del administrador***************
public static Mascotas consolaEditarInfo(String tipo,int posicion){
		datos = new ArrayList<Object>();
		LectorMascotas lm= new LectorMascotas("mascotas.txt");
		lm.LecturaLineas();
		datos.add(lm.getDatosMascotas().get(posicion).getNombre());
		String tipoFinal;
		System.out.println("desea modificar el tipo de animal? responder(s/n)");
		String res1 = sc.nextLine().trim();
		if(res1.equals("s")) {
			System.out.println("¿a que tipo desea cambiar?");
			System.out.println("1.gato");
			System.out.println("2.perro");
			System.out.println("3.conejo");
			System.out.println("4.pez");
			//< >
			List<String> animales = List.of("gato","perro","conejo","pez"); 
			Integer res = pedirnumero();
				while(res<1 || res>4){
				System.out.println("fuera de rango, intentalo nuevamente");
				res = pedirnumero();
				}
				datos.add(animales.get(res));
		}else {
			if(res1.equals("n")) {
				tipoFinal=tipo;
				datos.add(tipoFinal);
			}else {
				System.out.println("solo se acepta (s/n)");
			}
		}	
		
		System.out.println("reconfirma la raza:");
		String raza= sc.nextLine();
		System.out.println("reconfirma la edad:");
		Integer edad= pedirnumero();
		datos.add(raza);
		datos.add(edad);
		
		System.out.println("carga una nueva foto (direccion):");
		String foto= sc.next();
		datos.add(foto);
	
		String dato0= datos.get(0).toString();
		String dato1= datos.get(1).toString();
		String dato2= datos.get(2).toString();
		int dato3= Integer.parseInt(datos.get(3).toString());
		String dato4=datos.get(4).toString();
		Administrador admin= new Administrador();
		Mascotas datosEnviar = new Mascotas(dato0,dato1,dato2,dato3,dato4);
		return datosEnviar;
	}
	
	public static void consolaEliminarMascota(){
		System.out.println("");
		System.out.println(" ----------------------------------");
		System.out.println("| ####### ELIMINAR MASCOTA ####### |");
		System.out.println(" ----------------------------------");
		System.out.println("que mascota desea eliminar:");
		Administrador admin= new Administrador();
		
		LectorMascotas lm= new LectorMascotas("mascotas.txt");
		lm.LecturaLineas();
		
		int contador= 1;
		for (Mascotas i: lm.getDatosMascotas()){
			System.out.println(contador+". "+i.getTipo()+" "+i.getRaza()+" "+i.getEdad()+" "+i.getFoto());
			contador+=1;	
		}
		String opcion = sc.next();
		if(isNumeric(opcion)){
			int opcion1=Integer.parseInt(opcion);
			if(opcion1==0){
				admin.eliminarMascota(opcion1)	;
			}else{
				admin.eliminarMascota(opcion1-1)	;
			}
			
			System.out.println("la mascota se elimino correctamente...");
			consolaOpcionesAdministrador();
			
			
		}else{
			System.out.println("solo datos numericos");
			consolaEliminarMascota();
		}
	}
	
	public static void consolaEditar1Mascota(){
		System.out.println("");
		System.out.println(" ----------------------------------");
		System.out.println("| #######  EDITAR MASCOTA  ####### |");
		System.out.println(" ----------------------------------");
		
		System.out.println("que mascota desea modificar:");
		Administrador admin= new Administrador();
		
		LectorMascotas lm= new LectorMascotas("mascotas.txt");
		lm.LecturaLineas();
		
		int contador= 1;
		for (Mascotas i: lm.getDatosMascotas()){
			System.out.println(contador+". "+i.getTipo()+" "+i.getRaza()+" "+i.getEdad()+" "+i.getFoto());
			contador+=1;	
		}	
		String opcion = sc.nextLine();
		if(isNumeric(opcion)){
			int opcion1=Integer.parseInt(opcion);
			admin.editarMascota(opcion1-1, consolaEditarInfo(lm.getDatosMascotas().get(opcion1).getTipo(),opcion1-1));
			System.out.println("la mascota se agrego correctamente...");
			consolaOpcionesAdministrador();
			
		}else{
			System.out.println("solo datos numericos");
			consolaEditar1Mascota();
	
		}
		
	}
	
	
	public static void consolaOpcionesAdministrador() {
		System.out.println("");
		System.out.println(" ---------------------------------------");
		System.out.println("| ####### Menu de la fundacion ####### |");
		System.out.println(" ---------------------------------------");
		System.out.println("que deseaa hacer:");
		
		boolean variablePrueba= false;
		int respuesta1 = 0 ;
		
		while(!variablePrueba){

			System.out.println("1.agregar una mascota para adoptar");
			System.out.println("2.eliminar una mascota de adopcion");
			System.out.println("3.modificar informacion de una mascota");
			System.out.println("4.volver al menu de administrador");
			respuesta1 = pedirnumero();
			variablePrueba= isNumeric(Integer.valueOf(respuesta1).toString());	
			if(respuesta1<1 || respuesta1>4) {
				System.out.println("fuera de rango");
				variablePrueba=false;
			}
		}
		if (respuesta1==1) {
			Mascotas m = nuevasMascotas(1);
			consolaOpcionesAdministrador();
		}
		if(respuesta1==2) {
			consolaEliminarMascota();
			consolaOpcionesAdministrador();

		}
		if(respuesta1==3) {
			consolaEditar1Mascota();
			consolaOpcionesAdministrador();

		}
		if(respuesta1==4) {
			editarPerfil1();
		}
	}
	
	//Consola dedicada a Usuario Natural	
	public static void consolaAdoptarMascota() {

		LectorMascotas ld=new LectorMascotas("mascotas.txt");
		ld.LecturaLineas();
		System.out.println(" ---------------------------------------------------");
		System.out.println("| #   Estas son nuestras Mascotas en Adopcion UwU # |");
		System.out.println(" ---------------------------------------------------");
		System.out.println("1.Ver todas las mascotas para adoptar");
		System.out.println("2.Ver mascotas por filtro");
		int res = pedirnumero();
		while(res<1 || res>2){
			System.out.println("Elige una opcion valida");
			res = pedirnumero();
		}
		if (res==1) {
			LectorMascotas lm= new LectorMascotas("mascotas.txt");
			lm.LecturaLineas();
			int contador= 1;
			for (Mascotas i: lm.getDatosMascotas()){
				System.out.println(contador+". nombre: "+i.getNombre()+" tipo: "+i.getTipo()+" raza: "+i.getRaza()+" edad: "+i.getEdad()+" link foto: "+i.getFoto());
				if(contador==nin) {
					break;
				}
				contador+=1;
				
			}
			int res1 = pedirnumero();
			while(res1<1 || res1>ld.getDatosMascotas().size()){
				System.out.println("Elige una opcion valida");
				res1 = pedirnumero();
			}
			System.out.println(lm.getDatosMascotas().get(res1-1));
		} else{
			//*************filtrar mascotas*************
			System.out.println("1. Filtra por las macotas mas recientes");
			System.out.println("2. Filtra por las macotas mas antiguas");
			res = pedirnumero();
			while(res<1 || res>2){
				System.out.println("Elige una opcion valida");
				res = pedirnumero();
			}
			if (res== 1) {
				imprimirPorPila();
			} else {
				imprimirPorCola();
			}
		}
        
	}

	public static void imprimirPorPila() {
		//ulti
		PilaListaEnlazada<String> pila = new PilaListaEnlazada<String>();
		ArrayList<Mascotas> arrayDatos= new ArrayList<Mascotas>();
		LectorMascotas lm= new LectorMascotas("mascotas.txt");
		lm.LecturaLineas();
		arrayDatos= lm.getDatosMascotas();
		int contador=0;
		 long tinicial = System.nanoTime();
		Collections.reverse(arrayDatos);
		for(int i=nin;0<i;i--) {
			pila.Push(arrayDatos.get(i-1).toString().replaceAll("#", "").replaceAll("-", ":"));
		}
		 long tfinal = System.nanoTime();
		pila.printStack();
		System.out.println("hecho en :"+( tfinal-tinicial)+" nanosegundos");
	}
	public static void imprimirPorCola() {
		//prim
		QueueDoubleLinkedList<String> cola = new QueueDoubleLinkedList<String>();
		ArrayList<Mascotas> arrayDatos= new ArrayList<Mascotas>();
		LectorMascotas lm= new LectorMascotas("mascotas.txt");
		lm.LecturaLineas();
		arrayDatos= lm.getDatosMascotas();
		int contador=1;
		 long tinicial = System.nanoTime();

		
		for(int i=nin;0<i;i-- ) {
			cola.enqueue(arrayDatos.get(i-1).toString().replaceAll("#", "").replaceAll("-", ":"));
		}
		System.out.println();
		long tfinal = System.nanoTime();

		cola.printQueue();
		System.out.println();
		System.out.println("hecho en :"+( tfinal-tinicial)+" nanosegundos");
		/* funciona para cualquier cantidad de mascotas 
		Collections.reverse(arrayDatos);
		for(Mascotas m: arrayDatos) {
			if(contador==3) {
				break;
			}
			cola.enqueue(arrayDatos.get(contador).toString()); 
			contador++;
		}
		cola.printQueue();
		*/
		
		
	}
}