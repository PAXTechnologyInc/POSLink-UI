package com.pax.us.pay.ui.base.message.helper;


import java.util.Observable;

public class BaseHelper<T> extends Observable {
    @Override
    public final void notifyObservers() {
        super.notifyObservers();
        setChanged();

    }

    @Override
    public final void notifyObservers(Object arg) {
        super.notifyObservers(arg);
        setChanged();
    }

    protected void sendObjNext(T obj) {
        setChanged();
        notifyObservers(obj);
    }

    protected void sendObjNext() {
        setChanged();
        notifyObservers();
    }

    public void sendAbort() {
        setChanged();
        notifyObservers("Abort");
    }

    public void sendPrev() {
        setChanged();
        notifyObservers("Prev");
    }
}
