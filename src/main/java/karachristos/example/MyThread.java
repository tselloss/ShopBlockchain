package karachristos.example;

import java.util.concurrent.LinkedBlockingQueue;

public class MyThread implements Executor{
    static int numOfThreads;
    static int currentNumOfThreads;
    Execution e;

    static LinkedBlockingQueue<Runnable> linkedBlockingQueue;


    public MyThread(int numOfThreads)
    {
        this.numOfThreads=numOfThreads;
        currentNumOfThreads=0;
        linkedBlockingQueue=new LinkedBlockingQueue<Runnable>();
        e=new Execution();
    }

    @Override
    public void execute(Runnable runnable) {
        linkedBlockingQueue.add(runnable);
        e.executeMyMethod();
    }
}
