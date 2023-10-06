package manager;

import domain.Epic;
import domain.SubTask;
import domain.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private int generatorId = 1;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();




    public void addTask(Task task) {
        task.setId(generatorId);
        tasks.put(task.getId(),task);
        generatorId++;
    }

    public  void addEpic(Epic epic) {
        epic.setId(generatorId);
        epics.put(epic.getId(), epic);
        generatorId++;
    }

    public void addSubTask(SubTask subTask) {
        int epicId = subTask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }
        subTask.setId(generatorId);
        subTasks.put(subTask.getId(), subTask);
        epic.addSubTaskId(subTask.getId());
        updateEpicStatus(epic);
        generatorId++;

    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
    }

    public void deleteAllSubTasks() {
        subTasks.clear();
        for (Integer epicId : epics.keySet()) {
            int id = epicId;
            Epic epic = epics.get(id);
            updateEpicStatus(epic);
        }
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    public Task getTaskById (int id) {
        return tasks.get(id);
    }

    public Epic getEpicById (int id) {
        return epics.get(id);
    }

    public SubTask getSubTaskById(int id) {
        return subTasks.get(id);
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void deleteEpicById(int id) {  //Если удаляем эпик, то и удаляем все его подзадачи.
        Epic epic = epics.get(id);
        ArrayList<Integer> subtasks = epic.getSubTasksIds();
        if (subtasks.isEmpty()) {
            epics.remove(id);
        } else {
            for (Integer subId : subtasks) {
                subTasks.remove(subId);
            }
            epics.remove(id);
        }
    }

    public void deleteSubTaskById(int id) {
        SubTask subTask = subTasks.get(id);
        if (subTask == null) {
            System.out.println("Такой задачи нет в списке");
        }
        int epicId = subTask.getEpicId();
        Epic epic = epics.get(epicId);
        ArrayList<Integer> ids = epic.getSubTasksIds();
        for (int i = 0; i <= ids.size(); i++) {
            if (ids.get(i) == subTask.getId()) {
                ids.remove(i);
            }
            epic.setSubtaskIds(ids);
            break;
        }

        subTasks.remove(id);

        updateEpicStatus(epic);
    }

    public ArrayList<SubTask> getSubTasksOfEpic(int epicId) { // Получение всех подзадач определенного эпика.
        ArrayList<SubTask> subTasksOfEpic = new ArrayList<>();
        Epic epic = epics.get(epicId);
        ArrayList<Integer> subtasks = epic.getSubTasksIds();
        if (subtasks.isEmpty()) {
            return null; // если нет сабтасков
        } else {
            for (Integer subtaskId : subtasks) {
                SubTask subTask = subTasks.get(subtaskId);
                subTasksOfEpic.add(subTask);
            }
            return subTasksOfEpic;
        }
    }


    public void updateTask(Task task) {
        if (!tasks.containsKey(task.getId())) {
            System.out.println("Такой задачи нет в списке.");
        } else {
            String status = task.getStatus();
            task = tasks.get(task.getId());
            task.setStatus(status);
        }
    }

    public void updateEpic(Epic epic) {
        if (!epics.containsKey(epic.getId())) {
            System.out.println("Такой задачи нет в списке.");
        } else {
            String status = epic.getStatus();
            epic = epics.get(epic.getId());
            epic.setStatus(status);
        }
    }

    public void updateSubTask(SubTask subTask) {
        if (!subTasks.containsKey(subTask.getId())) {
            System.out.println("Такой задачи нет в списке.");
        } else {
            String status = subTask.getStatus();
            subTask = subTasks.get(subTask.getId());
            subTask.setStatus(status);
            Epic epic = epics.get(subTask.getEpicId());
            updateEpicStatus(epic);
        }
    }

    public void updateEpicStatus(Epic epic) {
        ArrayList<Integer> subTasksId = epic.getSubTasksIds();

        if (subTasksId.isEmpty()) {
            epic.setStatus("NEW");
            return;
        }

        String status = null;
        for (Integer subId : subTasksId) {
            SubTask subTask = subTasks.get(subId);

            if (status == null) {
                status = subTask.getStatus();
                continue;
            }

            if (status.equals(subTask.getStatus())
                    && !(status.equals("IN_PROGRESS"))) {
                continue;
            }
            epic.setStatus("IN_PROGRESS");
            return;
        }
        epic.setStatus(status);
    }

}
