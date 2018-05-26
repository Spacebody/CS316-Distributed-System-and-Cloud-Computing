public class Multithreading extends Thread {
    private Thread t;
    private String threadName;

    Multithreading(String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }

    public synchronized void run() {
        System.out.println("Running " +  threadName );
        LoginClient client1 = new LoginClient();
        client1.LoginClient();
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public synchronized void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}