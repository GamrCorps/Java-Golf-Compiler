import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 2/21/2015.
 */
public class StringBuilder {
    public static String getOutputString(String print) {
        List<Character> chars = new ArrayList<Character>();
        for (char c : print.toCharArray()) {
            chars.add(c);
        }
        List<Integer> quotes = new ArrayList<Integer>();
        for (int i = 0; i < chars.size(); i++) {
            if (chars.get(i) == '"') {
                quotes.add(i);
            }
        }
        List<String> strings = new ArrayList<String>();
        for (int i = 0; i < quotes.get(0); i++) {
            strings.add(Variables.getVariableString(chars.get(i)));
        }
        quotes.add(print.length());
        if ((quotes.size()-1) % 2 == 0) {
            for (int i = 0; i < quotes.size() / 2; i++) {
                strings.add(print.substring(quotes.get(i * 2) + 1, quotes.get(i * 2 + 1)));
                String s = print.substring(quotes.get(i * 2 + 1), quotes.get(i * 2 + 2));
                for (char c : s.toCharArray()){
                    if (c != '"')
                    strings.add(Variables.getVariableString(c));
                }
            }
        }
        for (int i = quotes.get(quotes.size() - 1); i < print.length() - 1; i++) {
            strings.add(Variables.getVariableString(print.charAt(i + 1)));
        }
        return Joiner.on("").join(strings);
    }
}
