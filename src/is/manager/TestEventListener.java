package is.manager;

import is.shapes.model.GraphicEvent;
import is.shapes.model.GraphicObjectListener;

public class TestEventListener implements GraphicObjectListener {

    private int eventCounter;

    public TestEventListener()
    {
        this.eventCounter = 0;
    }
    @Override
    public void graphicChanged(GraphicEvent e) {
        this.eventCounter++;
    }

    public int getEventCounter()
    {
        return eventCounter;
    }
}
