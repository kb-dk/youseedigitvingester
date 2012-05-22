package dk.statsbiblioteket.digitv.youseeingester;

import dk.statsbiblioteket.util.Files;
import org.apache.commons.cli.*;
import org.apache.log4j.xml.SAXErrorHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import java.io.File;
import java.io.IOException;

/**
 * @author jrg
 */
public class YouseeDigitvIngester {
    private static Options options;

    private static final Option FILENAME_OPTION = new Option("filename", true,
            "The sb filename of the media file");
    private static final Option STARTTIME_OPTION = new Option("starttime", true,
            "The recording start time of the file");
    private static final Option STOPTIME_OPTION = new Option("stoptime", true,
            "The recording stop time of the file");
    private static final Option CHANNELID_OPTION = new Option("channelid", true,
            "The recording channel ID of the file");
    private static final Option CONFIG_OPTION = new Option("config", true,
            "The config file for the YouSee DigiTV Ingester");

    static {
        options = new Options();
        options.addOption(FILENAME_OPTION);
        options.addOption(STARTTIME_OPTION);
        options.addOption(STOPTIME_OPTION);
        options.addOption(CHANNELID_OPTION);
        options.addOption(CONFIG_OPTION);
        for (Object option : options.getOptions()) {
            if (option instanceof Option) {
                Option option1 = (Option) option;
                option1.setRequired(true);
            }
        }


    }

    public static void printUsage() {
        final HelpFormatter usageFormatter = new HelpFormatter();
        usageFormatter.printHelp("youseeDigitvIngester", options, true);
    }


    public static void main(String[] args) {
        CommandLineParser parser = new PosixParser();
        CommandLine cmd;
        IngestContext context = new IngestContext();  // To hold input params

        try {
            cmd = parser.parse(options, args);
        } catch (org.apache.commons.cli.ParseException e) {
            parseError(e.toString());
            return;
        }

        // Get each parameter
        String filename = cmd.getOptionValue(FILENAME_OPTION.getOpt());
        if (filename == null){
            parseError(FILENAME_OPTION.toString());
            return;
        }
        context.setFilename(filename);

        String starttime = cmd.getOptionValue(STARTTIME_OPTION.getOpt());
        if (starttime == null){
            parseError(STARTTIME_OPTION.toString());
            return;
        }
        context.setStarttime(starttime);

        String stoptime = cmd.getOptionValue(STOPTIME_OPTION.getOpt());
        if (stoptime == null){
            parseError(STOPTIME_OPTION.toString());
            return;
        }
        context.setStoptime(stoptime);

        String channelid = cmd.getOptionValue(CHANNELID_OPTION.getOpt());
        if (channelid == null){
            parseError(CHANNELID_OPTION.toString());
            return;
        }
        context.setChannelid(channelid);

        String config = cmd.getOptionValue(CONFIG_OPTION.getOpt());
        if (config == null){
            parseError(CONFIG_OPTION.toString());
            return;
        }
        context.setConfig(config);

        // Get properties
        String filenameAndPath = context.getConfig();
        Properties properties = null;
        try {
            properties = getPropertiesFromPropertyFile(filenameAndPath);
        } catch (Exception e) {
            System.err.println("Could not load config file from path: "
                    + filenameAndPath);
            exit(13);
        }
        // TODO Set options anything gotten from the config file


        // Do the actual ingesting
        String output = insertDataIntoDigitvDatabase(context);

        System.out.println(output);
        exit(0);
    }

    private static String insertDataIntoDigitvDatabase(IngestContext context) {
        String output;

        // TODO implement...

        output = "{"
                + "   \"id\" : \"<en id>\""     // TODO insert real value
                + "}";
        return output;
    }

    private static Properties getPropertiesFromPropertyFile(
            String filenameAndPath) throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(filenameAndPath));
        return properties;
    }

    private static void parseError(String message){
        System.err.println("Error parsing arguments");
        System.err.println(message);
        printUsage();
        exit(1);
    }

    private static void exit(int code){
        System.exit(code);
    }

}
