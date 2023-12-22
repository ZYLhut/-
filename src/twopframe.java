import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class twopframe extends JFrame implements Runnable,ActionListener {
    int stare=0;
    int id=0;
    int m=0;
    ArrayList<PCB> list=new ArrayList<>();//就绪
    ArrayList<PCB> list1=new ArrayList<>();//后备
    ArrayList<PCB> list2=new ArrayList<>();//挂起
    ArrayList<CPU> c=new ArrayList<>();
    table_cata mulu=new table_cata(400);

    int flag=0;
    int flag2=0;
    int time_slice=7;
    private JLabel jl=new JLabel();
    private JLabel jl1=new JLabel();
    private JLabel jl2=new JLabel();
    private JPanel p1=new JPanel();
    private JPanel p11=new JPanel();
    private JButton tb=new JButton();
    private JButton CB=new JButton();
    private JButton yB=new JButton();
    private JButton guab=new JButton();
    private JButton jeB=new JButton();
    private JPanel p2=new JPanel();
    private JPanel p3=new JPanel();
    private JTable t1=new JTable();
    private JTable t2=new JTable();
    private JTable t3=new JTable();
    private JTextField text=new JTextField();
    private JPanel jx=new JPanel();
    private JPanel hb=new JPanel();
    private JPanel gq=new JPanel();
    private DefaultTableModel model = new DefaultTableModel();

    public twopframe() {
        CPU A=new CPU(0);
        CPU B=new CPU(1);
        c.add(A);
        c.add(B);
        p1.setLayout(new BorderLayout());
        tb.setText("添加");
        tb.setSize(80, 50);
        tb.addActionListener(this);

        CB.setText("创建");
        CB.setSize(80, 50);
        CB.addActionListener(this);

        yB.setText("运行");
        yB.setSize(80, 50);
        yB.addActionListener(this);

        guab.setText("挂起");
        guab.setSize(80, 50);
        guab.addActionListener(this);

        jeB.setText("解挂");
        jeB.setSize(80, 50);
        jeB.addActionListener(this);

        p11.add(CB);
        p11.add(tb);
        p11.add(yB);
        p11.add(guab);
        p11.add(jeB);
        p1.add(p11, BorderLayout.NORTH);

        text.setSize(1200, 50);
        text.setLocation(20,450);
        text.setText("你好！");
        p3.setLayout(new GridLayout(1,1,10,10));
        p3.add(text);
        p2.setLayout(new GridLayout(1, 3, 10, 10));
        hb.setLayout(new BorderLayout());
        jx.setLayout(new BorderLayout());
        gq.setLayout(new BorderLayout());
        jl.setText("后备队列");
        jl1.setText("就绪队列");
        jl2.setText("挂起队列");
        hb.add(jl,BorderLayout.NORTH);
        jx.add(jl1,BorderLayout.NORTH);
        gq.add(jl2,BorderLayout.NORTH);

        hb.add(t1,BorderLayout.CENTER);
        jx.add(t3,BorderLayout.CENTER);
        gq.add(t2,BorderLayout.CENTER);

        p2.add(hb);
        p2.add(jx);
        p2.add(gq);

        p1.add(p2, BorderLayout.CENTER);

        p1.add(p3, BorderLayout.SOUTH);
        this.setSize(1200, 600);
        this.add(p1);
        this.setVisible(true);

        new Thread(this).start();
        id++;
        new Thread(this).start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == CB) {
            new frame2(list,list1);
        }
        else if (e.getSource() == tb) {
            //控制程序运行
            flag=0;
            new frame2(list,list1);
        }
        else if(e.getSource()==yB)
        {
            flag=1;
        }
        else if(e.getSource()==guab)
        {
            flag2=1;
        }
        else{
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
            for (int h = 1; h < list2.size()+1; h++) {
                t2.setValueAt(list2.get(h - 1).pidshow(), h, 0);
                t2.setValueAt(list2.get(h - 1).getPriority(), h, 1);
                t2.setValueAt(list2.get(h - 1).getStatus(), h, 2);
                t2.setValueAt(list2.get(h - 1).getNeicun(), h, 3);
                t2.setValueAt(list2.get(h - 1).getleft_time(), h, 4);
            }

            model.setColumnCount(6);
            model.setRowCount(list.size()+1);
            t3.setModel(new DefaultTableModel(list.size()+1, 6));
            t3.setValueAt("pid", 0, 0);
            t3.setValueAt("优先数", 0, 1);
            t3.setValueAt("状态", 0, 2);
            t3.setValueAt("内存", 0, 3);
            t3.setValueAt("运行时间", 0, 4);
            t3.setValueAt("处理机", 0, 5);
            for (int h = 1; h < list.size()+1; h++) {
                t3.setValueAt(list.get(h - 1).pidshow(), h, 0);
                t3.setValueAt(list.get(h - 1).getPriority(), h, 1);
                t3.setValueAt(list.get(h - 1).getStatus(), h, 2);
                t3.setValueAt(list.get(h - 1).getNeicun(), h, 3);
                t3.setValueAt(list.get(h - 1).getleft_time(), h, 4);
                t3.setValueAt("-", h, 5);
            }
            hb.add(t1,BorderLayout.CENTER);
            jx.add(t3,BorderLayout.CENTER);
            gq.add(t2,BorderLayout.CENTER);

        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void run() {
        int cid;
        while (true) {
            if (m == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (list.size() > 0) {

                    model.setColumnCount(6);
                    model.setRowCount(list.size() + 1);
                    t3.setModel(new DefaultTableModel(list.size() + 1, 6));
                    t3.setValueAt("pid", 0, 0);
                    t3.setValueAt("优先数", 0, 1);
                    t3.setValueAt("状态", 0, 2);
                    t3.setValueAt("内存", 0, 3);
                    t3.setValueAt("运行时间", 0, 4);
                    t3.setValueAt("处理机", 0, 5);
                    for (int h = 1; h < list.size() + 1; h++) {
                        t3.setValueAt(list.get(h - 1).pidshow(), h, 0);
                        t3.setValueAt(list.get(h - 1).getPriority(), h, 1);
                        t3.setValueAt(list.get(h - 1).getStatus(), h, 2);
                        t3.setValueAt(list.get(h - 1).getNeicun(), h, 3);
                        t3.setValueAt(list.get(h - 1).getleft_time(), h, 4);
                        t3.setValueAt('-', h, 5);
                    }
                    hb.add(t1, BorderLayout.CENTER);
                    jx.add(t3, BorderLayout.CENTER);
                    gq.add(t2, BorderLayout.CENTER);


                    if (list1.size() > 0) {
                        model.setColumnCount(5);
                        model.setRowCount(list1.size() + 1);
                        t1.setModel(new DefaultTableModel(list1.size() + 1, 5));
                        t1.setValueAt("pid", 0, 0);
                        t1.setValueAt("优先数", 0, 1);
                        t1.setValueAt("状态", 0, 2);
                        t1.setValueAt("内存", 0, 3);
                        t1.setValueAt("运行时间", 0, 4);
                        for (int h = 1; h < list1.size() + 1; h++) {
                            t1.setValueAt(list1.get(h - 1).pidshow(), h, 0);
                            t1.setValueAt(list1.get(h - 1).getPriority(), h, 1);
                            t1.setValueAt(list1.get(h - 1).getStatus(), h, 2);
                            t1.setValueAt(list1.get(h - 1).getNeicun(), h, 3);
                            t1.setValueAt(list1.get(h - 1).getTotal_time(), h, 4);
                        }
                        hb.add(t1, BorderLayout.CENTER);
                        jx.add(t3, BorderLayout.CENTER);
                        gq.add(t2, BorderLayout.CENTER);
                    }
                }
                if (c.get(0).getS() == 0)
                    cid = 0;
                else
                    cid = 1;
                /*System.out.println("into");*/
                while (list.size() != 0 && flag == 1) {

                    //道数为1，添加进程
                    if (list.size() == 1) {
                        if (list1.size() == 0) {
                            break;
                        }
                        list.add(list1.get(0));
                        Collections.sort(list);
                        list1.remove(0);

                        model.setColumnCount(5);
                        model.setRowCount(list1.size() + 1);
                        t1.setModel(new DefaultTableModel(list1.size() + 1, 5));
                        t1.setValueAt("pid", 0, 0);
                        t1.setValueAt("优先数", 0, 1);
                        t1.setValueAt("状态", 0, 2);
                        t1.setValueAt("内存", 0, 3);
                        t1.setValueAt("运行时间", 0, 4);
                        for (int h = 1; h < list1.size() + 1; h++) {
                            t1.setValueAt(list1.get(h - 1).pidshow(), h, 0);
                            t1.setValueAt(list1.get(h - 1).getPriority(), h, 1);
                            t1.setValueAt(list1.get(h - 1).getStatus(), h, 2);
                            t1.setValueAt(list1.get(h - 1).getNeicun(), h, 3);
                            t1.setValueAt(list1.get(h - 1).getleft_time(), h, 4);
                        }

                        model.setColumnCount(6);
                        model.setRowCount(list.size() + 1);
                        t3.setModel(new DefaultTableModel(list.size() + 1, 6));
                        t3.setValueAt("pid", 0, 0);
                        t3.setValueAt("优先数", 0, 1);
                        t3.setValueAt("状态", 0, 2);
                        t3.setValueAt("内存", 0, 3);
                        t3.setValueAt("运行时间", 0, 4);
                        t3.setValueAt("处理机", 0, 5);
                        for (int h = 1; h < list.size() + 1; h++) {
                            t3.setValueAt(list.get(h - 1).pidshow(), h, 0);
                            t3.setValueAt(list.get(h - 1).getPriority(), h, 1);
                            t3.setValueAt(list.get(h - 1).getStatus(), h, 2);
                            t3.setValueAt(list.get(h - 1).getNeicun(), h, 3);
                            t3.setValueAt(list.get(h - 1).getleft_time(), h, 4);
                            t3.setValueAt("-", h, 5);
                        }
                        hb.add(t1, BorderLayout.CENTER);
                        jx.add(t3, BorderLayout.CENTER);
                        gq.add(t2, BorderLayout.CENTER);
                    }


                    c.get(cid).add();
                    PCB x = list.get(0);
                    if(x.getPh()!=-1||x.getPq()!=-1)
                    {
                        m=1;
                    }
                    else
                        m=0;
                    c.get(cid).setPid(x.pidshow());
                    {

                        model.setColumnCount(6);
                        model.setRowCount(list.size() + 1);
                        t3.setModel(new DefaultTableModel(list.size() + 1, 6));
                        t3.setValueAt("pid", 0, 0);
                        t3.setValueAt("优先数", 0, 1);
                        t3.setValueAt("状态", 0, 2);
                        t3.setValueAt("内存", 0, 3);
                        t3.setValueAt("运行时间", 0, 4);
                        t3.setValueAt("处理机", 0, 5);
                        for (int h = 1; h < list.size() + 1; h++) {
                            t3.setValueAt(list.get(h - 1).pidshow(), h, 0);
                            t3.setValueAt(list.get(h - 1).getPriority(), h, 1);
                            t3.setValueAt(list.get(h - 1).getStatus(), h, 2);
                            t3.setValueAt(list.get(h - 1).getNeicun(), h, 3);
                            t3.setValueAt(list.get(h - 1).getleft_time(), h, 4);
                            t3.setValueAt("-", h, 5);
                        }
                        t3.setValueAt("" + cid, 1, 5);
                        t3.setValueAt(1, 1, 2);
                        hb.add(t1, BorderLayout.CENTER);
                        jx.add(t3, BorderLayout.CENTER);
                        gq.add(t2, BorderLayout.CENTER);
                    }
                    mulu.divide(x.pidshow(), x.getNeicun());//首次适应分配内存
                    text.setText(mulu.out());


                    //设置初值
                    int time = 2;
                    while (time > 0 && flag2 == 0 && time_slice > 0) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        list.remove(0);
                        list.add(0, x);

                        {
                            model.setColumnCount(6);
                            model.setRowCount(list.size() + 1);
                            t3.setModel(new DefaultTableModel(list.size() + 1, 6));
                            t3.setValueAt("pid", 0, 0);
                            t3.setValueAt("优先数", 0, 1);
                            t3.setValueAt("状态", 0, 2);
                            t3.setValueAt("内存", 0, 3);
                            t3.setValueAt("运行时间", 0, 4);
                            t3.setValueAt("处理机", 0, 5);
                            for (int h = 1; h < list.size() + 1; h++) {
                                t3.setValueAt(list.get(h - 1).pidshow(), h, 0);
                                t3.setValueAt(list.get(h - 1).getPriority(), h, 1);
                                t3.setValueAt(list.get(h - 1).getStatus(), h, 2);
                                t3.setValueAt(list.get(h - 1).getNeicun(), h, 3);
                                t3.setValueAt(list.get(h - 1).getleft_time(), h, 4);
                                if (list.get(h - 1).pidshow() == c.get(0).getPid()) {
                                    t3.setValueAt("" + 0, h, 5);
                                } else if (list.get(h - 1).pidshow() == c.get(1).getPid()) {
                                    t3.setValueAt("" + 1, h, 5);
                                }
                                t3.setValueAt("-", h, 5);
                            }
                        }
                        Collections.sort(list);
                        if (list.get(0).pidshow() != x.pidshow()) {
                            break;
                        }
                        //对表进行更新
                        //时间片到了，重新排序
                        time = x.yun(cid);
                        time_slice--;
                        t3.setValueAt(x.getPriority(), 1, 1);
                        t3.setValueAt(x.getleft_time(), 1, 4);
                        hb.add(t1, BorderLayout.CENTER);
                        jx.add(t3, BorderLayout.CENTER);
                        gq.add(t2, BorderLayout.CENTER);
                    }

                    if (time == 0) {
                        list.remove(x);
                    }

                    if (time_slice == 0) {
                        Collections.sort(list);
                        time_slice = 7;
                    }

                    if (flag2 == 1)//挂起
                    {
                        list.remove(x);
                        Collections.sort(list);
                        list2.add(x);
                        Collections.sort(list2);
                        model.setColumnCount(5);
                        model.setRowCount(list2.size() + 1);
                        t2.setModel(new DefaultTableModel(list2.size() + 1, 5));
                        t2.setValueAt("pid", 0, 0);
                        t2.setValueAt("优先数", 0, 1);
                        t2.setValueAt("状态", 0, 2);
                        t2.setValueAt("内存", 0, 3);
                        t2.setValueAt("运行时间", 0, 4);
                        for (int h = 1; h < list2.size() + 1; h++) {
                            t2.setValueAt(list2.get(h - 1).pidshow(), h, 0);
                            t2.setValueAt(list2.get(h - 1).getPriority(), h, 1);
                            t2.setValueAt(list2.get(h - 1).getStatus(), h, 2);
                            t2.setValueAt(list2.get(h - 1).getNeicun(), h, 3);
                            t2.setValueAt(list2.get(h - 1).getleft_time(), h, 4);
                        }
                        hb.add(t1, BorderLayout.CENTER);
                        jx.add(t3, BorderLayout.CENTER);
                        gq.add(t2, BorderLayout.CENTER);

                        model.setColumnCount(6);
                        model.setRowCount(list.size() + 1);
                        t3.setModel(new DefaultTableModel(list.size() + 1, 6));
                        t3.setValueAt("pid", 0, 0);
                        t3.setValueAt("优先数", 0, 1);
                        t3.setValueAt("状态", 0, 2);
                        t3.setValueAt("内存", 0, 3);
                        t3.setValueAt("运行时间", 0, 4);
                        t3.setValueAt("处理机", 0, 5);
                        for (int h = 1; h < list.size() + 1; h++) {
                            t3.setValueAt(list.get(h - 1).pidshow(), h, 0);
                            t3.setValueAt(list.get(h - 1).getPriority(), h, 1);
                            t3.setValueAt(list.get(h - 1).getStatus(), h, 2);
                            t3.setValueAt(list.get(h - 1).getNeicun(), h, 3);
                            t3.setValueAt(list.get(h - 1).getleft_time(), h, 4);
                            t3.setValueAt("-", h, 5);
                        }
                        flag2 = 0;
                    }


                    mulu.merge(x.pidshow(), x.getNeicun());
                    text.setText(mulu.out());
                    c.get(cid).setPid(-1);
                    c.get(cid).de();
                }
            }
        }
    }
}


