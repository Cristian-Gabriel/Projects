package Model;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategy implements Strategy {

    @Override
    public int addTask(List<Server> servers, Task t) {
        int val=0,index=0,aux=0;
        for(Task t1:servers.get(0).getTasks()){
            val+=t1.getProcessingPeriod();
        }
        for(Server i: servers) {
            int p=0;
            for(Task t1:servers.get(index).getTasks()){
                p+=t1.getProcessingPeriod();
            }
            if(p<val){
                val=p;
                aux=index;
            }
            index++;
        }
        servers.get(aux).addTask(t);
        return val;
    }
    public int addTask1(List<Server> servers, Task t) {
        int val=0,index=0,aux=0;
        val=servers.get(0).getTasks().size();
        for(Server i: servers) {
            int p=0;
            p=i.getTasks().size();
            if(p<val){
                val=p;
                aux=index;
            }
            index++;
        }
        servers.get(aux).addTask(t);
        return val;
    }

}
