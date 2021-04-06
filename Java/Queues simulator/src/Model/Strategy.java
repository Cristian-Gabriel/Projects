package Model;

import Model.Server;
import Model.Task;

import java.util.List;

public interface Strategy {
    public int addTask(List<Server> servers, Task t);
    public int addTask1(List<Server> servers, Task t);
}