//单块空间
public class table {
   private int begin;//起始
   private int end;//终止
   private int length;//长度
   private int sta;//状态，0为空闲，1为被占用
    private int pid;
    public table(int b,int e,int s)
    {
        begin=b;
        end=e;
        length=end-begin+1;
        sta=s;
        pid=-1;
    }
    public table(int l,int pid)
    {
        length=l;
        this.pid=pid;
    }

    public int getEnd()
    {
        return end;
    }

    public int getBegin() {
        return begin;
    }
   public int getPid()
   {
       return pid;
   }
    public int getLength() {
        return length;
    }

    //被占用
    public int del(int l,int p)
    {
        length-=l;
        end=begin+l-1;
        sta=1;
        pid=p;
        return end;
    }
    public int increa()
    {
        pid=-1;
        sta=0;
        return end;
    }

    public boolean equals(Object o) {
        table w = (table)o;//在equals函数中强转类型
        if((w.length <= this.length&&w.pid==this.pid)||w.pid==this.pid)return true;
        else return false;
    }

}
