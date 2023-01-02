package karachristos.example;

public class MyExecutors {
    //This class define the number of threads
    int numOfThreads;

    static Executor myThreadPool(int numOfThreads)
    {
        return new MyThread(numOfThreads);
    }
}
