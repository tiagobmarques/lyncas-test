--------------------------------------------------------
-- Create usuario table
--------------------------------------------------------
DO
$$
  BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_tables WHERE tablename = 'usuario') THEN
      CREATE TABLE usuario (
        id UUID DEFAULT gen_random_uuid(),
        name VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL,
        PRIMARY KEY (id)
      );
      COMMENT ON COLUMN usuario.id IS 'Identificador único do usuário';
      COMMENT ON COLUMN usuario.name IS 'Nome do usuário';
      COMMENT ON COLUMN usuario.password IS 'Senha do usuário';
      RAISE INFO 'Tabela usuario criada com sucesso';
    ELSE
      RAISE INFO 'Tabela usuario já existe';
    END IF;
  END
$$;
