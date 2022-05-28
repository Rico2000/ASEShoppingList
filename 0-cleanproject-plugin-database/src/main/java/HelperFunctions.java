import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class HelperFunctions {
	static List<String> splidQuantityString(String quantityAndUnit) {
		quantityAndUnit = quantityAndUnit.replaceAll("\\s+","");
		List<String> allMatches = new ArrayList<String>();
		 Matcher m = Pattern.compile("([\\d.]+)|([^\\d.]+)").matcher(quantityAndUnit);
		 while (m.find()) {
		   allMatches.add(m.group());
		 }
		return allMatches;
	}
	
	

}
