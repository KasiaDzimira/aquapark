package Model;

import java.math.BigDecimal;

public class PassPriceListPosition {
    private int id;
    private BigDecimal price;
    private PassPriceList passPriceList;
    private DiscountGroup discountGroup;
    private PassType passType;
    private AttractionType attractionType;

    public PassPriceListPosition(BigDecimal price, PassPriceList passPriceList, DiscountGroup discountGroup, PassType passType, AttractionType attractionType) {
        this.price = price;
        this.passPriceList = passPriceList;
        this.discountGroup = discountGroup;
        this.passType = passType;
        this.attractionType = attractionType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public AttractionType getAttractionType() {
        return attractionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public DiscountGroup getDiscountGroup() {
        return discountGroup;
    }

    public void setAttractionType(AttractionType attractionType) {
        this.attractionType = attractionType;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDiscountGroup(DiscountGroup discountGroup) {
        this.discountGroup = discountGroup;
    }

    public void setPassType(PassType passType) {
        this.passType = passType;
    }

    public PassType getPassType() {
        return passType;
    }

    public PassPriceList getPassPriceList() {
        return passPriceList;
    }

    public void setPassPriceList(PassPriceList passPriceList) {
        this.passPriceList = passPriceList;
    }

    @Override public String toString() {
        return passType.toString() + " " + discountGroup.toString() + " " + attractionType.toString() + " " + price.toString();
    }
}
