package model;

public class Item {
    private String nomeItem;
    private String descricaoItem;

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nomeItem='" + nomeItem + '\'' +
                ", descricaoItem='" + descricaoItem + '\'' +
                '}';
    }
}
