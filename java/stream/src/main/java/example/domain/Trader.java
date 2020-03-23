package example.domain;

public class Trader {

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    private final String name;

    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("%s in %s", this.name, this.city);
    }
}
