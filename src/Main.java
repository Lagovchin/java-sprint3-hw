import domain.Task;
import domain.Epic;
import domain.SubTask;
import manager.TaskManager;


public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("Убраться", "Помыть пол в зале", "NEW");
        taskManager.addTask(task1);
        Task task2 = new Task("Заняться спортом", "Отжаться 15 раз", "NEW");
        taskManager.addTask(task2);

        Epic epic1 = new Epic("Помочь родителям", "В субботу прийти к родителям и помочь", "NEW");
        taskManager.addEpic(epic1);
        Epic epic2 = new Epic("Дом", "Помочь по дому", "NEW");
        taskManager.addEpic(epic2);

        SubTask subTask1 = new SubTask(epic1.getId(),  "Газон", "Подстричь газон", "NEW");
        taskManager.addSubTask(subTask1);
        SubTask subTask2 = new SubTask(epic2.getId(),  "Помочь маме", "Полить огород", "NEW");
        taskManager.addSubTask(subTask2);
        SubTask subTask3 = new SubTask(epic2.getId(), "Сделать  ремонт", "Поменять смеситель", "DONE");
        taskManager.addSubTask(subTask3);

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubTasks());

        task1.setStatus("IN_PROGRESS");
        task2.setStatus("IN_PROGRESS");
        subTask1.setStatus("DONE");
        subTask2.setStatus("IN_PROGRESS");
        subTask3.setStatus("DONE");

        taskManager.updateTask(task1);
        taskManager.updateTask(task2);
        taskManager.updateSubTask(subTask1);
        taskManager.updateSubTask(subTask2);
        taskManager.updateSubTask(subTask3);

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubTasks());

        taskManager.deleteTaskById(task1.getId());
        taskManager.deleteEpicById(epic1.getId());
        taskManager.deleteSubTaskById(subTask2.getId());

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubTasks());


    }
}