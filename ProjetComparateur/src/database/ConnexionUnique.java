package database;

import java.sql.*;

public class ConnexionUnique {
	private Connection connection;
    private static ConnexionUnique Instance;
    private String CONNECT_URL;


    private ConnexionUnique() throws SQLException {
        CONNECT_URL = "jdbc:mysql://mysql-vincentbogi.alwaysdata.net:3306/vincentbogi_compare";
        connection = DriverManager.getConnection(CONNECT_URL,"100889_compar","le010396");
    }

    public static ConnexionUnique getInstance() throws SQLException {
        if(Instance==null)
        {
            Instance = new ConnexionUnique();
        }
        return Instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
