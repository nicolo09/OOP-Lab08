package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


public class DrawNumberViewOnFile implements DrawNumberView {

    private DrawNumberViewObserver observer;
    private PrintStream output;
    private static final String NEW_GAME = ": a new game starts!";

    public DrawNumberViewOnFile(final File file) {
        try {
            output = new PrintStream(file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            observer.quit();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        output.println("Game started.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void numberIncorrect() {
        output.println("Incorrect Number... try again");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            output.println(res.getDescription());
            return;
        case YOU_WON:
            output.println(res.getDescription() + NEW_GAME);
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void limitsReached() {
        output.println("You lost" + NEW_GAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(final String message) {
        output.println("ERROR" + message);
    }

}
