import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class democ {
    private JPanel jp1;
    private JTextField t6;
    private JComboBox t3;
    private JTextField t5;
    private JTextField t1;
    private JTextField t4;
    private JButton qb;
    private JButton db;
    private JTextField t2;
    private JComboBox t7;

    public democ(ArrayList<PCB> list,ArrayList<PCB> list1) {
        qb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1=t1.getText();
                int pid=Integer.valueOf(s1).intValue();
                String s2=t2.getText();
                int time=Integer.valueOf(s2).intValue();
                String s3=t3.getSelectedItem().toString();
                int prio=Integer.valueOf(s3).intValue();
                String s4=t4.getText();
                int nei=Integer.valueOf(s4).intValue();
                String s5=t5.getText();
                int qq=Integer.valueOf(s5).intValue();
                String s6=t6.getText();
                int hj=Integer.valueOf(s6).intValue();
                String s7=t7.getSelectedItem().toString();
                PCB m=new PCB(pid,time,prio,nei,qq,hj);
                if(s7.equals("就绪队列"))
                {
                    list.add(m);
                    Collections.sort(list);
                }
                else {
                    list1.add(m);
                    Collections.sort(list1);
                }
            }
        });
    }
}
