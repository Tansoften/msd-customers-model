package com.tansoften.msd;

import com.tansoften.msd.data.Consumption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MsdApplication {
	public static void main(String[] args){
		//SpringApplication.run(MsdApplication.class, args);
		MSDMainApplication msdMainApplication = new MSDMainApplication();
		msdMainApplication.loadTree();
		msdMainApplication.loadAndTest();
		System.out.println("Win rate: "+ModelTesting.getWinRate()*100+"\nWins: "+ModelTesting.getWins()+"\nLoses: "+ModelTesting.getLoses()+"\nNew consumptions learned: "+ModelTesting.getConsumptionsLearned());
	}
}
