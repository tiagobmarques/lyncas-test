package com.lyncas.infrastructure.contasapagar;

import com.lyncas.domain.contasapagar.ContasAPagarEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasAPagarRepository extends JpaRepository<ContasAPagarEntity, UUID> {

  List<ContasAPagarEntity> findByDataPagamentoBetween(LocalDate startDate, LocalDate endDate);

  Page<ContasAPagarEntity> findByDataVencimentoBetweenAndDescricaoContaining(
      LocalDate startDate,
      LocalDate endDate,
      String descricao,
      Pageable pageable
  );
}
