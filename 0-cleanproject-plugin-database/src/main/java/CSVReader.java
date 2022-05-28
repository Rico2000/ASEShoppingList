import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
	private static final String CSV_LINE_SEPARATOR = "\n";

	public static List<String> readFile(String filePath) {
		List<String> content = new ArrayList<String>();

		try {
			Scanner rowScanner = new Scanner(new File(filePath), "UTF-8");
			rowScanner.useDelimiter(CSV_LINE_SEPARATOR);
			while (rowScanner.hasNext()) {
				content.add(rowScanner.next());
			}
			rowScanner.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return content;
	}
}
