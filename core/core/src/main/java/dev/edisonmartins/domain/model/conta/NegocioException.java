package dev.edisonmartins.domain.model.conta;

public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = -6798320465455769224L;

	public NegocioException(String mensagem) {
        super(mensagem);
    }
}
