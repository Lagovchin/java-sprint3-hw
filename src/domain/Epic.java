package domain;

import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Integer> subtaskIds;

    public Epic(String name, String description, String status) {
        super(name, description, status);
        subtaskIds = new ArrayList<>();
    }





    public void addSubTaskId(int subTaskId) {
        subtaskIds.add(subTaskId);
    }

    public ArrayList<Integer> getSubTasksIds() {
        return subtaskIds;
    }

    public void setSubtaskIds(ArrayList<Integer> subtaskIds) {
        this.subtaskIds = subtaskIds;
    }

    @Override
    public String toString() {
        return "domain.Epic{" +
                "id=" + id +
                ", subtaskIds.size=" + subtaskIds.size() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }



}
