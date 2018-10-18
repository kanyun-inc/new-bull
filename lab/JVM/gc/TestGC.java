import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class TestGC {

    public static Set<WeakReference<BigObject>> objs = new HashSet<>();

    public static class BigObject {
        private int ordinal;
        private byte[] mem;
        public BigObject(int ordinal, int size) {
            System.out.println("construct " + ordinal);
            mem = new byte[size];
        }
    }

    public static void main(String[] args) {
        int size = args.length > 0 ? Integer.parseInt(args[0]) : 1024 * 1024;
        int ordinal = 0;
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        while (true) {
            objs.add(new WeakReference<>(new BigObject(++ordinal, size)));
            objs.removeIf(ref -> ref.get() == null);
            System.out.println("active number: " + objs.size());
            sc.nextLine();
        }

    }
}
