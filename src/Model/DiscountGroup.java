package Model;

public class DiscountGroup {
    private int id;
    private String name;
    private float discount;

    public DiscountGroup(String name, float discount) {
        this.name = name;
        this.discount = discount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return name;
    }
}
