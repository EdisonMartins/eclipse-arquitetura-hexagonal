package dev.edisonmartins.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import dev.edisonmartins.domain.model.conta.Conta;
import dev.edisonmartins.ports.in.PortaTransferencia;

@Named
@ViewScoped
public class InicioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7784590300071512522L;
	private Integer contaDebito, contaCredito;
	private Double valor;
	private Conta contaRealDebito, contaRealCredito;
	
	@Inject
	private PortaTransferencia porta;


	public void inicializar() {
		System.out.println("Inicializar...");
	}

	public Conta getContaRealDebito() {
		return contaRealDebito;
	}

	public void setContaRealDebito(Conta contaRealDebito) {
		this.contaRealDebito = contaRealDebito;
	}

	public Conta getContaRealCredito() {
		return contaRealCredito;
	}

	public void setContaRealCredito(Conta contaRealCredito) {
		this.contaRealCredito = contaRealCredito;
	}

	public Integer getContaDebito() {
		return contaDebito;
	}

	public void setContaDebito(Integer contaDebito) {
		this.contaDebito = contaDebito;
	}

	public Integer getContaCredito() {
		return contaCredito;
	}

	public void setContaCredito(Integer contaCredito) {
		this.contaCredito = contaCredito;
	}
	
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void carregaContaDebito() {
		System.out.println("carregaContaDebito()");
		if(this.contaDebito == null) {
			return;
		}
		this.contaRealDebito = null;
		if(this.contaDebito.intValue() >= 100) {
			this.contaRealDebito = porta.getConta(this.contaDebito);
		}
		
		
	}
	
	public void carregaContaCredito() {
		System.out.println("carregaContaCredito()");
		if(this.contaDebito == null) {
			return;
		}
		
		this.contaRealCredito = null;
		if(this.contaDebito.intValue() >= 100) {
			this.contaRealCredito = porta.getConta(this.contaCredito);
		}
	}
	
	public void transferir() {
		System.out.println("transferir()");
		
		if(valor == null) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Valor não pode ser nulo.", "Insira um valor válido!");
			return;
		}
		
		if(valor < 0.0) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Valor não pode ser negativo.", "Insira um valor válido!");
			return;
		}

		
		try {
			porta.transferir(contaDebito, contaCredito, BigDecimal.valueOf(valor));
			addMessage(FacesMessage.SEVERITY_INFO, "Transferência realizada com sucesso! ", null);
	        resetaValores();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", e.getMessage());
	        PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
	
	
    private void resetaValores() {
		this.contaCredito = null;
		this.contaDebito = null;
		this.valor = null;
		this.contaRealCredito = null;
		this.contaRealDebito = null;
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public void showInfo() {
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Message Content");
    }
    
    public void showError() {
        addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Message Content");
    }
    
    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", " Always Bet on Prime!");
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }


}
