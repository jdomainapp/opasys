package jda.app.opasys.orgasset.kafka.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import jda.modules.msacommon.connections.UserContext;
import jda.modules.msacommon.events.model.ChangeModel2;

@Component
public class SimpleSourceBean {
    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(Source source){
        this.source = source;
    }

    public void publishChange(String typeName, String action, int id, String path){
       logger.debug("Sending Kafka message {} for OrgAsset Id: {}", action, id);
        ChangeModel2 change =  new ChangeModel2(
        		typeName,
                action,
                id,
                path,
                UserContext.getCorrelationId());

        source.output().send(MessageBuilder.withPayload(change).build());
    }
}
