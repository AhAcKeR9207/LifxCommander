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

      // Collects Lifx bulb IPs.
      System.out.println("Getting ips...");
      ArrayList<String> ips = IPGrabber.getIps();

      // Creates an object for each of the found lights
      ArrayList<Light> lights = new ArrayList<Light>();
      ips.forEach((ip) -> {
         System.out.println(ip);
         lights.add(new Light(ip, Color.red));
      });

      System.out.println("Initializing the text interface...");
      
      
      System.out.println("Done!");
   }
}