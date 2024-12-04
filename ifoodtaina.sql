
CREATE DATABASE IF NOT EXISTS Loja;

USE Loja;

CREATE TABLE IF NOT EXISTS Pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    produto VARCHAR(100) NOT NULL,    
    acompanhamento VARCHAR(100),     
    mes INT NOT NULL,                 
    forma_pagamento VARCHAR(50),      
    endereco VARCHAR(200),            
    data_pedido DATE                 
);

INSERT INTO Pedidos (produto, acompanhamento, mes, forma_pagamento, endereco, data_pedido)
VALUES
('Pizza Margherita', 'Molho Extra', 1, 'Cartão', 'Rua A, 123', '2024-01-15'),
('Hambúrguer', 'Batata Frita', 1, 'Dinheiro', 'Rua B, 456', '2024-01-18'),
('Pizza Calabresa', 'Molho Extra', 2, 'Cartão', 'Rua A, 123', '2024-02-10'),
('Hot Dog', 'Molho Barbecue', 2, 'Cartão', 'Rua C, 789', '2024-02-12'),
('Hambúrguer', 'Batata Frita', 3, 'Pix', 'Rua B, 456', '2024-03-05');


INSERT INTO Pedidos (produto, acompanhamento, mes, forma_pagamento, endereco, data_pedido)
VALUES ('Pizza Quatro Queijos', 'Molho Especial', 12, 'Cartão', 'Rua G, 123', CURDATE());

SELECT * FROM Pedidos ORDER BY id DESC LIMIT 1;

SELECT produto, COUNT(*) AS total_vendido
FROM Pedidos
GROUP BY produto
ORDER BY total_vendido DESC
LIMIT 1;

SELECT produto, COUNT(*) AS total_vendido
FROM Pedidos
GROUP BY produto
ORDER BY total_vendido ASC
LIMIT 1;

SELECT acompanhamento, COUNT(*) AS total_usado
FROM Pedidos
GROUP BY acompanhamento
ORDER BY total_usado DESC
LIMIT 1;

SELECT mes, COUNT(*) AS total_vendas
FROM Pedidos
GROUP BY mes
ORDER BY total_vendas DESC
LIMIT 1;

SELECT forma_pagamento, COUNT(*) AS total_usos
FROM Pedidos
GROUP BY forma_pagamento
ORDER BY total_usos DESC
LIMIT 1;

SELECT endereco, COUNT(*) AS total_entregas
FROM Pedidos
GROUP BY endereco
ORDER BY total_entregas DESC
LIMIT 1;


SELECT mes, produto, COUNT(*) AS total_vendido
FROM Pedidos
GROUP BY mes, produto
ORDER BY mes, total_vendido DESC;

SELECT 
    CASE mes
        WHEN 1 THEN 'Janeiro'
        WHEN 2 THEN 'Fevereiro'
        WHEN 3 THEN 'Março'
        WHEN 4 THEN 'Abril'
        WHEN 5 THEN 'Maio'
        WHEN 6 THEN 'Junho'
        WHEN 7 THEN 'Julho'
        WHEN 8 THEN 'Agosto'
        WHEN 9 THEN 'Setembro'
        WHEN 10 THEN 'Outubro'
        WHEN 11 THEN 'Novembro'
        WHEN 12 THEN 'Dezembro'
    END AS mes_nome,
    COUNT(*) AS total_vendas
FROM Pedidos
GROUP BY mes
ORDER BY total_vendas DESC
LIMIT 1;
