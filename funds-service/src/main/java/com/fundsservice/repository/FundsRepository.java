package com.fundsservice.repository;

import com.fundsservice.entities.Funds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundsRepository extends JpaRepository<Funds, String> {

}
