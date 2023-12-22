import java.util.ArrayList;

//表目，记录总块数
public class table_cata {
    int total_store=400;//总内存为400
    int number;
    ArrayList<table> t = new ArrayList<>();
    public table_cata(int total_store)
    {
        number=1;
        t.add(new table(0,total_store-1,0));
    }
    public void divide(int pid,int how_big)
    {
        number++;
        int m=t.indexOf(new table(how_big,-1));//找到索引
        table con=t.get(m);
       int end= con.getEnd();//得到end，方便分割
        int end_after=con.del(how_big,pid);
        table con1=new table(end_after+1,end,0);
        t.remove(m);
        t.add(m,con);
        t.add(m+1,con1);
    }
    public void merge(int pid,int how_big)
    {
        int m=t.indexOf(new table(how_big,pid));
        table con=t.get(m);
        con.increa();
        if(m>=1) {
            if (t.get(m - 1).getPid() == -1 && t.get(m + 1).getPid() == -1) {
                number = number - 2;
                int i = t.get(m - 1).getBegin();
                int j = t.get(m + 1).getEnd();
                table n = new table(i, j, 0);
                t.remove(m - 1);
                t.remove(m - 1);
                t.remove(m - 1);
                t.add(m - 1, n);
            } else if (t.get(m - 1).getPid() == -1) {
                number = number - 1;
                int i = t.get(m - 1).getBegin();
                int j = t.get(m).getEnd();
                table n = new table(i, j, 0);
                t.remove(m - 1);
                t.remove(m - 1);
                t.add(m - 1, n);
            } else {
                number = number - 1;
                int i = t.get(m).getBegin();
                int j = t.get(m + 1).getEnd();
                table n = new table(i, j, 0);
                t.remove(m );
                t.remove(m);
                t.add(m , n);
            }
        }
        else{
            if(t.get(m+1).getPid()==-1)
            {
                number = number - 1;
                int j = t.get(m + 1).getEnd();
                table n = new table(0, j, 0);
                t.remove(m );
                t.remove(m );
                t.add(m , n);
            }
        }

    }
    public String out()
    {
        String s=null;
        s=String.valueOf(t.get(0).getBegin())+" "+
                String.valueOf(t.get(0).getPid())+ " "+String.valueOf(t.get(0).getEnd());
        for(int i=1;i<number;i++)
        {
            s=s+" "+String.valueOf(t.get(i).getBegin())+" "+
               String.valueOf(t.get(i).getPid())+ " "+String.valueOf(t.get(i).getEnd());
            System.out.println(s);
        }
        return s;
    }
}
