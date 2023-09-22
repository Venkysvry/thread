package com.hr.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.hr.model.Invoice;



@FeignClient(url = "http://localhost:8538/dealer", name = "dealer-client")
public interface DealerClient {
	@PostMapping("/add")
	public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice);
	@GetMapping("/view")
	public ResponseEntity<List<Invoice>> viewAll();
}
