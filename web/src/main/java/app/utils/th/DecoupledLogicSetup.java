package app.utils.th;

import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.annotation.PostConstruct;

@Component
public class DecoupledLogicSetup {
    private final SpringResourceTemplateResolver resolver;

    public DecoupledLogicSetup(SpringResourceTemplateResolver resolver) {
        this.resolver = resolver;
    }

    @PostConstruct
    public void init(){
        resolver.setUseDecoupledLogic(true);
    }

}
