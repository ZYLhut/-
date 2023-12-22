import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class frame2 extends JFrame implements ActionListener {
    private JLabel pid=new JLabel();
    private JTextField p=new JTextField();

    private JLabel yun=new JLabel();
    private JTextField yun1=new JTextField();

    private JLabel prio=new JLabel();
    private JComboBox<String> pr=new JComboBox<>();

    private JLabel nei=new JLabel();
    private JTextField neii=new JTextField();

    private JLabel qian=new JLabel();
    private JTextField qi=new JTextField();

    private JLabel ho=new JLabel();
    private JTextField hoji=new JTextField();

    private JLabel duilie=new JLabel();
    private JComboBox<String> dui=new JComboBox<>();
    private JButton jb=new JButton();
    private ArrayList<PCB>l=new ArrayList<>();
    private ArrayList<PCB> l1=new ArrayList<>();

    public frame2(ArrayList<PCB>list,ArrayList<PCB>list1)
    {
        l=list;
        l1=list1;
        this.setLayout(null);
        pid.setText("PID");
        pid.setSize(60,50);
        pid.setLocation(20,20);
        this.add(pid);
        p.setSize(160,50);
        p.setLocation(90,20);
        this.add(p);

        yun.setText("运行时间");
        yun.setSize(60,50);
        yun.setLocation(20,80);
        this.add(yun);
        yun1.setSize(160,50);
        yun1.setLocation(90,80);
        this.add(yun1);

        prio.setText("优先数");
        prio.setSize(60,50);
        prio.setLocation(20,140);
        this.add(prio);
        pr.setSize(160,50);
        pr.setLocation(90,140);
        pr.addItem("7");
        pr.addItem("6");
        pr.addItem("5");
        pr.addItem("4");
        pr.addItem("3");
        pr.addItem("2");
        pr.addItem("1");
        this.add(pr);

        nei.setText("内存");
        nei.setSize(60,50);
        nei.setLocation(20,200);
        this.add(nei);
        neii.setSize(160,50);
        neii.setLocation(90,200);
        this.add(neii);

        qian.setText("前驱");
        qian.setSize(60,50);
        qian.setLocation(20,260);
        this.add(qian);
        qi.setSize(160,50);
        qi.setLocation(90,260);
        this.add(qi);

        ho.setText("后继");
        ho.setSize(60,50);
        ho.setLocation(20,320);
        this.add(ho);
        hoji.setSize(160,50);
        hoji.setLocation(90,320);
        this.add(hoji);

        duilie.setText("队列");
        duilie.setSize(60,50);
        duilie.setLocation(20,380);
        this.add(duilie);
        dui.setSize(160,50);
        dui.setLocation(90,380);
        dui.addItem("就绪队列");
        dui.addItem("后备队列");
        this.add(dui);

        jb.setSize(60,40);
        jb.setLocation(140,450);
        jb.setText("确认");
        jb.addActionListener(this);
        this.add(jb);
        this.setSize(380,550);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String s1=p.getText();
        int pid=Integer.valueOf(s1).intValue();
       /* System.out.println(pid);*/

        String s2=yun1.getText();
        int time=Integer.valueOf(s2).intValue();
        /*System.out.println(time);*/

        String s3=pr.getSelectedItem().toString();
        int prio=Integer.valueOf(s3).intValue();
       /* System.out.println(prio);*/

        String s4=neii.getText();
        int nei=Integer.valueOf(s4).intValue();
       /* System.out.println(nei);*/

        String s5=qi.getText();
        int qq=Integer.valueOf(s5).intValue();
        /*System.out.println(qq);*/

        String s6=hoji.getText();
        int hj=Integer.valueOf(s6).intValue();
       /* System.out.println(hj);*/

        String s7=dui.getSelectedItem().toString();
        PCB m=new PCB(pid,time,prio,qq,hj,nei);

        if(s7.equals("就绪队列"))
        {
            l.add(m);
            Collections.sort(l);
            /*System.out.println("yes");*/
        }
        else {
            l1.add(m);
            Collections.sort(l1);
           /* System.out.println("no");*/
        }
    }
}
