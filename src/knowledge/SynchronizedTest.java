package knowledge;

 public  class SynchronizedTest {

//    public synchronized void synWay(){
//        System.out.println(Thread.currentThread().getName());
//    }

    public void test(){
        synchronized (Reflection.class){
            Reflection.getName();
        }
    }

}
