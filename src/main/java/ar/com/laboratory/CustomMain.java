package ar.com.laboratory;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class CustomMain {
    public static void main(String[] args) {
        System.out.println("Running custom main method");
        Quarkus.run(CustomAPP.class, args);
    }
    public  static class CustomAPP implements QuarkusApplication{
        @Override
        public int run(String... args) throws Exception {
            System.out.println("Running custom app.......");
            Quarkus.waitForExit();
            return 0;
        }
    }
}
