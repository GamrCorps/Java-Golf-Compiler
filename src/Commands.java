import com.google.common.base.Joiner;
import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Matthew on 2/20/2015.
 */
abstract class CommandRunnable {
    public abstract void run(String block);
}

public enum Commands {
    DEBUG_MODE("D", 0, 0, new CommandRunnable() {
        @Override
        public void run(String block) {
            if (block.length() == 1) {
                Variables.debugMode = !Variables.debugMode;
                System.out.println("[DEBUG] Debug state: " + Variables.debugMode);
            } else if (block.charAt(1) == '0'){
                Variables.debugMode = true;
                System.out.println("[DEBUG] Debug state: " + Variables.debugMode);
            } else {
                Variables.debugMode = false;
                System.out.println("[DEBUG] Debug state: " + Variables.debugMode);
            }
        }
    }),
    VARIABLE("V", 0, 0, new CommandRunnable() {
        @Override
        public void run(String block) {
            Object object = block.substring(2);
            Variables.setVariable(block.charAt(1), object);
            //System.out.print(i);
            if (Variables.debugMode) {
                System.out.println("[DEBUG] Variable \"" + block.charAt(1) + "\" set to \"" + object + "\"");
            }
        }
    }),
    INPUT("I", 0, 0, new CommandRunnable() {
        @Override
        public void run(String block) {
            if (block.charAt(1) == 'A') {
                int i = Character.getNumericValue(block.charAt(2));
                Variables.variables[i] = Variables.args;
                if (Variables.debugMode) {
                    System.out.println("[DEBUG] Variable \"" + block.charAt(2) + "\" set to INPUT via PROGRAM_ARGUMENTS");
                }
            } else if (block.charAt(1) == 'S') {
                BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
                String s, t = "";
                try {
                    while ((s = r.readLine()) != null) t = t + s;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int j = Character.getNumericValue(block.charAt(2));
                Variables.variables[j] = t;
                if (Variables.debugMode) {
                    System.out.println("[DEBUG] Variable \"" + block.charAt(2) + "\" set to INPUT via STDIN");
                }
            }
        }


    }),
    OUTPUT("O", 0, 0, new CommandRunnable() {
        @Override
        public void run(String block) {
            /*
            if (block.charAt(1) == '_') {
                if (Variables.variables[Character.getNumericValue(block.charAt(2))] instanceof String[]) {
                    //System.out.println(StringUtils.join(new ArrayList<Object>(Arrays.asList(Variables.variables[Character.getNumericValue(block.charAt(2))]))," "));
                    System.out.println(Joiner.on(" ").join((Object[]) Variables.variables[Character.getNumericValue(block.charAt(2))]));
                } else {
                    System.out.println(Variables.variables[Character.getNumericValue(block.charAt(2))]);
                }
            } else {
                System.out.println(block.substring(1));
            }
            */
            String print = block.substring(1);
            if (!print.contains("\"")){
                for (char c : print.toCharArray()){
                    System.out.print(Variables.getVariableString(c));
                }
            } else {
                System.out.print(StringBuilder.getOutputString(print));
            }
            System.out.println();
        }
    });

    public String key;
    public int start;
    int end;
    CommandRunnable command;

    Commands(String w, int begin, int ending, CommandRunnable commandRunnable) {
        key = w;
        start = begin;
        end = ending;
        command = commandRunnable;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public CommandRunnable getCommand() {
        return command;
    }

    public void setCommand(CommandRunnable command) {
        this.command = command;
    }

    public void run(String block) {
        command.run(block);
    }
}
