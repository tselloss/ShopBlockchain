package karachristos.example;

import java.util.concurrent.ExecutionException;

public class Execution implements Runnable{
    public void executeMyMethod()
    {
        if (MyThread.currentNumOfThreads<MyThread.numOfThreads)
        {
            Thread thread = new Thread(new Execution());
            thread.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (MyThread.linkedBlockingQueue.size() != 0) {
                    MyThread.linkedBlockingQueue.poll().run();
                }
            } catch (Exception ex) {
                Thread t = Thread.currentThread();
                t.getUncaughtExceptionHandler().uncaughtException(t, ex);
            }

        }
    }
}
