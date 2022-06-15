package is.shapes.model;

import is.shapes.view.CircleObjectView;
import is.shapes.view.GraphicObjectView;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.Objects;

public class CircleObject extends AbstractGraphicObject {

	private Point2D position;

	private double radius;


	public CircleObject(Point2D pos, double r) {
		if (r <= 0)
			throw new IllegalArgumentException("Radius must be greater than zero");
		position = new Point2D.Double(pos.getX(), pos.getY());
		radius = r;
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
		radius *= factor;
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension d = new Dimension();
		d.setSize(2 * radius, 2 * radius);

		return d;
	}

	@Override
	public boolean contains(Point2D p) {
		return (position.distance(p) <= radius);

	}

	@Override
	public double getArea() {
		return radius*2*Math.PI;
	}

	@Override
	public double getPerimeter() {
		return radius*Math.PI*2;
	}

	@Override
	public CircleObject clone() {
		CircleObject cloned = (CircleObject) super.clone();
		cloned.position = (Point2D) position.clone();
		return cloned;
	}

	@Override
	public String getType() {

		return "Circle";
	}


	public double getRadius() {
		return radius;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CircleObject that = (CircleObject) o;
		return Double.compare(that.radius, radius) == 0 && position.equals(that.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, radius);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getType());
		sb.append("[");
		sb.append("radius="+this.radius);
		sb.append(",");
		sb.append("position=("+position.getX()+","+position.getY()+")");
		sb.append("]");
		return sb.toString();
	}
}
