package ALP.KBECurrency.RabbitMQ;

import java.util.ArrayList;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ALP.RabbitMessage;
import ALP.KBECurrency.Converter;
import ALP.KBECurrency.Model.Component;

@org.springframework.stereotype.Component
@RabbitListener(queues = "currency-queue", id = "listener")
public class RabbitMQReceiver {

    @Autowired
    RabbitMQSender sender;
    final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitHandler
    public void receiver(RabbitMessage message) throws JsonMappingException, JsonProcessingException {
        RabbitMessage rabbitMessage = new RabbitMessage("component", "");
        ArrayList<Component> components = objectMapper.readValue((String) message.getValue(),
                new TypeReference<ArrayList<Component>>() {
                });
        switch (((String) message.getAdditionalField())) {
            case "euro":
                for (Component component : components) {
                    component.setPreis(Converter.dollarToEuro(component.getPreis()));
                    }
                rabbitMessage.setValue(objectMapper.writeValueAsString(components));
                sender.send(rabbitMessage);
                break;
            case "dollar":
                for (Component component : components) {
                    component.setPreis(Converter.dollarToDollar(component.getPreis()));
                    }
                rabbitMessage.setValue(objectMapper.writeValueAsString(components));
                sender.send(rabbitMessage);
                break;
            case "kyat":
                for (Component component : components) {
                    component.setPreis(Converter.dollarToKyat(component.getPreis()));
                    }
                rabbitMessage.setValue(objectMapper.writeValueAsString(components));
                sender.send(rabbitMessage);
                break;
            case "yen":
                for (Component component : components) {
                    component.setPreis(Converter.dollarToYen(component.getPreis()));
                    }
                rabbitMessage.setValue(objectMapper.writeValueAsString(components));
                sender.send(rabbitMessage);
                break;
            case "riel":
                for (Component component : components) {
                    component.setPreis(Converter.dollarToRiel(component.getPreis()));
                    }
                rabbitMessage.setValue(objectMapper.writeValueAsString(components));
                sender.send(rabbitMessage);
                break;
        }
    }
}
