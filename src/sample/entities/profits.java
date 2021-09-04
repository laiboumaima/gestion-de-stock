package sample.entities;

import java.util.Date;

public class profits {


    private int id ;
    private  float expenses;
    private  float dayprofits;
    private Date create;
    private float result;

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public profits(int id, float expenses, float dayprofits, Date create, float result) {
        this.id = id;
        this.expenses = expenses;
        this.dayprofits = dayprofits;
        this.create = create;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getExpenses() {
        return expenses;
    }

    public void setExpenses(float expenses) {
        this.expenses = expenses;
    }

    public float getDayprofits() {
        return dayprofits;
    }

    public void setDayprofits(float dayprofits) {
        this.dayprofits = dayprofits;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public profits() {
    }



}
