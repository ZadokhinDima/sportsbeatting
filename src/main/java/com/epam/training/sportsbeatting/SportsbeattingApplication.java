package com.epam.training.sportsbeatting;

import com.epam.training.sportsbeatting.facade.SportBettingFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SportsbeattingApplication implements CommandLineRunner {

	@Autowired
	private SportBettingFacade sportBettingFacade;

	@Override
	public void run(final String... args) throws Exception {
		sportBettingFacade.initData();
		sportBettingFacade.registerUser();
		sportBettingFacade.performBets();
		sportBettingFacade.calculateResults();
	}

	public static void main(String[] args) {
		SpringApplication.run(SportsbeattingApplication.class, args);
	}
}
