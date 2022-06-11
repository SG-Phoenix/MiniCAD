package is.shapes.controller;

import is.manager.ObjectManager;
import is.shapes.model.GraphicEvent;
import is.shapes.model.GraphicObject;
import is.shapes.model.GraphicObjectListener;
import is.shapes.view.GraphicObjectView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GraphicObjectList extends JPanel implements GraphicObjectListener
{
    private ObjectManager manager;

    public GraphicObjectList(ObjectManager manager)
    {
        JPanel panel = new JPanel();
    }

    @Override
    public void graphicChanged(GraphicEvent e) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        for (Map.Entry<String, GraphicObject> object : manager.getManagedObjects().entrySet()) {

        }

    }
}
