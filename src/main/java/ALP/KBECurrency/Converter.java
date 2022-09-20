package ALP.KBECurrency;

public class Converter{

    private static final float DOLLAR_EURO = 0.9467f;
    private static final float DOLLAR_DOLLAR = 1f;
    private static final float DOLLAR_KYAT = 1851.1528f;
    private static final float DOLLAR_YEN = 127.5145f;
    private static final float DOLLAR_RIEL = 4061.3000f;

    public static String dollarToEuro(float value) {
        return String.valueOf(value * DOLLAR_EURO);
    }

    public static String dollarToKyat(float value) {
        return String.valueOf(value * DOLLAR_KYAT);
    }

    public static String dollarToYen(float value) {
        return String.valueOf(value * DOLLAR_YEN);
    }

    public static String dollarToRiel(float value) {
        return String.valueOf(value * DOLLAR_RIEL);
    }

    public static String dollarToDollar(float value) {
        return String.valueOf(value * DOLLAR_DOLLAR);
    }
}
