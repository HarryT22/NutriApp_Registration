package com.example.demo.inbound;

import com.example.demo.inbound.messaging.Config;
import com.example.demo.model.registration.RegistrationRequest;
import com.example.demo.model.registration.RegistrationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.stream.binder.rabbit.properties.RabbitBinderConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;

@RestController
@RequestMapping(path="api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private final String REGISTER_SERVICE =  "register_service";
    private RabbitTemplate rabbitTemplate;
    @PostMapping
public String register(@RequestBody RegistrationRequest request){

/*rabbitTemplate.convertAndSend(Config.EXCHANGE, Config.ROUTING_KEY,request);*/
return registrationService.register(request);

}

}
