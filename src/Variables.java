import com.google.common.collect.Iterables;

import java.io.File;

/**
 * Created by Matthew on 2/20/2015.
 */
public class Variables {
    public static String[] allArgs;
    public static File file;
    public static String[] codeBlocks;
    public static Object[] variables = new Object[62]; // 0-9 = 0-9, 10-35 = a-z, 36-62 = A-Z
    public static boolean debugMode = false;
    public static String[] args;
    public static String[] variableStrings = new String[62];

    public static void setVariable(Character variable, Object value) {
        if (Variables.variables[Character.getNumericValue(variable)] == null || Variables.variables[Character.getNumericValue(variable)].getClass() == value.getClass()) {
            Variables.variables[Character.getNumericValue(variable)] = value;
        } else {
            System.out.println("ERROR: Cannot change variable " + variable + " to " + value + " (Incompatible Types).");
        }
    }

    public static String getVariableString(Character variable) {
        if (Variables.variables[Character.getNumericValue(variable)].getClass().isArray()) {
            return arrayToString((Object[]) Variables.variables[Character.getNumericValue(variable)]);
        } else if (Variables.variables[Character.getNumericValue(variable)] instanceof Iterable) {
            return iterableToString((Iterable) Variables.variables[Character.getNumericValue(variable)]);
        } else {
            return (String) Variables.variables[Character.getNumericValue(variable)];
        }
    }

    public static String arrayToString(Object[] objects) {
        String s = "{";
        for (int i = 0; i < objects.length; i++) {
            s += objects[i];
            if (i < objects.length - 1)
                s += ",";
        }
        s += "}";
        return s;
    }

    public static String iterableToString(Iterable objects) {
        String s = "{";
        for (int i = 0; i < Iterables.size(objects); i++) {
            s += Iterables.get(objects, i);
            if (i < Iterables.size(objects) - 1)
                s += ",";
        }
        s += "}";
        return s;
    }

}