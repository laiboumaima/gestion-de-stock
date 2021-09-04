package sample.entities;

import java.util.Date;

public class Seltproduct {
    private int idsell ;
    private int id ;
    private  String name;
    private Date created;
    private  float firstprice;
    private  float price ;
    private  float sellprice ;
    private  int quantity;
    private  String category;
    private  float totalprice ;
    private  float winner ;

    public int getIdsell() {
        return idsell;
    }

    public void setIdsell(int idsell) {
        this.idsell = idsell;
    }

    public Seltproduct() {
    }

    public Seltproduct(int idsell,int id, String name, Date created, float firstprice, float price, float sellprice, int quantity, String category, float totalprice, float winner) {
        this.id = id;
        this.idsell = idsell;
        this.name = name;
        this.firstprice = firstprice;
        this.price = price;
        this.sellprice = sellprice;
        this.quantity = quantity;
        this.category = category;
        this.totalprice = totalprice;
        this.winner = winner;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFirstprice() {
        return firstprice;
    }

    public void setFirstprice(float firstprice) {
        this.firstprice = firstprice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSellprice() {
        return sellprice;
    }

    public void setSellprice(float sellprice) {
        this.sellprice = sellprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public float getWinner() {
        return winner;
    }

    public void setWinner(float winner) {
        this.winner = winner;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
