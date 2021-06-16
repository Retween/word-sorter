package com.siberteam.edu.zernest.wsorter;

public enum WordSorterExitCode {
    COMMAND_LINE_USAGE(64, "Command line usage error"),
    CANNOT_OPEN_INPUT(66, "File not found"),
    INPUT_OUTPUT(74, "Input/Output exception was caught"),
    FILE_ALREADY_EXISTS(74, "File already exists"),
    CLASS_NOT_FOUND(64, "Class not found"),
    INSTANTIATION(64, "Instantiation exception was caught"),
    ILLEGAL_ACCESS(64, "Illegal Access exception was caught"),
    UNEXPECTED_ERROR(64, "Unexpected error was caught");

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
