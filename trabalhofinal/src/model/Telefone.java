package model;

public class Telefone {
    private String telefone;
    private int codCliente;
    private String tipoTelefone;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "telefone='" + telefone + '\'' +
                ", codCliente=" + codCliente +
                ", tipoTelefone='" + tipoTelefone + '\'' +
                '}';
    }
}
