package model;

import java.sql.Date;

public class Hospedagem {
    private int codHospedagem;
    private int codChale;
    private int codCliente;  
    private String estado;
    private Date dataInicio;
    private Date dataFim;
    private int qtdPessoas;
    private double desconto;
    private double valorFinal;

    public int getCodHospedagem() {
        return codHospedagem;
    }

    public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public void setCodHospedagem(int codHospedagem) {
        this.codHospedagem = codHospedagem;
    }

    public int getCodChale() {
        return codChale;
    }

    public void setCodChale(int codChale) {
        this.codChale = codChale;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(int qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    @Override
    public String toString() {
        return "Hospedagem{" +
                "codHospedagem=" + codHospedagem +
                ", codChale=" + codChale +
                ", estado='" + estado + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", qtdPessoas=" + qtdPessoas +
                ", desconto=" + desconto +
                ", valorFinal=" + valorFinal +
                '}';
    }
}
