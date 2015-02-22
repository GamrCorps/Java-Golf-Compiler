/**
 * Created by Matthew on 2/20/2015.
 */
public class Interpreter {
    public static void interpret() {
        Variables.codeBlocks[0] = Variables.codeBlocks[0].substring(1);
        Variables.codeBlocks[Variables.codeBlocks.length - 1] = Variables.codeBlocks[Variables.codeBlocks.length - 1].substring(0, Variables.codeBlocks[Variables.codeBlocks.length - 1].length() - 1);
        for (String block : Variables.codeBlocks) {
            for (Commands command : Commands.values()) {
                if (block.startsWith(command.getKey())) {
                    System.out.println("Running Block: " + block);
                    command.run(block);
                    System.out.println();
                }
            }
        }
    }
}