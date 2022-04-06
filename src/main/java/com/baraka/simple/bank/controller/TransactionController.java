package com.baraka.simple.bank.controller;

import com.baraka.simple.bank.dtos.InternationalTransferTransaction;
import com.baraka.simple.bank.dtos.LocalTransferTransaction;
import com.baraka.simple.bank.dtos.SelfTransactionDto;
import com.baraka.simple.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/transaction")
@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("deposit")
    public ResponseEntity depositMoney(@RequestBody  SelfTransactionDto selfTransactionDto) {
        return ResponseEntity.ok(transactionService.depositMoney(selfTransactionDto));
    }


    @PostMapping("withdraw")
    public ResponseEntity withdrawMoney(@RequestBody  SelfTransactionDto selfTransactionDto) {
        return ResponseEntity.ok(transactionService.withdrawMoney(selfTransactionDto));
    }

    @PostMapping("transfer")
    public ResponseEntity transferMoney(@RequestBody LocalTransferTransaction localTransferTransaction) {
        return ResponseEntity.ok(transactionService.localTransfer(localTransferTransaction));
    }

    @PostMapping("transfer/international")
    public ResponseEntity transferMoneyInternational(@RequestBody InternationalTransferTransaction internationalTransferTransaction) {
        return ResponseEntity.ok(transactionService.internationalTransfer(internationalTransferTransaction));
    }

    @GetMapping
    public ResponseEntity depositMoney() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
}
