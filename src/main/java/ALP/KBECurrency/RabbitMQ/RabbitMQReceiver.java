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
import ALP.KBECurrency.Model.Product;

@org.springframework.stereotype.Component
@RabbitListener(queues = "currency-queue", id = "listener")
public class RabbitMQReceiver {

    @Autowired
    RabbitMQSender sender;
    final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitHandler
    public void receiver(RabbitMessage message) throws JsonMappingException, JsonProcessingException {
        if (message.getAdditionalField() == null) {
            message.setType("product");
            sender.send(message);
            return;
        }
        if (message.getType().equals("components")) {
            RabbitMessage rabbitMessage = new RabbitMessage("component", "");
            ArrayList<Component> components = objectMapper.readValue((String) message.getValue(),
                    new TypeReference<ArrayList<Component>>() {
                    });
            switch (((String) message.getAdditionalField())) {
                case "euro":
                    for (Component component : components) {
                        component.setPreis(Converter.dollarToEuro(component.getPreis()));
                    }
                    break;
                case "dollar":
                    for (Component component : components) {
                        component.setPreis(Converter.dollarToDollar(component.getPreis()));
                    }
                    break;
                case "kyat":
                    for (Component component : components) {
                        component.setPreis(Converter.dollarToKyat(component.getPreis()));
                    }
                    break;
                case "yen":
                    for (Component component : components) {
                        component.setPreis(Converter.dollarToYen(component.getPreis()));
                    }
                    break;
                case "riel":
                    for (Component component : components) {
                        component.setPreis(Converter.dollarToRiel(component.getPreis()));
                    }
                    break;
            }
            rabbitMessage.setValue(objectMapper.writeValueAsString(components));
            sender.send(rabbitMessage);
        } else if (message.getType().equals("product")) {
            Product product = objectMapper.readValue((String) message.getValue(), new TypeReference<Product>() {
            });
            switch (((String) message.getAdditionalField())) {
                case "euro":
                    product.setPrice(Converter.dollarToEuro(product.getPrice()));
                    break;
                case "dollar":
                    product.setPrice(Converter.dollarToDollar(product.getPrice()));
                    break;
                case "kyat":
                    product.setPrice(Converter.dollarToKyat(product.getPrice()));
                    break;
                case "yen":
                    product.setPrice(Converter.dollarToYen(product.getPrice()));
                    break;
                case "riel":
                    product.setPrice(Converter.dollarToRiel(product.getPrice()));
                    break;
            }
            RabbitMessage rabbitMessage = new RabbitMessage("product", objectMapper.writeValueAsString(product));
            sender.send(rabbitMessage);
        } else if (message.getType().equals("products")) {
            ArrayList<Product> products = objectMapper.readValue((String) message.getValue(),
                    new TypeReference<ArrayList<Product>>() {
                    });
            if (products.stream().anyMatch(product -> product == null)) {
                message.setType("product");
                sender.send(message);
                return;
            }
            switch (((String) message.getAdditionalField())) {
                case "euro":
                    for (Product product : products) {
                        product.setPrice(Converter.dollarToEuro(product.getPrice()));
                    }
                    break;
                case "dollar":
                    for (Product product : products) {
                        product.setPrice(Converter.dollarToDollar(product.getPrice()));
                    }
                    break;
                case "kyat":
                    for (Product product : products) {
                        product.setPrice(Converter.dollarToKyat(product.getPrice()));
                    }
                    break;
                case "yen":
                    for (Product product : products) {
                        product.setPrice(Converter.dollarToYen(product.getPrice()));
                    }
                    break;
                case "riel":
                    for (Product product : products) {
                        product.setPrice(Converter.dollarToRiel(product.getPrice()));
                    }
                    break;
            }
            RabbitMessage rabbitMessage = new RabbitMessage("product", objectMapper.writeValueAsString(products));
            sender.send(rabbitMessage);
        }
    }
}
