package com.contaazul.bankslipchallenge.repositories;

import com.contaazul.bankslipchallenge.entities.Bankslip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BankslipRepository extends JpaRepository<Bankslip, UUID> {

	List<Bankslip> findByTenantId(Long tenantId);

	Bankslip findByTenantIdAndId(Long tenantId, UUID id);
}
