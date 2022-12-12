package br.com.fooddelivery.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fooddelivery.payment.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
