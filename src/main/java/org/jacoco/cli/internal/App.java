package org.jacoco.cli.internal;

import java.io.PrintWriter;
import java.io.Writer;

public class App {

    static void main(String[] args) throws Exception {
        final PrintWriter out = new ReplacingPrintWriter(new PrintWriter(System.out, true));
        final PrintWriter err = new ReplacingPrintWriter(new PrintWriter(System.err, true));
        final int returncode = new Main(args).execute(out, err);
        System.exit(returncode);
    }

    private static class ReplacingPrintWriter extends PrintWriter {
        private static final String SEARCH = "java -jar jacococli.jar";
        private static final String REPLACE = "jacoco-cli";

        public ReplacingPrintWriter(Writer out) {
            super(out, true);
        }

        @Override
        public void println(String x) {
            super.println(x != null ? x.replace(SEARCH, REPLACE) : null);
        }

        @Override
        public void print(String s) {
            super.print(s != null ? s.replace(SEARCH, REPLACE) : null);
        }
    }
}
