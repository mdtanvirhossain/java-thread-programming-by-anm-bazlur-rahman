package com.mdtanvirhossain.jtp.appendix.a3;

public class Person {
    private String name;
    private boolean active;

    public Person(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public synchronized void work(Corridor corridor, Person otherPerson) {
        while (active) {
            // wait for the resource to become available
            if (corridor.getOwner() != this) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new AssertionError(e);
                }
                continue;
            }

            // if other worker id also active
            // do it's work first
            if (otherPerson.isActive()) {
                System.out.println(getName() + ": handover the corridor to the person " + otherPerson.getName());
                corridor.setOwner(otherPerson);
                continue;
            }

            // now use the corridor
            System.out.println(getName() + ": working on the corridor");
            active = false;
            corridor.setOwner(otherPerson);
        }
    }
}
