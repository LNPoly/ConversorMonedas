package conversorMonedas.conversor;

import java.io.IOException;
import java.util.Scanner;


public class PrincipalMenu {
    private Archive archive;
    private int n = 0;
    private double cantidad = 0;
    private Scanner sc = new Scanner(System.in);
    private GeneralCalc generalCalc;

    {
        try {
            generalCalc = new GeneralCalc();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public PrincipalMenu(Archive archive){
        this.archive = archive;
    }

    public void menu() {

        System.out.println("""
                
                ********** WELCOME TO THE CURRENCY CONVERTER **********
                               \s
                        ENTER AN OPTION TO MAKE A CONVERSION
                               \s
                     1 - DOLLAR(USD) >>>> ARGENTINE PESO(ARS)
                     2 - ARGENTINE PESO(ARS) >>>> DOLLAR(USD)
                     3 - ARGENTINE PESO(ARS) >>>> EURO(EUR)
                     4 - EURO(EUR) >>>> ARGENTINE PESO(ARS)
                     5 - ARGENTINE PESO(ARS) >>>> POUND STERLING(GBP)
                     6 - POUND STERLING(GBP) >>>> ARGENTINE PESO(ARS)
                     7 - ARGENTINE PESO(ARS) >>>> SOUTH KOREAN(KRW)
                     8 - SOUTH KOREAN(KRW) >>>> ARGENTINE PESO(ARS)
                     9 - ARGENTINE PESO(ARS) >>>> YEN JAPANESE(JPY)
                     10 - YEN JAPANESE(JPY) >>>> ARGENTINE PESO(ARS)
                     11 - CONVERSION HISTORY
                     12 - QUIT
                """);
        //verificaciones particulares
        if (sc.hasNextInt()) {
            n = sc.nextInt();
            if (n < 1 || n > 12){
                System.out.println("The option is invalid.");
                menu();

            } else if (n == 11) {
                conversionHistory();
            } else if (n == 12) {
                System.out.println("Thank You!");
            } else{
                operations();
            }
        }
    }
    public void conversionHistory() {
        if (this.archive.getStringList().size() != 0){
            System.out.println("This are the last conversions: ");
            for (String s : this.archive.getStringList()){
                System.out.println(s);
            }
            continueConvert();
        }else {
            System.out.println("You didn't make any conversions.");
            continueConvert();
        }
    }

    public void operations(){

            System.out.println("Enter the amount of money you want to convert.");
            if (sc.hasNextDouble()) {
                cantidad = sc.nextDouble();

                if (generalCalc.checkAmount(cantidad)){
                    cantidad = 0;
                    operations();
                } else {
                    String s = generalCalc.convertAmount(n,cantidad);
                    this.archive.addList(s);
                    System.out.println(s);
                    continueConvert();

                }
            }
        }
    public void continueConvert(){
        n = 0;
        System.out.println("""
                keep continue?
                Enter a numer:
                1 - go back
                2 - Quit
                """);
        if (sc.hasNextInt()){
            n = sc.nextInt();
            if (n < 1 || n > 2 ){
                System.out.println("Invalid Option");
                continueConvert();
            } else {
                if (n == 1)menu();
                else this.archive.writteFile();
            }
        }
    }
}

