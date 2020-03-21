package src.zodzie;

import java.net.ServerSocket;

public class Main {
	public static void main(String[] args) {
		final int PORT = 7999;
		try {
			@SuppressWarnings("resource")
			ServerSocket port = new ServerSocket(PORT);
			System.out.println("Server active "+PORT+"");
			do {
				new Runner(port.accept());
			} while (true);
		} catch (Exception port) {
			return;
		}
	}
}
