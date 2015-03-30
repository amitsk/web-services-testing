package com.github.webservicetesting
/**
 * A representation of a Todo item
 */
class TodoItem {
    String name;
    String task;

    private TodoItem() {
        this(null,null)
    }
    TodoItem(name,task) {
        this.name = name
        this.task = task
    }
}
