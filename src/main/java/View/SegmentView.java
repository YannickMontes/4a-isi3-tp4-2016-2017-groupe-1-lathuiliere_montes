package View;

import Model.Segment;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yannick on 26/04/17.
 */
public class SegmentView extends JComponent
{
    private Segment segment;

    public SegmentView(Segment segment) {
        this.segment = segment;
    }

    public void drawSegment(Graphics graph)
    {
        if (graph != null) {
            graph.setColor(segment.color);
            graph.drawLine(segment.ptStart.x, segment.ptStart.y, segment.ptEnd.x, segment.ptEnd.y);
        }
    }
}
