package is.shapes.model;

import is.shapes.view.GraphicObjectView;
import is.shapes.view.ImageObjectView;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.Objects;

import javax.swing.ImageIcon;

public class ImageObject extends AbstractGraphicObject {
	private double factor = 1.0;

	private Image image;

	private Point2D position;

	private GraphicObjectView view;

	public Image getImage() {
		return image;
	}

	public ImageObject(ImageIcon img, Point2D pos) {
		position = new Point2D.Double(pos.getX(), pos.getY());
		image = img.getImage();
		view = new ImageObjectView();
	}

	@Override
	public boolean contains(Point2D p) {
		double w = (factor * image.getWidth(null)) / 2;
		double h = (factor * image.getHeight(null)) / 2;
		double dx = Math.abs(p.getX() - position.getX());
		double dy = Math.abs(p.getY() - position.getY());
		return dx <= w && dy <= h;
	}

	@Override
	public double getArea() {
		return (factor*image.getWidth(null))*(factor*image.getHeight(null));
	}

	@Override
	public double getPerimeter() {
		return 2*factor*(image.getWidth(null)+image.getHeight(null));
	}

	@Override
	public void moveTo(Point2D p) {
		position.setLocation(p);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public ImageObject clone() {
		ImageObject cloned = (ImageObject) super.clone();
		cloned.position = (Point2D) position.clone();
		return cloned;

	}

	@Override
	public Point2D getPosition() {

		return new Point2D.Double(position.getX(), position.getY());
	}

	@Override
	public void scale(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException();
		this.factor *= factor;
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension dim = new Dimension();
		dim.setSize(factor * image.getWidth(null),
				factor * image.getHeight(null));
		return dim;
	}

	@Override
	public String getType() {

		return "Image";
	}

	@Override
	public GraphicObjectView getView() {
		return view;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ImageObject that = (ImageObject) o;
		return Double.compare(that.factor, factor) == 0 && image.equals(that.image) && position.equals(that.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(factor, image, position);
	}
}
