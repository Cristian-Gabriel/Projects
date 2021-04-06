package com.company;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.Statement;

public class Interfata {
    private JTextField polinomialCalculatorTextField;
    private JTextField polinom1TextField;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField polinom2TextField;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton impartireButton;
    private JButton derivarePolinom1Button;
    private JButton integrarePolinom2Button;
    private JButton inmultireButton;
    private JPanel panel;
    private JTextField textField1;
    private JTextField textField5;
    private JButton adunareButton;
    private JButton scadereButton;
    private JTextField textField9;

    public Interfata(JFrame frame) {
        initialize(frame);

        derivarePolinom1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p1=textField3.getText();
                boolean ok=false;
                boolean okk=false;
                boolean min=false;
                double c=0;
                int p=1;
                int nr=1;
                Polinom aa=new Polinom();
                for(int i=0;i<p1.length();i++){
                    if(p1.charAt(i)-'0'>=0 && p1.charAt(i)-'0'<=9){
                        if(!ok) {
                            c = c * 10 + p1.charAt(i) - '0';
                        }else{
                            p = p * 10 + p1.charAt(i) - '0';
                        }
                    }
                    if(p1.charAt(i)=='.'){
                        okk=true;
                        nr=0;
                    }
                    if(okk){
                        nr++;
                    }
                    if(p1.charAt(i)=='x'){
                        ok=true;
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        nr=0;
                        okk=false;
                    }
                    if(p1.charAt(i)=='^'){
                        p=0;
                    }
                    if(p1.charAt(i)=='+'||p1.charAt(i)=='-'){
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        if(c==0){
                            c=1;
                        }
                        if(!ok){
                            p=0;
                        }
                        if(min){
                            c=-c;
                        }
                        min=false;
                        if(p1.charAt(i)=='-'){
                            min=true;
                        }
                        Monom a=new Monom(c,p);
                        aa.monom.add(a);
                        c=0;
                        p=1;
                        ok=false;
                    }
                }
                for(int j=1;j<=nr-2;j++){
                    c/=10;
                }
                if(c==0){
                    c=1;
                }
                if(!ok){
                    p=0;
                }
                if(min){
                    c=-c;
                }
                Monom a=new Monom(c,p);
                aa.monom.add(a);
                for(Monom it1:aa.monom){
                    it1.derivare();
                }
                String l=null;
                for(Monom it1:aa.monom){
                    if(it1.getCoeficient()!=0) {
                        if (l == null) {
                            double q = it1.getCoeficient();
                            if (q == -1 && it1.getPutere() != 0) {
                                l ="-";
                            }
                            if (q != 1 && q != -1) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = String.valueOf((int) q);
                                } else {
                                    l = String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                if (l != null)
                                    l = l + "x";
                                else
                                    l = "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        } else {
                            double q = it1.getCoeficient();
                            if (q > 0) {
                                l = l + "+";
                            }
                            if (q == -1 && it1.getPutere() != 0) {
                                l = l + "-";
                            }
                            if (q > 1 || q < -1 || it1.getPutere() == 0) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                l = l + "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        }
                    }
                }
                if(l==null){
                    l="0";
                }
                textField7.setText(l);
            }
        });
        integrarePolinom2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p1=textField6.getText();
                boolean ok=false;
                boolean okk=false;
                boolean min=false;
                double c=0;
                int p=1;
                int nr=1;
                Polinom aa=new Polinom();
                for(int i=0;i<p1.length();i++){
                    if(p1.charAt(i)-'0'>=0 && p1.charAt(i)-'0'<=9){
                        if(!ok) {
                            c = c * 10 + p1.charAt(i) - '0';
                        }else{
                            p = p * 10 + p1.charAt(i) - '0';
                        }
                    }
                    if(p1.charAt(i)=='.'){
                        okk=true;
                        nr=0;
                    }
                    if(okk){
                        nr++;
                    }
                    if(p1.charAt(i)=='x'){
                        ok=true;
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        nr=0;
                        okk=false;
                    }
                    if(p1.charAt(i)=='^'){
                        p=0;
                    }
                    if(p1.charAt(i)=='+'||p1.charAt(i)=='-'){
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        if(c==0){
                            c=1;
                        }
                        if(!ok){
                            p=0;
                        }
                        if(min){
                            c=-c;
                        }
                        min=false;
                        if(p1.charAt(i)=='-'){
                            min=true;
                        }
                        Monom a=new Monom(c,p);
                        aa.monom.add(a);
                        c=0;
                        p=1;
                        ok=false;
                    }
                }
                for(int j=1;j<=nr-2;j++){
                    c/=10;
                }
                if(c==0){
                    c=1;
                }
                if(!ok){
                    p=0;
                }
                if(min){
                    c=-c;
                }
                Monom a=new Monom(c,p);
                aa.monom.add(a);
                for(Monom it1:aa.monom){
                    it1.integrare();
                }
                String l=null;
                for(Monom it1:aa.monom){
                    if(it1.getCoeficient()!=0) {
                        if (l == null) {
                            double q = it1.getCoeficient();
                            if (q == -1 && it1.getPutere() != 0) {
                                l ="-";
                            }
                            if (q != 1 && q != -1) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = String.valueOf((int) q);
                                } else {
                                    l = String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                if (l != null)
                                    l = l + "x";
                                else
                                    l = "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        } else {
                            double q = it1.getCoeficient();
                            if (q > 0) {
                                l = l + "+";
                            }
                            if (q == -1 && it1.getPutere() != 0) {
                                l = l + "-";
                            }
                            if (q > 1 || q < -1 || it1.getPutere() == 0) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                l = l + "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        }
                    }
                }
                if(l==null){
                    l="0";
                }
                textField8.setText(l);
            }
        });
        adunareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p1=textField3.getText();
                String p2=textField6.getText();
                boolean ok=false;
                boolean okk=false;
                boolean min=false;
                double c=0;
                int p=1;
                int nr=1;
                Polinom aa=new Polinom();
                Polinom bb=new Polinom();
                for(int i=0;i<p1.length();i++){
                    if(p1.charAt(i)-'0'>=0 && p1.charAt(i)-'0'<=9){
                        if(!ok) {
                            c = c * 10 + p1.charAt(i) - '0';
                        }else{
                            p = p * 10 + p1.charAt(i) - '0';
                        }
                    }
                    if(p1.charAt(i)=='.'){
                        okk=true;
                        nr=0;
                    }
                    if(okk){
                        nr++;
                    }
                    if(p1.charAt(i)=='x'){
                        ok=true;
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        nr=0;
                        okk=false;
                    }
                    if(p1.charAt(i)=='^'){
                        p=0;
                    }
                    if(p1.charAt(i)=='+'||p1.charAt(i)=='-'){
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        if(c==0){
                            c=1;
                        }
                        if(!ok){
                            p=0;
                        }
                        if(min){
                            c=-c;
                        }
                        min=false;
                        if(p1.charAt(i)=='-'){
                            min=true;
                        }
                        Monom a=new Monom(c,p);
                        aa.monom.add(a);
                        c=0;
                        p=1;
                        ok=false;
                    }
                }
                for(int j=1;j<=nr-2;j++){
                    c/=10;
                }
                if(c==0){
                    c=1;
                }
                if(!ok){
                    p=0;
                }
                if(min){
                    c=-c;
                }
                Monom a=new Monom(c,p);
                aa.monom.add(a);
                c=0;
                p=1;
                ok=false;
                okk=false;
                min=false;
                for(int i=0;i<p2.length();i++){
                    if(p2.charAt(i)-'0'>=0 && p2.charAt(i)-'0'<=9){
                        if(!ok) {
                            c = c * 10 + p2.charAt(i) - '0';
                        }else{
                            p = p * 10 + p2.charAt(i) - '0';
                        }
                    }
                    if(p2.charAt(i)=='.'){
                        okk=true;
                        nr=0;
                    }
                    if(okk){
                        nr++;
                    }
                    if(p2.charAt(i)=='x'){
                        ok=true;
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        nr=0;
                        okk=false;
                    }
                    if(p2.charAt(i)=='^'){
                        p=0;
                    }
                    if(p2.charAt(i)=='+'||p2.charAt(i)=='-'){
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        if(c==0){
                            c=1;
                        }
                        if(!ok){
                            p=0;
                        }
                        if(min){
                            c=-c;
                        }
                        min=false;
                        if(p2.charAt(i)=='-'){
                            min=true;
                        }
                        a=new Monom(c,p);
                        aa.monom.add(a);
                        c=0;
                        p=1;
                        ok=false;
                    }
                }
                for(int j=1;j<=nr-2;j++){
                    c/=10;
                }
                if(c==0){
                    c=1;
                }
                if(!ok){
                    p=0;
                }
                if(min){
                    c=-c;
                }
                a=new Monom(c,p);
                aa.monom.add(a);
                for(int i=0;i<aa.monom.size();i++){
                    for(int j=i+1;j<aa.monom.size();j++){
                        if(aa.monom.get(i).getPutere()==aa.monom.get(j).getPutere()){
                            aa.monom.get(i).adunare(aa.monom.get(j));
                            aa.monom.remove(j);
                        }
                    }
                }
                String l=null;
                for(Monom it1:aa.monom){
                    if(it1.getCoeficient()!=0) {
                        if (l == null) {
                            double q = it1.getCoeficient();
                            if (q == -1 && it1.getPutere() != 0) {
                                l ="-";
                            }
                            if (q > 1 || q < -1) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = String.valueOf((int) q);
                                } else {
                                    l = String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                if (l != null)
                                    l = l + "x";
                                else
                                    l = "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        } else {
                            double q = it1.getCoeficient();
                            if (q > 0) {
                                l = l + "+";
                            }
                            if (q == -1 && it1.getPutere() != 0) {
                                l = l + "-";
                            }
                            if (q > 1 || q < -1 || it1.getPutere() == 0) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                l = l + "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        }
                    }
                }
                if(l==null){
                    l="0";
                }
                textField1.setText(l);
            }
        });
        scadereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p1=textField3.getText();
                String p2=textField6.getText();
                boolean ok=false;
                boolean okk=false;
                boolean min=false;
                double c=0;
                int p=1;
                int nr=1;
                Polinom aa=new Polinom();
                Polinom bb=new Polinom();
                for(int i=0;i<p1.length();i++){
                    if(p1.charAt(i)-'0'>=0 && p1.charAt(i)-'0'<=9){
                        if(!ok) {
                            c = c * 10 + p1.charAt(i) - '0';
                        }else{
                            p = p * 10 + p1.charAt(i) - '0';
                        }
                    }
                    if(p1.charAt(i)=='.'){
                        okk=true;
                        nr=0;
                    }
                    if(okk){
                        nr++;
                    }
                    if(p1.charAt(i)=='x'){
                        ok=true;
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        nr=0;
                        okk=false;
                    }
                    if(p1.charAt(i)=='^'){
                        p=0;
                    }
                    if(p1.charAt(i)=='+'||p1.charAt(i)=='-'){
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        if(c==0){
                            c=1;
                        }
                        if(!ok){
                            p=0;
                        }
                        if(min){
                            c=-c;
                        }
                        min=false;
                        if(p1.charAt(i)=='-'){
                            min=true;
                        }
                        Monom a=new Monom(c,p);
                        aa.monom.add(a);
                        c=0;
                        p=1;
                        ok=false;
                    }
                }
                for(int j=1;j<=nr-2;j++){
                    c/=10;
                }
                if(c==0){
                    c=1;
                }
                if(!ok){
                    p=0;
                }
                if(min){
                    c=-c;
                }
                Monom a=new Monom(c,p);
                aa.monom.add(a);
                c=0;
                p=1;
                ok=false;
                okk=false;
                min=false;
                for(int i=0;i<p2.length();i++){
                    if(p2.charAt(i)-'0'>=0 && p2.charAt(i)-'0'<=9){
                        if(!ok) {
                            c = c * 10 + p2.charAt(i) - '0';
                        }else{
                            p = p * 10 + p2.charAt(i) - '0';
                        }
                    }
                    if(p2.charAt(i)=='.'){
                        okk=true;
                        nr=0;
                    }
                    if(okk){
                        nr++;
                    }
                    if(p2.charAt(i)=='x'){
                        ok=true;
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        nr=0;
                        okk=false;
                    }
                    if(p2.charAt(i)=='^'){
                        p=0;
                    }
                    if(p2.charAt(i)=='+'||p2.charAt(i)=='-'){
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        if(c==0){
                            c=1;
                        }
                        if(!ok){
                            p=0;
                        }
                        if(min){
                            c=-c;
                        }
                        min=false;
                        if(p2.charAt(i)=='-'){
                            min=true;
                        }
                        a=new Monom(-c,p);
                        aa.monom.add(a);
                        c=0;
                        p=1;
                        ok=false;
                    }
                }
                for(int j=1;j<=nr-2;j++){
                    c/=10;
                }
                if(c==0){
                    c=1;
                }
                if(!ok){
                    p=0;
                }
                if(min){
                    c=-c;
                }
                min=false;
                a=new Monom(-c,p);
                aa.monom.add(a);
                for(int i=0;i<aa.monom.size();i++){
                    for(int j=i+1;j<aa.monom.size();j++){
                        if(aa.monom.get(i).getPutere()==aa.monom.get(j).getPutere()){
                            aa.monom.get(i).adunare(aa.monom.get(j));
                            aa.monom.remove(j);
                        }
                    }
                }
                String l=null;
                for(Monom it1:aa.monom){
                    if(it1.getCoeficient()!=0) {
                        if (l == null) {
                            double q = it1.getCoeficient();
                            if (q == -1 && it1.getPutere() != 0) {
                                l ="-";
                            }
                            if (q > 1 || q < -1) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = String.valueOf((int) q);
                                } else {
                                    l = String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                if (l != null)
                                    l = l + "x";
                                else
                                    l = "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        } else {
                            double q = it1.getCoeficient();
                            if (q > 0) {
                                l = l + "+";
                            }
                            if (q == -1 && it1.getPutere() != 0) {
                                l = l + "-";
                            }
                            if (q > 1 || q < -1 || it1.getPutere() == 0) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                l = l + "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        }
                    }
                }
                if(l==null){
                    l="0";
                }
                textField5.setText(l);
            }
        });
        impartireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p1=textField3.getText();
                String p2=textField6.getText();
                boolean ok=false;
                boolean okk=false;
                boolean min=false;
                double c=0;
                int p=1;
                int nr=1;
                Polinom aa=new Polinom();
                Polinom bb=new Polinom();
                for(int i=0;i<p1.length();i++){
                    if(p1.charAt(i)-'0'>=0 && p1.charAt(i)-'0'<=9){
                        if(!ok) {
                            c = c * 10 + p1.charAt(i) - '0';
                        }else{
                            p = p * 10 + p1.charAt(i) - '0';
                        }
                    }
                    if(p1.charAt(i)=='.'){
                        okk=true;
                        nr=0;
                    }
                    if(okk){
                        nr++;
                    }
                    if(p1.charAt(i)=='x'){
                        ok=true;
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        nr=0;
                        okk=false;
                    }
                    if(p1.charAt(i)=='^'){
                        p=0;
                    }
                    if(p1.charAt(i)=='+'||p1.charAt(i)=='-'){
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        if(c==0){
                            c=1;
                        }
                        if(!ok){
                            p=0;
                        }
                        if(min){
                            c=-c;
                        }
                        min=false;
                        if(p1.charAt(i)=='-'){
                            min=true;
                        }
                        Monom a=new Monom(c,p);
                        aa.monom.add(a);
                        c=0;
                        p=1;
                        ok=false;
                    }
                }
                for(int j=1;j<=nr-2;j++){
                    c/=10;
                }
                if(c==0){
                    c=1;
                }
                if(!ok){
                    p=0;
                }
                if(min){
                    c=-c;
                }
                min=false;
                Monom a=new Monom(c,p);
                aa.monom.add(a);
                c=0;
                p=1;
                ok=false;
                okk=false;
                min=false;
                for(int i=0;i<p2.length();i++){
                    if(p2.charAt(i)-'0'>=0 && p2.charAt(i)-'0'<=9){
                        if(!ok) {
                            c = c * 10 + p2.charAt(i) - '0';
                        }else{
                            p = p * 10 + p2.charAt(i) - '0';
                        }
                    }
                    if(p2.charAt(i)=='.'){
                        okk=true;
                        nr=0;
                    }
                    if(okk){
                        nr++;
                    }
                    if(p2.charAt(i)=='x'){
                        ok=true;
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        nr=0;
                        okk=false;
                    }
                    if(p2.charAt(i)=='^'){
                        p=0;
                    }
                    if(p2.charAt(i)=='+'||p2.charAt(i)=='-'){
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        if(c==0){
                            c=1;
                        }
                        if(!ok){
                            p=0;
                        }
                        if(min){
                            c=-c;
                        }
                        min=false;
                        if(p2.charAt(i)=='-'){
                            min=true;
                        }
                        a=new Monom(c,p);
                        bb.monom.add(a);
                        c=0;
                        p=1;
                        ok=false;
                    }
                }
                for(int j=1;j<=nr-2;j++){
                    c/=10;
                }
                if(c==0){
                    c=1;
                }
                if(!ok){
                    p=0;
                }
                if(min){
                    c=-c;
                }
                min=false;
                a=new Monom(c,p);
                bb.monom.add(a);
                Polinom cat=new Polinom();
                while(aa.grad().getPutere()>=bb.grad().getPutere()){
                    a=new Monom(aa.grad().getCoeficient(), aa.grad().getPutere());
                    a.impartire(bb.grad());
                    cat.add(a);
                    for(Monom it2:bb.monom){
                        Monom a1=new Monom(-a.getCoeficient(),a.getPutere());
                        a1.inmultire(it2);
                        aa.monom.add(a1);
                    }
                    for(int i=0;i<aa.monom.size();i++){
                        for(int j=i+1;j<aa.monom.size();j++){
                            if(aa.monom.get(i).getPutere()==aa.monom.get(j).getPutere()){
                                aa.monom.get(i).adunare(aa.monom.get(j));
                                aa.monom.remove(j);
                            }
                        }
                        if(aa.monom.get(i).getCoeficient()==0){
                            aa.monom.remove(i);
                            i--;
                        }
                    }
                }
                String l=null;
                for(Monom it1:cat.monom){
                    if(it1.getCoeficient()!=0) {
                        if (l == null) {
                            double q = it1.getCoeficient();
                            if (q == -1 && it1.getPutere() != 0) {
                                l ="-";
                            }
                            if (q != 1 && q != -1) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = String.valueOf((int) q);
                                } else {
                                    l = String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                if (l != null)
                                    l = l + "x";
                                else
                                    l = "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        } else {
                            double q = it1.getCoeficient();
                            if (q > 0) {
                                l = l + "+";
                            }
                            if (q == -1 && it1.getPutere() != 0) {
                                l = l + "-";
                            }
                            if (q > 1 || q < -1 || it1.getPutere() == 0) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                l = l + "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        }
                    }
                }
                if(l==null){
                    l="0";
                }
                textField4.setText(l);
                l=null;
                for(Monom it1:aa.monom){
                    if(it1.getCoeficient()!=0) {
                        if (l == null) {
                            double q = it1.getCoeficient();
                            if (q == -1 && it1.getPutere() != 0) {
                                l ="-";
                            }
                            if (q > 1 || q < -1) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = String.valueOf((int) q);
                                } else {
                                    l = String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                if (l != null)
                                    l = l + "x";
                                else
                                    l = "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        } else {
                            double q = it1.getCoeficient();
                            if (q > 0) {
                                l = l + "+";
                            }
                            if (q == -1 && it1.getPutere() != 0) {
                                l = l + "-";
                            }
                            if (q > 1 || q < -1 || it1.getPutere() == 0) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                l = l + "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        }
                    }
                }
                if(l==null){
                    l="0";
                }
                textField9.setText(l);
            }
        });
    }

    public void initialize(JFrame frame) {
        if(frame==null){
            frame = new JFrame("Polinomial Calculator");
            frame.setContentPane(this.panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        Dimension a=new Dimension();
        a.height=768;
        a.width=1366;
        frame.setMinimumSize(a);
        frame.pack();
        frame.setVisible(true);
        JFrame finalFrame = frame;
        inmultireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p1=textField3.getText();
                String p2=textField6.getText();
                boolean ok=false;
                boolean okk=false;
                boolean min=false;
                double c=0;
                int p=1;
                int nr=1;
                Polinom aa=new Polinom();
                Polinom bb=new Polinom();
                for(int i=0;i<p1.length();i++){
                    if(p1.charAt(i)-'0'>=0 && p1.charAt(i)-'0'<=9){
                        if(!ok) {
                            c = c * 10 + p1.charAt(i) - '0';
                        }else{
                            p = p * 10 + p1.charAt(i) - '0';
                        }
                    }
                    if(p1.charAt(i)=='.'){
                        okk=true;
                        nr=0;
                    }
                    if(okk){
                        nr++;
                    }
                    if(p1.charAt(i)=='x'){
                        ok=true;
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        nr=0;
                        okk=false;
                    }
                    if(p1.charAt(i)=='^'){
                        p=0;
                    }
                    if(p1.charAt(i)=='+'||p1.charAt(i)=='-'){
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        if(c==0){
                            c=1;
                        }
                        if(!ok){
                            p=0;
                        }
                        if(min){
                            c=-c;
                        }
                        if(p1.charAt(i)=='-'){
                            min=true;
                        }
                        Monom a=new Monom(c,p);
                        aa.monom.add(a);
                        c=0;
                        p=1;
                        ok=false;
                    }
                }
                for(int j=1;j<=nr-2;j++){
                    c/=10;
                }
                if(c==0){
                    c=1;
                }
                if(!ok){
                    p=0;
                }
                if(min){
                    c=-c;
                }
                min=false;
                Monom a=new Monom(c,p);
                aa.monom.add(a);
                c=0;
                p=1;
                ok=false;
                okk=false;
                min=false;
                for(int i=0;i<p2.length();i++){
                    if(p2.charAt(i)-'0'>=0 && p2.charAt(i)-'0'<=9){
                        if(!ok) {
                            c = c * 10 + p2.charAt(i) - '0';
                        }else{
                            p = p * 10 + p2.charAt(i) - '0';
                        }
                    }
                    if(p2.charAt(i)=='.'){
                        okk=true;
                        nr=0;
                    }
                    if(okk){
                        nr++;
                    }
                    if(p2.charAt(i)=='x'){
                        ok=true;
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        nr=0;
                        okk=false;
                    }
                    if(p2.charAt(i)=='^'){
                        p=0;
                    }
                    if(p2.charAt(i)=='+'||p2.charAt(i)=='-'){
                        for(int j=1;j<=nr-2;j++){
                            c/=10;
                        }
                        if(c==0){
                            c=1;
                        }
                        if(!ok){
                            p=0;
                        }
                        if(min){
                            c=-c;
                        }
                        min=false;
                        if(p2.charAt(i)=='-'){
                            min=true;
                        }
                        a=new Monom(c,p);
                        bb.monom.add(a);
                        c=0;
                        p=1;
                        ok=false;
                    }
                }
                for(int j=1;j<=nr-2;j++){
                    c/=10;
                }
                if(c==0){
                    c=1;
                }
                if(!ok){
                    p=0;
                }
                if(min){
                    c=-c;
                }
                a=new Monom(c,p);
                bb.monom.add(a);
                c=0;
                p=1;
                ok=false;

                Polinom cc=new Polinom();
                for(Monom it1:aa.monom){
                    for(Monom it2:bb.monom){
                        a=new Monom(it1.getCoeficient(), it1.getPutere());
                        a.inmultire(it2);
                        cc.monom.add(a);
                    }
                }
                for(int i=0;i<cc.monom.size();i++){
                    for(int j=i+1;j<cc.monom.size();j++){
                        if(cc.monom.get(i).getPutere()==cc.monom.get(j).getPutere()){
                            cc.monom.get(i).adunare(cc.monom.get(j));
                            cc.monom.remove(j);
                        }
                    }
                }
                String l=null;
                for(Monom it1:cc.monom){
                    if(it1.getCoeficient()!=0) {
                        if (l == null) {
                            double q = it1.getCoeficient();
                            if (q == -1 && it1.getPutere() != 0) {
                                l ="-";
                            }
                            if (q > 1 || q < -1) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = String.valueOf((int) q);
                                } else {
                                    l = String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                if (l != null)
                                    l = l + "x";
                                else
                                    l = "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        } else {
                            double q = it1.getCoeficient();
                            if (q > 0) {
                                l = l + "+";
                            }
                            if (q == -1 && it1.getPutere() != 0) {
                                l = l + "-";
                            }
                            if (q > 1 || q < -1 || it1.getPutere() == 0) {
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                            if (it1.getPutere() > 0)
                                l = l + "x";
                            q = it1.getPutere();
                            if (q > 1 || q < -1) {
                                l = l + "^";
                                double qq = q - (int) q;
                                if (qq == 0) {
                                    l = l + String.valueOf((int) q);
                                } else {
                                    l = l + String.valueOf(q);
                                }
                            }
                        }
                    }
                }
                if(l==null){
                    l="0";
                }
                textField2.setText(l);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
