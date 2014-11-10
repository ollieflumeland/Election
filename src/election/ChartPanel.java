package election;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Jeremy Bourgein
 */
public class ChartPanel extends JPanel {

    private static final int DEPTH_CHART_PANEL = 120;
    private static final Dimension CHART_PANEL_SIZE = new Dimension(GraphicView.VIEW_WIDTH, DEPTH_CHART_PANEL);
    private static final int NAME_Y_OFFSET = 12;
    private static final int NAME_LINE_SPACING = 20;
    private static final int GRAPH_X_OFFSET = 110;
    private static final int BAR_WIDTH = 18;
    private ElectionModel model;
    private Color[] colors = new Color[ElectionModel.TOTAL_NUMBER_CANDIDATES];

    /**
     * Creates a new instance of ChartPanel
     */
    public ChartPanel(ElectionModel model) {
        this.model = model;
        colors[0] = Color.BLUE;
        colors[1] = Color.GREEN;
        colors[2] = Color.ORANGE;
        colors[3] = Color.RED;
        colors[4] = Color.YELLOW;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        
        for(int i=0;i<ElectionModel.TOTAL_NUMBER_CANDIDATES;i++){
            g.drawString(model.getCandidateName(i),0,NAME_LINE_SPACING*i+NAME_Y_OFFSET);
        }

        for(int i=0;i<ElectionModel.TOTAL_NUMBER_CANDIDATES;i++){
            g.setColor(colors[i]);
            g.fillRect(GRAPH_X_OFFSET,i*NAME_LINE_SPACING,model.getVotes(i)* ((GraphicView.VIEW_WIDTH - GRAPH_X_OFFSET) / ElectionModel.TOTAL_ALLOWED_VOTES), BAR_WIDTH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return CHART_PANEL_SIZE;
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
}
