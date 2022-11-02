import java.util.*;
public class Demo{
   public static void main(String args[]){
      Scanner my_scan = new Scanner(System.in);
      System.out.println("Enter the input string ");
      String my_in = my_scan.next();
      int my_checksum = generate_checksum(my_in);
      System.out.println("The checksum that has been generated is " + Integer.toHexString(my_checksum));
      System.out.println("Enter the data that needs to be sent to the receiver ");
      my_in = my_scan.next();
      System.out.println("Enter the checksum that needs to be sent to the receiver ");
      my_checksum = Integer.parseInt((my_scan.next()), 16);
      receive(my_in, my_checksum);
      my_scan.close();
   }
   static int generate_checksum(String s){
      String my_hex_val = new String();
      int x, i, my_checksum = 0;
      for (i = 0; i < s.length() - 2; i = i + 2){
         x = (int) (s.charAt(i));
         my_hex_val = Integer.toHexString(x);
         x = (int) (s.charAt(i + 1));
         my_hex_val = my_hex_val + Integer.toHexString(x);
         System.out.println(s.charAt(i) + "" + s.charAt(i + 1) + " : " + my_hex_val);
         x = Integer.parseInt(my_hex_val, 16);
         my_checksum += x;
      }
      if (s.length() % 2 == 0){
         x = (int) (s.charAt(i));
         my_hex_val = Integer.toHexString(x);
         x = (int) (s.charAt(i + 1));
         my_hex_val = my_hex_val + Integer.toHexString(x);
         System.out.println(s.charAt(i) + "" + s.charAt(i + 1) + " : "+ my_hex_val);
         x = Integer.parseInt(my_hex_val, 16);
      } else {
         x = (int) (s.charAt(i));
         my_hex_val = "00" + Integer.toHexString(x);
         x = Integer.parseInt(my_hex_val, 16);
         System.out.println(s.charAt(i) + " : " + my_hex_val);
      }
      my_checksum += x;
      my_hex_val = Integer.toHexString(my_checksum);
      if (my_hex_val.length() > 4){
         int carry = Integer.parseInt(("" + my_hex_val.charAt(0)), 16);
         my_hex_val = my_hex_val.substring(1, 5);
         my_checksum = Integer.parseInt(my_hex_val, 16);
         my_checksum += carry;
      }
      my_checksum = generate_complement(my_checksum);
      return my_checksum;
   }
   static void receive(String s, int my_checksum){
      int gen_checksum = generate_checksum(s);
      gen_checksum = generate_complement(gen_checksum);
      int syndrome = gen_checksum + my_checksum;
      syndrome = generate_complement(syndrome);
      System.out.println("The value of syndrome is " + Integer.toHexString(syndrome));
      if (syndrome == 0){
         System.out.println("Data has been received without any errors");
      } else {
         System.out.println("An error was encountered in the received data");
      }
   }
   static int generate_complement(int my_checksum){
      my_checksum = Integer.parseInt("FFFF", 16) - my_checksum;
      return my_checksum;
   }
}
