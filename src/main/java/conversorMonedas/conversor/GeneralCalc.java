package conversorMonedas.conversor;

import java.io.IOException;
import java.text.DecimalFormat;

public class GeneralCalc {

    RequestApi requestApi = new RequestApi();
    Currency currency = requestApi.CurrencyCheck();

    double usd = currency.getConversion_rates().get("USD");
    double ars = currency.getConversion_rates().get("ARS");
    double eur = currency.getConversion_rates().get("EUR");
    double gbp = currency.getConversion_rates().get("GBP");
    double jpy = currency.getConversion_rates().get("JPY");
    double krw = currency.getConversion_rates().get("KRW");

    public GeneralCalc() throws IOException, InterruptedException {
    }

    public boolean checkAmount(double cantidad) {

        if (cantidad < 0){
            System.out.println("The amount entered is not valid. Please enter a new amount.");
        } else {
            return false;
        }
        return false;
    }

    public String convertAmount(int n, double cantidad) {
        String result = "";
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        if (n == 1)result = cantidad + " USD >>>> " + decimalFormat.format(cantidad * ars) + " ARS.";

        else if (n == 2)result = cantidad + " ARS >>>> " + decimalFormat.format(cantidad / usd) + " USD.";

        else if (n == 3)result = cantidad + " ARS >>>> " + decimalFormat.format(cantidad * eur) + " EUR.";

        else if (n == 4)result = cantidad + " EUR >>>> " + decimalFormat.format(cantidad / ars ) + " ARS.";

        else if (n == 5)result = cantidad + " ARS >>>> " + decimalFormat.format(cantidad * gbp ) + " GBP.";

        else if (n == 6)result = cantidad + " GBP >>>> " + decimalFormat.format(cantidad / ars) + " ARS.";

        else if (n == 7)result = cantidad + " ARS >>>> " + decimalFormat.format(cantidad / krw) + " KRW.";

        else if (n == 8)result = cantidad + " KRW >>>> " + decimalFormat.format(cantidad / ars) + " ARS.";

        else if (n == 9)result = cantidad + " ARS >>>> " + decimalFormat.format(cantidad / jpy) + " JPY.";

        else if (n == 10)result = cantidad + " JPY >>>> " + decimalFormat.format(cantidad / ars) + " ARS.";

        return result;
    }
}
