package ALP.KBECurrency.RabbitMQ;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ALP.RabbitMessage;
import ALP.KBECurrency.Converter;

@Component
@RabbitListener(queues = "main-queue", id = "listener")
public class RabbitMQReceiver {

    @Autowired
    RabbitMQSender sender;

    @RabbitHandler
    public void receiver(RabbitMessage message) {
        System.out.println("message angekommen " + message.getValue());
        switch (message.getType()) {
            case "getEuro":
                if (message.getValue() instanceof ArrayList) {
                    ArrayList<ALP.KBECurrency.Model.Component> newArrayList = new ArrayList<>();
                    ArrayList<LinkedHashMap<String, String>> arrayList = (ArrayList<LinkedHashMap<String, String>>) message
                            .getValue();
                    for (LinkedHashMap<String, String> linkedHashMap : arrayList) {
                        ALP.KBECurrency.Model.Component component = processLinkedHashMap(linkedHashMap);
                        component.setPreis(Converter.dollarToEuro(Float.valueOf(component.getPreis())));
                        newArrayList.add(component);
                    }
                    sender.send(newArrayList);
                }
                break;
            case "getDollar":
                if (message.getValue() instanceof ArrayList) {
                    ArrayList<ALP.KBECurrency.Model.Component> newArrayList = new ArrayList<>();
                    ArrayList<LinkedHashMap<String, String>> arrayList = (ArrayList<LinkedHashMap<String, String>>) message
                            .getValue();
                    for (LinkedHashMap<String, String> linkedHashMap : arrayList) {
                        ALP.KBECurrency.Model.Component component = processLinkedHashMap(linkedHashMap);
                        component.setPreis(Converter.dollarToDollar(Float.valueOf(component.getPreis())));
                        newArrayList.add(component);
                    }
                    sender.send(newArrayList);
                }
                break;
            case "getKyat":
                if (message.getValue() instanceof ArrayList) {
                    ArrayList<ALP.KBECurrency.Model.Component> newArrayList = new ArrayList<>();
                    ArrayList<LinkedHashMap<String, String>> arrayList = (ArrayList<LinkedHashMap<String, String>>) message
                            .getValue();
                    for (LinkedHashMap<String, String> linkedHashMap : arrayList) {
                        ALP.KBECurrency.Model.Component component = processLinkedHashMap(linkedHashMap);
                        component.setPreis(Converter.dollarToKyat(Float.valueOf(component.getPreis())));
                        newArrayList.add(component);
                    }
                    sender.send(newArrayList);
                }
                break;
            case "getYen":
                if (message.getValue() instanceof ArrayList) {
                    ArrayList<ALP.KBECurrency.Model.Component> newArrayList = new ArrayList<>();
                    ArrayList<LinkedHashMap<String, String>> arrayList = (ArrayList<LinkedHashMap<String, String>>) message
                            .getValue();
                    for (LinkedHashMap<String, String> linkedHashMap : arrayList) {
                        ALP.KBECurrency.Model.Component component = processLinkedHashMap(linkedHashMap);
                        component.setPreis(Converter.dollarToYen(Float.valueOf(component.getPreis())));
                        newArrayList.add(component);
                    }
                    sender.send(newArrayList);
                }
                break;
            case "getRiel":
                if (message.getValue() instanceof ArrayList) {
                    ArrayList<ALP.KBECurrency.Model.Component> newArrayList = new ArrayList<>();
                    ArrayList<LinkedHashMap<String, String>> arrayList = (ArrayList<LinkedHashMap<String, String>>) message
                            .getValue();
                    for (LinkedHashMap<String, String> linkedHashMap : arrayList) {
                        ALP.KBECurrency.Model.Component component = processLinkedHashMap(linkedHashMap);
                        component.setPreis(Converter.dollarToRiel(Float.valueOf(component.getPreis())));
                        newArrayList.add(component);
                    }
                    sender.send(newArrayList);
                }
                break;
        }
    }

    private ALP.KBECurrency.Model.Component processLinkedHashMap(LinkedHashMap<String, String> linkedHashMap) {
        ALP.KBECurrency.Model.Component component = new ALP.KBECurrency.Model.Component();
        linkedHashMap.forEach((key, val) -> component.set(key, val));
        return component;
    }
}
