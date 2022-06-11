package is.shapes.specificcommand;

import is.command.Command;
import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;

import java.util.Set;

public class UngroupCmd implements Command {


    private String id;
    private ObjectManager manager;
    private Set<String> elements;
    public UngroupCmd(String id, ObjectManager manager) {
        this.manager = manager;
        this.id = id;
    }


    @Override
    public boolean doIt() {
        GroupObject group = (GroupObject)manager.getObject(id);
        elements = group.getObjects().keySet();
        manager.ungroupObject(id);
        return true;
    }

    @Override
    public boolean undoIt() {
        manager.groupObject(elements);
        return true;
    }
}
