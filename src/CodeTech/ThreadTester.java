package CodeTech;

public class ThreadTester {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("main thread Starting");
//        Thread thread = new Thread1("thread");
//        thread.start();

//        Thread thread2 = new Thread(new Thread2(),"thread2");
//        thread2.start();
//        Thread thread2 = new Thread(()->{
//            for (int i= 0;i<5;i++) {
//                System.out.println("Thread2 " + Thread.currentThread() + " " + i);
//            }
//        },"thread2");
//        thread2.start();
//        System.out.println("main thread existing");


        /**Thread.join concepts**/
//        Thread threads = new Thread(()->{
//            System.out.println(Thread.currentThread());
//        }, "our threads");
//        threads.start();
//        try {
//            threads.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("main thread Ending");


        /**Deadlock concept**/
        String lock1 = "hello";
        String lock2 = "Hi";

        Thread  thread1 = new Thread(() -> {
            synchronized (lock1){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){
                    System.out.println("lock acquired");
                }
            }
        }, "our thread1");
        Thread  thread2 = new Thread(() -> {
            synchronized (lock1){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){
                    System.out.println("lock acquired");
                }
            }
        }, "our thread1");
        thread1.start();
        thread2.start();
    }
}
