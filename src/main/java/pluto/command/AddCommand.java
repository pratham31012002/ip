package pluto.command;

import java.io.IOException;

import pluto.PlutoException;
import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;
import pluto.task.Task;

/**
 * Command to add a task to the tasklist.
 */
public class AddCommand extends Command {
    /** Task to be added */
    private Task t;

    /**
     * Initializes global variables.
     * @param t Task to add.
     */
    public AddCommand(Task t) {
        super();
        this.t = t;
    }

    /**
     * {@inheritDoc}
     *
     * Appends the task to the task list, updates the local file,
     * and displays an appropriate message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException {
        try {
            storage.appendToFile(t.toFile());
            tasks.addTask(t);
            return ui.addUi(t) + ui.numTasks(tasks);
        } catch (IOException e) {
            throw new PlutoException("OOPS!!! Couldn't add task. Try again!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AddCommand) {
            AddCommand other = (AddCommand) o;
            return this.t.equals(other.t);
        }
        return false;
    }
}
