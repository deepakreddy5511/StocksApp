package com.transaction.transaction.Repository;

import com.transaction.transaction.Enitity.TransactionSave;
import com.transaction.transaction.dto.StocksDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStockRespository extends JpaRepository<TransactionSave, String> {
    TransactionSave findByIdAndStockCode(String id, String code);
    List<TransactionSave> findAllById(String id);
}
