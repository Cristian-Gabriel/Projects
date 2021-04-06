package com.company;

public class Monom {
    private double coeficient;
    private int putere;
    public Monom(double coeficient, int putere) {
        this.coeficient = coeficient;
        this.putere = putere;
    }

    public double getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(double coeficient) {
        this.coeficient = coeficient;
    }

    public int getPutere() {
        return putere;
    }

    public void setPutere(int putere) {
        this.putere = putere;
    }

    public void inmultire(Monom a){
        this.coeficient*=a.coeficient;
        this.putere+=a.putere;
    }

    public void impartire(Monom a){
        this.coeficient/=a.coeficient;
        this.putere-=a.putere;
    }

    public void derivare(){
        this.coeficient*=this.putere;
        this.putere--;
    }

    public void integrare(){
        this.coeficient/=(this.putere+1);
        this.putere++;
    }

    public void adunare(Monom a){
        this.coeficient+=a.coeficient;
    }

    public void scadere(Monom a){
        this.coeficient-=a.coeficient;
    }
}
