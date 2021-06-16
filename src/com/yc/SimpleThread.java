package com.yc;

/**
 * @program: bigdata
 * @description:
 * @author: 作者
 * @create: 2021-01-20 19:56
 */
public class SimpleThread extends Thread{
    //private int count;//使用权重1 2 3 4 5 6 7 ->1/1 1/2 1/3 1/4

    private Taskable task;//任务
    private boolean runningFlag;//此线程的运行状态


    public SimpleThread(){
        this.runningFlag=false;
        System.out.println("线程："+this.getName()+"实例化完成，进入创建态。。。");
    }
    //获取线程运行状态
    public boolean isRunning(){
        return runningFlag;
    }

    public synchronized void setRunningFlag(boolean flag){
        this.runningFlag=flag;
        if(this.runningFlag) {
            this.notify();
            System.out.println(this.getName()+"进入active");
        }
    }
    public void setTask(Taskable task){
        this.task=task;//绑定任务给当前线程
    }

    @Override
    public synchronized void run() {//jvm调度到run 运行态
        while(true){//设置为true是为了让线程池中的线程不会结束，销毁
            if(runningFlag==false){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                if(this.task!=null){
                    this.task.doTask();//执行任务，直到任务完成
                    setRunningFlag(false);//任务完成后，将运行态改为false
                    System.out.println(Thread.currentThread().getName()+"进入wait");
                    //其实任务完成后，最终的目标都是将当前的线程设置成wait。
                }
            }
        }
    }
}
