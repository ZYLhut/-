import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class demo extends JFrame implements Runnable{
    int stare=0;
    ArrayList<PCB> list=new ArrayList<>();
    ArrayList<PCB> list1=new ArrayList<>();
    ArrayList<PCB> list2=new ArrayList<>();
    table_cata mulu=new table_cata(400);
    int flag=0;
    int flag2=0;
    int time_slice=7;
    private JPanel p1;
    private DefaultTableModel model;
    private JPanel p11;
    private JButton tb;
    private JButton CB;
    private JButton yB;
    private JButton guab;
    private JButton jeB;
    private JPanel p2;
    private JPanel p3;
    private JTable t1;
    private JTable t2;
    private JTable t3;
    private JTextField text;
    private JScrollPane jx;
    private JScrollPane hb;
    private JScrollPane gq;

    public demo() {
        this.setContentPane(new demo().p1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        CB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new democ(list,list1);
            }
        });
        tb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag=0;
                new democ(list,list1);
            }
        });
        yB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag=1;

            }
        });
        guab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag2=1;
            }
        });
        jeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PCB o=list2.get(0);
                list2.remove(0);
                Collections.sort(list2);
                list.add(o);
                Collections.sort(list);

                model.setColumnCount(5);
                model.setRowCount(list2.size());
                t2.setModel(new DefaultTableModel(list2.size(), 5));
                t2.setValueAt("pid", 0, 0);
                t2.setValueAt("优先数", 0, 1);
                t2.setValueAt("状态", 0, 2);
                t2.setValueAt("内存", 0, 3);
                t2.setValueAt("运行时间", 0, 4);
                for (int h = 1; h < list2.size(); h++) {
                    t2.setValueAt(list2.get(h - 1).pidshow(), h, 0);
                    t2.setValueAt(list2.get(h - 1).getPriority(), h, 1);
                    t2.setValueAt(list2.get(h - 1).getStatus(), h, 2);
                    t2.setValueAt(list2.get(h - 1).getNeicun(), h, 3);
                    t2.setValueAt(list2.get(h - 1).getTotal_time(), h, 4);
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void run() {
        while(true) {

            if(list.size()>0) {
                model.setColumnCount(5);
                model.setRowCount(list.size());
                t3.setModel(new DefaultTableModel(list.size(), 5));
                t3.setValueAt("pid", 0, 0);
                t3.setValueAt("优先数", 0, 1);
                t3.setValueAt("状态", 0, 2);
                t3.setValueAt("内存", 0, 3);
                t3.setValueAt("运行时间", 0, 4);
                for (int h = 1; h < list.size(); h++) {
                    t3.setValueAt(list.get(h - 1).pidshow(), h, 0);
                    t3.setValueAt(list.get(h - 1).getPriority(), h, 1);
                    t3.setValueAt(list.get(h - 1).getStatus(), h, 2);
                    t3.setValueAt(list.get(h - 1).getNeicun(), h, 3);
                    t3.setValueAt(list.get(h - 1).getTotal_time(), h, 4);
                }

                if(list1.size()>0)
                {
                    model.setColumnCount(5);
                    model.setRowCount(list1.size());
                    t1.setModel(new DefaultTableModel(list1.size(), 5));
                    t1.setValueAt("pid", 0, 0);
                    t1.setValueAt("优先数", 0, 1);
                    t1.setValueAt("状态", 0, 2);
                    t1.setValueAt("内存", 0, 3);
                    t1.setValueAt("运行时间", 0, 4);
                    for (int h = 1; h < list1.size(); h++) {
                        t1.setValueAt(list1.get(h - 1).pidshow(), h, 0);
                        t1.setValueAt(list1.get(h - 1).getPriority(), h, 1);
                        t1.setValueAt(list1.get(h - 1).getStatus(), h, 2);
                        t1.setValueAt(list1.get(h - 1).getNeicun(), h, 3);
                        t1.setValueAt(list1.get(h - 1).getTotal_time(), h, 4);
                    }
                }
            }
            if (flag == 1) {
                if (stare == 0) {
                    while(list.size()!=0)
                    {
                        stare=1;
                        PCB x=list.get(0);
                        {

                            model.setColumnCount(5);
                            model.setRowCount(list.size());
                            t3.setModel(new DefaultTableModel(list.size(), 5));
                            t3.setValueAt("pid",0,0);
                            t3.setValueAt("优先数",0,1);
                            t3.setValueAt("状态",0,2);
                            t3.setValueAt("内存",0,3);
                            t3.setValueAt("运行时间",0,4);
                            for(int h=1;h<list.size();h++) {
                                t3.setValueAt(list.get(h - 1).pidshow(), h, 0);
                                t3.setValueAt(list.get(h - 1).getPriority(), h, 1);
                                t3.setValueAt(list.get(h - 1).getStatus(), h, 2);
                                t3.setValueAt(list.get(h - 1).getNeicun(), h, 3);
                                t3.setValueAt(list.get(h - 1).getTotal_time(), h, 4);
                            }
                            t3.setValueAt(1,1,2);
                        }
                        mulu.divide(x.pidshow(),x.getNeicun());//首次适应分配内存
                        text.setText(mulu.out());
                        list.remove(0);
                        if(list.size()==1)//道数为1，添加进程
                        {
                            list.add(list1.get(0));
                            Collections.sort(list);
                            list1.remove(0);

                            model.setColumnCount(5);
                            model.setRowCount(list1.size());
                            t1.setModel(new DefaultTableModel(list1.size(), 5));
                            t1.setValueAt("pid", 0, 0);
                            t1.setValueAt("优先数", 0, 1);
                            t1.setValueAt("状态", 0, 2);
                            t1.setValueAt("内存", 0, 3);
                            t1.setValueAt("运行时间", 0, 4);
                            for (int h = 1; h < list1.size(); h++) {
                                t1.setValueAt(list1.get(h - 1).pidshow(), h, 0);
                                t1.setValueAt(list1.get(h - 1).getPriority(), h, 1);
                                t1.setValueAt(list1.get(h - 1).getStatus(), h, 2);
                                t1.setValueAt(list1.get(h - 1).getNeicun(), h, 3);
                                t1.setValueAt(list1.get(h - 1).getTotal_time(), h, 4);
                            }

                            model.setColumnCount(5);
                            model.setRowCount(list.size());
                            t3.setModel(new DefaultTableModel(list.size(), 5));
                            t3.setValueAt("pid", 0, 0);
                            t3.setValueAt("优先数", 0, 1);
                            t3.setValueAt("状态", 0, 2);
                            t3.setValueAt("内存", 0, 3);
                            t3.setValueAt("运行时间", 0, 4);
                            for (int h = 1; h < list.size(); h++) {
                                t3.setValueAt(list.get(h - 1).pidshow(), h, 0);
                                t3.setValueAt(list.get(h - 1).getPriority(), h, 1);
                                t3.setValueAt(list.get(h - 1).getStatus(), h, 2);
                                t3.setValueAt(list.get(h - 1).getNeicun(), h, 3);
                                t3.setValueAt(list.get(h - 1).getTotal_time(), h, 4);
                            }
                        }
                        //设置初值
                        int time=2;
                        while (time!=0&&flag2==0) {
                            time=x.yun(0);
                            list.add(x);
                            Collections.sort(list);
                            if(list.get(0).pidshow()!=x.pidshow())
                            {
                                break;
                            }
                            else {
                                list.remove(0);
                            }
                            //时间片到了，重新排序
                            if(time_slice==0)
                            {
                                list.add(x);
                                Collections.sort(list);
                                time_slice=7;
                                break;
                            }
                            time_slice--;
                            t3.setValueAt(x.getleft_time(),1,4);
                        }
                        if(flag2==1)//挂起
                        {
                            list2.add(x);
                            Collections.sort(list2);
                            model.setColumnCount(5);
                            model.setRowCount(list2.size());
                            t2.setModel(new DefaultTableModel(list2.size(), 5));
                            t2.setValueAt("pid", 0, 0);
                            t2.setValueAt("优先数", 0, 1);
                            t2.setValueAt("状态", 0, 2);
                            t2.setValueAt("内存", 0, 3);
                            t2.setValueAt("运行时间", 0, 4);
                            for (int h = 1; h < list2.size(); h++) {
                                t2.setValueAt(list2.get(h - 1).pidshow(), h, 0);
                                t2.setValueAt(list2.get(h - 1).getPriority(), h, 1);
                                t2.setValueAt(list2.get(h - 1).getStatus(), h, 2);
                                t2.setValueAt(list2.get(h - 1).getNeicun(), h, 3);
                                t2.setValueAt(list2.get(h - 1).getTotal_time(), h, 4);
                            }
                            flag2=0;
                        }

                        mulu.merge(x.pidshow(),x.getNeicun());
                        text.setText(mulu.out());
                        stare=0;
                    }
                }
            }
        }
    }
}
