public class PCB implements Comparable<PCB>{
    private int pid;
    private int total_time;
    private int left_time;//剩余运行时间
    private int priority;//优先数
    private  int status;//1为正在运行，0为在就绪队列中,-1为已运行结束
    private int pq;//记录前驱，-1为无
    private int ph;//记录后继，-1为无
    private int neicun;//所占内存
    public PCB(int pid1,int time,int pr,int pid2,int pid3,int nei)
    {
        pid=pid1;
        total_time=time;
        left_time=time;
        priority=pr;
        status=0;
        pq=pid2;
        ph=pid3;
        neicun=nei;
    }
    public PCB(int pid1)
    {
        pid=pid1;
    }
    public int pidshow()//返回pid
    {
        return pid;
    }
    public int getStatus(){
        return status;
    }
    public int getPriority()
    {
        return priority;
    }
    public int getleft_time()
    {
        return left_time;
    }

    public int getTotal_time() {
        return total_time;
    }

    public int getPh() {
        return ph;
    }

    public int getPq() {
        return pq;
    }

    public int getNeicun()
    {
        return neicun;
    }
    //进入运行态,返回剩余时间
    public int yun(int id)
    {
        priority++;
        left_time--;
        return left_time;
    }
    @Override
    public String toString() {
        return "pid:"+pid+' '+"total_time:"+total_time+' '+"priority:"+priority+' '
                +"status:"+status+' '+"pq:"+pq+' '+"ph:"+ph+"";
    }

//按优先级排序
    @Override
    public int compareTo(PCB o) {
        if((this.pq!=o.pid)&&(this.ph!=o.pid)) {
            return (int) (this.priority - o.priority);
        }
        else if (this.pq==o.pid)
        {
            return 1;
        }
        else if(this.ph==o.pid){
            return -1;
        }
        return 0;
    }
    public boolean equals(Object o) {
        PCB w = (PCB)o;//在equals函数中强转类型
        if(w.pid == this.pid)return true;
        else return false;
    }
}
