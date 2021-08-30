package controlador;

import conexion.Conexion;
import dao.LibroDao;
import java.util.List;
import java.util.Scanner;

import modelo.Libro;


public class LibroController {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Libro book = new Libro(0);
        Conexion conn = new Conexion();
        LibroDao bookd = new LibroDao(conn);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        book.setFechaIngreso(sqlDate);
        
        System.out.println("ingrese nombre del libro");
        book.setNombreLibro(scan.next());
        System.out.println("Fecha de ingreso: "+sqlDate);
        System.out.println("ingrese categoria del libro");
        book.setCategoria(scan.next());
        System.out.println("ingrese stock del libro");
        book.setStock(scan.nextInt());

        boolean respuesta = bookd.insertar(book);
        
        if (respuesta) {
            System.out.println("Su registro fue guardado con exito");
        } else {
            System.out.println("error al guardar registro");
        }
        List<Libro> list = bookd.selectAll();
        for (Libro book2 : list) {
            System.out.println("------------------------------------------");
            System.out.println("el id del libro es : " + book2.getIdLibro());
            System.out.println("el nombre del libro es : " + book2.getNombreLibro());
            System.out.println("la fecha de ingreso es : " + book2.getFechaIngreso());
            System.out.println("la categoria del libro es : " + book2.getCategoria());
            System.out.println("el stock del libro: " + book2.getStock());
            
        }

    }

}