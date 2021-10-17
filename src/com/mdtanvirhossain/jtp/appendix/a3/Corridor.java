package com.mdtanvirhossain.jtp.appendix.a3;

public class Corridor {
    private Person owner;

    public Corridor(Person owner) {
        this.owner = owner;
    }

    public Person getOwner() {
        return owner;
    }

    public synchronized void setOwner(Person owner) {
        this.owner = owner;
    }
}
