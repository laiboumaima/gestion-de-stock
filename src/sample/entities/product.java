package sample.entities;

public class product {

    private int id ;
    private  String name;
    private  float firstprice;
    private  float price ;
    private  int quantity;
    private  String category;

    private  float totalprice ;
    private  float winner ;

    public product() {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public product(int id, String name, float firstprice, float price, int quantity, String category, float totalprice, float winner) {
        this.id = id;
        this.name = name;
        this.firstprice = firstprice;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.totalprice = totalprice;
        this.winner = winner;
    }
}
