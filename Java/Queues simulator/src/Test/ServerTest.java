package Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Model.Server;

import java.util.concurrent.atomic.AtomicInteger;

class ServerTest {

    @Test
    void getWaitingPeriod() {
        Server a=new Server();
        AtomicInteger b=new AtomicInteger(12);
        a.setWaitingPeriod(b);
        Assertions.assertEquals(b,a.getWaitingPeriod());
    }

    @Test
    void setWaitingPeriod() {
        Server a=new Server();
        AtomicInteger b=new AtomicInteger(12);
        a.setWaitingPeriod(b);
        Assertions.assertEquals(b,a.getWaitingPeriod());
    }

    @Test
    void getNumber() {
        Server a=new Server();
        a.setNumber(4);
        Assertions.assertEquals(4,a.getNumber());
    }

    @Test
    void setNumber() {
        Server a=new Server();
        a.setNumber(4);
        Assertions.assertEquals(4,a.getNumber());
    }
}