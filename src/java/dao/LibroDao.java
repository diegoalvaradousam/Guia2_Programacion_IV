package dao;

import conexion.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import modelo.Libro;

public class LibroDao {

    Conexion conn;

    public LibroDao(Conexion conn) {
        this.conn = conn;
    }

    public boolean insertar(Libro book) {

        String sql = "insert into Libro values (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(sql);
            ps.setInt(1, book.getIdLibro());
            ps.setString(2, book.getNombreLibro());
            ps.setDate(3, (Date) book.getFechaIngreso());
            ps.setString(4, book.getCategoria());
            ps.setInt(5, book.getStock());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Libro> selectAll() {
        String sql = "select * from Libro ";
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Libro book;
            List<Libro> lista = new LinkedList<>();
            //mientras tenga registros va a crear un objeto y lo llenara
            //con el id y lo vaciara en la lista
            while (rs.next()) {
                book = new Libro(rs.getInt("IdLibro"));
                book.setNombreLibro(rs.getString("NombreLibro"));
                book.setFechaIngreso(rs.getDate("FechaIngreso"));
                book.setCategoria(rs.getString("Categoria"));
                 book.setStock(rs.getInt("Stock"));
                lista.add(book);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }

    }
}
