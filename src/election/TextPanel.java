package election;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Paul Oliver 11027418
 */
public class TextPanel extends JPanel {
    
    private static final int DEPTH_TEXT_PANEL = 190;
    private static final Dimension TEXT_PANEL_SIZE = new Dimension(GraphicView.VIEW_WIDTH, DEPTH_TEXT_PANEL);
    private ElectionModel model;
    private JPanel textPanel = new JPanel();
    private ArrayList<JLabel> totVotesLabels = new ArrayList<>();
    private ArrayList<JLabel> canTxtLabels = new ArrayList<>();
    private JLabel candNameTxtTitles = new JLabel("CANDIDATES");
    private JLabel candTxtTotVotes = new JLabel("Number of Votes");

    private JLabel candTxtRemVotesLab = new JLabel("Remaining Voters/Votes");
    private JLabel candTxtRemVotes = new JLabel("Vote(s)");
    private JLabel candTxtVotesCastLab = new JLabel("Total Number of Votes Cast");
    private JLabel candTxtTotCastVotes = new JLabel("0");

    /**
     * Creates a new instance of TextPanel
     */
    public TextPanel(ElectionModel model) {
        
        for(int i=0;i<ElectionModel.TOTAL_NUMBER_CANDIDATES;i++){
            totVotesLabels.add(new JLabel());
            canTxtLabels.add(new JLabel("Cand"+i));
        }
             
        this.model = model;

        this.add(candNameTxtTitles);
        this.add(candTxtTotVotes);

        for(int i=0;i<ElectionModel.TOTAL_NUMBER_CANDIDATES;i++){
            this.add(canTxtLabels.get(i));
            this.add(totVotesLabels.get(i));     
        }
        
        this.add(candTxtVotesCastLab);
        this.add(candTxtTotCastVotes);
        this.add(candTxtRemVotesLab);
        this.add(candTxtRemVotes);

        for(int i=0;i<ElectionModel.TOTAL_NUMBER_CANDIDATES;i++){
            totVotesLabels.get(i).setText("" + model.getVotes(i));
            canTxtLabels.get(i).setText(""+model.getCandidateName(i));     
        }
    
        this.setPreferredSize(TEXT_PANEL_SIZE);
    }

    @Override
    public Dimension getPreferredSize() {
        return TEXT_PANEL_SIZE;
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    /**
     * Updates the text panel
     */
    void update() {
        
        for(int i=0;i<ElectionModel.TOTAL_NUMBER_CANDIDATES;i++){
                totVotesLabels.get(i).setText("" + model.getVotes(i));
        }

        candTxtTotCastVotes.setText("" + Integer.toString(model.getCastVotes()));
        candTxtRemVotes.setText("" + Integer.toString(model.getRemainingVotes()));
    }
}
