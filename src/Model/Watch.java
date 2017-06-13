package Model;

public class Watch {
    private int id;
    private int status;

    public Watch(int status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Watch: " + Integer.toString(id) + ", status: " + Integer.toString(status);
    }
}
