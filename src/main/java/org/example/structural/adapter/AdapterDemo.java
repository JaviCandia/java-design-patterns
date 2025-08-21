package org.example.structural.adapter;

interface ILoggerAdapter {
    void writeLog(String msg);
    void writeWarning(String msg);
    void writeError(String msg);
}

class LoggerAdapter implements ILoggerAdapter {

    private final String file;

    public LoggerAdapter(String file) {
        this.file = file;
    }

    @Override
    public void writeLog(String msg) {
        System.out.println("[" + file + " Log] " + msg);
    }

    @Override
    public void writeWarning(String msg) {
        System.out.println("\u001B[33m[" + file + " Warning] " + msg + "\u001B[0m");
    }

    @Override
    public void writeError(String msg) {
        System.err.println("[" + file + " Error] " + msg);
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        ILoggerAdapter logger = new LoggerAdapter("01-adapter.java");

        logger.writeLog("Normal Log Message");
        logger.writeWarning("Warning Message");
        logger.writeError("Something bad happened");
    }
}
