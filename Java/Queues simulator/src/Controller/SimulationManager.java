package Controller;

import Model.Scheduler;
import Model.SelectionPolicy;
import Model.Server;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationManager implements Runnable,Comparable{

    private static final Object R = null;
    public int timeLimit=15;
    public int maxProcessingTime=30;
    public int minProcessingTime=2;
    public int numberOfServers=2;
    public int numberOfClients=100;
    public int minServingTime=0;
    public int maxServingTime=0;
    public double averageWaitingTime=0;
    public double averageServiceTime=0;
    public int peakHour;
    private int ok,ok1;
    public SelectionPolicy selectionPolicy= SelectionPolicy.SHORTEST_TIME;
    public String in;
    public String out;

    private Scheduler scheduler;

    private List<Task> generatedTasks;
    private JPanel panel;
    private JTextArea textArea1;
    private JTextField clientsTextField;
    private JTextField queuesTextField;
    private JTextField queuesTextField1;
    private JTextField textField2;
    private JTextField tSimulationTextField;
    private JTextField textField4;
    private JTextField tMinArrivalTextField;
    private JTextField textField3;
    private JTextField tMaxArrivalTextField;
    private JTextField textField5;
    private JTextField tMinServiceTextField;
    private JTextField textField6;
    private JTextField tMaxServiceTextField;
    private JTextField textField8;
    private JButton startButton;
    private JPanel panel11;
    private JComboBox comboBox1;

    public SimulationManager(String in, String out,JFrame frame) throws IOException, InterruptedException {
        initialize(frame);
        this.in = in;
        this.out = out;
        ok=1;
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok=1;
                averageServiceTime=0;
                averageWaitingTime=0;
                String a=comboBox1.getSelectedItem().toString();
                if(a.equals("Shortest time")){
                    ok1=1;
                }
                else{
                    ok1=0;
                }
            }
        });
        while(ok==1) {
            ok=0;
            while(ok==0)
                TimeUnit.SECONDS.sleep(1);
            if(ok == 1) {
                getData();
                scheduler = new Scheduler(numberOfServers, numberOfServers);
                generateNRandomTasks();
                run();
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }

    public List<Task> getGeneratedTasks() {
        return generatedTasks;
    }

    public void setGeneratedTasks(List<Task> generatedTasks) {
        this.generatedTasks = generatedTasks;
    }

    @Override
    public int compareTo(Object o) {
        SimulationManager s=(SimulationManager) o;
        return this.generatedTasks.get(0).compareTo(s.getGeneratedTasks().get(0));
    }

    private void generateNRandomTasks() {
        generatedTasks=new ArrayList<Task>();
        for(int i=1;i<=numberOfClients;++i) {
            int proc=(int)(Math.random()*(maxProcessingTime-minProcessingTime+1)) + minProcessingTime;
            int arr=(int)(Math.random()*(maxServingTime-minServingTime+1))+minServingTime;
            generatedTasks.add(new Task(i, proc, arr));
            averageWaitingTime+=proc;
            averageWaitingTime+=arr;
            averageServiceTime+=arr;
        }
        averageServiceTime/=numberOfClients;
        Collections.sort(generatedTasks);
        int m=1,max=0;
        for(int i=1;i<numberOfClients;i++){
            if(generatedTasks.get(i).getArrivalTime()==generatedTasks.get(i-1).getArrivalTime()){
                m++;
            }
            else{
                if(m>max){
                    max=m;
                    peakHour=generatedTasks.get(i-1).getArrivalTime();
                }
            }
        }
        if(m>max){
            max=m;
            peakHour=generatedTasks.get(numberOfClients-1).getArrivalTime();
        }
    }
    @Override
    public void run() {
        try {
            FileWriter writer=new FileWriter(this.out);
            int time=0;
            String total="";
            while(time<timeLimit) {
                int okay=1, k=0, nra=0;
                String sir="";
                writer.write("Time "+time);
                total="Time "+time;
                for (Task t : generatedTasks)
                    if (time<t.getArrivalTime())
                        sir=sir+t+"; ";
                    else if (t.getArrivalTime() == time) {
                        int r=0;
                        if(ok1==1) {
                            r = scheduler.dispatchTask(t);
                        }
                        else{
                            r = scheduler.dispatchTask1(t);
                        }
                        averageWaitingTime+=r;
                    }
                writer.write("\n"+"Waiting clients: "+sir+"\n");
                total=total+"\n"+"Waiting clients: "+sir+"\n";
                if(sir.equals("")) okay=0;
                for (Server s : scheduler.getServers()) {
                    k++;
                    if(s.toString().equals("Queue "+k+": "+"closed")) nra++;
                    writer.write(s.toString()+"\n");
                    total=total+s.toString()+"\n";
                }
                Font font = new Font("Segoe Script", Font.BOLD, 30);
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                Color randomColor = new Color(r, g, b);
                textArea1.setBackground(randomColor);
                r = rand.nextFloat();
                g = rand.nextFloat();
                b = rand.nextFloat();
                randomColor = new Color(r, g, b);
                panel11.setBackground(randomColor);
                r = rand.nextFloat();
                g = rand.nextFloat();
                b = rand.nextFloat();
                randomColor = new Color(r, g, b);
                panel.setBackground(randomColor);
                textArea1.setFont(font);
                textArea1.setText(total);
                TimeUnit.SECONDS.sleep(1);
                total="";
                if(nra==k && okay==0) break;
                time++;
                writer.write("\n");
            }
            averageWaitingTime/=numberOfClients;
            total="Average waiting time: ";
            total+=averageWaitingTime+"\n";
            total+="Average service time: "+averageServiceTime+"\n"+"Peak hour: "+peakHour;
            Font font = new Font("Segoe Script", Font.BOLD, 50);
            textArea1.setFont(font);
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            textArea1.setBackground(randomColor);
            r = rand.nextFloat();
            g = rand.nextFloat();
            b = rand.nextFloat();
            randomColor = new Color(r, g, b);
            panel11.setBackground(randomColor);
            r = rand.nextFloat();
            g = rand.nextFloat();
            b = rand.nextFloat();
            randomColor = new Color(r, g, b);
            panel.setBackground(randomColor);
            writer.write("\n"+total);
            writer.close();
            total+="\n\n";
            total+="                     {\\_/}"+"\n";
            total+="                     ( °.°)"+"\n";
            total+="                     />Finish!";
            textArea1.setText(total);
        } catch (IOException | InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void getData() {
        numberOfClients=Integer.parseInt(queuesTextField.getText());
        numberOfServers=Integer.parseInt(textField2.getText());
        timeLimit=Integer.parseInt(textField4.getText());
        minProcessingTime=Integer.parseInt(textField3.getText());
        maxProcessingTime=Integer.parseInt(textField5.getText());
        minServingTime=Integer.parseInt(textField6.getText());
        maxServingTime=Integer.parseInt(textField8.getText());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame frame=null;
        SimulationManager gen=new SimulationManager(args[0],args[1],frame);
        Thread t=new Thread(String.valueOf(gen));
        t.start();
    }
    public void initialize(JFrame frame) {
        if (frame == null) {
            frame = new JFrame("Queue");
            frame.setContentPane(this.panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        Dimension a = new Dimension();
        a.height = 768;
        a.width = 1385;
        frame.setMinimumSize(a);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(-10, 0, (int) dim.getWidth(), (int) dim.getHeight());
        frame.pack();
        frame.setVisible(true);
    }
}