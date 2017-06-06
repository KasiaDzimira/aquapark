package Model;

import java.util.Date;

public class Pass {
    private int id;
    private Date startDate;
    private Date endDate;
    private Watch watch;
    private User user;
    private PassType passType;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getEndDate() {
        return endDate;
    }

    public PassType getPassType() {
        return passType;
    }

    public void setPassType(PassType passType) {
        this.passType = passType;
    }

    @Override
    public String toString() {
        return "Pass: " + Integer.toString(id) + ", user: " + user + ", type: " + passType;
    }
}
