/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package election;

public class Election {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        createAndShowGUI();
                    }
                });
    }

    public static void createAndShowGUI() {
        ElectionModel model = new ElectionModel();
        ElectionController controller = new ElectionController(model);
        GraphicView view = new GraphicView(model, controller);
    }
}
