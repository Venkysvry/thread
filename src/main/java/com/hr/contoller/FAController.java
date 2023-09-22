package com.hr.contoller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.client.BankDetailsClient;
import com.hr.client.CropProduct;
import com.hr.client.DealerClient;
import com.hr.model.BankDetails;
import com.hr.model.BankDetailsDto;
import com.hr.model.Crop;
import com.hr.model.CropDto;
import com.hr.model.Invoice;
import com.hr.model.Receipt;
import com.hr.repositories.RoleRepository;
import com.hr.repositories.UserRepository;
import com.hr.util.JwtUtils;
@CrossOrigin (origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/hr")
@EnableFeignClients(basePackages = "com.*")
public class FAController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	CropProduct cropclient;
	@Autowired
	DealerClient dealerclient;
	@Autowired
	BankDetailsClient bankclient;

	@GetMapping("/viewAllCrops")
	@PreAuthorize("hasRole('FARMER') or hasRole('DEALER') or hasRole('ADMIN')")
	public ResponseEntity<List<Crop>> getAllCrops() {

		return cropclient.getAll();
	}

	@GetMapping("/getByType/{type}")
	@PreAuthorize("hasRole('FARMER') or hasRole('DEALER') or hasRole('ADMIN')")
	public ResponseEntity<List<Crop>> viewByType(@PathVariable String type)  {
		ResponseEntity<List<Crop>> list = cropclient.viewBytype(type);

		return list;
	}

	@PostMapping("/add")
	@PreAuthorize("hasRole('FARMER')or  hasRole('ADMIN')")
	public ResponseEntity<Crop> add(@RequestBody @Valid Crop crop) {
		return cropclient.add(crop);
	}

	@PostMapping("/purchase")
	@PreAuthorize("hasRole('DEALER')or hasRole('ADMIN')")
	public ResponseEntity<String> purchaseCrop(@RequestBody Invoice p) {
		if(p.getCropId()<0||p.getAccountNumber()<0) throw new IllegalStateException(); 
		Receipt r = new Receipt();
		r.setCropId(p.getCropId());
		r.setCropType(p.getCropType());
		r.setCropName(p.getCropName());
		r.setAccountNumber(p.getAccountNumber());
		r.setAmount(p.getAmount());
		BankDetailsDto bank = new BankDetailsDto(p.getAccountNumber(), p.getAmount());
		bankclient.credit(bank);
		dealerclient.addInvoice(p);
		cropclient.receipt(r);
		cropclient.deleteCropbyId(p.getCropId());
		return new ResponseEntity<>("purchased succesfully", HttpStatus.OK);
	}

	@GetMapping("/viewByAccountNumber/{id}")
	@PreAuthorize("hasRole('FARMER')")
	public ResponseEntity<BankDetails> detailsByAccountnumber(@PathVariable Long id) {
		ResponseEntity<BankDetails> bank = bankclient.detailsByAccountnumber(id);
		return bank;
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('FARMER')or  hasRole('ADMIN')")
	public ResponseEntity<String> update(@RequestBody CropDto cropdto) {
		return cropclient.update(cropdto);

	}

	@GetMapping("/getcropbyname/{name}")
	@PreAuthorize("hasRole('FARMER') or hasRole('DEALER') or hasRole('ADMIN')")
	public ResponseEntity<List<Crop>> viewByname(@PathVariable String name) {
		return cropclient.viewByname(name);
	}

	@GetMapping("/view")
	@PreAuthorize("hasRole('DEALER')or hasRole('ADMIN')")
	public ResponseEntity<List<Invoice>> viewAll() {
		return dealerclient.viewAll();
	}
	@GetMapping("/viewReceipts")
	@PreAuthorize("hasRole('FARMER')or hasRole('ADMIN')")
	public ResponseEntity<List<Receipt>> viewAllReceipt(){
		return cropclient.viewAllReceipt();
	}
	@DeleteMapping("/deletebyId/{id}")
	public ResponseEntity<String> deleteCropbyId(@PathVariable int id){
		return cropclient.deleteCropbyId(id);
	}
	@GetMapping("/viewByFarmer/{id}")
	public ResponseEntity<List<Crop>> viewFarmer(@PathVariable int id){
		return cropclient.viewFarmer(id);
		
	}
}
