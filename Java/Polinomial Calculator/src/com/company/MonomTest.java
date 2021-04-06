package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonomTest {

    @Test
    void getCoeficient() {
        Monom m=new Monom(1,2);
        assertEquals(1,m.getCoeficient());
    }

    @Test
    void setCoeficient() {
        Monom m=new Monom(1,2);
        m.setCoeficient(4);
        assertEquals(4,m.getCoeficient());
    }

    @Test
    void getPutere() {
        Monom m=new Monom(1,2);
        assertEquals(2,m.getPutere());
    }

    @Test
    void setPutere() {
        Monom m=new Monom(1,2);
        m.setPutere(5);
        assertEquals(5,m.getPutere());
    }

    @Test
    void inmultire() {
        Monom m=new Monom(1,2);
        Monom m1=new Monom(3,4);
        m.inmultire(m1);
        assertEquals(3,m.getCoeficient());
        assertEquals(6,m.getPutere());
    }

    @Test
    void impartire() {
        Monom m=new Monom(6,5);
        Monom m1=new Monom(3,4);
        m.impartire(m1);
        assertEquals(2,m.getCoeficient());
        assertEquals(1,m.getPutere());
    }

    @Test
    void derivare() {
        Monom m=new Monom(6,5);
        m.derivare();
        assertEquals(30,m.getCoeficient());
        assertEquals(4,m.getPutere());
    }

    @Test
    void integrare() {
        Monom m=new Monom(6,5);
        m.integrare();
        assertEquals(1,m.getCoeficient());
        assertEquals(6,m.getPutere());
    }

    @Test
    void adunare() {
        Monom m=new Monom(6,5);
        Monom m1=new Monom(3,5);
        m.adunare(m1);
        assertEquals(9,m.getCoeficient());
        assertEquals(5,m.getPutere());
    }

    @Test
    void scadere() {
        Monom m=new Monom(6,5);
        Monom m1=new Monom(3,5);
        m.scadere(m1);
        assertEquals(3,m.getCoeficient());
        assertEquals(5,m.getPutere());
    }
}