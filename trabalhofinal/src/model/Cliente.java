package model;

import java.sql.Date;

public class Cliente {
    private int codCliente;
    private String nomeCliente;
    private String rgCliente;
    private String enderecoCliente;
    private String bairroCliente;
    private String cidadeCliente;
    private String estadoCliente;
    private String CEPCliente;
    private Date nascimentoCliente;

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getRgCliente() {
        return rgCliente;
    }

    public void setRgCliente(String rgCliente) {
        this.rgCliente = rgCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getBairroCliente() {
        return bairroCliente;
    }

    public void setBairroCliente(String bairroCliente) {
        this.bairroCliente = bairroCliente;
    }

    public String getCidadeCliente() {
        return cidadeCliente;
    }

    public void setCidadeCliente(String cidadeCliente) {
        this.cidadeCliente = cidadeCliente;
    }

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public String getCEPCliente() {
        return CEPCliente;
    }

    public void setCEPCliente(String CEPCliente) {
        this.CEPCliente = CEPCliente;
    }

    public Date getNascimentoCliente() {
        return nascimentoCliente;
    }

    public void setNascimentoCliente(Date nascimentoCliente) {
        this.nascimentoCliente = nascimentoCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codCliente=" + codCliente +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", rgCliente='" + rgCliente + '\'' +
                ", enderecoCliente='" + enderecoCliente + '\'' +
                ", bairroCliente='" + bairroCliente + '\'' +
                ", cidadeCliente='" + cidadeCliente + '\'' +
                ", estadoCliente='" + estadoCliente + '\'' +
                ", CEPCliente='" + CEPCliente + '\'' +
                ", nascimentoCliente=" + nascimentoCliente +
                '}';
    }
}
