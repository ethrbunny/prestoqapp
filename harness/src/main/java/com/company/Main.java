package com.company;

import com.companylib.Parse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
       Main main = new Main();
       main.ReadFile(args[0]);
    }

    public void ReadFile(String filename) {
        Parse parse = new Parse();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                LOGGER.info(parse.parseLine(line).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
