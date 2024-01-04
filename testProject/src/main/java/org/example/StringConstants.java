package org.example;

/**
 * Класс для хранения строковых констант
 * В классе {@link Main} используется остаточно много строк, чтоб выделить им отдельное место
 */
public class StringConstants {
    public static final String URL = "https://github.com/PeacockTeam/new-job/releases/download/v1.0/lng-big.7z";

    public static final String PATTERN = "^(\\d*|)(;(\\d*|))*$";

    public static final String DEFAULT_OUTPUT = "file.txt";

    public static final String FINAL_TITLE = "A number of groups with more than 1 element: ";

    public static final String NOT_FOUND_EXCEPTION_MESSAGE = "Output file cannot be found";

    public static final String IOE_EXCEPTION_MESSAGE = "It is not possible to write to the output file";

    public static final String READ_FILE_MESSAGE = "Reading the input file";

    public static final String UNION_FIND_MESSAGE = "Grouping strings into a union-find structure";

    public static final String GROUP_MESSAGE = "Grouping rows into groups";

    public static final String GROUP_OUTPUT_MESSAGE = "Writing the result to the output file (If the file was not specified, then this is file.txt in the execution directory)";

    public static final String URL_ERROR_MESSAGE = "It is not possible to access the link https://github.com/PeacockTeam/new-job/releases/download/v1.0/lng-4.txt.gz";

    public static final String EXTERNAL_FILE_ERROR_MESSAGE = "The problem of reading the archive of the input file";
}
