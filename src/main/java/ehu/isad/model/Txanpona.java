package ehu.isad.model;

import javafx.scene.image.Image;

public class Txanpona {

    private Integer id;
    private String txanpona;
    private String data;
    private Float price;
    private Float volume;
    private Image irudia;

    public Txanpona(Integer id, String txanpona, String data, Float price, Float volume, Image irudia) {
        this.id = id;
        this.txanpona = txanpona;
        this.data = data;
        this.price = price;
        this.volume = volume;
        this.irudia = irudia;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTxanpona() {
        return txanpona;
    }

    public void setTxanpona(String txanpona) {
        this.txanpona = txanpona;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Image getIrudia() {
        return irudia;
    }

    public void setIrudia(Image irudia) {
        this.irudia = irudia;
    }
}