package com.example.firstwatch;


public class AppState {

    private static AppState state;
    private CurrentUser currentUser;

    private AppState() {

    }

    public static AppState getInstance() {
        if(state == null) {
            state = new AppState();
            return state;
        }

        return state;
    }


    public CurrentUser getCurrentUser() {
        return this.currentUser;
    }
    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }
}
