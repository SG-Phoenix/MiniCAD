package is;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.*;

import is.command.HistoryCommandHandler;
import is.interpreter.Parser;
import is.interpreter.SyntaxException;
import is.manager.ObjectManager;
import is.shapes.graphiccomponents.GraphicMenu;
import is.shapes.graphiccomponents.GraphicObjectController;
import is.shapes.model.*;
import is.shapes.specificcommand.IntrCmdAdapter;
import is.shapes.view.*;

public class Main {

	public static void main(String[] args) {

		JFrame f = new JFrame();


		JToolBar toolbar = new JToolBar();
		ObjectManager manager = new ObjectManager();
		Parser parser = new Parser();

		JButton undoButt = new JButton("Undo");
		JButton redoButt = new JButton("Redo");
		final HistoryCommandHandler handler = new HistoryCommandHandler();

		JMenuBar bar = new GraphicMenu(handler,manager,parser);

		undoButt.addActionListener(evt -> handler.handle(HistoryCommandHandler.NonExecutableCommands.UNDO));

		redoButt.addActionListener(evt -> handler.handle(HistoryCommandHandler.NonExecutableCommands.REDO));

		toolbar.add(undoButt);
		toolbar.add(redoButt);

		final GraphicObjectPanel gpanel = new GraphicObjectPanel(manager);

		gpanel.setPreferredSize(new Dimension(400, 400));

		Map<Class<? extends GraphicObject>, GraphicObjectView> viewMap = new HashMap<>();
		viewMap.put(RectangleObject.class,new RectangleObjectView());
		viewMap.put(CircleObject.class,new CircleObjectView());
		viewMap.put(ImageObject.class,new ImageObjectView());
		viewMap.put(GroupObject.class, new GroupObjectView(viewMap));
		gpanel.setViewMap(viewMap);

		JTextField cmdInput = new JTextField();
		JButton cmdButton = new JButton("Execute");
		cmdButton.addActionListener(evt ->
		{
			String cmd = cmdInput.getText();
			if ((cmd != null) && (cmd.length() > 0))
			{
				try
				{
					handler.handle(new IntrCmdAdapter(parser.parse(cmd).getCommand(), manager));
				}catch (SyntaxException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "SyntaxException",JOptionPane.WARNING_MESSAGE, null);
				}
				finally {
					cmdInput.setText("");
				}

			}


		});
		toolbar.add(cmdInput);
		toolbar.add(cmdButton);


		final GraphicObjectController goc = new GraphicObjectController(handler, manager);

		gpanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				goc.setControlledObject(gpanel.getGraphicObjectAt(e.getPoint()));
			}
		});

		f.setJMenuBar(bar);
		f.add(toolbar, BorderLayout.NORTH);
		f.add(new JScrollPane(gpanel), BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new FlowLayout());

		controlPanel.add(goc);
		f.setTitle("Shapes");
		f.getContentPane().add(controlPanel, BorderLayout.SOUTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

	}
}