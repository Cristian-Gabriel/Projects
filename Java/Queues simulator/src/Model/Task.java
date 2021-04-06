package Model;

public class Task implements Comparable{
    private int id;
    private int arrivalTime;
    private int finishTime;
    private int processingPeriod;
    private int in;
    private int num;

    public Task(int id, int arrivalTime, int processingPeriod) {
        this.id=id;
        this.arrivalTime=arrivalTime;
        this.processingPeriod=processingPeriod;
        this.finishTime=0;
        this.in=0;
        this.num=0;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public int getFinishTime() {
        return finishTime;
    }
    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }
    public int getProcessingPeriod() {
        return processingPeriod;
    }
    public void setProcssingPeriod(int processingPeriod) {
        this.processingPeriod = processingPeriod;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public void setProcessingPeriod(int processingPeriod) {
        this.processingPeriod = processingPeriod;
    }

    @Override
    public int compareTo(Object o) {
        Task t=(Task) o;
        if(this.arrivalTime>t.getArrivalTime()) return 1;
        else if(this.arrivalTime<t.getArrivalTime()) return -1;
        return 0;
    }

    @Override
    public String toString() {
        String s="";
        s="("+this.id+", "+this.arrivalTime+", "+this.processingPeriod+")";
        return s;
    }
}