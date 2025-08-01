package Escuela;
import Escuela.Materias.BasesData;
import Escuela.Materias.DisSoftware;
import Escuela.Materias.LogComputacional;
import Escuela.Materias.PruebaSoft;
import Escuela.TiposUsuarios.*;

import java.util.ArrayList;
import java.util.Scanner;
import lombok.Data;

@Data
public class Main {
    Admin admin = new Admin();//instancia principal (administrador del sistema)
    static Scanner sc = new Scanner(System.in);
    // Listas globales de usuarios
    private static ArrayList<Estudiante> todosEst = new ArrayList<>();
    private static ArrayList<Profesor> todosProf = new ArrayList<>();

    // Instancias de materias
    private static LogComputacional logComputador = new LogComputacional();
    private static BasesData basesData = new BasesData();
    private static DisSoftware disSoftware = new DisSoftware();
    private static PruebaSoft pruebaSoft = new PruebaSoft();

    public static void main(String[] args) {
        Main m = new Main(); // accede a menus y a metodos

        System.out.println("Bienvenido al sistema");
        String resp = "si",usuario,contr;
        boolean exist = false;
        System.out.println("desea iniciar sesion? (si/no)");
        String op = sc.nextLine();

        // bucle principal y control
        do{
            // validacion de respuesta
            while(!op.equals("si")&&!op.equals("no")){
                System.out.println("Opcion no permitida... intenta de nuevo");
                op = sc.nextLine();
            }
            if (op.equals("si")){
                System.out.println("Ingrese el usuario");
                usuario = sc.nextLine();
                System.out.println("Ingrese la contraseña");
                contr = sc.nextLine();

                System.out.println("Escriba el numero del cargo que pertezca");
                String cargo="";
                do {
                    System.out.println("1. Administrador");
                    System.out.println("2. Profesor");
                    System.out.println("3. Estudiante");
                    cargo = sc.nextLine();


                    // un tipo de auntenticacion para acceder a los menus segun el tipo de usuario
                    switch (cargo) {
                        case "1":
                            exist = m.verificacion(usuario, contr, "administrador");
                            if (exist) {
                                m.menuAdmin(); // acede a funciones del administrador
                            }
                            break;
                        case "2":
                            exist = m.verificacion(usuario, contr, "profesor");
                            if (exist) {
                                // busca el indice del profesor y accede al menu (para verificar)
                                m.menuProfesor(todosProf.indexOf(Admin.buscarProfesor(usuario)));
                            }
                            break;
                        case "3":
                            exist = m.verificacion(usuario, contr, "estudiante");
                            if (exist) {
                                // busca el indice del estudiante y accede al menu (para verificar)
                                m.menuEstudiante(todosEst.indexOf(Admin.buscarEstudiante(usuario)));
                            }
                            break;
                        default:
                            System.out.println("Opcion invalida, intente nuevamente.");
                    }
                }while (!cargo.equals("1")&&!cargo.equals("2")&&!cargo.equals("3")   );
                // sistema de control si llega a fallar la auntenticacion
                if (!exist){
                    System.out.println("Se ha equivocado en la contraseña o el usuario");
                    System.out.println("Desea cambiar de usuario? (si/no)");
                    op = sc.nextLine();

                    while(!op.equals("si")&&!op.equals("no")){
                        System.out.println("Debe responder si o no");
                        op = sc.nextLine();
                    }
                }

            }if(op.equals("no")){
                resp = "no";
            }
            if (exist){
                System.out.println("Desea cambiar de usuario? (si/no)");
                resp = sc.nextLine();
                while(!resp.equals("si")&&!resp.equals("no")){
                    System.out.println("Debe responder si o no");
                    resp = sc.nextLine();
                }
            }

        }while(!resp.equals("no")); // Fin del bucle
        System.out.println("Nos vemos a la proxima:)");
    }

    // Inserta un apunte en la lista correspondiente segun la materia que se indique
    public void insertApunte(String materia, Apuntes apunte ) {
        // verifica la materia y añade el apunte en la lista correspondiente
        if (materia.equals("logica computacional")) {
            ArrayList <Apuntes> apuntesList = this.logComputador.getApuntesClase();
            apuntesList.add(apunte);
            this.logComputador.setApuntesClase(apuntesList);
        }else if (materia.equals("bases de datos")) {
            ArrayList <Apuntes> apuntesList = this.basesData.getApuntesClase();
            apuntesList.add(apunte);
            this.basesData.setApuntesClase(apuntesList);
        }else if  (materia.equals("pruebas de software")) {
            ArrayList <Apuntes> apuntesList = this.pruebaSoft.getApuntesClase();
            apuntesList.add(apunte);
            this.pruebaSoft.setApuntesClase(apuntesList);
        }else if (materia.equals("diseño de software")) {
            ArrayList <Apuntes> apuntesList = this.disSoftware.getApuntesClase();
            apuntesList.add(apunte);
            this.disSoftware.setApuntesClase(apuntesList);
        }
    }

    // Agrega un nuevo profesor a la lista general de profesores
    public void insrtProfes(Profesor profesor){
        todosProf.add(profesor);
    }
    // Agrega un nuevo estudiante a la lista general de estudiantes
    public void insertEstu(Estudiante estudiante){
        todosEst.add(estudiante);
    }
    public void actualizarProfEst(Estudiante estudianteAct, int posEst){
        todosEst.set(posEst, estudianteAct);
    }

    // Retorna la lista completa de estudiantes registrados
    public ArrayList <Estudiante> getEstudiantes(){
        return todosEst;
    }
    // Retorna la lista completa de profesores registrados
    public ArrayList <Profesor> getProfesores(){
        return todosProf;
    }

    // Verifica si algun usuario existe
    public Boolean verificacion(String usuario, String contr, String cargo){
        boolean respu = false;

        // segun el cargo, va realizando la verificacion correspondiente
        switch(cargo){
            case "administrador":
                // Verifica si el administrador registrado coincide con los datos ingresados
                if (admin.getNombre().equals(usuario)&&admin.getContr().equals(contr)){
                    respu = true;
                }
                break;
            case "profesor":
                // Recorre la lista de profesores y compara nombre y contraseña
                for (Profesor profesor : getProfesores()){
                    if (profesor.getNombre().equals(usuario)&&profesor.getContr().equals(contr)){
                        respu = true;
                    }
                }
                break;
            case "estudiante":
                // Recorre la lista de estudiantes y compara nombre y contraseña
                for (Estudiante estudiante : getEstudiantes()){
                    if (estudiante.getNombre().equals(usuario)&&estudiante.getContr().equals(contr)){
                        respu = true;
                    }
                }
                break;
        }
        return respu; //devuelve true si encontro concidiencia, false si no
    }

    // Menu del administrador
    public void menuAdmin(){
        System.out.println("Bienvenido al sistema "+admin.getNombre()+" que desea hacer el dia de hoy:");
        String nombre,correo,seg = "no";

        // Bucle principal del menú que se repite mientras el usuario lo desee
        do {
            System.out.println("1. Crear un profesor \n2. Crear un estudiante \n3. Buscar a un profesor \n4. Buscar a un estudiante \n5. Verificar si el usuario existe");
            String op = sc.nextLine();
            boolean exist;
            switch (op) {
                case "1":
                    admin.crearProfesor();
                    seg="no";
                    break;
                case "2":
                    admin.crearEstudiante();
                    seg="no";
                    break;
                case "3":
                    System.out.println("Escriba el nombre del profesor: ");
                    nombre = sc.nextLine();
                    System.out.println("Escriba el correo de la profesor: ");
                    correo = sc.nextLine();
                    exist = Admin.verificarPersona(nombre, correo,"profesor");
                    if (exist){
                        Profesor profbusc = Admin.buscarProfesor(nombre);
                        System.out.println("Nombre: "+profbusc.getNombre());
                        System.out.println("Apellido: "+profbusc.getApellido());
                        System.out.println("Edad: "+profbusc.getEdad());
                        System.out.println("Email: "+profbusc.getEmail());
                        System.out.println("Telefono: "+profbusc.getTelefono());
                    }else{
                        System.out.println("No existe el profesor");
                    }
                    seg="no";
                    break;
                case "4":
                    System.out.println("Escriba el nombre del estudiantes: ");
                    nombre = sc.nextLine();
                    System.out.println("Escriba el correo de la estudiante: ");
                    correo = sc.nextLine();
                    exist = Admin.verificarPersona(nombre, correo,"estudiante");
                    if (exist){
                        Estudiante estbusc = Admin.buscarEstudiante(nombre);
                        System.out.println("Nombre: "+estbusc.getNombre());
                        System.out.println("Apellido: "+estbusc.getApellido());
                        System.out.println("Edad: "+estbusc.getEdad());
                        System.out.println("Email: "+estbusc.getEmail());
                        System.out.println("Telefono: "+estbusc.getTelefono());
                    }else{
                        System.out.println("No existe el estudiante");
                    }
                    seg="no";
                    break;
                case "5":
                    System.out.print("Escriba el nombre del usuario: ");
                    nombre = sc.nextLine();
                    System.out.print("Escriba el correo del usuario: ");
                    correo = sc.nextLine();
                    System.out.println();
                    System.out.println("Escriba el numero del cargo que pertezca \n1. Profesor \n2. Estudiante  ");
                    String cargo=sc.nextLine();
                    while (!cargo.equals("1") && !cargo.equals("2")){
                        System.out.println("La opcion no es valida");
                        cargo=sc.nextLine();
                    }
                    if (cargo.equals("1")){
                        cargo="profesor";
                    }else if (cargo.equals("2")){
                        cargo="estudiante";
                    }
                    exist=Admin.verificarPersona(nombre, correo,cargo);
                    if (exist){
                        System.out.println("El usuario "+nombre+" si existe");
                    }else{
                        System.out.println("El usuario "+nombre+" no existe");
                    }
                    break;
                default:
                    System.out.println("Opcion invalida, intente nuevamente");
                    seg="si";
                    break;
            }
            // Pregunta si el usuario desea realizar otra acción
            System.out.println("Desea hacer algo mas?");
            seg=sc.nextLine();
            // validacion y control
            while(!seg.equals("no") && !seg.equals("si")){
                System.out.println("Opcion invalida, intente nuevamente");
                seg= sc.nextLine();
            }
        }while (seg.equals("si"));// repite el menu si el usuario quiere continuar
    }
    // Menu del profesor en el sistema
    public void menuProfesor(int profesor){
        System.out.println("Bienvenido al sistema profesor "+todosProf.get(profesor).getNombre()+" que desea hacer");
        String seg = "no";

        // Bucle principal del menú que se repite mientras el usuario lo desee
        do {
            System.out.println("1. Crear una tarea \n2. Mostrar tareas asignadas \n3. Buscar informacion de un estudiante \n4. Crear un horario \n5. Revisar mi horario \n6. Ingresar un estudiante a mi lista \n7. Ver mi lista de estudiantes");
            String op = sc.nextLine();
            boolean exist;
            switch (op) {
                case "1":
                    todosProf.get(profesor).crearTarea();
                    seg = "no";
                    break;
                case "2":
                    todosProf.get(profesor).mostrarTareas();
                    seg = "no";
                    break;
                case "3":
                    todosProf.get(profesor).infoEstudiante();
                    seg = "no";
                    break;
                case "4":
                    todosProf.get(profesor).crearHorario();
                    seg = "no";
                    break;
                case "5":
                    todosProf.get(profesor).listaHorario();
                    seg = "si";
                    break;
                case "6":
                    todosProf.get(profesor).ingresarEstudiante();
                    seg = "no";
                    break;
                case "7":
                    todosProf.get(profesor).listaEstudiantes();
                    seg = "no";
                    break;
                default:
                    System.out.println("Opcion invalida, intente nuevamente. ");
                    seg = "si";
                    break;
            }
            // Pregunta si el usuario desea realizar otra acción
            System.out.println("Desea hacer algo mas?");
            seg = sc.nextLine();
            // validacion y control
            while (!seg.equals("no") && !seg.equals("si")) {
                System.out.println("Opcion invalida, intente nuevamente");
                seg = sc.nextLine();
            }
        }while (seg.equals("si")); // repite el menu si el usuario quiere continuar
    }
    // Menu del estudiante en el sistema
    public void menuEstudiante(int estudiante){
        System.out.println("Bienvenido al sistema "+todosEst.get(estudiante).getNombre()+" que desea hacer el dia de hoy:");
        String seg = "no";

        // Bucle principal del menú que se repite mientras el usuario lo desee
        do {
            System.out.println("1. Ver tareas pendientes \n2. Buscar informacion de un profesor \n3. Mostrar horarios \n4. Ver lista de profesores \n5. Crear apuntes \n6. Mostrar apuntes");
            String op = sc.nextLine();
            boolean exist;
            switch (op) {
                case "1":
                    todosEst.get(estudiante).verTareas();
                    seg="no";
                    break;
                case "2":
                    todosEst.get(estudiante).infoProfesores();
                    seg="no";
                    break;
                case "3":
                    todosEst.get(estudiante).mostrarHorarios();
                    seg="no";
                    break;
                case "4":
                    todosEst.get(estudiante).verProfesores();
                    seg="no";
                    break;
                case "5":
                    todosEst.get(estudiante).crearApunte();
                    break;
                case "6":
                    todosEst.get(estudiante).mostrarApunte();
                    break;
                default:
                    System.out.println("Opcion invalida, intente nuevamente");
                    seg="si";
                    break;
            }
            // Pregunta si el usuario desea realizar otra acción
            System.out.println("Desea hacer algo mas? (si/no)");
            seg=sc.nextLine();
            // validacion y control
            while(!seg.equals("no") && !seg.equals("si")){
                System.out.println("Opcion invalida, intente nuevamente");
                seg= sc.nextLine();
            }
        }while (seg.equals("si")); // repite el menu si el usuario quiere continuar
    }
}