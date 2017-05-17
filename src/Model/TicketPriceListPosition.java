package Model;

import java.math.BigDecimal;

public class TicketPriceListPosition {
    private int id;
    private BigDecimal price;
    private TicketPriceList ticketPriceList;
    private Day day;
    private DiscountGroup discountGroup;
    private Daytime daytime;
    private AttractionType attractionType;

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

    public Day getDay() {
        return day;
    }

    public DiscountGroup getDiscountGroup() {
        return discountGroup;
    }

    public void setAttractionType(AttractionType attractionType) {
        this.attractionType = attractionType;
    }

    public TicketPriceList getTicketPriceList() {
        return ticketPriceList;
    }

    public Daytime getDaytime() {
        return daytime;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setDaytime(Daytime daytime) {
        this.daytime = daytime;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTicketPriceList(TicketPriceList ticketPriceList) {
        this.ticketPriceList = ticketPriceList;
    }

    public void setDiscountGroup(DiscountGroup discountGroup) {
        this.discountGroup = discountGroup;
    }
}
