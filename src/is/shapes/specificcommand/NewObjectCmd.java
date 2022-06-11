package is.shapes.specificcommand;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.Parser;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.io.StringReader;

public class NewObjectCmd implements Command {

	private ObjectManager manager;
	private Parser parser;
	private String id;
	public NewObjectCmd(ObjectManager manager, String type,) {
		
		this.manager = manager;
		this.object = object;
		
	}

	@Override
	public boolean doIt() {

		if(id == null)
			id = manager.addObject(object);
		else
			id = manager.setObject(id,object);
		return true;
	}

	@Override
	public boolean undoIt() {

		manager.removeObject(id);
		return true;
	}

}
