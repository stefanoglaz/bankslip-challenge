package com.contaazul.bankslipchallenge.repositories;

import com.contaazul.bankslipchallenge.entities.Bankslip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankslipRepository extends JpaRepository<Bankslip, UUID> {

}
