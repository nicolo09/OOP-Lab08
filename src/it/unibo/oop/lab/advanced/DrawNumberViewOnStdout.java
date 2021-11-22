package it.unibo.oop.lab.advanced;

public class DrawNumberViewOnStdout extends DrawNumberViewOnStream implements DrawNumberView {

    DrawNumberViewOnStdout() {
        super(System.out);
    }

}
