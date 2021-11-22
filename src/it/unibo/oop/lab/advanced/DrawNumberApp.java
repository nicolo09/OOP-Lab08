package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final String CONFIG_FILE_NAME = "res" + System.getProperty("file.separator") + "config.yml";
    private static final String LOG_FILE_NAME = "res" + System.getProperty("file.separator") + "log.txt";
    private static final String SEPARATOR_CHAR = ":";
    private static final String YAML_MINIMUM = "minimum";
    private static final String YAML_MAXIMUM = "maximum";
    private static final String YAML_ATTEMPTS = "attempts";
    private final DrawNumber model;
    private final List<DrawNumberView> views;
    private int min;
    private int max;
    private int attempts;

    private void loadFromFile() throws IOException {
        try (BufferedReader r = new BufferedReader(new FileReader(CONFIG_FILE_NAME))) {
            // Iterates all lines
            final Iterator<String> linesIterator = r.lines().iterator();
            while (linesIterator.hasNext()) {
                // Separate each line in his key-value
                final String[] param = linesIterator.next().split(SEPARATOR_CHAR);
                // Discriminate parameter
                switch (param[0].trim()) {
                case YAML_MINIMUM:
                    min = Integer.parseInt(param[1].trim());
                    break;
                case YAML_MAXIMUM:
                    max = Integer.parseInt(param[1].trim());
                    break;
                case YAML_ATTEMPTS:
                    attempts = Integer.parseInt(param[1].trim());
                    break;
                default:
                    throw new IOException("Wrong file format");
                }
            }
        }
    }

    /**
     * 
     */
    public DrawNumberApp() {
        views = new ArrayList<>();
        //Swing view
        this.views.add(new DrawNumberViewImpl());
        //Write on file view
        this.views.add(new DrawNumberViewOnFile(new File(LOG_FILE_NAME)));
        try {
            loadFromFile();
        } catch (IOException e) {
            for (final DrawNumberView view : this.views) {
                view.displayError(e.getMessage());
            }
            quit();
        }
        this.model = new DrawNumberImpl(min, max, attempts);
        for (final DrawNumberView view : this.views) {
            view.setObserver(this);
            view.start();
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view : this.views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : this.views) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView view : this.views) {
                view.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *                 ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
