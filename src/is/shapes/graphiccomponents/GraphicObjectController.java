package is.shapes.graphiccomponents;

import com.sun.jdi.ObjectCollectedException;
import is.command.CommandHandler;
import is.manager.ObjectManager;
import is.manager.ObjectNotFoundException;
import is.shapes.model.GraphicEvent;
import is.shapes.model.GraphicObjectListener;
import is.shapes.specificcommand.MoveCommand;
import is.shapes.specificcommand.ZoomCommand;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GraphicObjectController extends JPanel implements GraphicObjectListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9177631848186263965L;

	private CommandHandler cmdHandler;

	private ObjectManager manager;
	private String subject;

	private JPanel grid;

	private JPanel zoom;

	static final int offset = 10;

	static final double zoom_factor = 0.1;

	public void setControlledObject(String go) {
		subject = go;
	}

	public GraphicObjectController(CommandHandler cmdH, ObjectManager manager) {
		this(null, cmdH, manager);
	}

	public GraphicObjectController(String go, CommandHandler cmdH, ObjectManager man) {
		cmdHandler = cmdH;
		subject = go;
		manager = man;
		manager.addGraphicObjectListener(this);
		grid = new JPanel(new GridLayout(3, 3));
		zoom = new JPanel(new GridLayout(1, 2));

		JButton minus = new JButton("-");

		minus.addActionListener(e -> {
			if (subject != null) {
				cmdHandler.handle(new ZoomCommand(manager.getObject(subject), 1.0 - zoom_factor));
			}
		});

		zoom.add(minus);

		JButton plus = new JButton("+");
		plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subject != null) {
					cmdHandler.handle(new ZoomCommand(manager.getObject(subject), 1.0 + zoom_factor));
				}
			}
		});

		zoom.add(plus);

		JButton nw = new JButton("\\");

		nw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subject == null)
					return;
				Point2D p = manager.getObject(subject).getPosition();
				cmdHandler.handle(new MoveCommand(manager.getObject(subject), new Point2D.Double(p.getX() - offset, p.getY() - offset)));
			}
		});
		grid.add(nw);

		JButton n = new JButton("|");
		n.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subject == null)
					return;
				Point2D p = manager.getObject(subject).getPosition();
				cmdHandler.handle(new MoveCommand(manager.getObject(subject), new Point2D.Double(p.getX(), p.getY() - offset)));
			}
		});

		grid.add(n);

		JButton ne = new JButton("/");
		ne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subject == null)
					return;
				Point2D p = manager.getObject(subject).getPosition();
				cmdHandler.handle(new MoveCommand(manager.getObject(subject), new Point2D.Double(p.getX() + offset, p.getY() - offset)));

			}
		});

		grid.add(ne);

		JButton w = new JButton("<-");
		w.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subject == null)
					return;
				Point2D p = manager.getObject(subject).getPosition();
				cmdHandler.handle(new MoveCommand(manager.getObject(subject), new Point2D.Double(p.getX() - offset, p.getY())));
			}
		});

		grid.add(w);

		grid.add(zoom);

		JButton e = new JButton("->");
		e.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subject == null || manager.getObject(subject) == null)
					return;
				Point2D p = manager.getObject(subject).getPosition();
				cmdHandler.handle(new MoveCommand(manager.getObject(subject), new Point2D.Double(p.getX() + offset, p.getY())));
			}
		});

		grid.add(e);

		JButton sw = new JButton("/");

		sw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subject == null)
					return;
				Point2D p = manager.getObject(subject).getPosition();
				cmdHandler.handle(new MoveCommand(manager.getObject(subject), new Point2D.Double(p.getX() - offset, p.getY() + offset)));
			}
		});
		grid.add(sw);

		JButton s = new JButton("|");
		s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subject == null)
					return;
				Point2D p = manager.getObject(subject).getPosition();
				// subject.moveTo(p.getX(), p.getY() + offset);
				cmdHandler.handle(new MoveCommand(manager.getObject(subject), new Point2D.Double(p.getX(), p.getY() + offset)));
			}
		});

		grid.add(s);

		JButton se = new JButton("\\");
		se.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subject == null || manager.getObject(subject) == null)
					return;
				Point2D p = manager.getObject(subject).getPosition();
				cmdHandler.handle(new MoveCommand(manager.getObject(subject), new Point2D.Double(p.getX() + offset, p.getY() + offset)));
			}
		});
		grid.add(se);
		add(grid);

	}

	@Override
	public void graphicChanged(GraphicEvent e) {
		try
		{
			manager.getObject(subject);
		}catch (ObjectNotFoundException err)
		{
			subject = null;
		}
	}
}
