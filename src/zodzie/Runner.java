package src.zodzie;

import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class Runner extends Thread {
	private Socket socketbind;
	String postData = null;
	String query = null;

	Runner(Socket insocket) {
		this.socketbind = insocket;
		this.start();
	}

	public void run() {
		try {
			InputStream is = this.socketbind.getInputStream();
			PrintWriter out = new PrintWriter(this.socketbind.getOutputStream());
			BufferedReader in = new BufferedReader((Reader) new InputStreamReader(is));
			String line = in.readLine();
			line = "";
			int postDataI = -1;

			out.write("HTTP/1.0 200 OK\r\n");
			out.write("Content-Type: text/plain\r\n");
			out.write("\r\n");

			while ((line = in.readLine()) != null && line.length() != 0) {
				// if you want to read the header information
				// System.out.println("HTTP-HEADER: " + line);

				String newLine = line.toLowerCase();
				// lowercase because some apps header information is not following common format
				if (newLine.indexOf("content-length:") <= -1)
					continue;
				postDataI = new Integer(newLine.substring(newLine.indexOf("content-length:") + 16, newLine.length()));
			}

			if (postDataI >= 0) {
				char[] charArray = new char[postDataI];
				in.read(charArray, 0, postDataI);
				postData = new String(charArray);

				query = postData.toString();
				
				
				// if you want to read the post data
				// System.out.println("Post DATA: " + postData.toString());

				// query is most likely a json block. you can turn query into a json object -
				// parse and respond accordingly
				// example:
				// startNew.classHere(query);
				// Confluence.api.Search();

				
				// cleanup
				out.close();
				in.close();
				socketbind.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
