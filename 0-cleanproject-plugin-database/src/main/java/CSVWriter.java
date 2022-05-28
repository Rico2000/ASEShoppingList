import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVWriter {
	public static void writeLine(String filePath, String lineToWrite) {
		try {
			FileWriter writer = new FileWriter(filePath, true);
			writer.append(lineToWrite);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void updateLine(String filePath, String oldString, String newString) {
		Scanner sc;
		try {
			sc = new Scanner(new File(filePath));
			StringBuffer buffer = new StringBuffer();
			while (sc.hasNextLine()) {
				buffer.append(sc.nextLine() + System.lineSeparator());
			}
			String fileContents = buffer.toString();
			sc.close();
			fileContents = fileContents.replaceAll(oldString, newString);
			FileWriter fileWriter = new FileWriter(filePath);
			fileWriter.append(fileContents);
			fileWriter.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
