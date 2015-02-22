import java.io.File;
import java.util.Arrays;

/**
 * Created by Matthew on 2/20/2015.
 */
abstract class ArgumentRunner {
    public abstract void run(String[] args, String arg);
}

public enum ProgramArguments {
    FILE("-f:", new ArgumentRunner() {
        @Override
        public void run(String[] args, String arg) {
            Variables.file = new File(arg.substring(3));
            if (Variables.file.exists()) {
                Main.interpret();
            } else {
                System.out.println("The file " + Variables.file.getName() + " does not exist!");
            }
        }
    }),
    CODE("{", new ArgumentRunner() {
        @Override
        public void run(String[] args, String arg) {
            Main.interpretCode(Arrays.copyOfRange(args, 0, Main.x + 2));
        }
    }),
    NULL("matthewmccaskillmadethisscript", new ArgumentRunner() {
        @Override
        public void run(String[] args, String arg) {

        }
    });
    public String argument = "";
    public ArgumentRunner runner;

    ProgramArguments(String s, ArgumentRunner ar) {
        argument = s;
        runner = ar;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public ArgumentRunner getArgumentRunners() {
        return runner;
    }

    public void setArgumentRunners(ArgumentRunner argumentRunners) {
        this.runner = argumentRunners;
    }

    public void run(String[] args, String arg) {
        runner.run(args, arg);
    }

    public static void runTests(String[] args) {
        for (String arg : args) {
            getArgument(arg).run(args, getArgument(arg).getArgument());
        }
    }

    public static ProgramArguments getArgument(String s) {
        for (ProgramArguments arg : ProgramArguments.values()) {
            if (s.startsWith(arg.getArgument())) {
                return arg;
            }
        }
        return NULL;
    }
}
