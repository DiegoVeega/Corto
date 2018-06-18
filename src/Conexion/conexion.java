package Conexion;

/**
 *
 * @author LN710Q
 */
import com.mysql.jdbc.Connection;//
//import com.sun.istack.internal.logging.Logger;
import java.sql.DriverManager;//
import java.sql.SQLException;//
import java.util.logging.Level;//
import java.util.logging.Logger;

/**
 *
 * @author LN710Q
 */
public class conexion {
    private String user;
    private String pass;
    private String driver;
    private String url;
    private Connection cnx;
    public static conexion instance;
    public synchronized static conexion conectar(){
        if(instance==null){
            return new conexion();
            
        }
        return instance;
    }
    private conexion(){
        cargarCredenciales();
        
        try{
            Class.forName(this.driver);
            cnx=(Connection) DriverManager.getConnection(this.url, this.user, this.pass);
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    private void cargarCredenciales(){
        user="root";
        pass="";
        driver="com.mysql.jdbc.Driver";
        url="jdbc:mysql://localhost/productos";
    }
    
    public Connection getCnx(){
        return cnx;
    }
    
    public void cerrarConexion(){
        instance=null;
    }
}
