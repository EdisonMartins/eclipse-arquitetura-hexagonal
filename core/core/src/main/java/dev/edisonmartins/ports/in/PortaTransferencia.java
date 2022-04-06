package dev.edisonmartins.ports.in;

import java.math.BigDecimal;

import dev.edisonmartins.domain.model.conta.Conta;

/** Responsável por definir a porta de entrada (driver) de operações para caso de uso de transferência. **/

public interface PortaTransferencia {
    Conta getConta(Integer numero);
    void transferir(Integer contaDebito, Integer contaCredito, BigDecimal valor);
}
