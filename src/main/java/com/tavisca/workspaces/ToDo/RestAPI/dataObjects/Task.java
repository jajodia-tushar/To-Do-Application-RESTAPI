package com.tavisca.workspaces.ToDo.RestAPI.dataObjects;

public class Task {

    private String id;
    private String data;

    public Task(String id, String data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object object) {
        Task task = (Task) object;
        return this.getData().equals(task.getData()) && this.getId().equals(task.getId());
    }
}
