package example.domain;

public class Pear {

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Pear(Double price) {
        this.price = price;
    }

    public Pear() {
    }

    private Double price;

    @Override
    public String toString() {
        return String.format("This pear's price is " + this.price);
    }
}
