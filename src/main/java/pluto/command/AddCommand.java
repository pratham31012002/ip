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
     * Constructor that initializes global variables.
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException {
        try {
            storage.appendToFile(t.toFile());
            tasks.addTask(t);
            StringBuilder addMessage = new StringBuilder();
            addMessage.append("\tGot it. I've added this task:\n");
            addMessage.append("\t\t" + t.toString() + "\n");
            addMessage.append(String.format("\tNow you have %d tasks in the list.", tasks.nTasks()));
            ui.print(addMessage);
        } catch (IOException e) {
            throw new PlutoException("\tOOPS!!! Couldn't add task. Try again!");
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
