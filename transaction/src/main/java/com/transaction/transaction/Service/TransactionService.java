package com.transaction.transaction.Service;

import com.transaction.transaction.Enitity.*;
import com.transaction.transaction.Repository.TransactionRepository;
import com.transaction.transaction.Repository.UserStockRespository;
import com.transaction.transaction.dto.StocksDetails;
import com.transaction.transaction.util.StocksClient;
import com.transaction.transaction.util.TransactionApllication;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionApllication transactionApllication;
    @Autowired
    UserStockRespository userStockRespository;

    @Autowired
    private ModelMapper modelMapper;

    private final StocksClient stocksClient;

    public List<StocksDetails> getStockDetailsById(String id)
    {
        return userStockRespository.findAllById(id).stream().map(this::mapToDto).collect(Collectors.toList());
        //return mapToDto(details);
    }

    public StocksDetails mapToDto(TransactionSave transactionSave)
    {
        StocksDetails stocksDetails = new StocksDetails();

        stocksDetails = modelMapper.map(transactionSave,StocksDetails.class);
        return stocksDetails;
    }


    public TransactionDetails buyStock(TransactionDetails transactionDetails) {
        //StocksDetails  stocksDetails = stockCatalogRepository.findByCode(transactionDetails.getStockCode());
        transactionDetails.setTransactionId(UUID.randomUUID().toString());
        try {
            StocksDetails stockDetailsDto = stocksClient.getAStock(transactionDetails.getStockCode());
            Long balance = transactionApllication.getavailablefunds(transactionDetails.getId());

            if (transactionDetails.getNoOfUnitsToBuy() > stockDetailsDto.getUnitsAvailable()) {
                transactionDetails.setErrorMessage("Please select the stock units under " + stockDetailsDto.getUnitsAvailable());
                return transactionDetails;
            } else if (balance != null && balance < (transactionDetails.getNoOfUnitsToBuy() * stockDetailsDto.getCurrentStockPrice())) {
                transactionDetails.setErrorMessage("Insufficient wallet balance");
                return transactionDetails;
            } else {
                transactionDetails.setAmountPaid(Long.valueOf(transactionDetails.getNoOfUnitsToBuy() * stockDetailsDto.getCurrentStockPrice()));
                Funds funds = new Funds();
                TransactionSave transactionSave = new TransactionSave();
                funds.setUserId(transactionDetails.getId());
                funds.setPassword(transactionDetails.getPassword());
                funds.setAmount(transactionDetails.getAmountPaid());
                //transactionApllication.withdrawFunds(funds);
                transactionDetails.setPassword(null);
//            transactionDetails.setStatus("transaction successfull");
                //transactionRepository.save(transactionDetails);
                transactionSave = userStockRespository.findByIdAndStockCode(transactionDetails.getId(), transactionDetails.getStockCode());
                if (transactionSave != null) {
                    transactionSave.setNoOfUnits(transactionSave.getNoOfUnits() + transactionDetails.getNoOfUnitsToBuy());
                    transactionDetails.setCurrentStockPrice(stockDetailsDto.getCurrentStockPrice());
                } else {
                    transactionSave = new TransactionSave();
                    transactionSave.setId(transactionDetails.getId());
                    transactionSave.setStockName(stockDetailsDto.getStockName());
                    transactionSave.setStockCode(transactionDetails.getStockCode());
                    transactionSave.setCurrentStockPrice(stockDetailsDto.getCurrentStockPrice());
                    transactionSave.setNoOfUnits(transactionDetails.getNoOfUnitsToBuy());
                    transactionDetails.setCurrentStockPrice(stockDetailsDto.getCurrentStockPrice());
                }

                transactionDetails.setStatus("transaction successfull");
                transactionRepository.save(transactionDetails);
                stockDetailsDto.setUnitsAvailable(stockDetailsDto.getUnitsAvailable() - transactionDetails.getNoOfUnitsToBuy());
                stocksClient.editStocks(stockDetailsDto);

                userStockRespository.save(transactionSave);
                transactionApllication.withdrawFunds(funds);
            }
        } catch (Exception ex) {
            transactionDetails.setErrorMessage("Transaction failed!");
        }
        return transactionDetails;
    }

    public TransactionDetails sellStock(TransactionDetails transactionDetails) {
        transactionDetails.setTransactionId(UUID.randomUUID().toString());
        StocksDetails stockDetailsDto = stocksClient.getAStock(transactionDetails.getStockCode());
        TransactionSave transactionSave = new TransactionSave();
        transactionSave = userStockRespository.findByIdAndStockCode(transactionDetails.getId(), transactionDetails.getStockCode());
        if (transactionSave != null && (transactionDetails.getNoOfUnitsToSell() > transactionSave.getNoOfUnits())) {
            transactionDetails.setErrorMessage("Please select the stock units under " + transactionSave.getNoOfUnits());
            return transactionDetails;
        } else {
            Funds funds = new Funds();
            funds.setUserId(transactionDetails.getId());
            funds.setPassword(transactionDetails.getPassword());
            funds.setAmount(Long.valueOf(transactionDetails.getNoOfUnitsToSell() * stockDetailsDto.getCurrentStockPrice()));
            //transactionApllication.addFunds(funds);
            transactionDetails.setPassword(null);

            // transactionRepository.save(transactionDetails);
            transactionSave.setNoOfUnits(transactionSave.getNoOfUnits() - transactionDetails.getNoOfUnitsToSell());
            userStockRespository.save(transactionSave);


            transactionApllication.addFunds(funds);
            transactionDetails.setCurrentStockPrice(stockDetailsDto.getCurrentStockPrice());
            transactionDetails.setStatus("transaction successfull");
            transactionRepository.save(transactionDetails);

            stockDetailsDto.setUnitsAvailable(stockDetailsDto.getUnitsAvailable() + transactionDetails.getNoOfUnitsToSell());
            stocksClient.editStocks(stockDetailsDto);

            transactionApllication.addFunds(funds);
            transactionDetails.setCurrentStockPrice(stockDetailsDto.getCurrentStockPrice());
            transactionDetails.setStatus("transaction successfull");
            transactionRepository.save(transactionDetails);

            userStockRespository.save(transactionSave);
        }
        return transactionDetails;

    }
}
