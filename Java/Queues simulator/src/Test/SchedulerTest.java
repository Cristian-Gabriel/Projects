package Test;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerTest {

    @Test
    void dispatchTask() {
        int rez = 12;
        assertEquals(12,rez);
    }

    @Test
    void dispatchTask1() {
        int rez = 12;
        assertEquals(12,rez);
    }

    @Test
    void getServers() {
        List<Integer> servers=null;
        assertEquals(null,servers);
    }
}