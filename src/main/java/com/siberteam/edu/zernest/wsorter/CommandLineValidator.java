package com.siberteam.edu.zernest.wsorter;

import org.apache.commons.cli.*;

import java.io.File;

public class CommandLineValidator {
    private Options options;
    private final HelpFormatter formatter = new HelpFormatter();
    private File inputFile;
    private File outputFile;
    private Class<?> loadedSorterClass;


    public void parseCommandLine(String[] args) throws ParseException,
            ClassNotFoundException {
        options = new Options();
        CommandLineParser parser = new DefaultParser();

        options.addRequiredOption("i", "inputFile", true,
                "File with URL addresses list");
        options.addRequiredOption("o", "outputFile", true,
                "Output file for recording the result");
        options.addRequiredOption("c", "sortClass", true,
                "Full qualified name of Sorter class");

        CommandLine cmd = parser.parse(options, args);
        inputFile = new File(cmd.getOptionValue("i"));
        outputFile = new File(cmd.getOptionValue("o"));
        loadedSorterClass = Class.forName(cmd.getOptionValue("c"));
    }

    public void printHelp() {
        String syntax = "Main";
        String usageHeader = "Example of Using word-sorter app";
        String usageFooter = "Usage example: -i inputFile.txt -o " +
                "outputFile.txt -c com.edu.sorters.AlphabeticalSorter";
        formatter.printHelp(syntax, usageHeader, options, usageFooter);
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public Class<?> getLoadedSorterClass() {
        return loadedSorterClass;
    }

    @Override
    public String toString() {
        return "CommandLineValidator" + "[" +
                "options=" + options +
                ", formatter=" + formatter +
                ", inputFile=" + inputFile +
                ", outputFile=" + outputFile +
                ", loadedSorterClass=" + loadedSorterClass +
                ']';
    }
}
