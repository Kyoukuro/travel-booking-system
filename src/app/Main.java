package app;

public class Main {
    public static void main(String[] args) {
        TravelApp app = new TravelApp();
        try {
            app.run();
        } finally {
            app.shutdown();
        }
    }
}
