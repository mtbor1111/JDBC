import java.sql.*;

public class JdbcDemo {
public static void main (String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
    Class.forName("org.h2.Driver").newInstance();
    Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
    //wyłączamy autocommit
    connection.setAutoCommit(false);
    System.out.println(connection.getAutoCommit());
    Statement statement = connection.createStatement();
    String createTable = "CREATE TABLE user (" +
            "id int primary key, " +
            "login varchar(20), " +
            "pass varchar(20)" +
            ")";
    statement.executeUpdate(createTable);
    //początek transakcji jako savepoint
    connection.setSavepoint();
    statement.executeUpdate("INSERT INTO user VALUES (1, 'kazik', '1234')");
    System.out.println("dane w bazie przed rollback");
    ResultSet set = statement.executeQuery("SELECT * FROM user");

    while (set.next()) {
        System.out.println(set.getString("login"));
    }
    connection.rollback();
    System.out.println("dane w bazie po rollback");
    set = statement.executeQuery("SELECT * FROM user");
    while (set.next()) {
        System.out.println(set.getString("login"));
    }

}
}
