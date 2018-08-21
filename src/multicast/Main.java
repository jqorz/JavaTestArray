package multicast;


public class Main {

    public static void main(String[] args) {
//        System.out.print("" + compare("133", "13344", 0));
        MulticastThread multicastThread = new MulticastThread();
        multicastThread.close();
        while (true) {
            multicastThread.parseDatagramPacket();
        }
    }


}
