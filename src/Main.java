import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.ObjectArrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Matthew on 2/20/2015.
 */
public class Main {
    public static int x = 0;

    public static void main(String[] args) {
        Variables.allArgs = new String[args.length];
        Variables.allArgs = args;
        ProgramArguments.runTests(args);
    }

    public static void interpret() {
        File file = Variables.file;
        String code = FileFunctions.getFileContents(file);
        //Variables.codeBlocks = code.split(";", -1);
        Iterable<String> temp = Splitter.on(';').split(code);
        List<String> list = new ArrayList<String>();
        for (String a : temp) {
            list.add(a);
        }
        Variables.codeBlocks = new String[list.size()];
        list.toArray(Variables.codeBlocks);
        Interpreter.interpret();
    }

    public static void interpretCode(String[] code) {
        //Variables.codeBlocks = code.split(";", -1);
        if (Variables.allArgs[0].startsWith("{")) {
            for (int i = 0; i < Variables.allArgs.length; i++) {
                if (Variables.allArgs[i].endsWith("}")) {
                    x = i;
                }
            }
        }
        Variables.args = new String[Variables.allArgs.length - (x + 1)];
        Variables.args = Arrays.copyOfRange(Variables.allArgs, x + 1, Variables.allArgs.length);
        String codes = Joiner.on("").join(code);
        System.out.println("==" + codes + "==");
        Iterable<String> temp = Splitter.on(';').split(codes);
        List<String> list = new ArrayList<String>();
        for (String a : temp) {
            list.add(a);
        }
        String[] temp2 = Iterables.toArray(temp, String.class);
        Variables.codeBlocks = new String[temp2.length];
        Variables.codeBlocks = temp2;
        list.toArray(Variables.codeBlocks);
        Interpreter.interpret();
    }
}
