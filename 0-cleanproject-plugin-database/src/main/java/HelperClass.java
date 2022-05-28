import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class HelperClass {
    public static List<String> splitQuantityAndItemFromString(String inputQuantityItem) {
        inputQuantityItem = inputQuantityItem.replaceAll("\\s+","");
        List<String> matches = new ArrayList<String>();
        Matcher m = Pattern.compile("([\\d.]+)|([^\\d.]+)").matcher(inputQuantityItem);
        while (m.find()) {
            matches.add(m.group());
        }
        return matches;
    }
}
