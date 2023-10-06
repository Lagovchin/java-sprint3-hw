package domain;

public class SubTask extends Task {

    private int epicId;

    public SubTask(int epicId, String name, String description, String status) {
        super(name, description, status);
        this.epicId = epicId;
    }



    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "domain.SubTask{" +
                "id=" + id +
                ", epicId=" + epicId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
