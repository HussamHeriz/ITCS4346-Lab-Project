package hussamheriz.aug.todolistproject;

import java.util.ArrayList;

import hussamheriz.aug.todolistproject.Models.Category;
import hussamheriz.aug.todolistproject.Models.Task;

public class SampleData {

    public static Category[] getCategories() {
        return new Category[]{
                new Category("Home", 3),
                new Category("Personal", 3),
                new Category("Work", 3),
                new Category("University", 3)
        };
    }

    public static Task[] getTasks() {
        return new Task[]{
                new Task(1,
                        "Meeting with client",
                        "01 October 2018, 10:00",
                        "Have to meet him because I want to show him my latest app design in person.\nAlso need to ask for advice on these:\n" +
                                "- style\n" +
                                "- interaction\n" +
                                "- copy",
                        false
                ),
                new Task(1,
                        "Lunch with Julie",
                        "02 October 2018, 10:00",
                        "Have to meet him because I want to show him my latest app design in person.\nAlso need to ask for advice on these:\n" +
                                "- style\n" +
                                "- interaction\n" +
                                "- copy",
                        false
                ),
                new Task(1,
                        "Scrum Meeting",
                        "02 October 2018, 10:00",
                        "Have to meet him because I want to show him my latest app design in person.\nAlso need to ask for advice on these:\n" +
                                "- style\n" +
                                "- interaction\n" +
                                "- copy",
                        true
                ),
                new Task(2,
                        "Good Meeting",
                        "02 October 2018, 10:00",
                        "Have to meet him because I want to show him my latest app design in person.\nAlso need to ask for advice on these:\n" +
                                "- style\n" +
                                "- interaction\n" +
                                "- copy",
                        true
                ),
        };
    }

    public static Task[] getSearchTasks(String search) {

        Task[] allTasks = SampleData.getTasks();
        ArrayList<Task> tasksMatchSearch = new ArrayList<>();
        for(Task task: allTasks) {
            if(task.getTitle().toLowerCase().contains(search)) {
                tasksMatchSearch.add(task);
            }
        }

        Task[] tasksMatchSearchArray = new Task[tasksMatchSearch.size()];
        tasksMatchSearch.toArray(tasksMatchSearchArray);

        return tasksMatchSearchArray;

    }
}
