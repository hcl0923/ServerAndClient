package com.yc;

import java.util.Vector;

/**
 * @program: bigdata
 * @description:线程池管理器
 * 1.初始化线程池容器
 * 2.执行任务
 * @author: 作者
 * @create: 2021-01-20 19:55
 */
public class ThreadPoolManager {
    //线程池容器
    private Vector<SimpleThread> vector;
    //最多线程数
    private int maxThread;
    private static int coreCounts;
    static{
        coreCounts=Runtime.getRuntime().availableProcessors();
        System.out.println("系统核数："+coreCounts);
        System.out.println("线程池开始初始化");
    }
    public ThreadPoolManager(){
        this(coreCounts*2);
        //1.获取系统核数

    }

    public ThreadPoolManager(int threadCount){
        vector=new Vector<SimpleThread>();
        //2.根据上面的核数  创建 Thread对象，存到vector中
        for(int i=0;i<threadCount;i++){
            SimpleThread st=new SimpleThread();
            //st.setName("线程"+(i+1));
            st.setDaemon(true);
            vector.add(st);
            st.start();//线程组
        }
        //3.启动  SimpleThread对象 start()  ->进入就绪 ->jvm来调用 ->run()
        //注意点：新线程要进入wait()
    }
    public void Process(Taskable task){
        //1.task不能为空
        if(task==null){
            return;
        }
        //2.（循环）这个vector，取出一个线程（随机/hash/weight）
        SimpleThread st=getFreeSimpleThread();
        //3.将task绑定到SimpleThread线程中
        st.setTask(task);
        //4.设置这个SimpleThread的状态为运行态
        st.setRunningFlag(true);


    }
    /*
    循环/随机/hash/weight获取一个空闲的thread
    * */
    private SimpleThread getFreeSimpleThread(){
        int j=0;
        for(int i=0;i<vector.size();i++){
            SimpleThread stt=vector.get(i);
            j++;
            if(stt.isRunning()==false){
                return stt;
            }
        }
        System.out.println("线程池没有空线程，扩容："+coreCounts+"个新线程");
        //线程数不够，产生新的线程，存到vector中
        for(int i=0;i<coreCounts;i++){
            SimpleThread st=new SimpleThread();
            //st.setName("线程"+(i+1));
            System.out.println(i+1);
            st.setDaemon(true);
            vector.add(st);
            st.start();//线程组
        }
        //return getFreeSimpleThread();//递归方案
        return vector.get(j);
    }
}
