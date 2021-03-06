package is.shapes.model;

import is.shapes.view.GraphicObjectView;
import is.shapes.view.RectangleObjectView;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.Objects;

public class RectangleObject extends AbstractGraphicObject {

	private Point2D position;

	private Dimension2D dim;

	private GraphicObjectView view;

	public RectangleObject(Point2D pos, double w, double h) {
		if (w <= 0 || h <= 0)
			throw new IllegalArgumentException();
		dim = new Dimension();
		dim.setSize(w, h);
		position = new Point2D.Double(pos.getX(), pos.getY());
		view = new RectangleObjectView();

	}

	@Override
	public boolean contains(Point2D p) {
		double w = dim.getWidth() / 2;
		double h = dim.getHeight() / 2;
		double dx = Math.abs(p.getX() - position.getX());
		double dy = Math.abs(p.getY() - position.getY());
		return dx <= w && dy <= h;

	}

	@Override
	public double getArea() {
		return dim.getHeight()*dim.getWidth();
	}

	@Override
	public double getPerimeter() {
		return 2*(dim.getHeight()+dim.getWidth());
	}

	@Override
	public void moveTo(Point2D p) {
		position.setLocation(p);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Point2D getPosition() {

		return new Point2D.Double(position.getX(), position.getY());
	}

	@Override
	public void scale(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException();
		dim.setSize(dim.getWidth() * factor, dim.getHeight() * factor);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension2D d = new Dimension();
		d.setSize(dim);
		return d;
	}

	@Override
	public RectangleObject clone() {
		RectangleObject cloned = (RectangleObject) super.clone();
		cloned.position = (Point2D) position.clone();
		cloned.dim = (Dimension2D) dim.clone();
		return cloned;
	}

	@Override
	public String getType() {

		return "Rectangle";
	}

	@Override
	public GraphicObjectView getView() {
		return view;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RectangleObject that = (RectangleObject) o;
		return position.equals(that.position) && dim.equals(that.dim);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, dim);
	}
}
