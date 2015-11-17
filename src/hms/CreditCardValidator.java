package hms;

/*
    hhwang9
    luhnTest method takes a String and returns true or false (card number validity)
    cardTypeTest method takes a String and returns Visa, Mastercard, Discover, Amex, or invalid card type
    11/17/15
*/

public class CreditCardValidator {
    
    //Driver to test cards
    /*
    public static void main(String[] args) {
        System.out.println(luhnTest("6011000990139424"));
        System.out.println(cardTypeTest("6011000990139424"));
        System.out.println(luhnTest("4007000000027"));
        System.out.println(cardTypeTest("4007000000027"));
    }
    */
    
    //Luhn's algorithm
    public static boolean luhnTest(String number){
        //even digits are doubled, odd digits are left alone
        int s1 = 0, s2 = 0;
        //reverse String
        String reverse = new StringBuffer(number).reverse().toString();
        for(int i = 0 ;i < reverse.length();i++){
            //takes a character, then returns equivalent numerical value in base 10
            int digit = Character.digit(reverse.charAt(i), 10);
            //for odd digits, they are 1-indexed in the algorithm
            if(i % 2 == 0){
                s1 += digit;
            //add 2 * digit for 0-4, add 2 * digit - 9 for 5-9    
            }else{
                s2 += 2 * digit;
                //if result is greater than or equal to 5, then 9 is subtracted (i.e. 14 is considered 1+4 = 5, or 14-9 = 5)
                if(digit >= 5){
                    s2 -= 9;
                }
            }
            //System.out.println(digit);
        }
        //if mod 10 is zero, card number is valid
        return (s1 + s2) % 10 == 0;
    }
    
    public static String cardTypeTest(String number){
        String reverse = new StringBuffer(number).reverse().toString();
        
        //Amex has 15 digits
        if (reverse.length() == 15){
            if (reverse.charAt(14) == '3'){
                return "Amex";
            }else
                return "invalid Amex card";
        }
        //Visa has 13 or 16 digits, and starts with 4
        else if ((reverse.length() == 13) && (reverse.charAt(12) == '4')){
            return "Visa";
        }else if ((reverse.length() == 16) && (reverse.charAt(15) == '4')){
            return "Visa";
        }
        //Discover or Mastercard has 16 digits    
        else if ((reverse.length() == 16) && (reverse.charAt(15) == '6')){
            //Mastercard starts with 5
            if (reverse.charAt(15) == '5'){                
                return "Mastercard";
            }else if (reverse.charAt(15) == '6'){
                //6011 Discover
                if ((reverse.charAt(14) == '0') && (reverse.charAt(13) == '1') && (reverse.charAt(12) == '1')){
                    return "Discover";   
                //644 Discover
                }else if ((reverse.charAt(14) == '4') && (reverse.charAt(13) == '4')){
                    return "Discover";
                //65 Discover
                }else if (reverse.charAt(14) == '5'){
                    return "Discover";
                }else
                    return "invalid Discover card";  
            }else
                return "invalid Mastercard or Discover card";
        }else{
            return "invalid credit card type";
        } 
                
    }
}