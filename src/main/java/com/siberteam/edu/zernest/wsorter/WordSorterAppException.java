package com.siberteam.edu.zernest.wsorter;

public class WordSorterAppException extends Exception {
    private final WordSorterExitCode exitCode;

    public WordSorterAppException(WordSorterExitCode exitCode,
                                  String message) {
        super(message);
        this.exitCode = exitCode;
    }

    public WordSorterAppException(WordSorterExitCode exitCode) {
        super("Word Sorter App Exception");
        this.exitCode = exitCode;
    }

    public WordSorterExitCode getExitCode() {
        return exitCode;
    }

    @Override
    public String toString() {
        return "WordSorterAppException" + "[" +
                "exitCode=" + exitCode +
                ']';
    }
}
