package ru.serce.jmhspring;

import org.openjdk.jmh.runner.Defaults;
import org.openjdk.jmh.runner.NoBenchmarksException;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.CommandLineOptionException;
import org.openjdk.jmh.runner.options.CommandLineOptions;
import org.openjdk.jmh.runner.options.VerboseMode;
import org.openjdk.jmh.util.Optional;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.GenericXmlContextLoader;

import java.io.IOException;

/**
 * @author Sergey Tselovalnikov
 * @since 13.06.2014
 */
public class JMHSpringMain {

    /**
     * Main Spring benchmark entry point
     *
     * @see org.openjdk.jmh.Main
     */
    public static void main(String[] argv) throws IOException, RunnerException {
        try {
            Optional<String> appContextPath = extractAppContext(argv);
            CommandLineOptions cmdOptions = new CommandLineOptions(argv);

            Runner runner = new Runner(cmdOptions);

            if (cmdOptions.shouldHelp()) {
                cmdOptions.showHelp();
                return;
            }

            if (cmdOptions.shouldList()) {
                runner.list();
                return;
            }

            if (cmdOptions.shouldListProfilers()) {
                cmdOptions.listProfilers();
                return;
            }

            if (cmdOptions.shouldListResultFormats()) {
                cmdOptions.listResultFormats();
                return;
            }

            initContext(appContextPath);
            try {
                runner.run();
            } catch (NoBenchmarksException e) {
                System.err.println("No matching benchmarks. Miss-spelled regexp?");

                if (cmdOptions.verbosity().orElse(Defaults.VERBOSITY) != VerboseMode.EXTRA) {
                    System.err.println("Use " + VerboseMode.EXTRA + " verbose mode to debug the pattern matching.");
                } else {
                    runner.list();
                }
            } catch (RunnerException e) {
                System.err.println("ERROR: " + e.getMessage());
            }

        } catch (CommandLineOptionException e) {
            System.err.println("Error parsing command line:");
            System.err.println(" " + e.getMessage());
        }
    }

    private static Optional<String> extractAppContext(String[] argv) {
        if(argv == null)
            return Optional.none();
        for (int i = 0; i < argv.length; i++) {
            if ("-context".equals(argv[i])) {
                if(i != argv.length -1) {
                    String result = argv[i + 1];
                    argv[i] = "";
                    argv[i + 1] = "";
                    return Optional.eitherOf(result);
                }
            }
        }
        return Optional.none();
    }

    private static void initContext(Optional<String> appContextPath) {
        String applicationContext = appContextPath.orElse("applicationContext.xml");

        GenericXmlContextLoader loader = new GenericXmlContextLoader();
        ConfigurableApplicationContext context;
        try {
            context = loader.loadContext(applicationContext);
        } catch (Exception e) {
            System.err.println("Error loading context '" + applicationContext +"', error: " + e.getMessage());
            return;
        }
        System.out.println("Context loaded" + context.toString());
        SpringContext.setContext(context);
    }
}
