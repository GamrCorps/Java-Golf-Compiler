import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by Matthew on 2/18/2015.
 */
public class FileFunctions {
    public static void findAndReplaceAll(File file, String find, String replace, String occurrenceMessage) {
        try {
            FileReader fr = new FileReader(file);
            String s;
            String totalStr = "";
            try {
                BufferedReader br = new BufferedReader(fr);
                while ((s = br.readLine()) != null) {
                    totalStr += s;
                    totalStr += "\n";
                }

                String[] totalStr2 = totalStr.split("\n");
                for (int i = 0; i < totalStr2.length; i++) {
                    String[] line = totalStr2[i].split(find);
                    if (line.length - 1 > 0) {
                        System.out.printf(occurrenceMessage + "\n", line.length - 1, i + 1);
                    }
                }

                totalStr = totalStr.replaceAll(find, replace);
                FileWriter fw = new FileWriter(file);
                fw.write(totalStr);
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFileContents(File file) {
        try {
            FileReader fr = new FileReader(file);
            String s;
            String totalStr = "";
            try {
                BufferedReader br = new BufferedReader(fr);
                while ((s = br.readLine()) != null) {
                    totalStr += s;
                    totalStr += "\n";
                }
                return totalStr;
                /*
                String[] totalStr2 = totalStr.split("\n");
                for (int i = 0; i < totalStr2.length; i++) {
                    String[] line = totalStr2[i].split(find);
                    if (line.length - 1 > 0) {
                        System.out.printf(occurrenceMessage + "\n", line.length - 1, i + 1);
                    }
                }

                totalStr = totalStr.replaceAll(find, replace);
                FileWriter fw = new FileWriter(file);
                fw.write(totalStr);
                fw.close();
                */
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}