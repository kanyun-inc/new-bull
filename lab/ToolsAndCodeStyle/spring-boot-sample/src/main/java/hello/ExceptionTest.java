package hello;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author fankai
 */
public class ExceptionTest {
    public static void main(String... args) {

        try {
          runMethod("in main");
        } catch (Exception e) {
            try {
                runMethod("in catch");
            } catch (Exception ee) {
                ee.addSuppressed(e);
                throw ee;
            }

            throw e;
        } finally {
            //runMethod("in finally");
        }


    }

    private static void runMethod(String str) {
        throw new RuntimeException(str);
    }
}
