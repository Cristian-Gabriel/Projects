package Model;


import java.util.ArrayList;

public class Polinom {
    public ArrayList<Monom> monom=new ArrayList<>();
    public void add(Monom a){
        this.monom.add(a);
    }
    public Monom grad(){
        Monom g=new Monom(0.0,0);
        for(Monom it:this.monom){
            if(it.getPutere()>g.getPutere()){
                g=it;
            }
        }
        return g;
    }
}
