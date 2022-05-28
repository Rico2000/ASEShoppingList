public class CommandLineLogger {
	private static final CommandLineLogger instance = new CommandLineLogger();
	private CommandLineLogger(){}
	public void log(String text) {
		System.out.println(text);
	}
	public static CommandLineLogger getInstance(){
		return instance;
	}
}


