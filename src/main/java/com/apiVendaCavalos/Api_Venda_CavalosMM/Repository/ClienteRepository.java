package com.apiVendaCavalos.Api_Venda_CavalosMM.Repository;

import com.apiVendaCavalos.Api_Venda_CavalosMM.Entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findByEmail(String email);
}
