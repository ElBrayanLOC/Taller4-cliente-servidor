package agenciaviajes.presentacion;

import agenciaviajes.negocio.ConectorJdbcRespaldo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Hilo extends Thread {
    
    private String id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String celular;
    private String email;
    private String sexo;

    public Hilo(String id, String nombres, String apellidos, String direccion, String celular, String email, String sexo) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.celular = celular;
        this.email = email;
        this.sexo = sexo;
    }
    
    private void agregarCliente(){
        ConectorJdbcRespaldo conectorExtra = new ConectorJdbcRespaldo();
        try {
            conectorExtra.conectarse();
            conectorExtra.actualizar("INSERT INTO Clientes (id, nombres, apellidos, direccion, celular, email, sexo)"
                    + " VALUES ("
                    + "'" + this.id + "',"
                    + "'" + this.nombres + "',"
                    + "'" + this.apellidos + "',"
                    + "'" + this.direccion + "',"
                    + "'" + this.celular + "',"
                    + "'" + this.email + "',"
                    + "'" + this.sexo + "'"
                    + ")");
            
            conectorExtra.desconectarse();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
        agregarCliente();
    }
}