public class testMultithreading {
    public static void main(String args[]) {
        for(int i = 0; i < 3; ++i){
            Multithreading T = new Multithreading("Client");
            T.start();
        }
    }
}
