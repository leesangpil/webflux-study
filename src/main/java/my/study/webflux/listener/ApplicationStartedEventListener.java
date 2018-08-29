package my.study.webflux.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by leesangpil on 2018. 8. 29..
 */
@Component
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent>{
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.println("started app");
    }
}
