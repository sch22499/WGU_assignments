package sample.Model;

public class Outsourced extends Part{

    private String companyName;

    public Outsourced( String name, double price, int stock, int min, int max, String companyName) {
        super( name, price, stock, min, max);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

