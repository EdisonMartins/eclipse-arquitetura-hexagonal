package dev.edisonmartins.ports.out;

import dev.edisonmartins.domain.model.conta.Conta;

public interface ContaRepositorio {
    Conta get(Integer numero);
    void alterar(Conta conta);
}
