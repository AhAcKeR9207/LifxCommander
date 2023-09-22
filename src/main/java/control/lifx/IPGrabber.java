package control.lifx;

import java.io.InputStreamReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Scanner;

public class IPGrabber {
   public static ArrayList<String> getIps() {
      ArrayList<String> ips = new ArrayList<String>();

      try {
         InetAddress localHost = InetAddress.getLocalHost();
         NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
         InterfaceAddress interfaceAddress = networkInterface.getInterfaceAddresses().get(0);

         String broadcastIp = interfaceAddress.getBroadcast().toString().substring(1);

         /* Gets the subnet mask.  I forget why I wanted this, but the code is too valuable to lose, so it stays.
            // Creates two variables.  The binary string for the subnet mask, and a stringbuilder for the final IP.
            StringBuilder subnetMaskBinary = new StringBuilder("00000000000000000000000000000000");
            StringBuilder subnetMaskIp = new StringBuilder("000.000.000.000");

            // This gets the number of 1s to put into the subnet mask.
            int numOf1s = interfaceAddress.getNetworkPrefixLength();

            // Loads the 1s into a binary representation of the subnet mask.
            for (int i = 0; i < 32; i++) {
                  if (numOf1s > i) {
                     subnetMaskBinary.setCharAt(i, '1');
                  }
            }
            
            // Loads the numbers into the final IP.  Runs in reverse to account for any changes in length from prior statements.
            subnetMaskIp.replace(12, 15, Integer.toString(Integer.parseInt(subnetMaskBinary.substring(24, 32), 2)));
            subnetMaskIp.replace(8, 11, Integer.toString(Integer.parseInt(subnetMaskBinary.substring(16, 24), 2)));
            subnetMaskIp.replace(4, 7, Integer.toString(Integer.parseInt(subnetMaskBinary.substring(8, 16), 2)));
            subnetMaskIp.replace(0, 3, Integer.toString(Integer.parseInt(subnetMaskBinary.substring(0, 8), 2)));
         */

         // Builds and send the command
         String[] commands = {"powershell.exe", "ping " + broadcastIp + " -n 4 -w 20; arp -a | select-string 'd0-73-d5' |% { $_.ToString().Trim().Split(' ')[0] }"};
         ProcessBuilder processBuilder = new ProcessBuilder(commands);
         Process process = processBuilder.start();

         // Closes the output stream
         process.getOutputStream().close();
         
         // Gets the input stream from the process.
         Scanner psIn = new Scanner(new InputStreamReader(process.getInputStream()));
         for (int i = 0; i < 9; i++) { // Skips over the output from the ping command
            psIn.nextLine();
         }
         
         // Checks for any new lines to add to the list of ips
         while (psIn.hasNext()) {
            ips.add(psIn.nextLine());
         }

         // If there are no lights, the program puts null into the array.  This allows the null panel to appear.
         if (ips.size() == 0) ips.add(null);
         
         // Closes the input stream and returns the ips.
         psIn.close();
         return ips;
      } catch (IOException e) {}
      return null;
   }
}