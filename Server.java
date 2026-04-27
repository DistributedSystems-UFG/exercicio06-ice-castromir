import com.zeroc.Ice.*;

public class Server {
    public static class PrinterI implements Demo.Printer {
        @Override
        public String printString(String s, Current current) {
            System.out.println(s);
            return s;
        }

        @Override
        public String upper(String s, Current current) {
            return s.toUpperCase();
        }

        @Override
        public int length(String s, Current current) {
            return s.length();
        }
    }

    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("SimpleAdapter", "default -p 5678");
            adapter.add(new PrinterI(), Util.stringToIdentity("SimplePrinter"));
            adapter.activate();
            communicator.waitForShutdown();
        }
    }
}
