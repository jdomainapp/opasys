package jda.app.opasys.opa;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import jda.app.opasys.opa.modules.orgasset.model.OrgAsset;
import jda.modules.msacommon.connections.UserContextInterceptor;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;
import jda.modules.msacommon.controller.InterfaceController;
import jda.modules.msacommon.controller.InterfaceControllerRegistry;
import jda.modules.msacommon.controller.ServiceRegistry;
import jda.modules.msacommon.events.model.ChangeModel;
import jda.modules.msacommon.messaging.kafka.KafkaChangeAction;


@SpringBootApplication
@RefreshScope
@EnableEurekaClient
//@EnableBinding(Sink.class)
public class OPAServiceApp {
	private static final Logger logger = LoggerFactory.getLogger(OPAServiceApp.class);
	
	public static void main(String[] args) {
		final ServiceRegistry serviceRegistry = ServiceRegistry.getInstance();
		final ControllerRegistry controllerRegistry = ControllerRegistry.getInstance();
		final InterfaceControllerRegistry interfaceControllerRegistry = InterfaceControllerRegistry.getInstance();
		ApplicationContext ctx = SpringApplication.run(OPAServiceApp.class, args);
		ctx.getBeansOfType(PagingAndSortingRepository.class).forEach((k, v) -> {serviceRegistry.put(k, v);
		System.out.println("CHECK SERVICES: "+ k +"_"+v);
			});
		ctx.getBeansOfType(DefaultController.class).forEach((k, v) -> {controllerRegistry.put(k, v);
		System.out.println("CHECK Controller: "+ k +"_"+v);
			});
		ctx.getBeansOfType(InterfaceController.class).forEach((k, v) -> {interfaceControllerRegistry.put(k, v);
		System.out.println("CHECK IntefaceController: "+ k +"_"+v);
			});
	}

//
//	@StreamListener(Sink.INPUT)
//	public void processChanges(ChangeModel<Integer> model) {
//
//		logger.debug("Received a message of type " + model.getType());
//		if (model.getAction().equals(KafkaChangeAction.CREATED) || model.getAction().equals(KafkaChangeAction.UPDATED) || model.getAction().equals(KafkaChangeAction.DELETED)) {
//			logger.debug("Received a {} event from the OrgAsset service for user id {}",model.getAction(), model.getId());
//			DefaultController<OrgAsset, Integer> controller = ControllerRegistry.getInstance().get(OrgAsset.class);
//			controller.executeReceivedEvent(model.getAction(), model.getId(), model.getPath());
//		} else {
//			logger.error("Received an UNKNOWN event from the OrgAsset service of type {}", model.getType());
//		}
//
//	}
	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasenames("messages");
		return messageSource;
	}
	
	@SuppressWarnings("unchecked")
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate(){
		RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors==null){
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        }
        else{
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
	}

}
