package com.siberteam.edu.zernest.wsorter;

import org.apache.commons.cli.ParseException;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CommandLineValidator validator = new CommandLineValidator();
        try {
            validator.parseCommandLine(args);
            File inputFile = validator.getInputFile();
            File outputFile = validator.getOutputFile();
            Class<?> loadedSorterClass = validator.getLoadedSorterClass();

            if (!inputFile.exists()) {
                throw new WordSorterAppException(
                        WordSorterExitCode.CANNOT_OPEN_INPUT,
                        inputFile.getName());
            }

            if (outputFile.exists() && outputFile.isFile()) {
                throw new WordSorterAppException(
                        WordSorterExitCode.FILE_ALREADY_EXISTS,
                        outputFile.getName());
            }
            try (InputStream inputStream = new FileInputStream(inputFile);
                 OutputStream outputStream = new FileOutputStream(outputFile)) {


                InputStreamToListReader reader =
                        new InputStreamToListReader(inputStream);
                ListToOutputStreamWriter writer =
                        new ListToOutputStreamWriter(outputStream);

                List<String> words = reader.getWordsList();

                @SuppressWarnings("unchecked")
                Comparator<String> comparator = (Comparator<String>)
                        loadedSorterClass.newInstance();
                words.sort(comparator);

                writer.writeListToFile(words);
            }
        } catch (Exception e) {
            if (e instanceof ParseException) {
                validator.printHelp();
                defaultHandler.handleException(WordSorterExitCode
                        .COMMAND_LINE_USAGE, e);
            }

            IErrorHandler handler = errorHandlerMap.get(e.getClass());
            if (handler == null) {
                defaultHandler.handleException(WordSorterExitCode
                        .UNEXPECTED_ERROR, e);
            } else {
                if (e instanceof WordSorterAppException) {
                    handler.handleException(((WordSorterAppException) e)
                            .getExitCode(), e);
                }
                handler.handleException(exceptionExitCode
                        .get(e.getClass()), e);
            }
        }
    }

    private static final Map<Class<?>, IErrorHandler> errorHandlerMap =
            new HashMap<>();

    private static final Map<Class<?>, WordSorterExitCode> exceptionExitCode =
            new HashMap<>();

    private static final IErrorHandler defaultHandler = new IErrorHandler() {
        @Override
        public void handleException(WordSorterExitCode exitCode,
                                    Exception e) {
            IErrorHandler.super.handleException(exitCode, e);
        }
    };

    static {
        errorHandlerMap.put(ParseException.class, defaultHandler);
        errorHandlerMap.put(WordSorterAppException.class, defaultHandler);
        errorHandlerMap.put(IOException.class, defaultHandler);
        errorHandlerMap.put(ClassNotFoundException.class, defaultHandler);
        errorHandlerMap.put(InstantiationException.class, defaultHandler);
        errorHandlerMap.put(IllegalAccessException.class, defaultHandler);
    }

    static {
        exceptionExitCode.put(ParseException.class, WordSorterExitCode
                .COMMAND_LINE_USAGE);
        exceptionExitCode.put(IOException.class, WordSorterExitCode
                .INPUT_OUTPUT);
        exceptionExitCode.put(ClassNotFoundException.class, WordSorterExitCode
                .CLASS_NOT_FOUND);
        exceptionExitCode.put(InstantiationException.class, WordSorterExitCode
                .INSTANTIATION);
        exceptionExitCode.put(IllegalAccessException.class, WordSorterExitCode
                .ILLEGAL_ACCESS);
    }

    public static void log(String message) {
        System.out.println(message);
    }
}
