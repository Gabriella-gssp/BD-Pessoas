import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    public static void main(String[] args) throws SQLException {

        // 1. Habilitar o driver JDBC a partir da aplicação cliente;
        //      ok

        // 2. Estabelecer uma conexão entre a aplicação cliente e servidor do banco de dados;
        String paramsConexao = "jdbc:h2:mem:testdb";
        String usuario = "";
        String senha = "";
        Connection conexao = DriverManager.getConnection(paramsConexao, usuario, senha);

        // 3. Montar e executar a consulta SQL desejada;
        String criacaoSql = "create table pessoa (id int primary key, nome varchar(150), qtdAcesso int, naturalidade varchar(75) )";
        Statement stmtC = conexao.createStatement();
        stmtC.executeUpdate(criacaoSql);
        stmtC.close();
        
        String inserir = "insert into pessoa(id, nome, qtdAcesso, naturalidade) values (1, 'Gabi', 12, 'Coromandel-MG');";
        String inserir1 = "insert into pessoa(id, nome, qtdAcesso, naturalidade) values (2, 'Touro', 5, 'Porto Velho-RO');";
        String inserir2 = "insert into pessoa(id, nome, qtdAcesso, naturalidade) values (3, 'Távora', 7, 'Brasilia-DF');";
        String inserir3 = "insert into pessoa(id, nome, qtdAcesso, naturalidade) values (4, 'Santana', 18, 'Brasilia-DF');";
        String inserir4 = "insert into pessoa(id, nome, qtdAcesso, naturalidade) values (5, 'Mario', 25, 'Brasilia-DF');";
        String inserir5 = "insert into pessoa(id, nome, qtdAcesso, naturalidade) values (6, 'Gui', 22, 'Teresina-PI');";
        Statement insere = conexao.createStatement();
        insere.executeUpdate(inserir);
        insere.executeUpdate(inserir1);
        insere.executeUpdate(inserir2);
        insere.executeUpdate(inserir3);
        insere.executeUpdate(inserir4);
        insere.executeUpdate(inserir5);
        insere.close();

        String consulta = "select * from pessoa";
        //consulta = "select nome as nome_completo, naturalidade from pessoa";
        Statement stmt = conexao.createStatement();
        ResultSet resultado = stmt.executeQuery(consulta);

        // 4. Processar no cliente o resultado da consulta.
        while (resultado.next()) {
            String nome = resultado.getString("nome");
            Integer quantidade = resultado.getInt("qtdAcesso");
            String naturalidade = resultado.getString("naturalidade");

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setQuantidadeAcesso(quantidade);
            pessoa.setNaturalidade(naturalidade);
            // print
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Quantidade de acesso: " + pessoa.getQuantidadeAcesso());
            System.out.println("Naturalidade: " + pessoa.getNaturalidade());
           
            // add list
        }

        System.out.println("\nTchau, até a próxima\n\n\t\t:-)");
    }

}
