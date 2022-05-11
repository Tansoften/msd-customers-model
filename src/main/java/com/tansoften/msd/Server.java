package com.tansoften.msd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public abstract class Server {
	private final static Model model = new Model();

	private static class Commands implements Runnable{
		@Override
		public void run(){
			boolean isRunning = true;
			Scanner readInput = new Scanner(System.in);

			while(isRunning){
				System.out.print("CLI >");
				String input = readInput.next();

				switch (input){
					case "help" ->
						System.out.println(
								 "\nstart			- This will start both server and model."
								+"\nstart-server 	- This will start server."
								+"\nstart-model 	- This will start model."
								+"\nvalidate-model 	- This will validate your model and give out model accuracy."
								+"\nhelp 			- This displays a list of all commands that are supported."
						);
					case "start" -> {
						try {
							Server.startServer();
							Server.startModel();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					case "start-server" -> Server.startServer();
					case "start-model" 	-> {
						try {
							Server.startModel();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					case "validate-model" -> Server.startModelValidation();
                    //case "restart-count"-> ModelController.resetCounters();
                    case "change-rate "-> {
						System.out.print("Enter time in milliseconds:");
                        int rate = readInput.nextInt();
						ModelController.changeRate(rate);
                    }
					case "close" -> {
						isRunning = false;
						//SpringApplication.exit();
					}
					default -> System.out.println("Didn't understand your command, type help to learn.");
				}
			}
		}
	}

	private static void startServer(){
		SpringApplication.run(Server.class);
		System.out.println("Server was started.");
	}

	private static void startModel() throws IOException {
		model.loadTree();
	}

	private static void startModelValidation(){
		if(model.getRoot().isEmpty()){
			System.out.println("Model is empty, please start it first using: start-model");
		}else {
			model.loadValidatingData();
			System.out.println(
				"Win rate: "+ModelTesting.getWinRate()*100
				+"\nWins: "+ModelTesting.getWins()
				+"\nLoses: "+ModelTesting.getLoses()
				+"\nNew consumptions learned: "
				+ModelTesting.getConsumptionsLearned()
			);
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Thread commandLine = new Thread(new Commands());
		commandLine.start();
	}
}
