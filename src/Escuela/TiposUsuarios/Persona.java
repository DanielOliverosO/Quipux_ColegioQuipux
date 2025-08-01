package Escuela.TiposUsuarios;
import lombok.Data;
import java.util.Scanner;

// Clase base
@Data
public abstract class Persona {
    static Scanner sc = new Scanner(System.in);
    static Scanner scint = new Scanner(System.in);

    // Atributos comunes a profesores, estudiantes y administrador
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String contr;
    private long  telefono;
    private String cargo;

    // Metodo estatico para escribir una fecha valida
    public static String escribirFecha(){
        int dia=0,mes=0;
        boolean pass=false;

        System.out.print("Escriba el mes: ");
        do{
            try{
                mes = scint.nextInt();
                if(mes>=1 && mes<=12){
                    pass=true;
                }else{
                    System.out.print("El valor del mes tiene que ser entre 1 y 12: ");
                }
            }catch(Exception e){
                System.out.print("El valor del mes tiene que ser un numero: ");
            }
        }while(!pass);
        pass=false;
        System.out.print("Escriba el dia: ");

        while(!pass){
            try{
                dia = scint.nextInt();
                if ((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && dia >= 1 && dia <= 31) {
                    pass = true;
                } else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia >= 1 && dia <= 30) {
                    pass = true;
                } else if (mes == 2 && dia >= 1 && dia <= 28) { // Assuming non-leap year for simplicity
                    pass = true;
                } else {
                    System.out.print("El numero del dia tiene que cuadrar con el mes que introdujiste");
                }
            }catch(Exception e){
                System.out.print("El valor del dia tiene que ser un numero: ");
                sc.nextLine();
            }
        }
        return dia+"/"+mes+"/"+"2025";
    }
}
