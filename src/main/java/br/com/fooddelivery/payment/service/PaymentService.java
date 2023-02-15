package br.com.fooddelivery.payment.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fooddelivery.payment.dto.PaymentDto;
import br.com.fooddelivery.payment.http.OrderClient;
import br.com.fooddelivery.payment.model.Payment;
import br.com.fooddelivery.payment.model.Status;
import br.com.fooddelivery.payment.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentService {
	
	private PaymentRepository repository;

	private ModelMapper mapper;

	private OrderClient orderClient;
	
	@Autowired
	public PaymentService(PaymentRepository repository, ModelMapper modelMapper, OrderClient orderClient) {
		super();
		this.repository = repository;
		this.mapper = modelMapper;
		this.orderClient = orderClient;
	}
	
	public PaymentDto getById(Long id) {
		Payment payment = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return mapper.map(payment, PaymentDto.class);
	}
	
	
	public Page<PaymentDto> getAll(Pageable pageable){
		return repository.findAll(pageable).map(payemnt-> mapper.map(payemnt, PaymentDto.class));
	}
	
	public PaymentDto create(PaymentDto paymentDto) {
		Payment payment = mapper.map(paymentDto, Payment.class);
		Payment paymentSaved = repository.save(payment);
		return mapper.map(paymentSaved, PaymentDto.class);
		
	}
	
	public PaymentDto update(Long id, PaymentDto paymentDto) {
		Payment payment = mapper.map(paymentDto, Payment.class);
		payment.setId(id);
		Payment updatedPayment = repository.save(payment);
		return mapper.map(updatedPayment, PaymentDto.class);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public void confirmPayment(Long id) {
		Payment payment = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		payment.setStatus(Status.CONFIRMED);
		repository.save(payment);
		orderClient.updatePayment(payment.getId());
	}
}
