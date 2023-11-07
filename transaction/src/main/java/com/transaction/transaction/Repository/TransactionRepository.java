package com.transaction.transaction.Repository;

import com.transaction.transaction.Enitity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetails, String> {

}
