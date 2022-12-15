package Lab_exam_practice;

/**
 * CRC
 */
import java.util.*;
public class CRC {
    String input,generator,secret,result;
    int generator_len,input_len,secret_len;
    Scanner sc = new Scanner(System.in);
    void getData(){
        System.out.println("Enter data to be encoded");
        input = sc.nextLine();
        secret =input;
        System.out.println("Enter generator");
        generator = sc.nextLine();

        generator_len = generator.length();
        input_len = secret_len = secret.length();
        int rem = generator_len -1;
        for(int i=0;i<rem;i++){
            secret = secret + "0";
        }
        secret_len = secret.length();
        modulo_div();
    }
    void modulo_div(){
        String divisor = generator;
        String temp_dividend = secret.substring(0,generator_len);
        int j = generator_len;
        while(j< secret_len){
            temp_dividend =  XOR(temp_dividend,divisor);
            temp_dividend = temp_dividend + secret.charAt(j);
            j++;
        }
        result = input + XOR(temp_dividend,divisor);

    }
    String XOR(String dividend,String divisor){
        String res = "";
        if(dividend.charAt(0)=='0'){
            return dividend.substring(1);
        }
        else{
            for(int i=0;i<generator_len;i++){
                res = res + (dividend.charAt(i)==divisor.charAt(i)?"0":"1");
            }
        }
        
        return res.substring(1);
    }
    void receiverSide(){
        System.out.println("Enter received String");
        String rcv = sc.nextLine();
        int rcv_len = rcv.length();
        String temp_dividend = rcv.substring(0, generator_len);
        String divisor = generator;
        int j = generator_len;
        while(j<rcv_len){
            temp_dividend = XOR(temp_dividend,divisor);
            temp_dividend = temp_dividend + rcv.charAt(j);
            j++;
        }
        String error = XOR(temp_dividend,divisor);
        boolean flag = false;
        for(int i=0;i<error.length();i++){
            if(error.charAt(i)=='1'){
                flag = true;
            }
        }
        if(flag){
            System.out.println("Error in transmission");
        }
        else{
            System.out.println("No error in transmission");
        }


    }    
    void senderSide(){
        System.out.println("Input: "+input);
        System.out.println("Generator: "+generator);
        System.out.println("Dividend: "+secret);
        System.out.println("Data to send: "+ result);
    }
    public static void main(String[] args) {
        CRC crc = new CRC();
        crc.getData();
        crc.senderSide();
        crc.receiverSide();
    }
    
}