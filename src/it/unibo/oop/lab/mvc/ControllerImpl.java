package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private String current;
    private final List<String> printList = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setString(final String s) {
        if (s != null) {
            this.current = s;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getString() {
        return this.current;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> history() {
        return new ArrayList<String>(printList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printCurrent() {
        if (current != null) {
            System.out.println(this.current);
            printList.add(this.current);
        } else {
            throw new IllegalStateException("String was unset");
        }
    }
}
