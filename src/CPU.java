public class CPU {
    private int id;
    private int pid;
    private int s;
    public CPU(int id)
    {
        this.id=id;
        s=0;
        pid=-1;
    }

    public int getS() {
        return s;
    }

    public int getPid() {
        return pid;
    }

    public int getId() {
        return id;
    }

    public void add()
    {
        s++;
    }
    public void de()
    {
        s--;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
