package app.windowsprompt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PromptBasis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub



		String commandOutput;

		try {
			// 'dir' command doesn't work
			Process process = Runtime.getRuntime().exec("help");

			String commandName;
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((commandOutput = reader.readLine()) != null) {
				
//				System.out.println(commandOutput);
				commandName = commandOutput.split(" ")[0];
				System.out.println(commandName);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	

	}

}
