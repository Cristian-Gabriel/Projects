package Model;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers=maxNoServers;
        this.maxTasksPerServer=maxTasksPerServer;
        strategy=new ConcreteStrategy();
        this.servers=new ArrayList<Server>();
        for(int i=1;i<=this.maxNoServers;++i) {
            Server server=new Server(i);
            servers.add(server);
            server.getThreadStrarted();
        }
    }
    public void changeStrategy(SelectionPolicy policy) {
            strategy=new ConcreteStrategy();
    }

    public int dispatchTask(Task t) {
        int rez = strategy.addTask(this.servers, t);
        return rez;
    }
    public int dispatchTask1(Task t) {
        int rez = strategy.addTask1(this.servers, t);
        return rez;
    }
    public List<Server> getServers(){
        return servers;
    }
}