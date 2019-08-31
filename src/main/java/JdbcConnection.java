import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum JdbcConnection {

    H2("org.h2.Driver", "jdbc:h2:mem:testdb", "sa", "");


    private String driver;
    private String user;
    private String url;
    private String pass;
    private  Connection connection;

    JdbcConnection(String driver, String user, String url, String pass) throws  ClassNotFoundException {
        this.driver = driver;
        this.user = user;
        this.url = url;
        this.pass = pass;
        try {
            Class.forName(driver).newInstance();

        }catch (InstantiationException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Connection get() throws SQLException{
        if (connection == null){
            connection = DriverManager.getConnection(url,user,pass);
        }
        return connection;
    }
}
