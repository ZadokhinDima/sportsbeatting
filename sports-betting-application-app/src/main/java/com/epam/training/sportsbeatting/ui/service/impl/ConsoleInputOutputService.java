package com.epam.training.sportsbeatting.ui.service.impl;

import java.util.Scanner;

import com.epam.training.sportsbeatting.ui.service.InputOutputService;

import org.springframework.stereotype.Component;

@Component
public class ConsoleInputOutputService implements InputOutputService {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void print(final String output) {
        System.out.println(output);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
