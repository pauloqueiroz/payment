package br.com.fooddelivery.payment.dto;

import java.math.BigDecimal;

import br.com.fooddelivery.payment.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
	
	private Long id;
	
	private BigDecimal value;
	
	private String name;
	
	private String number;

	private String expiration;

	private String code;
	
	private Status status;
	
	private Long orderId;
	
	private Long paymentMethodId;

}
