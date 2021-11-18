package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private String current;
    private final List<String> printList = new ArrayList<>();

    /**
     * Sets a string as the current of this controller.
     * 
     */
    @Override
    public void setString(final String s) throws IllegalArgumentException {
        if (s != null) {
            this.current = s;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @return the current set string
     */
    @Override
    public String getString() {
        return this.current;
    }

    /**
     * @return the printing history
     */
    @Override
    public List<String> history() {
        return new ArrayList<String>(printList);
    }

    /**
     * Prints the current set string on stdout.
     */
    @Override
    public void printCurrent() throws IllegalStateException {
        if (current != null) {
            System.out.println(this.current);
            printList.add(this.current);
        } else {
            throw new IllegalStateException("String was unset");
        }
    }
}
