package Escuela.TiposUsuarios;
import Escuela.Main;
import java.util.ArrayList;

// Usuario con mas privilegios
public class Admin extends Persona {
    static Main main = new Main();

    // Constructor del admin con valores por defecto
    public Admin(){
        setNombre("sael");
        setApellido("moreno");
        setEmail("gilmore@gmail.com");
        setContr("hi(;");
        setCargo("Administrador");
        setTelefono(3012168553L);
        setEdad(17);
    }

    // 1. Crea un profesor mediante lo que se ingrese en consola
    public void crearProfesor(){
        Profesor newProfesor=new Profesor();

        // recoleccion de datos del nuevo profesor
        System.out.print("Escriba el nombre del profesor: ");
        newProfesor.setNombre(sc.nextLine());
        System.out.print("Escriba el apellido del profesor: ");
        newProfesor.setApellido(sc.nextLine());

        //validacion del telefono con 10 digitos
        System.out.print("Escriba el telefono del profesor (debe contener 10 cifras): ");
        String telefono = sc.nextLine();
        while (!telefono.matches("^\\d{10}$")) { // expresiones regulares:D
            System.out.println("El telefono debe tener 10 cifras numericas");
            telefono = sc.nextLine();
        }
        newProfesor.setTelefono(Long.parseLong(telefono));

        System.out.print("Escriba el email del profesor: ");
        newProfesor.setEmail(sc.nextLine());
        System.out.print("Escriba la contraseña del profesor: ");
        newProfesor.setContr(sc.nextLine());
        System.out.print("Escriba la edad del profesor: ");
        String edad = sc.nextLine();
        while (!edad.matches("^\\d{1,2}$")) {
            System.out.println("La edad del profesor debe ser un numero menor a tres dijitos");
            edad = sc.nextLine();
        }
        newProfesor.setEdad(Integer.parseInt(edad));
        newProfesor.setCargo("profesor");
        // Datos academicos para el perfil
        System.out.print("Que estudios tiene el profesor? ");
        newProfesor.setEstudios(sc.nextLine());
        System.out.println("Escriba la materia del profesor? (logica computacional, bases de datos, diseño de software, pruebas de software)");
        String materia = sc.nextLine();
        while (!materia.equals("logica computacional")&&(!materia.equals("bases de datos"))&&(!materia.equals("diseño de software"))&&(!materia.equals("pruebas de software"))) {
            System.out.println(materia + " Es incorrecta, ingresa una materia valida");
            materia = sc.nextLine();
        }
        newProfesor.setMateria(materia);

        // Verificacion que no se repita nombre o correo
        boolean exist = verificarPersona(newProfesor.getNombre(), newProfesor.getEmail(), "profesor");
        if(!exist){
            Main main = new Main();
            main.insrtProfes(newProfesor);
            System.out.println("Ya se ha creado el profesor, accediendo a su usuario ya podra crear sus tareas, horarios y lista de estudiantes");
        }else{
            System.out.println("Este profesor ya existe, debe tener un nombre de usuario y correo electronico diferentes al resto");
        }
    }
    // 2. Crea un estudiante mediante lo que se ingrese en consola
    public void crearEstudiante(){
        Estudiante newEstudiante =  new Estudiante();

        // Se recolectan datos del nuevo estudiante
        System.out.print("Escriba el nombre del estudiante: ");
        newEstudiante.setNombre(sc.nextLine());
        System.out.print("Escriba el apellido del estudiante: ");
        newEstudiante.setApellido(sc.nextLine());
        System.out.print("Escriba el telefono del estudiante (debe contener 10 cifras): ");
        String telefono = sc.nextLine();

        // Validacion del telefono de 10 digitos
        while (!telefono.matches("^\\d{10}$")) {
            System.out.println("El telefono debe tener 10 cifras numericas");
            telefono = sc.nextLine();
        }
        newEstudiante.setTelefono(Long.parseLong(telefono));

        System.out.print("Escriba el email del estudiante: ");
        newEstudiante.setEmail(sc.nextLine());
        System.out.print("Escriba la contraseña del estudiante: ");
        newEstudiante.setContr(sc.nextLine());
        System.out.print("Escriba la edad del estudiante: ");
        String edad = sc.nextLine();
        while (!edad.matches("^\\d{1,2}$")) {
            System.out.println("La edad del estudiante debe ser un numero menor a tres dijitos");
            edad = sc.nextLine();
        }
        newEstudiante.setEdad(Integer.parseInt(edad));

        newEstudiante.setCargo("estudiante");

        // Verificacion que no se repita nombre o correo
        boolean exist = verificarPersona(newEstudiante.getNombre(), newEstudiante.getEmail(), "estudiante");
        if(!exist){
            Main main = new Main();
            main.insertEstu(newEstudiante);
            System.out.println("Ya se ha creado el estudiante, accediendo a su usuario ya podra crear sus apuntes");
        }else{
            System.out.println("Este estudiante ya existe, debe tener un nombre de usuario y correo electronico diferentes al resto");
        }
    }
    // 3. Verifica si un nombre o email si ya existe, para evitar que se duplique
    public static boolean verificarPersona(String usuario, String gmail, String cargo ){
        boolean exist = false;
        if (cargo.equals("profesor")){
            ArrayList <Profesor> profesores = main.getProfesores();
            if (profesores.isEmpty()){
                exist = false;
            }else{
                for (Profesor profesor : profesores) {
                    if (profesor.getNombre().equals(usuario) || profesor.getEmail().equals(gmail)){
                        exist = true;
                    }
                }
            }

        }else if (cargo.equals("estudiante")){
            ArrayList <Estudiante> estuantes = main.getEstudiantes();

            if (estuantes.isEmpty()){
                exist = false;
            }else {
                for (Estudiante estudiante : estuantes) {
                    if (estudiante.getNombre().equals(usuario) || estudiante.getEmail().equals(gmail)){
                        exist = true;
                    }
                }
            }
        }
        return exist;
    }
    // 4. Busca estudiante por nombre
    public static Estudiante buscarEstudiante(String nombre){
        Estudiante estBusc = new Estudiante();
        for (Estudiante i:main.getEstudiantes()){
            if (i.getNombre().equals(nombre)){
                estBusc = i;
            }
        }
        return estBusc;
    }
    // 5. Busca profesor por nombre
    public static Profesor buscarProfesor(String nombre){
        Profesor profBusc = new Profesor();
        for (Profesor i:main.getProfesores()){
            if (i.getNombre().equals(nombre)){
                profBusc = i;
            }
        }
        return profBusc;
    }
}
