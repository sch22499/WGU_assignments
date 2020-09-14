package sample.Model;

import sample.Exceptions.AddModifyExceptions;

abstract public class Part {

    private int id;

    private String name;

    private double price;

    private int stock;

    private int min;

    private int max;

    public Part( String name, double price, int stock, int min, int max){
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public Part(){

    }
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;

    }



    public boolean isValid() throws AddModifyExceptions {
        if (getName().equals("")) {
            throw new AddModifyExceptions("The name field cannot be empty.");
        }

        if (getStock() < 0) {
            throw new AddModifyExceptions("The current inventory must be greater than 0.");
        }

        if (getPrice() < 0) {
            throw new AddModifyExceptions("The price must be greater than $0");
        }

        if (getMin() < 0) {
            throw new AddModifyExceptions("The minimum inventory must be greater than 0.");
        }

        if (getMin() > getMax()) {
            throw new AddModifyExceptions("The minimum inventory must be less than the maximum.");
        }

        if (getStock() < getMin() || getStock() > getMax()) {
            throw new AddModifyExceptions("The current inventory must be between the minimum and maximum inventory.");
        }

        return true;
    }

    public void setStock(int stock){
        this.stock = stock;
        if(stock < min || stock > max){
            System.out.println("stop it. get some help");
        }
    }

    public void setMin(int min){
        this.min = min;
    }

    public void setMax(int max){
        this.max = max;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getStock(){
        return stock;
    }

    public int getMin(){
        return min;
    }

    public int getMax(){
        return max;
    }




}
