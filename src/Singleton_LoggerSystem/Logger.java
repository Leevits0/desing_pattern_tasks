package Singleton_LoggerSystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static Logger instance;       // the single instance
    private BufferedWriter writer;        // writer for the log file
    private String fileName = "default_log.txt"; // default file name

    // private constructor so no one else can create it
    private Logger() {
        openWriter();
    }

    // global access point
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // open a writer for the current fileName
    private void openWriter() {
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true)); // append mode
        } catch (IOException e) {
            System.err.println("Failed to open log file: " + e.getMessage());
        }
    }

    // change log file
    public synchronized void setFileName(String newFileName) {
        close(); // close old file
        this.fileName = newFileName;
        openWriter(); // open new file
    }

    // write a message
    public synchronized void write(String message) {
        try {
            if (writer != null) {
                writer.write(message);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.err.println("Failed to write log: " + e.getMessage());
        }
    }

    // close the writer
    public synchronized void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to close log: " + e.getMessage());
        }
    }
}
