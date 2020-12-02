package com.bse.homeshop;

public interface Writer {
    /**
     * Start writing process
     */
    void start();

    /**
     * Write on line
     * @param line The line to write
     */
    void writeLine(String line);

    /**
     * Stop writing process
     */
    void stop();
}
