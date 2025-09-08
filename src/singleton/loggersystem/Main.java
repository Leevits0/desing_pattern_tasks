package singleton.loggersystem;


public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.write("Program started");
        logger.setFileName("new_log.txt"); // switch to another file
        logger.write("Simulation started");
        logger.write("Processing data...");
        logger.write("Simulation finished");

        logger.close();
    }
}
