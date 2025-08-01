package Escuela.TiposUsuarios;
import java.util.ArrayList;
import Escuela.Apuntes;
import Escuela.Main;
import Escuela.Tareas;
import Escuela.TiposUsuarios.Persona;
import lombok.Data;

@Data
public class Estudiante extends Persona {
    Main main = new Main();
    Profesor profesor = new Profesor();

    private ArrayList <Apuntes> apuntesEst = new ArrayList<>(); // Apuntes escolares
    private ArrayList <String> profeEst = new ArrayList<>(); // Lista de nombres de los profesores que lo tienen de alumno

    // 1. Permite ver las tareas asignadas por los profesores
    public void verTareas() {
        ArrayList<Profesor> profesores = main.getProfesores();
        try{
            for (String i: profeEst){
                try {
                    for(Profesor p: profesores){
                        if(p.getNombre().equals(i)){
                            System.out.println("Profesor: "+p.getNombre());
                            System.out.println("Tareas");
                            p.mostrarTareas();
                        }
                    }
                }catch (Exception e){
                    System.out.println("No existen profesores aun.");
                }
            }
        }catch(Exception e){
            System.out.println("No se le han asignado profesores aun.");
        }

    }
    // 2. Busca la informacion de un profesor
    public void infoProfesores() {
        System.out.println("Escriba el nombre del profesor: ");
        String nombre = sc.nextLine();
        System.out.println("Escriba el correo de la profesor: ");
        String correo = sc.nextLine();
        boolean exist = Admin.verificarPersona(nombre, correo,"profesor");
        if (exist){
            Profesor profbusc = Admin.buscarProfesor(nombre);
            System.out.println("Nombre : "+profbusc.getNombre());
            System.out.println("Apellido : "+profbusc.getApellido());
            System.out.println("Edad: "+profbusc.getEdad());
            System.out.println("Email : "+profbusc.getEmail());
            System.out.println("Telefono : "+profbusc.getTelefono());
        }else{
            System.out.println("No existe el profesor");
        }
    }
    // 3. Permite ver el horario de un profesor (si existe)
    public void mostrarHorarios(){
        System.out.println("Escriba el nombre del profesor: ");
        String nombre = sc.nextLine();
        System.out.println("Escriba el correo de la profesor: ");
        String correo = sc.nextLine();
        boolean exist = main.verificacion(nombre, correo,"profesor");
        if (exist){
            Profesor profbusc = Admin.buscarProfesor(nombre);
            profbusc.listaHorario();
        }else{
            System.out.println("No existe el profesor");
        }
    }
    // 4. Muestra todos los profesores existentes
    public void verProfesores(){
        ArrayList<Profesor> profesores = main.getProfesores();
        try{
            System.out.println("Lista de Profesores:");
            for (Profesor i: profesores){
                System.out.println("Nombre: "+i.getNombre()+"\n Correo: "+i.getEdad()+"\n Telefono: "+i.getTelefono());
            }
        }
        catch(Exception e){
            System.out.println("No existen profesores aun.");
        }
    }
    // 5. Crea y guarda un nuevo apunte de clase
    public void crearApunte(){
        Apuntes apuntes = new Apuntes();

        System.out.println("Escribe el numero de la clase: ");
        String numeroDeClase = sc.nextLine();
        apuntes.setNumeroDeClase(numeroDeClase);

        System.out.println("Escribe la materia correspondiente (logica computacional, bases de datos, diseño de software o pruebas de software) ");
        String materia = sc.nextLine();
        while (!materia.equals("logica computacional")&&(!materia.equals("bases de datos"))&&(!materia.equals("diseño de software"))&&(!materia.equals("pruebas de software"))) {
            System.out.println(materia + "Es incorrecta, ingresa una materia valida");
            materia = sc.nextLine();
        }
        apuntes.setMateria(materia);

        System.out.println("Escribe la fecha ");
        apuntes.setFecha(escribirFecha());


        System.out.println("Escribe el titulo del tema visto: ");
        String titulo = sc.nextLine();
        apuntes.setTitulo(titulo);

        System.out.println("Escribe el subtitulo: ");
        String subtitulo = sc.nextLine();
        apuntes.setSubtitulo(subtitulo);

        System.out.println("Escribe la descripcion de la parte mas importante: ");
        String descripcion = sc.nextLine();
        apuntes.setDescripcion(descripcion);


        System.out.println("Deseas hacer una nota extra? (si/no)");
        String notaExtra = sc.nextLine();
        while(!notaExtra.equals("si")&&(!notaExtra.equals("no")) ) {
            System.out.println("Opcion invalida, intenta de nuevo");
            notaExtra = sc.nextLine();
        }
        if (notaExtra.equals("si")) {
            System.out.println("Escribe la nota: ");
            notaExtra= sc.nextLine();
            apuntes.setNotasExtra(notaExtra);
        }else{
            System.out.println("Hasta luego!!");
        }

        apuntesEst.add(apuntes);
        main.insertApunte(materia,apuntes);
    }
    // 6. Muestra todos los apuntes tomados del estudiantes
    public void mostrarApunte(){
        try{
            for (Apuntes i: apuntesEst){
                System.out.println("Clase: #"+i.getNumeroDeClase());
                System.out.println("Materia: "+i.getMateria());
                System.out.println("Fecha: "+i.getFecha());
                System.out.println("Titulo: "+i.getTitulo());
                System.out.println("Subtitulo: "+i.getSubtitulo());
                System.out.println("Descripcion: "+i.getDescripcion());
                System.out.println("Nota Extra: "+i.getNotasExtra()+"\n");
            }
        } catch (Exception e) {
            System.out.println("No se a ingresado ningun apunte aun");
        }
    }
    // 7. Muestra todos los apuntes de una materia en especifico
    public void mostrarApuntesMateria(){
        System.out.println("Escriba el nombre de la materia de la cual desea ver los apuntes (logica computacional, bases de datos, diseño de software o pruebas de software): ");
        String materia = sc.nextLine();
        while (!materia.equals("logica computacional")&&(!materia.equals("bases de datos"))&&(!materia.equals("diseño de software"))&&(!materia.equals("pruebas de software"))) {
            System.out.println(materia + "Es incorrecta, ingresa una materia valida");
            materia = sc.nextLine();
        }
        ArrayList <Apuntes> apuntesMateria = new ArrayList();
        if (materia.equals("logica computacional")) {
            apuntesMateria = main.getLogComputador().getApuntesClase();
        }else if (materia.equals("bases de datos")) {
            apuntesMateria = main.getBasesData().getApuntesClase();
        }else if (materia.equals("diseño de software")){
            apuntesMateria = main.getDisSoftware().getApuntesClase();
        }else {
            apuntesMateria = main.getPruebaSoft().getApuntesClase();
        }
        System.out.println("Apuntes de la clase de "+materia+":");
        for (Apuntes i : apuntesMateria){
            System.out.println("Clase: #"+i.getNumeroDeClase());
            System.out.println("Fecha: "+i.getFecha());
            System.out.println("Titulo: "+i.getTitulo());
            System.out.println("Subtitulo: "+i.getSubtitulo());
            System.out.println("Descripcion: "+i.getDescripcion());
            System.out.println("Nota Extra: "+i.getNotasExtra()+"\n");
        }
    }
    // 8. Temario de cada materia
    public void verTemario(){
        System.out.println("Escriba el nombre de la materia de la cual quiere ver el temario (logica computacional, bases de datos, diseño de software o pruebas de software): ");
        String materia = sc.nextLine();
        while (!materia.equals("logica computacional")&&(!materia.equals("bases de datos"))&&(!materia.equals("diseño de software"))&&(!materia.equals("pruebas de software"))) {
            System.out.println(materia + "Es incorrecta, ingresa una materia valida");
            materia = sc.nextLine();
        }
        if (materia.equals("logica computacional")) {
            main.getLogComputador().darClase();
        }else if (materia.equals("bases de datos")) {
            main.getBasesData().darClase();
        }else if (materia.equals("diseño de software")){
            main.getDisSoftware().darClase();
        }else {
            main.getPruebaSoft().darClase();
        }
    }
}
