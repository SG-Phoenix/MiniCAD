package is;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.*;

import is.command.HistoryCommandHandler;
import is.interpreter.Parser;
import is.manager.ObjectManager;
import is.shapes.controller.GraphicObjectController;
import is.shapes.model.*;
import is.shapes.specificcommand.GroupCmd;
import is.shapes.specificcommand.PromptCmd;
import is.shapes.specificcommand.UngroupCmd;
import is.shapes.view.*;

public class TestGraphics2 {

	public static void main(String[] args) {

		JFrame f = new JFrame();

		JToolBar toolbar = new JToolBar();
		ObjectManager manager = new ObjectManager();

		JButton undoButt = new JButton("Undo");
		JButton redoButt = new JButton("Redo");
		final HistoryCommandHandler handler = new HistoryCommandHandler();

		undoButt.addActionListener(evt -> handler.handle(HistoryCommandHandler.NonExecutableCommands.UNDO));

		redoButt.addActionListener(evt -> handler.handle(HistoryCommandHandler.NonExecutableCommands.REDO));

		toolbar.add(undoButt);
		toolbar.add(redoButt);

		final GraphicObjectPanel gpanel = new GraphicObjectPanel(manager);

		gpanel.setPreferredSize(new Dimension(400, 400));

		AbstractGraphicObject go = new RectangleObject(new Point(180,80),20,50);
		JButton rectButton = new JButton(new CreateObjectAction(go, manager, handler));
		rectButton.setText(go.getType());
		toolbar.add(rectButton);

		go = new CircleObject(new Point(200,100),10);
		JButton imgButton = new JButton(new CreateObjectAction(go, manager, handler));
		imgButton.setText(go.getType());
		toolbar.add(imgButton);

		go = new ImageObject(new ImageIcon(TestGraphics2.class.getResource("shapes/model/NyaNya.gif")), new Point(240,187));
		JButton circButton = new JButton(new CreateObjectAction(go, manager, handler));
		circButton.setText(go.getType());
		toolbar.add(circButton);

		JButton cmdButton = new JButton("Command");
		cmdButton.addActionListener(evt ->
		{
			String cmd = JOptionPane.showInputDialog("Command to execute: ");
			if ((cmd != null) && (cmd.length() > 0))
				handler.handle(new PromptCmd(cmd,manager));

		});
		toolbar.add(cmdButton);


		JButton groupButton = new JButton("Group");
		groupButton.addActionListener( evt ->
		{
			JPanel groupObjSelection = new JPanel(new FlowLayout());
			JLabel label = new JLabel("Choose elements to group: ");
			groupObjSelection.add(label);
			java.util.List<JCheckBox> boxes = new ArrayList<JCheckBox>();
			for(String key : manager.getManagedObjects().keySet())
			{
				JCheckBox selectBox = new JCheckBox(key);
				boxes.add(selectBox);
				groupObjSelection.add(selectBox);
			}

			JOptionPane.showMessageDialog(null, groupObjSelection,"Group",JOptionPane.PLAIN_MESSAGE,null);
			Set<String> toGroup = new HashSet<>();
			for(JCheckBox box : boxes)
			{
				if(box.isSelected())
					toGroup.add(box.getText());
			}

			if(toGroup.size()>1)
				handler.handle(new GroupCmd(toGroup,manager));

		});
		toolbar.add(groupButton);

		JButton ungroupButton = new JButton("Ungroup");
		ungroupButton.addActionListener( evt ->
		{
			ArrayList<String> groups = new ArrayList<>();
			for(Map.Entry<String, GraphicObject> object : manager.getManagedObjects().entrySet())
			{
				if(object.getValue().getType().equals("Group"))
					groups.add(object.getKey());
			}
			String res = (String)JOptionPane.showInputDialog(null,
																	"Choose element to ungroup: ",
																	"Ungroup", JOptionPane.PLAIN_MESSAGE,
																	null,groups.toArray(),
																    0);
			if(res != null)
				handler.handle(new UngroupCmd(res,manager));

		});
		toolbar.add(ungroupButton);

		final GraphicObjectController goc = new GraphicObjectController(handler, manager);

		gpanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				goc.setControlledObject(gpanel.getGraphicObjectAt(e.getPoint()));
			}
		});

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