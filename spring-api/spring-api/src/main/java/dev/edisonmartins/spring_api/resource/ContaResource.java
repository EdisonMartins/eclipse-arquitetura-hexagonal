package dev.edisonmartins.spring_api.resource;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.edisonmartins.domain.model.conta.Conta;
import dev.edisonmartins.ports.in.PortaTransferencia;

@RestController
@RequestMapping("/conta")
public class ContaResource {
	
	@Inject
	private PortaTransferencia porta;
	
	@GetMapping("/{numero}")
	public ResponseEntity<Conta> buscaPorNumero(@PathVariable Integer numero) {
		Conta conta = porta.getConta(numero);
		if (conta == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(conta);
	}
	
	@PostMapping("/{contaDebito}/transferir/{contaCredito}/{valor}")
	public void transferir(@PathVariable Integer contaDebito, @PathVariable Integer contaCredito, @PathVariable BigDecimal valor) {		
		porta.transferir(contaDebito, contaCredito, valor);
	}


}
