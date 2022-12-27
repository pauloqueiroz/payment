package br.com.fooddelivery.payment.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fooddelivery.payment.dto.PaymentDto;
import br.com.fooddelivery.payment.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService service;
	
	@GetMapping
	public Page<PaymentDto> list(Pageable pageable){
		return service.getAll(pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentDto> getById(@PathVariable @NotNull Long id) {
		PaymentDto dto = service.getById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<PaymentDto> create(@RequestBody @Valid PaymentDto paymentDto, UriComponentsBuilder builder){
		PaymentDto dto = service.create(paymentDto);
		URI uri = builder.path("/payments/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PaymentDto> update(@PathVariable Long id, @RequestBody @Valid PaymentDto paymentDto){
		PaymentDto updatedPayment = service.update(id, paymentDto);
		return ResponseEntity.ok(updatedPayment);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PaymentDto> delete(@PathVariable @NotNull Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
