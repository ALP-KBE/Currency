package ALP.KBECurrency.RabbitMQ;

import ALP.KBECurrency.Converter;
import ALP.RabbitMessage;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
@RabbitListener(queues = "currency-queue", id = "listener")
public class RabbitMQReceiver {

    @Autowired
    Converter converter;

    @RabbitHandler
    public void receiver(RabbitMessage message) {
        System.out.println("message angekommen " + message.getValue());
        if(message.getType().equals("getAdjustedPrice"))   {
            converter.handle(message);
        } else System.out.println("die angekommene Message hat den falschen Typ");
    }
}
