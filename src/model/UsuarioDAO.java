package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//a Classe  usuárioDAO extende de Conexão 

public class UsuarioDAO extends Conexao {

    //Método insert do tipo Usuario
    public void insert(Usuario u) throws Exception {
        //atribuir uma query para a variável sql
        String sql = "INSERT INTO usuario (nome,login,senha) VALUES (?,?,?)";
        //conectar no banco
        this.conectar();
        //criar um objeto do tipo prepareStatement recebendo o objeto con do tipo Connection
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, u.getNome());
        ps.setString(2, u.getLogin());
        ps.setString(3, u.getSenha());
        ps.execute();
        ps.close();
        this.desconectar();

    }

    public void update(Usuario u) throws Exception {
        String sql = "UPDATE usuario SET nome=?,login=?,senha=? WHERE id = ?";
        this.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, u.getNome());
        ps.setString(2, u.getLogin());
        ps.setString(3, u.getSenha());
        ps.setInt(4, u.getId());
        ps.execute();
        ps.close();
        this.desconectar();
    }

    //Criar um método publico do tipo arraylist de usuario
    public ArrayList<Usuario> listar() throws Exception {
        //atribui a query para a variavel sql
        String sql = "SELECT * FROM usuario";
        //cria um objeto li do tipo arraylist
        ArrayList<Usuario> li = new ArrayList<Usuario>();
        //conecta ao banco
        this.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        //cria um objeto do tipo resultset executa a query
        ResultSet rs = ps.executeQuery();
        //enquanto ouver resultado rs.next
        while (rs.next()) {
            //cria um usuario
            Usuario u = new Usuario();
            //atribui os valores do resultset para o usuario
            u.setId(rs.getInt("id"));
            u.setNome(rs.getString("nome"));
            u.setLogin(rs.getString("login"));
            u.setSenha(rs.getString("senha"));
            u.setStatus(rs.getBoolean("status"));
            //lista adiciona o usuário ao arraylist
            li.add(u);
        }
        this.desconectar();
        //retorna a lista
        return li;
    }

    public Usuario login(String login,String senha) throws Exception {
        Usuario user = null;
        String sql = "SELECT login,senha,status FROM usuario WHERE login=? AND senha=?";
        this.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, login);
        ps.setString(2, senha);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user = new Usuario();
            user.setLogin(rs.getString("login"));
            user.setSenha(rs.getString("senha"));
            user.setStatus(rs.getBoolean("status"));
        }
        rs.close();
        ps.close();
        this.desconectar();
        return user;
    }
}
