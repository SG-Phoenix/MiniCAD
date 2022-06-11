package is.interpreter;

import is.manager.ObjectManager;
import is.shapes.view.GraphicObjectPanel;

public interface GrammarElement {

    public String interpreta(ObjectManager context);
}
