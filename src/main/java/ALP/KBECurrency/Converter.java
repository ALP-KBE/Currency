package ALP.KBECurrency;

public class Converter{

    private static final float DOLLAR_EURO = 0.9467f;
    private static final float DOLLAR_DOLLAR = 1f;
    private static final float DOLLAR_KYAT = 1851.1528f;
    private static final float DOLLAR_YEN = 127.5145f;
    private static final float DOLLAR_RIEL = 4061.3000f;

    public static String dollarToEuro(String value) {
        return String.valueOf(Float.valueOf(value) * DOLLAR_EURO);
    }

    public static String dollarToKyat(String value) {
        return String.valueOf(Float.valueOf(value) * DOLLAR_KYAT);
    }

    public static String dollarToYen(String value) {
        return String.valueOf(Float.valueOf(value) * DOLLAR_YEN);
    }

    public static String dollarToRiel(String value) {
        return String.valueOf(Float.valueOf(value) * DOLLAR_RIEL);
    }

    public static String dollarToDollar(String value) {
        return String.valueOf(Float.valueOf(value) * DOLLAR_DOLLAR);
    }
}
