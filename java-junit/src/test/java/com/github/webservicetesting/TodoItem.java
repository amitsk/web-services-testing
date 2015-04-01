package com.github.webservicetesting;

/**
 * Created by amit on 3/31/15.
 */
public class TodoItem {
    public final String name;
    public final String task;

    public TodoItem() { this ( null,null);}

    public TodoItem(String name, String task) {
        this.name = name;
        this.task = task;
    }
}
