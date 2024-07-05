package com.marcoant.salesforce_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcoant.salesforce_api.entity.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Optional<Vendedor> findByEmail(String email);
    Optional<Vendedor> findByCpfCnpj(String cpfCnpj);
}
