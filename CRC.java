/**
 * CRC
 */
import java.util.*;
 public class CRC {
    String input,generator,dividend,result;
    int gen_len,input_len,dividend_len;  
    Scanner sc = new Scanner(System.in); 
    void getData(){
        System.out.print("Enter input: ");
        input = sc.nextLine();
        dividend = input;
        System.out.println();
        System.out.println("Enter Generator");
        generator = sc.nextLine();
        gen_len = generator.length();
        input_len = input.length();
        int rem = gen_len -1;
        for(int i=0;i<rem;i++){
            dividend=dividend+'0';
        }
        dividend_len = dividend.length();
        modulo_div();
    }
    private void modulo_div() {
        String temp_div = generator;
        String temp_dividend = dividend.substring(0, gen_len);
        int j = gen_len;
        while(j<dividend_len){
            temp_dividend = XOR(temp_dividend, temp_div);
            temp_dividend = temp_dividend + dividend.charAt(j);
            j++;
        }
        result = input + XOR(temp_dividend, temp_div);
    }
    private String XOR(String dividend, String divisor) {
        String res = "";
        if(dividend.charAt(0)=='0'){
            return dividend.substring(1);
        }
        else{
            for(int i=0;i<gen_len;i++){
                res = res + (dividend.charAt(i)==divisor.charAt(i)?'0':'1');
            }
        }

        return res.substring(1); //As first bit will be made 0 during XOR op
    }
    void senderSide(){
        System.out.println("Input: "+input);
        System.out.println("Generator: "+generator);
        System.out.println("Dividend: "+dividend);
        System.out.println("Data to send: "+ result);
    }
    void receiverSide(){
        System.out.println("Enter String received");
        String receivedString = sc.nextLine();
        String temp_div = generator;
        String temp_dividend = receivedString.substring(0,gen_len);
        int j = gen_len;
        while(j<receivedString.length()){
            temp_dividend = XOR(temp_dividend, temp_div);
            temp_dividend = temp_dividend + receivedString.charAt(j);
            j++;
        }
        System.out.println(temp_dividend);
        String error = XOR(temp_dividend, temp_div);
        System.out.println(error);
        boolean flag = false;
        for(int i=0;i<gen_len-1;i++){
            if(error.charAt(i) == '1'){
                flag = true;
            }
        }
        if(flag){
            System.out.println("Error in received Message!!");
        }
        else{
            System.out.println("No transmission error");
        }

    }
    public static void main(String[] args) {
    CRC crc = new CRC();
    crc.getData();
    crc.senderSide();
    crc.receiverSide();
}

}
