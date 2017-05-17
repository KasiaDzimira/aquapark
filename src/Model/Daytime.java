package Model;

import java.util.Date;

public class Daytime {
    private int id;
    private String name;
    private Date startHour;
    private Date endHour;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEndHour() {
        return endHour;
    }

    public Date getStartHour() {
        return startHour;
    }

    public void setEndHour(Date endHour) {
        this.endHour = endHour;
    }

    public void setStartHour(Date startHour) {
        this.startHour = startHour;
    }
}
