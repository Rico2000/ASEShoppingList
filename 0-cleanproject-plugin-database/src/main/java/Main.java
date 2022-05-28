public class Main {


	public static void main(String[] args) {
		CommandLineLogger.getInstance().log(
				"Willkommen zum Einkauflisten Planer für WG's.");

		new ActionMenu().interact();
	}

}
