package model;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class Conexao {

    Connection con;

    public void conectar() throws Exception {
        Class.forName("org.gjt.mm.mysql.Driver");
        String url = "jdbc:mysql://localhost/loja";
        String user = "root";
        String senha = "";
        con = DriverManager.getConnection(url, user, senha);
    }

    public void desconectar() throws Exception {
        if (!con.isClosed()) {
            con.close();
        }
    }
}
