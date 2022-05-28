import java.util.logging.Logger;

public class CommandLineLogger {
	private static final Logger LOGGER = Logger.getLogger(CommandLineLogger.class.getName());
	private static final CommandLineLogger instance = new CommandLineLogger();
	private CommandLineLogger(){}
	public void log(String text) {
		System.out.println(text);
	}
	public static CommandLineLogger getInstance(){
		return instance;
	}
}


