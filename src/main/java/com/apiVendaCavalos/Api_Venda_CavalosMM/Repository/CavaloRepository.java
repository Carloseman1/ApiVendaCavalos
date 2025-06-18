package com.apiVendaCavalos.Api_Venda_CavalosMM.Repository;

import com.apiVendaCavalos.Api_Venda_CavalosMM.Entity.CavaloEntity;
import com.apiVendaCavalos.Api_Venda_CavalosMM.Entity.CavaloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CavaloRepository extends JpaRepository<CavaloEntity, Long> {
    List<CavaloEntity> findByVendidoFalse();

    List<CavaloEntity> findByNomeContainingIgnoreCaseOrDocumentoContainingIgnoreCase(String nome, String documento);

}

