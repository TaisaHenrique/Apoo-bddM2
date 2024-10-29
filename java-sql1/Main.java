
    
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/seu_banco"; // Altere para seu banco de dados
        String user = "usuario"; // Altere para seu usuário
        String password = "senha"; // Altere para sua senha
        connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

class Restaurante {
    private int idRestaurante;
    private String nome;
    private String endereco;
    private String telefone;


    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO Restaurante (nome, endereco, telefone) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, endereco);
            pstmt.setString(3, telefone);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idRestaurante = rs.getInt(1);
            }
        }
    }
}

class Pedido {
    private int idPedido;
    private int idRestaurante;
    private int idCliente;
    private Timestamp dataPedido;
    private int statusEntrega;


    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO Pedido (id_restaurante, id_cliente, data_pedido, status_entrega) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, idRestaurante);
            pstmt.setInt(2, idCliente);
            pstmt.setTimestamp(3, dataPedido);
            pstmt.setInt(4, statusEntrega);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idPedido = rs.getInt(1);
            }
        }
    }
}

class Promocao {
    private int idPromocao;
    private int idRestaurante;
    private String descricao;
    private double desconto;
    private Date dataInicio;
    private Date dataFim;


    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO Promocao (id_restaurante, descricao, desconto, data_inicio, data_fim) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, idRestaurante);
            pstmt.setString(2, descricao);
            pstmt.setDouble(3, desconto);
            pstmt.setDate(4, dataInicio);
            pstmt.setDate(5, dataFim);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idPromocao = rs.getInt(1);
            }
        }
    }
}

class Produto {
    private int idProduto;
    private int idRestaurante;
    private String nome;
    private double preco;
    private String descricao;
    private int idCategoria;


    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO Produto (id_restaurante, nome, preco, descricao, id_categoria) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, idRestaurante);
            pstmt.setString(2, nome);
            pstmt.setDouble(3, preco);
            pstmt.setString(4, descricao);
            pstmt.setInt(5, idCategoria);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idProduto = rs.getInt(1);
            }
        }
    }
}

class Avaliacao {
    private int idAvaliacao;
    private int idRestaurante;
    private int idCliente;
    private int nota;
    private String comentario;


    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO Avaliacao (id_restaurante, id_cliente, nota, comentario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, idRestaurante);
            pstmt.setInt(2, idCliente);
            pstmt.setInt(3, nota);
            pstmt.setString(4, comentario);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idAvaliacao = rs.getInt(1);
            }
        }
    }
}

class Acompanhamento {
    private int idAcompanhamento;
    private String nome;
    private double preco;

 

    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO Acompanhamento (nome, preco) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, nome);
            pstmt.setDouble(2, preco);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idAcompanhamento = rs.getInt(1);
            }
        }
    }
}

class PedidoProduto {
    private int idPedido;
    private int idProduto;
    private int quantidade;

  

    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO Pedido_Produto (id_pedido, id_produto, quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPedido);
            pstmt.setInt(2, idProduto);
            pstmt.setInt(3, quantidade);
            pstmt.executeUpdate();
        }
    }
}

class ProdutoAcompanhamento {
    private int idProduto;
    private int idAcompanhamento;



    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO Produto_Acompanhamento (id_produto, id_acompanhamento) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProduto);
            pstmt.setInt(2, idAcompanhamento);
            pstmt.executeUpdate();
        }
    }
}

class StatusEntrega {
    private int idStatus;
    private String descricao;



    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO Status_Entrega (descricao) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, descricao);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idStatus = rs.getInt(1);
            }
        }
    }
}


class HistoricoPagamento {
    private int idPagamento;
    private int idPedido;
    private int formaPagamento;
    private double valor;


    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO Historico_Pagamento (id_pedido, forma_pagamento, valor) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, idPedido);
            pstmt.setInt(2, formaPagamento);
            pstmt.setDouble(3, valor);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idPagamento = rs.getInt(1);
            }
        }
    }
}


class RestaurantePagamento {
    private int idRestaurante;
    private int idPagamento;


    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO Restaurante_Pagamento (id_restaurante, id_pagamento) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idRestaurante);
            pstmt.setInt(2, idPagamento);
            pstmt.executeUpdate();
        }
    }
}


class HistoricoEntrega {
    private int idEntrega;
    private int idPedido;
    private Timestamp dataEntrega;



    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO Historico_Entrega (id_pedido, data_entrega) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPedido);
            pstmt.setTimestamp(2, dataEntrega);
            pstmt.executeUpdate();
        }
    }
}

class Endereco {
    private int idEndereco; }

 