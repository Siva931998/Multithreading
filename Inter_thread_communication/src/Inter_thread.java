import com.sun.javaws.IconUtil;

class Q {
    int num;
    boolean flag = false;
    public synchronized void set(int num){

        while (flag){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("set " + num);
        this.num = num;
        flag = true;
        notify();
    }
    public synchronized void get(){

        while (!flag){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("get " + num);
        flag = false;
        notify();
    }
}

class Producer implements Runnable{

    Q q;

    public Producer(Q q) {
        this.q = q;
        Thread thread = new Thread(this,"Producer");
        thread.start();
    }

    @Override
    public void run() {
        int i = 0;
        while(true){
            q.set(i++);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class Consumer implements Runnable{

    Q q;

    public Consumer(Q q) {
        this.q = q;
        Thread thread = new Thread(this,"Consumer");
        thread.start();
    }

    @Override
    public void run() {
        while (true){
            q.get();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Inter_thread {

    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
    }
}
