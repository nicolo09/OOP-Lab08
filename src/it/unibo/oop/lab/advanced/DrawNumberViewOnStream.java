package it.unibo.oop.lab.advanced;

import java.io.PrintStream;

public class DrawNumberViewOnStream implements DrawNumberView {

    private final PrintStream output;
    private static final String NEW_GAME = ": a new game starts!";


    public DrawNumberViewOnStream(final PrintStream stream) {
        output = stream;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
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
