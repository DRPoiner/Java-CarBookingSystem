/*
 * Main class to start application.
 *
 * 02/10/2022
 * 
 * Copyright David Poiner - S3579878
 */

import View.*;
import java.io.*;
import Controller.*;

public class Main {

  public static void main(String[] args) {

    try {
      FileController.readFile();
    } catch (IOException e) {
      System.out.println("Failed to load csv file");
    }

    Menu.main();

  }

}
