package connectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class connector {
	private Process engineProcess;
	private BufferedReader processReader;
	private OutputStreamWriter processWriter;
	private static final String Path = "C:\\Users\\saru1\\eclipse-workspace\\chessTrial\\Connector\\stockfish.exe";
	
	public boolean startEngine() {
		try {
			engineProcess = Runtime.getRuntime().exec(Path);
			processReader = new BufferedReader(new InputStreamReader(
					engineProcess.getInputStream()));
			processWriter = new OutputStreamWriter(
					engineProcess.getOutputStream());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void sendCommand(String command) {
		try {
			processWriter.write(command + "\n");
			processWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getBestMove(String fen, int waitTime) throws IOException, InterruptedException {
		
		sendCommand("position fen " + fen);
		sendCommand("go movetime " + waitTime);
		return getOutput(waitTime + 20).split("bestmove ")[0].split(" ")[0];
		
	}
	public String getOutput(int waitTime) throws IOException, InterruptedException {
		StringBuffer buffer = new StringBuffer();
		try {
			while (true) {
				String text = processReader.readLine();
				if (text.contains("bestmove")) {
					buffer.append(text + "\n");
					break;
				}
					
				else {
					
				}
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	public void stopEngine() {
		try {
			sendCommand("quit");
			processReader.close();
			processWriter.close();
		} catch (IOException e) {
		}
	}
	
}
