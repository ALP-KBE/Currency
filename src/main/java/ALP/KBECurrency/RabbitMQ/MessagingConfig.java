package ALP.KBECurrency.RabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    static final String TOPIC_EXCHANGE_NAME = "component-exchange";

    @Bean
    public Queue mainQueue() {
        return new Queue("main-queue");
    }

    @Bean
    public Queue currencyQueue()    {return new Queue("currency-queue");}

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }


    @Bean
    public Binding currencyBinding(Queue currencyQueue, TopicExchange exchange) {
        return BindingBuilder.bind(currencyQueue).to(exchange).with("currency-key");
    }
    @Bean
    public Binding mainBinding(Queue mainQueue, TopicExchange exchange) {
        return BindingBuilder.bind(mainQueue).to(exchange).with("main-key");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
