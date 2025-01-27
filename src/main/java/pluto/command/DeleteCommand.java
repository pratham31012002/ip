package pluto.command;

import java.io.IOException;

import pluto.PlutoException;
import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;
import pluto.task.Task;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /** Index of the task to be deleted */
    private int idx;

    /**
     * Initializes global variables.
     * @param idx Index of task to delete.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * {@inheritDoc}
     *
     * Deletes a task from the task list, updates the local file,
     * and displays an appropriate message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException {
        Task t = tasks.deleteTask(idx);
        try {
            storage.rewriteFile(tasks);
            return ui.deleteUi(t) + ui.numTasks(tasks);
        } catch (IOException e) {
            tasks.addTask(idx - 1, t);
            throw new PlutoException("OOPS!!! Couldn't delete task. Try again!");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeleteCommand) {
            DeleteCommand other = (DeleteCommand) o;
            return this.idx == other.idx;
        }
        return false;
    }
}
