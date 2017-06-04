package Model;

public class Attraction {
    private int id;
    private String name;
    private boolean status;
    private AttractionType attractionType;

    public Attraction(String name, boolean status, AttractionType attractionType) {
        this.name = name;
        this.status = status;
        this.attractionType =attractionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public AttractionType getAttractionType() {
        return attractionType;
    }

    public void setAttractionType(AttractionType attractionType) {
        this.attractionType = attractionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
