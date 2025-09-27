package zoro;

import zoro.core.State;
import zoro.ui.UserInterface;
import zoro.ui.InputHandler;
import zoro.core.TaskManager;
import zoro.core.StateHandler;

/**
* Main application class for the Zoro task management system.
* Handles main program loop and state transitions.
*/
public class Zoro {

    private State ZoroState;
    private final StateHandler stateHandler;
    private final InputHandler inputHandler;

    /**
    * Constructs new Zoro application instance.
    */
    public Zoro() {
        UserInterface ui = new UserInterface();
        TaskManager taskManager = new TaskManager();

        this.inputHandler = new InputHandler();
        this.stateHandler = new StateHandler(ui, taskManager, inputHandler);
        this.ZoroState = State.MENU;
    }

    /**
    * Starts main application loop.
    * Continues running until EXIT state.
    */
    public void run() {
        try {
            while (ZoroState != State.EXIT) {
                ZoroState = handleState(ZoroState);

            }
        } finally {
            inputHandler.closeScanner();
        }
    }

    /**
    * Handles state transitions based on current state.
    *
    * @param state - the current state to handle
    * @return - next state to transition to
    */
    private State handleState(State state) {
        switch (state) {
        case MENU:
            return stateHandler.handleMenu();
        case ECHO:
            return stateHandler.handleEcho();
        case TASK:
            return stateHandler.handleTask();
        default:
            return State.MENU;
        }
    }

    public static void main(String[] args) {
        Zoro zoro = new Zoro();
        zoro.run();
    }
}
