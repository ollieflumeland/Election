/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package election;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphicView implements Observer, ActionListener {

    public static final int VIEW_WIDTH = 410;
    private static final int VIEW_DEPTH = 200;
    private static final Dimension PANEL_SIZE = new Dimension(VIEW_WIDTH, VIEW_DEPTH);
    private ElectionModel model;
    private ElectionController controller;
    private JFrame inputFrame;
    private JPanel inputPanel;
    private JPanel candButTitles;
    private ChartPanel colorPanel;
    private TextPanel textPanel;
    private ArrayList<JPanel> butPanelCand = new ArrayList<>();
    private ArrayList<JTextField> candNoVotes = new ArrayList<>();
    private ArrayList<JLabel> labelCand = new ArrayList<>();
    private ArrayList<JButton> butCandPlus = new ArrayList<>();
    private ArrayList<JButton> butCandMinus = new ArrayList<>();
    private JLabel candNameTitles = new JLabel(" CANDIDATES");
    private JLabel candAddTitles = new JLabel(" +Vote(s)");
    private JLabel candMinusTitles = new JLabel(" -Vote(s)");
    private JLabel candNoVotesTitles = new JLabel(" Amount");
    private JButton resetButton = new JButton("Reset");

    public GraphicView(ElectionModel model, ElectionController controller) {
        this.model = model;
        this.model.addObserver(this);
        this.controller = controller;
        createControls();
        update(this.model, null);

    }

    private void createControls() {
        inputFrame = new JFrame("Election Monitor");

        Container contentPane = inputFrame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        createPanel();
        contentPane.add(inputPanel);

        textPanel = new TextPanel(model);
        contentPane.add(textPanel);
        textPanel.setLayout(new GridLayout(11, 2, 10, 4));
        colorPanel = new ChartPanel(model);
        contentPane.add(colorPanel);

        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.pack();
        inputFrame.setResizable(false);
        inputFrame.setVisible(true);
    }

    private void createPanel() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2, 10, 4));

        candButTitles = new JPanel();
        candButTitles.setLayout(new GridLayout(1, 3, 10, 0));
        candButTitles.add(candAddTitles);
        candButTitles.add(candMinusTitles);
        candButTitles.add(candNoVotesTitles);
        
        inputPanel.add(candNameTitles);
        inputPanel.add(candButTitles);

        
        for(int i=0;i<ElectionModel.TOTAL_NUMBER_CANDIDATES;i++){
           candNoVotes.add(new JTextField());
           labelCand.add(new JLabel());
           butCandPlus.add(new JButton(" + "));
           butCandMinus.add(new JButton(" - "));
           butPanelCand.add(new JPanel());
           butPanelCand.get(i).setLayout(new GridLayout(1, 3, 10, 0));
           butPanelCand.get(i).add(butCandPlus.get(i));
           butPanelCand.get(i).add(butCandMinus.get(i));
           butPanelCand.get(i).add(candNoVotes.get(i)); 
           labelCand.get(i).setText(" " + model.getCandidateName(i));
           inputPanel.add(labelCand.get(i));
           inputPanel.add(butPanelCand.get(i));
           butCandPlus.get(i).addActionListener(this);
           butCandMinus.get(i).addActionListener(this);
        }
        inputPanel.add(resetButton);
        inputPanel.setPreferredSize(PANEL_SIZE);
        resetButton.addActionListener(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        for (int i = 0; i < ElectionModel.TOTAL_NUMBER_CANDIDATES; i++) {
            butCandPlus.get(i).setEnabled(controller.statusPlusButton(i));
            butCandMinus.get(i).setEnabled(controller.statusMinusButton(i));
            candNoVotes.get(i).setText("1");
        }
        resetButton.setEnabled(controller.statusReset());
        inputFrame.repaint();
        textPanel.update();

    }
    
    public void statusPlusButtons() {
        for (int i = 0; i < ElectionModel.TOTAL_NUMBER_CANDIDATES; i++) {
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < ElectionModel.TOTAL_NUMBER_CANDIDATES; i++) {
            if (e.getSource() == butCandMinus.get(i)) {
                int v = Integer.parseInt(candNoVotes.get(i).getText());
                controller.removeVotes(i, v);
            }
            if (e.getSource() == butCandPlus.get(i)) {
                int v = Integer.parseInt(candNoVotes.get(i).getText());
                controller.addVotes(i, v);
            }
        }
        if (e.getSource() == resetButton) {
            controller.reset();
        }
    }
}
