package ALP.KBECurrency;

import ALP.KBECurrency.RabbitMQ.RabbitMQSender;
import ALP.RabbitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Converter{
    @Autowired
    RabbitMQSender currencySender;

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

    public void handle(RabbitMessage message)    {
        Double basePrice=(Double) message.getValue();
        float basePriceFloat= basePrice.floatValue();
        float adjustedPrice;

        switch ((String) message.getAdditionalField()) {
            case "KYAT" -> adjustedPrice = dollarToKyat(basePriceFloat);
            case "EURO" -> adjustedPrice = dollarToEuro(basePriceFloat);
            case "YEN" -> adjustedPrice = dollarToYen(basePriceFloat);
            case "RIEL" -> adjustedPrice = dollarToRiel(basePriceFloat);
            case "DOLLAR" -> adjustedPrice = dollarToDollar(basePriceFloat);
            default -> {
                System.out.println("keine Enum mitgeliefert :/");
                adjustedPrice = dollarToDollar(basePriceFloat);
            }
        }
        RabbitMessage rabbitMessage=new RabbitMessage("currency", adjustedPrice);
        currencySender.send(rabbitMessage);
    }
}
