import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        log.info("Program Initiating");
        try(ConfigurableApplicationContext context =
                    new ClassPathXmlApplicationContext("beans.xml")){
            NumberGeneratorImpl generator = context.getBean("numberGenerator", NumberGeneratorImpl.class);
            System.out.println(generator.next());
            GameImpl game = context.getBean(GameImpl.class);
            game.reset();
            log.info(String.valueOf(game.getNumber()));
        }
    }
}
