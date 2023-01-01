package org.example.ch4.counter;

public class RaceConditionDemo {
    public static void main(String[] args) {
        /*
        * 멀티 스레드 환경에서 하나의 객체를 공유하게 된다면 -> 원하지 않는 결과가 나올 수 있음
        * -> Thread Safe 하지 않음
        * 여러 스레드가 동시에 하나의 자원에 접근하기 위해 경쟁하는 상태 -> RaceCondition
        * */
        Counter counter = new Counter();
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
