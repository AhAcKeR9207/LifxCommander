package control.lifx;

import java.awt.Color;
import java.util.ArrayList;

import control.lifx.LifxCommander.ReceiveMessages;

public class Main {
   public static void main(String[] args) {
      // Init
      System.out.println("Initializing...");
      ReceiveMessages receiveMessages = new ReceiveMessages();
      receiveMessages.start();

      System.out.println("Initializing the text interface...");
      
      
      System.out.println("Done!");
   }
}