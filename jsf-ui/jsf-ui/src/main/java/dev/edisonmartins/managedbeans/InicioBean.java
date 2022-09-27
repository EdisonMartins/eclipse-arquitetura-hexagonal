package dev.edisonmartins.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import dev.edisonmartins.ports.in.PortaTransferencia;

@Named
@ViewScoped
public class InicioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7784590300071512522L;
	private Double contaDebito, contaCredito, valor;
	
	@Inject
	private PortaTransferencia porta;


	public void inicializar() {
		System.out.println("Inicializar...");
	}

	public Double getContaDebito() {
		return contaDebito;
	}

	public void setContaDebito(Double contaDebito) {
		this.contaDebito = contaDebito;
	}

	public Double getContaCredito() {
		return contaCredito;
	}

	public void setContaCredito(Double contaCredito) {
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
		addMessage(FacesMessage.SEVERITY_WARN, "carregaContaDebito", "teste");
	}
	
	public void carregaContaCredito() {
		System.out.println("carregaContaCredito()");
		addMessage(FacesMessage.SEVERITY_WARN, "carregaContaCredito", "teste");
	}
	
	public void transferir() {
		System.out.println("transferir()");
		addMessage(FacesMessage.SEVERITY_INFO, "Transferir", "teste");
		showMessage();
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
