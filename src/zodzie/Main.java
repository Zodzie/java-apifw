package zodzie;

import java.net.ServerSocket;

public class Main {
	public static void main(String[] args) {
		final int port = 7999;
		final int connectionLimit = 10;
		try {
			ServerSocket connection = new ServerSocket(port, connectionLimit);
			System.out.println("Server active "+port+"");
			do {
				new Runner(connection.accept());
			} while (true);
		} catch (Exception connection) {
			return;
		}
	}
}
