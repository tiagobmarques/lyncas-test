package com.lyncas.infrastructure.usuario;

import com.lyncas.domain.usuario.UsuarioEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
  Optional<UsuarioEntity> findByName(String name);
}
