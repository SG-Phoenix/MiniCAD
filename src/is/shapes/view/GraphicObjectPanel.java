package is.shapes.view;

import is.interpreter.Parser;
import is.manager.ObjectManager;
import is.shapes.model.GraphicEvent;
import is.shapes.model.GraphicObject;
import is.shapes.model.GraphicObjectListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

public class GraphicObjectPanel extends JComponent implements GraphicObjectListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8993548105090978185L;

	/**
	 * @directed true
	 */

	private ObjectManager manager;

	public GraphicObjectPanel(ObjectManager manager) {

		this.manager = manager;
		manager.addGraphicObjectListener(this);
		setBackground(Color.WHITE);
	}

	public void setObjectManager(ObjectManager manager)
	{
		if(manager != null)
		{
			this.manager.removeGraphicObjectListener(this);
			this.manager = manager;
			this.manager.addGraphicObjectListener(this);
			repaint();
		}
	}

	@Override
	public void graphicChanged(GraphicEvent e) {
		repaint();
		revalidate();

	}

	
	public String getGraphicObjectAt(Point2D p) {
		for (Map.Entry<String, GraphicObject> g : manager.getManagedObjects().entrySet()) {
			if (g.getValue().contains(p))
				return g.getKey();
		}
		return null;
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension ps = super.getPreferredSize();
		double x = ps.getWidth();
		double y = ps.getHeight();
		for (GraphicObject go : manager.getManagedObjects().values()) {
			double nx = go.getPosition().getX() + go.getDimension().getWidth() / 2;
			double ny = go.getPosition().getY() + go.getDimension().getHeight() / 2;
			if (nx > x)
				x = nx;
			if (ny > y)
				y = ny;
		}
		return new Dimension((int) x, (int) y);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (GraphicObject go : manager.getManagedObjects().values()) {
			go.getView().drawGraphicObject(go, g2);
		}

	}

}
