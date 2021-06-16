package com.siberteam.edu.zernest.wsorter;

public interface IErrorHandler {
    default void handleException(WordSorterExitCode exitCode, Exception e) {
        Main.log(exitCode.getDescription() + "\n" + e + "\n" + e.getCause());
        System.exit(exitCode.getCode());
    }
}
