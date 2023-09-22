package control.lifx;

import java.awt.Color;
import java.util.ArrayList;

import control.lifx.GUI.LifxGUI;
import control.lifx.LifxCommander.ReceiveMessages;

public class Main {
   public static void main(String[] args) {
      // Init
      System.out.println("Initializing...");
      ReceiveMessages receiveMessages = new ReceiveMessages();
      receiveMessages.start();

      // Collects Lifx bulb IPs.
      System.out.println("Getting ips...");
      ArrayList<String> ips = IPGrabber.getIps();

      // Creates an object for each of the found lights
      ArrayList<Light> lights = new ArrayList<Light>();
      for (String ip : ips) {
         System.out.println(ip);
         lights.add(new Light(ip, Color.white));
      }

      System.out.println("Loading GUI...");
      new LifxGUI(lights);
      
      System.out.println("Done!");
   }
}