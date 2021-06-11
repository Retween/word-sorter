package com.siberteam.edu.zernest.wsorter;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        Options options = new Options();
        CommandLineParser parser = new DefaultParser();

        options.addRequiredOption("i", "inputFile", true,
                "File with URL addresses list");
        options.addRequiredOption("o", "outputFile", true,
                "Output file for recording the result");
        options.addRequiredOption("c", "sortClass", true,
                "full qualified name of Sorter class");

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

            inputStream = new FileInputStream(inputFile);
            outputStream = new FileOutputStream(outputFile);


            InputStreamToListReader reader =
                    new InputStreamToListReader(inputStream);
            ListToOutputStreamWriter writer =
                    new ListToOutputStreamWriter(outputStream);
            List<String> words = reader.getDictionary();

            @SuppressWarnings("unchecked")
            Comparator<String> comparator = (Comparator<String>)
                    loadedSorterClass.newInstance();
            words.sort(comparator);

            writer.writeListToFile(words);

        } catch (ParseException e) {
            handleException(WordSorterExitCode.COMMAND_LINE_USAGE, e);
        } catch (WordSorterAppException e) {
            handleException(e.getExitCode(), e);
        } catch (IOException e) {
            handleException(WordSorterExitCode.INPUT_OUTPUT, e);
        } catch (ClassNotFoundException e) {
            handleException(WordSorterExitCode.CLASS_NOT_FOUND, e);
        } catch (InstantiationException e) {
            handleException(WordSorterExitCode.INSTANTIATION, e);
        } catch (IllegalAccessException e) {
            handleException(WordSorterExitCode.ILLEGAL_ACCESS, e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void handleException(WordSorterExitCode exitCode,
                                       Exception e) {
        log(exitCode.getDescription() + "\n" + e.getMessage() + "\n" +
                e.getCause());
        System.exit(exitCode.getCode());
    }

    public static void log(String message) {
        System.out.println(message);
    }
}
