package ALP.KBECurrency;

public class Converter{

    private static final float DOLLAR_EURO = 0.9467f;
    private static final float DOLLAR_DOLLAR = 1f;
    private static final float DOLLAR_KYAT = 1851.1528f;
    private static final float DOLLAR_YEN = 127.5145f;
    private static final float DOLLAR_RIEL = 4061.3000f;

    public static float dollarToEuro(float value) {
        return value * DOLLAR_EURO;
    }

    public static float dollarToKyat(float value) {
        return value * DOLLAR_KYAT;
    }

    public static float dollarToYen(float value) {
        return value * DOLLAR_YEN;
    }

    public static float dollarToRiel(float value) {
        return value * DOLLAR_RIEL;
    }

    public static float dollarToDollar(float value) {
        return value * DOLLAR_DOLLAR;
    }
}
