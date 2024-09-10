package books.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {

    private final JmsTemplate jmsTemplate;

    public JmsSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String objectAsString = objectMapper.writeValueAsString(obj);
            System.out.println("Sending a JMS Message: " + objectAsString);
            jmsTemplate.convertAndSend("bookQueue", objectAsString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
