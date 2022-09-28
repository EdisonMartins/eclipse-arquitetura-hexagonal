package dev.edisonmartins.ports.in.imp;

import static dev.edisonmartins.domain.model.conta.Erro.inexistente;
import static dev.edisonmartins.domain.model.conta.Erro.mesmaConta;
import static dev.edisonmartins.domain.model.conta.Erro.obrigatorio;
import static java.util.Objects.isNull;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import dev.edisonmartins.domain.model.conta.Conta;
import dev.edisonmartins.domain.service.Transferencia;
import dev.edisonmartins.ports.in.PortaTransferencia;
import dev.edisonmartins.ports.out.ContaRepositorio;

/**
 * Responsável por implementar a porta de operações para caso de uso de transferência.
 * Sera gerenciado pelo IoC
 **/

@Named
public class PortaTransferenciaImp implements PortaTransferencia, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6316002444833178270L;
	private ContaRepositorio repositorio;
    private Transferencia transferencia;


    @Inject
    public PortaTransferenciaImp(ContaRepositorio repositorio, Transferencia transferencia) {
        this.repositorio = repositorio;
        this.transferencia = transferencia;
    }

    @Override
    public Conta getConta(Integer numero) {
        return repositorio.get(numero);
    }

    @Override
    @Transactional
    public void transferir(Integer contaDebito, Integer contaCredito, BigDecimal valor) {
        //1. validação de parametros
        if (isNull(contaDebito)) {
            obrigatorio("Conta débito");
        }
        if (isNull(contaCredito)) {
            obrigatorio("Conta crédito");
        }
        if (isNull(valor)) {
            obrigatorio("Valor");
        }

        //2. validação de contas
        // edison.martins - Essas validações poderiam estar dentro DomainService.
        var debito = repositorio.get(contaDebito);
        if (isNull(debito)) {
            inexistente("Conta débito");
        }
        var credito = repositorio.get(contaCredito);
        if (isNull(credito)) {
            inexistente("Conta crédito");
        }

        //3.validacao mesma conta
        // edison.martins - Esta validação poderia estar dentro DomainService.
        if (debito.getNumero().equals(credito.getNumero())) {
            mesmaConta();
        }

        //4. operação
        transferencia.processar(valor, debito, credito);
        repositorio.alterar(debito);
        repositorio.alterar(credito);
    }
}
