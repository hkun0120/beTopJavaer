package top.oo;

/**
 * @description: java 内存可见性的问题
 * 验证结果：这里并没有加任何synchronized关键字，但是执行结果只有6。按照网上的说法，这里面编译器
 * 做了优化。但是为了确保线程安全，建议把synchronized关键字必须加上。
 * @author: H.K
 * @program: beTopJavaer
 * @create: 2020-02-22 01:07
 */
public class Visiblity {
    // 共享变量
    private  int a=0;
    private  int b=2;
    private  int c;
    private boolean ready=false;
    public static void main(String[] args) {
        Visiblity visiblity = new Visiblity();
//        while (true) {
            visiblity.new ReadWriteThread(true).start();
            visiblity.new ReadWriteThread(false).start();
//        }
//        new Visiblity().Sum();

    }

    private void Sum(){
        for (int i=0;i<60;i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    c = a + b;
                    System.out.println("c:" + c);
                }
            });
            thread.run();
            System.out.println(thread.getName());
        }
    }
    private void write(){
        ready = true;
        try {
            Thread.sleep(0);
        }catch (Exception e){
            e.printStackTrace();
        }

        a = 3;
    }
    private void read(){
        if(ready){
            c=a*2;
        }
        System.out.println("result:"+c);
    }
    // 内部线程类
    class ReadWriteThread extends Thread{
        boolean flag;
        public ReadWriteThread(boolean flag){
            this.flag=flag;
        }
        @Override
        public void run() {
            if(flag){
                write();
            }else {
                read();
            }
        }
    }

}
