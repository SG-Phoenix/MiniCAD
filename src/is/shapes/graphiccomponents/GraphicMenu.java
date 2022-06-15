package is.shapes.graphiccomponents;

import is.command.HistoryCommandHandler;
import is.interpreter.Parser;
import is.interpreter.SyntaxException;
import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;
import is.shapes.specificcommand.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphicMenu extends JMenuBar {

    private HistoryCommandHandler cmdH;
    private ObjectManager manager;
    private Parser parser;
    public GraphicMenu(HistoryCommandHandler handler, ObjectManager manager, Parser parser)
    {
        this.cmdH = handler;
        this.manager = manager;
        this.parser = parser;

        JMenu cmd = new JMenu("Commands");

        /* CREATE MENU */
        JMenu create = new JMenu("Create");
        JMenuItem circle = new JMenuItem("Circle");
        circle.addActionListener(
                evt ->
                {
                    JPanel panel = new JPanel(new GridLayout(3,2));
                    JLabel rLabel = new JLabel("radius");
                    JFormattedTextField radius = new JDoubleTextField();
                    JLabel xLabel = new JLabel("x");
                    JFormattedTextField x = new JDoubleTextField();
                    JLabel yLabel = new JLabel("y");
                    JFormattedTextField y = new JDoubleTextField();
                    panel.add(rLabel);
                    panel.add(radius);
                    panel.add(xLabel);
                    panel.add(x);
                    panel.add(yLabel);
                    panel.add(y);

                    int res = JOptionPane.showConfirmDialog(null,panel,"Create circle", JOptionPane.PLAIN_MESSAGE);

                    if(res == JOptionPane.OK_OPTION)
                    {
                        try
                        {
                            double rValue = Double.parseDouble(radius.getText());
                            double xValue = Double.parseDouble(x.getText());
                            double yValue = Double.parseDouble(y.getText());
                            Point2D pos = new Point2D.Double(xValue,yValue);
                            cmdH.handle(
                                    new IntrCircleCreateCmd(manager,rValue,pos));
                        }catch (SyntaxException e)
                        {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "SyntaxException",JOptionPane.WARNING_MESSAGE, null);
                        }
                        catch ( IllegalArgumentException e)
                        {

                        }

                    }


                }
        );

        JMenuItem rectangle = new JMenuItem("Rectangle");
        rectangle.addActionListener(
                evt ->
                {
                    JPanel panel = new JPanel(new GridLayout(4,2));
                    JLabel wLabel = new JLabel("width");
                    JTextField width = new JDoubleTextField();
                    JLabel hLabel = new JLabel("height");
                    JTextField height = new JDoubleTextField();
                    JLabel xLabel = new JLabel("x");
                    JTextField x = new JDoubleTextField();
                    JLabel yLabel = new JLabel("y");
                    JTextField y = new JDoubleTextField();
                    panel.add(wLabel);
                    panel.add(width);
                    panel.add(hLabel);
                    panel.add(height);
                    panel.add(xLabel);
                    panel.add(x);
                    panel.add(yLabel);
                    panel.add(y);

                    int res = JOptionPane.showConfirmDialog(null,panel,"Create rectangle", JOptionPane.PLAIN_MESSAGE);

                    if(res == JOptionPane.OK_OPTION)
                    {
                        try
                        {
                            double wValue = Double.parseDouble(width.getText());
                            double hValue = Double.parseDouble(height.getText());
                            double xValue = Double.parseDouble(x.getText());
                            double yValue = Double.parseDouble(y.getText());
                            Dimension dim = new Dimension();
                            dim.setSize(wValue,hValue);
                            Point2D pos = new Point2D.Double(xValue,yValue);
                            cmdH.handle(
                                    new IntrRectangleCreateCmd(manager,pos,dim)
                            );
                        }catch (SyntaxException e)
                        {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "SyntaxException",JOptionPane.WARNING_MESSAGE, null);
                        }

                    }


                }
        );


        JMenuItem img = new JMenuItem("Image");
        img.addActionListener(
                evt ->
                {
                    JPanel panel = new JPanel(new GridLayout(3,2));
                    JTextField path = new JTextField();
                    path.setEditable(false);
                    JButton file = new JButton("File");
                    file.addActionListener(e ->
                    {
                        JFileChooser fc = new JFileChooser();
                        FileFilter imageFilter = new FileNameExtensionFilter(
                                "Image files", ImageIO.getReaderFileSuffixes());
                        fc.setFileFilter(imageFilter);
                        int selected = fc.showOpenDialog(null);
                        if(selected == JFileChooser.OPEN_DIALOG);
                        path.setText(fc.getSelectedFile().getAbsolutePath());
                    });
                    JLabel xLabel = new JLabel("x");
                    JTextField x = new JDoubleTextField();
                    JLabel yLabel = new JLabel("y");
                    JTextField y = new JDoubleTextField();
                    panel.add(path);
                    panel.add(file);
                    panel.add(xLabel);
                    panel.add(x);
                    panel.add(yLabel);
                    panel.add(y);

                    int res = JOptionPane.showConfirmDialog(null,panel,"Create image", JOptionPane.PLAIN_MESSAGE);

                    if(res == JOptionPane.OK_OPTION)
                    {

                        try
                        {
                            double xValue = Double.parseDouble(x.getText());
                            double yValue = Double.parseDouble(y.getText());
                            Point2D pos = new Point2D.Double(xValue,yValue);
                            cmdH.handle(
                                    new IntrImgCreateCmd(manager,path.getText(),pos));
                        }catch (SyntaxException e)
                        {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "SyntaxException",JOptionPane.WARNING_MESSAGE, null);
                        }

                    }


                }
        );

        create.add(circle);
        create.add(rectangle);
        create.add(img);
        cmd.add(create);

        /* END CREATE MENU */

        /* DELETE MENU */

        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener( evt ->
        {
            String[] objList = manager.getManagedObjects().keySet().toArray(new String[0]);
            String objID = (String) JOptionPane.showInputDialog(null, "Choose object to delete", null,
                    JOptionPane.PLAIN_MESSAGE,null, objList, 0);
            if(objID != null)
                cmdH.handle(new IntrDeleteCmd(manager,objID));
        });

        cmd.add(delete);

        /* END DELETE MENU */

        /* LIST MENU */

        JMenu list = new JMenu("List");
        JMenuItem listAll = new JMenuItem("List All");
        listAll.addActionListener( evt ->
        {
            cmdH.handle(new IntrListCmd(manager,"all"));
        });

        JMenuItem listObj = new JMenuItem("List Obj");
        listObj.addActionListener( evt ->
        {
            String[] objList = manager.getManagedObjects().keySet().toArray(new String[0]);
            String objID = (String) JOptionPane.showInputDialog(null, "Choose object to display", null,
                    JOptionPane.PLAIN_MESSAGE,null, objList, 0);
            if(objID != null)
                cmdH.handle(new IntrListCmd(manager,objID));
        });

        JMenuItem listGroups = new JMenuItem("List Groups");
        listGroups.addActionListener( evt ->
        {
            cmdH.handle(new IntrListCmd(manager,"groups"));
        });

        JMenuItem listType = new JMenuItem("List Type");
        listType.addActionListener( evt ->
        {
            String[] typeList = {"Circle", "Rectangle", "Img"};
            String type = (String) JOptionPane.showInputDialog(null, "Choose type to display", null,
                    JOptionPane.PLAIN_MESSAGE,null, typeList, 0);
            if(type != null)
                cmdH.handle(new IntrListCmd(manager,type));
        });

        list.add(listAll);
        list.add(listObj);
        list.add(listGroups);
        list.add(listType);
        cmd.add(list);

        /* END LIST MENU */

        /* AREA MENU */

        JMenu area = new JMenu("Area");

        JMenuItem areaAll = new JMenuItem("Area All");
        areaAll.addActionListener( evt ->
        {
            cmdH.handle(new IntrAreaCmd(manager,"all"));
        });

        JMenuItem areaObj = new JMenuItem("Area Obj");
        areaObj.addActionListener( evt ->
        {
            String[] objList = manager.getManagedObjects().keySet().toArray(new String[0]);
            String objID = (String) JOptionPane.showInputDialog(null, "Choose object:", null,
                    JOptionPane.PLAIN_MESSAGE,null, objList, 0);
            if(objID != null)
                cmdH.handle(new IntrAreaCmd(manager,objID));
        });

        JMenuItem areaType = new JMenuItem("Area Type");
        areaType.addActionListener( evt ->
        {
            String[] typeList = {"Circle", "Rectangle", "Img"};
            String type = (String) JOptionPane.showInputDialog(null, "Choose type: ", null,
                    JOptionPane.PLAIN_MESSAGE,null, typeList, 0);
            if(type != null)
                cmdH.handle(new IntrAreaCmd(manager,type));
        });

        area.add(areaAll);
        area.add(areaObj);
        area.add(areaType);
        cmd.add(area);

        /* END AREA MENU */

        /* PERIMETER MENU */

        JMenu perimeter = new JMenu("Perimeter");

        JMenuItem perimeterAll = new JMenuItem("Perimeter All");
        perimeterAll.addActionListener( evt ->
        {
            cmdH.handle(new IntrPerimeterCmd(manager,"all"));
        });

        JMenuItem perimeterObj = new JMenuItem("Perimeter Obj");
        perimeterObj.addActionListener( evt ->
        {
            String[] objList = manager.getManagedObjects().keySet().toArray(new String[0]);
            String objID = (String) JOptionPane.showInputDialog(null, "Choose object:", null,
                    JOptionPane.PLAIN_MESSAGE,null, objList, 0);
            if(objID != null)
                cmdH.handle(new IntrPerimeterCmd(manager,objID));
        });

        JMenuItem perimeterType = new JMenuItem("Perimeter Type");
        perimeterType.addActionListener( evt ->
        {
            String[] typeList = {"Circle", "Rectangle", "Img"};
            String type = (String) JOptionPane.showInputDialog(null, "Choose type: ", null,
                    JOptionPane.PLAIN_MESSAGE,null, typeList, 0);
            if(type != null)
                cmdH.handle(new IntrPerimeterCmd(manager,type));
        });

        perimeter.add(perimeterAll);
        perimeter.add(perimeterObj);
        perimeter.add(perimeterType);
        cmd.add(perimeter);

        /* END PERIMETER MENU */

        /* GROUP MENU */

        JMenuItem group = new JMenuItem("Group");
        group.addActionListener( evt ->
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

            JOptionPane.showMessageDialog(null, groupObjSelection,"Group",
                    JOptionPane.PLAIN_MESSAGE,null);
            Set<String> toGroup = new HashSet<>();
            for(JCheckBox box : boxes)
            {
                if(box.isSelected())
                    toGroup.add(box.getText());
            }

            if(toGroup.size()>0)
                handler.handle(
                        new IntrGroupCmd(manager,toGroup)
                );
        });

        cmd.add(group);

        /* END GROUP MENU */

        /* UNGROUP MENU */

        JMenuItem ungroup = new JMenuItem("Ungroup");
        ungroup.addActionListener( evt ->
        {
            ArrayList<String> groups = new ArrayList<>();
            for(Map.Entry<String, GraphicObject> object : manager.getManagedObjects().entrySet())
            {
                if(object.getValue().getType().equals("Group"))
                    groups.add(object.getKey());
            }
            String objID = (String)JOptionPane.showInputDialog(null,
                    "Choose element to ungroup: ",
                    "Ungroup", JOptionPane.PLAIN_MESSAGE,
                    null,groups.toArray(),
                    0);
            if(objID != null)
                handler.handle(
                        new IntrUngroupCmd(manager,objID)
                );
        });

        cmd.add(ungroup);
        add(cmd);

    }
}
