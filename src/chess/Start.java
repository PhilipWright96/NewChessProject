package chess;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class Start {
    static Logger logger = Logger.getLogger(Start.class);
    public static void main(String[] args){
        BasicConfigurator.configure();
        logger.info("Hello World!");
    }
}
