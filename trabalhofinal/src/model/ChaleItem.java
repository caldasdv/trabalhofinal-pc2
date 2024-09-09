package model;

public class ChaleItem {
    private int codChale;
    private String nomeItem;

    public int getCodChale() {
        return codChale;
    }

    public void setCodChale(int codChale) {
        this.codChale = codChale;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    @Override
    public String toString() {
        return "ChaleItem{" +
                "codChale=" + codChale +
                ", nomeItem='" + nomeItem + '\'' +
                '}';
    }
}
