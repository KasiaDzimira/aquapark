package Model;

public class Attraction {
    private int id;
    private String name;
    private int status;
    private AttractionType attractionType;

    public Attraction(String name, int status, AttractionType attractionType) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AttractionType getAttractionType() {
        return attractionType;
    }

    public void setAttractionType(AttractionType attractionType) {
        this.attractionType = attractionType;
    }

    @Override
    public String toString() {
        return name;
    }
}
