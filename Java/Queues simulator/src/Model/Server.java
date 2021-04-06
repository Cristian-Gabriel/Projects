package Model;

import Model.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private int number;
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private Thread thread=null;

    public Server(int number) {
        this.number=number;
        this.tasks= new LinkedBlockingQueue<>();
        this.waitingPeriod=new AtomicInteger(0);
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public Server() {
        BlockingQueue<Task> tasks = new LinkedBlockingDeque<>();
        waitingPeriod=new AtomicInteger(0);
    }

    public Server(AtomicInteger waitingPeriod) {
        BlockingQueue<Task> tasks = new LinkedBlockingDeque<>();
        this.waitingPeriod=waitingPeriod;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod.incrementAndGet();
    }

    public void start(Thread thread, int time) {
        System.out.println("Time "+time);
        thread.start();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        while (true) {
            try {
                if (tasks.isEmpty() == true) {
                    Thread.sleep(1000);
                } else {
                    Task t = tasks.take();
                    int i=0, j;
                    t.setFinishTime(t.getArrivalTime()+t.getProcessingPeriod());
                    while(i<t.getProcessingPeriod()-1) {
                        int k=t.getProcessingPeriod()-1;
                        t.setProcessingPeriod(k);
                        if (t.getIn() == 0 && t.getProcessingPeriod()!=0) {
                            Task s=t;
                            tasks.put(s);
                            for(j=1;j<tasks.size();++j)
                                tasks.add(tasks.take());
                            t=tasks.element();
                            t.setIn(1);
                        }
                        Thread.sleep(1000);
                    }
                    AtomicInteger p=new AtomicInteger(waitingPeriod.getAndDecrement());
                    waitingPeriod = p;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public String toString() {
        String s= "Queue " + number + ": ";
        boolean ok=true;
        for (Task t : tasks) {
            ok=false;
            s = s + t.toString() + " ";
        }
        if(ok==true) s+="closed";
        return s;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public void getThreadStrarted() {
        thread = new Thread(this);
        thread.start();
    }

}