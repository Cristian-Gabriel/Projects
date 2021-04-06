package Test;

import Model.Task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void getNum() {
        Task a=new Task(1,2,3);
        a.setNum(5);
        Assertions.assertEquals(5,a.getNum());
    }

    @Test
    void setNum() {
        Task a=new Task(1,2,3);
        a.setNum(5);
        Assertions.assertEquals(5,a.getNum());
    }

    @Test
    void getId() {
        Task a=new Task(1,2,3);
        Assertions.assertEquals(1,a.getId());
    }

    @Test
    void setId() {
        Task a=new Task(1,2,3);
        a.setId(7);
        Assertions.assertEquals(7,a.getId());
    }

    @Test
    void getArrivalTime() {
        Task a=new Task(1,2,3);
        Assertions.assertEquals(2,a.getArrivalTime());
    }

    @Test
    void setArrivalTime() {
        Task a=new Task(1,2,3);
        a.setArrivalTime(4);
        Assertions.assertEquals(4,a.getArrivalTime());
    }

    @Test
    void getFinishTime() {
        Task a=new Task(1,2,3);
        a.setFinishTime(5);
        Assertions.assertEquals(5,a.getFinishTime());
    }

    @Test
    void setFinishTime() {
        Task a=new Task(1,2,3);
        a.setFinishTime(5);
        Assertions.assertEquals(5,a.getFinishTime());
    }

    @Test
    void getProcessingPeriod() {
        Task a=new Task(1,2,3);
        Assertions.assertEquals(3,a.getProcessingPeriod());
    }

    @Test
    void setProcssingPeriod() {
        Task a=new Task(1,2,3);
        a.setProcssingPeriod(9);
        Assertions.assertEquals(9,a.getProcessingPeriod());
    }

    @Test
    void getIn() {
        Task a=new Task(1,2,3);
        a.setIn(9);
        Assertions.assertEquals(9,a.getIn());
    }

    @Test
    void setIn() {
        Task a=new Task(1,2,3);
        a.setIn(5);
        Assertions.assertEquals(5,a.getIn());
    }

    @Test
    void setProcessingPeriod() {
        Task a=new Task(1,2,3);
        a.setProcessingPeriod(9);
        Assertions.assertEquals(9,a.getProcessingPeriod());
    }
}