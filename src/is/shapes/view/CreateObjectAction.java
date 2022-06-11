package is.shapes.view;

import is.command.CommandHandler;
import is.interpreter.Command;
import is.interpreter.Parser;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.specificcommand.NewObjectCmd;

import java.awt.event.ActionEvent;
import java.io.StringReader;

import javax.swing.AbstractAction;

public class CreateObjectAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5399493440620423134L;
	AbstractGraphicObject prototype;
	ObjectManager manager;
	CommandHandler ch;

	public CreateObjectAction(AbstractGraphicObject prototype,
							  ObjectManager manager, CommandHandler ch) {
		super();
		this.prototype = prototype;
		this.manager = manager;
		this.ch = ch;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		GraphicObject graphicObject = prototype.clone();
		ch.handle(new NewObjectCmd(manager, graphicObject));

	}

}
