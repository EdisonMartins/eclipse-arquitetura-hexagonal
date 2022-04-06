package dev.edisonmartins.domain.service;

import static dev.edisonmartins.domain.model.conta.Erro.obrigatorio;
import static java.util.Objects.isNull;

import java.math.BigDecimal;

import javax.inject.Named;

import dev.edisonmartins.domain.model.conta.Conta;

@Named
public class Transferencia {
	public void processar(BigDecimal valor, Conta debito, Conta credito) {
		if (isNull(valor)) {
			obrigatorio("Valor da transferência");
		}
		if (isNull(debito)) {
			obrigatorio("Conta débito");
		}
		if (isNull(credito)) {
			obrigatorio("Conta crédito");
		}
		debito.debitar(valor);
		credito.creditar(valor);
	}

}
