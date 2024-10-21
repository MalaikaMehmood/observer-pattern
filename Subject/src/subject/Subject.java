/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subject;

/**
 *
 * @author CUI
 */
import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            if (observer.isActive()) {
                observer.update();
            }
        }
    }
}

abstract class Observer {
    protected Subject subject;
    protected boolean status; // Active status

    public abstract void update();

    public boolean isActive() {
        return status;
    }

    public void setActive(boolean status) {
        this.status = status;
    }
}

class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
        this.status = true; // Default status is active
    }

    @Override
    public void update() {
        System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
    }
}

class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
        this.status = true; // Default status is active
    }

    @Override
    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}

class HexaObserver extends Observer {

    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
        this.status = true; // Default status is active
    }

    @Override
    public void update() {
        System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase());
    }
}

class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        BinaryObserver binaryObserver = new BinaryObserver(subject);
        OctalObserver octalObserver = new OctalObserver(subject);
        HexaObserver hexaObserver = new HexaObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15); // All observers are active

        // Deactivate Binary Observer
        System.out.println("Deactivating Binary Observer");
        binaryObserver.setActive(false);

        System.out.println("Second state change: 10");
        subject.setState(10); // Only Octal and Hexa observers are notified

        // Activate Binary Observer
        System.out.println("Activating Binary Observer");
        binaryObserver.setActive(true);

        System.out.println("Third state change: 20");
        subject.setState(20); // All observers are notified again
    }
}