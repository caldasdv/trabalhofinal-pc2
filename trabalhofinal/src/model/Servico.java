package model;

public class Servico {
    private int codServico;
    private String nomeServico;
    private double valorServico;

    public int getCodServico() {
        return codServico;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public double getValorServico() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "codServico=" + codServico +
                ", nomeServico='" + nomeServico + '\'' +
                ", valorServico=" + valorServico +
                '}';
    }
}
