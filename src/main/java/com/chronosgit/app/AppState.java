package com.chronosgit.app;

class AppState {
    static enum State {
        STARTUP(1);

        private final int id;

        State(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }
    }

    private State state = State.STARTUP;

    State getState() {
        return this.state;
    };
}
