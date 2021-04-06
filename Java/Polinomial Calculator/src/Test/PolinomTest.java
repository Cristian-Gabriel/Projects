package Test;

import Model.Monom;
import Model.Polinom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolinomTest {

    @Test
    void add() {
        Polinom p=new Polinom();
        Monom m=new Monom(1,2);
        p.add(m);
        assertEquals(m,p.grad());
        assertEquals(1,p.monom.get(0).getCoeficient());
        assertEquals(2,p.monom.get(0).getPutere());
    }

    @Test
    void grad() {
        Polinom p=new Polinom();
        Monom m=new Monom(1,2);
        p.add(m);
        assertEquals(m,p.grad());
    }
}