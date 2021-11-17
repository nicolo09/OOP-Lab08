package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private String current;
    private List<String> printList = new ArrayList<>();

    @Override
    public void setString(final String s) throws IllegalArgumentException {
        this.current = s;
    }

    @Override
    public String getString() {
        return this.current;
    }

    @Override
    public List<String> history() {
        return new ArrayList<String>(printList);
    }

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
