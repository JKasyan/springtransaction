package spring.transaction;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.transaction.service.LibraryService;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("desc.xml");
        LibraryService service = context.getBean(LibraryService.class);
        service.insertBook();
        logger.info("end...");
    }
}
