package com.siberteam.edu.zernest.wsorter;

public enum WordSorterExitCode {
    COMMAND_LINE_USAGE(64, "usage: url-dictionary\n" +
            " -i,--inputFile <arg>       File with URL addresses list\n" +
            " -o,--outputFile <arg>      Output file for recording the " +
            "final dictionary\n" +
            " -c,--sortClass <arg>   full qualified name of Sorter class\n" +
            "Example: -i inputFile.txt -o outputFile.txt -c " +
            "com.edu.sorters.AlphabeticalSorter"),
    CANNOT_OPEN_INPUT(66, "File not found"),
    INPUT_OUTPUT(74, "Input/Output exception was caught"),
    FILE_ALREADY_EXISTS(74, "File already exists"),
    CLASS_NOT_FOUND(1, "Class not found"),
    INSTANTIATION(1, "Instantiation exception was caught"),
    ILLEGAL_ACCESS(1, "Illegal Access exception was caught");

    private final int code;
    private final String description;

    WordSorterExitCode(int code, String description) {
        this.code = code;
        this.description = description;
    }


    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "WordSorterExitCode" + "[" +
                "code=" + code +
                ", description='" + description + '\'' +
                ']';
    }
}
