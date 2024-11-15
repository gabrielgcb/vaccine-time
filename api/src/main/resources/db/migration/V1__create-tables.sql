-- Criando a tabela tb_alergias
CREATE TABLE tb_alergias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(40) NOT NULL
);

-- Criando a tabela tb_usuarios
CREATE TABLE tb_usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    data_nascimento DATE NOT NULL,
    sexo CHAR(1) NOT NULL,
    logradouro VARCHAR(60),
    numero INT,
    setor VARCHAR(40),
    cidade VARCHAR(40),
    uf CHAR(2)
);

-- Criando a tabela usuario_alergia (tabela de associação entre usuario e alergia)
CREATE TABLE usuario_alergia (
    usuario_id INT NOT NULL,
    alergia_id INT NOT NULL,
    PRIMARY KEY (usuario_id, alergia_id),
    FOREIGN KEY (usuario_id) REFERENCES tb_usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (alergia_id) REFERENCES tb_alergias(id) ON DELETE CASCADE
);

-- Criando a tabela tb_vacinas
CREATE TABLE tb_vacinas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(60) NOT NULL,
    descricao VARCHAR(200),
    doses INT NOT NULL,
    periodicidade INT NOT NULL,
    intervalo INT NOT NULL
);

-- Criando a tabela tb_agendas
CREATE TABLE tb_agendas (
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    situacao VARCHAR(10),
    data_situacao DATE,
    observacoes TEXT,
    usuario_id INT,
    vacina_id INT,
    FOREIGN KEY (usuario_id) REFERENCES tb_usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (vacina_id) REFERENCES tb_vacinas(id) ON DELETE CASCADE
);
