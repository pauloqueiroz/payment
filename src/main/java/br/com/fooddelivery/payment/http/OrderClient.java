package br.com.fooddelivery.payment.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("pagamentos-ms")
public interface OrderClient {
	
	
	@RequestMapping(method = RequestMethod.PUT, value="/pedidos/{id}/pago")
	void updatePayment(@PathVariable Long id);

}
