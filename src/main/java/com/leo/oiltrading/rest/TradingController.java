package com.leo.oiltrading.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.leo.oiltrading.domain.model.Transaction;
import com.leo.oiltrading.domain.model.Transaction.Type;
import com.leo.oiltrading.domain.model.TransactionVO;
import com.leo.oiltrading.domain.service.oiltype.OilTypeService;
import com.leo.oiltrading.domain.service.transaction.TransactionService;

@RestController
@RequestMapping("/trading")
public class TradingController {
	
	Logger logger = LoggerFactory.getLogger(TradingController.class);
	
	@Autowired
	private OilTypeService oilTypeService;
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("trade")
	public ResponseEntity<String> trade(@RequestBody String transaction) {
		var vo = new Gson().fromJson(transaction, TransactionVO.class);	// deserialize transaction	
		
		try {
			if (transactionService.findById(vo.getTimestamp()) != null) {
				return ResponseEntity.badRequest().body("A transaction with the given timestamp already exists");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		var entity = new Transaction(
				vo.getTimestamp(), 
				Double.toString(vo.getTimestamp()), 
				vo.getTransactionType().equalsIgnoreCase("BUY") ? Type.BUY : Type.SELL, 
				vo.getQty(), vo.getPrice());
		
		try {
			transactionService.add(entity);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok("Transaction registered successfully");
	}
	
	@GetMapping("/vw-oil-price")
	public ResponseEntity<String> getVolumeWeightedOilPrice() {
		try {
			return ResponseEntity.ok(Double.toString(transactionService.calculateVolumeWeightedOilPrice()));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
