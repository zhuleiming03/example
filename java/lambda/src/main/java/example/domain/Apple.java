package example.domain;

public class Apple {

    public Apple(Float weight,String color) {
        this.weight = weight;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("This apple's weight is %s and color is %s", weight, color);
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private Float weight;

    private String color;

}
