package com.siberteam.edu.zernest.wsorter;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Options options = new Options();
        CommandLineParser parser = new DefaultParser();

        options.addRequiredOption("i", "inputFile", true,
                "File with URL addresses list");
        options.addRequiredOption("o", "outputFile", true,
                "Output file for recording the result");
        options.addRequiredOption("c", "sortClass", true,
                "Full qualified name of Sorter class");

        final HelpFormatter formatter = new HelpFormatter();
        final String syntax = "Main";
        final String usageHeader = "Example of Using word-sorter app";
        final String usageFooter = "Example: -i inputFile.txt -o " +
                "outputFile.txt -c com.edu.sorters.AlphabeticalSorter";

        try {
            CommandLine cmd = parser.parse(options, args);

            File inputFile = new File(cmd.getOptionValue("i"));
            File outputFile = new File(cmd.getOptionValue("o"));
            Class<?> loadedSorterClass = Class.forName(cmd.getOptionValue("c"));

            if (!inputFile.exists()) {
                throw new WordSorterAppException(
                        WordSorterExitCode.CANNOT_OPEN_INPUT,
                        inputFile.getName());
            }

//            if (outputFile.exists() && outputFile.isFile()) {
//                throw new WordSorterAppException(
//                        WordSorterExitCode.FILE_ALREADY_EXISTS,
//                        outputFile.getName());
//            }
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
                formatter.printHelp(syntax, usageHeader, options, usageFooter);
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
