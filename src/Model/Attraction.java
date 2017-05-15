package Model;

public class Attraction {
    private int id;
    private  int status;
    private  AttractionType attractionType;

    public Attraction(int status, AttractionType attractionType) {
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
        return Integer.toString(status);
    }
}
