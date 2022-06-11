package is.shapes.specificcommand;

import is.command.Command;
import is.manager.ObjectManager;

import java.util.Set;

public class GroupCmd implements Command {

    private String id;
    private ObjectManager manager;
    private Set<String> elements;
    public GroupCmd(Set<String> toGroup, ObjectManager manager) {
        this.manager = manager;
        this.elements = toGroup;
    }


    @Override
    public boolean doIt() {
        id = manager.groupObject(elements);
        return true;
    }

    @Override
    public boolean undoIt() {
        manager.ungroupObject(id);
        return true;
    }
}
