/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package election;

public class ElectionController {

    private ElectionModel model;
    private GraphicView view;

    public ElectionController(ElectionModel model) {
        this.model = model;
    }

    public void addVotes(int candidateID, int v) {
        model.addVotes(candidateID, v);
    }

    public void removeVotes(int candidateID, int v) {
        model.removeVotes(candidateID, v);
    }

    void reset() {
        System.out.println("controller reset");
        model.reset();
    }

    boolean statusPlusButton(int i) {
            if (model.getCastVotes() == ElectionModel.TOTAL_ALLOWED_VOTES) {
                return false;
            } else {
                return true;
            }
    }

    boolean statusMinusButton(int i) {
        if (model.getCastVotes() == 0) {
            return false;
        } else if (model.getVotes(i) == 0) {
            return false;
        } else {
            return true;
        }
    }

    boolean statusReset() {
        if (model.getCastVotes() == 0) {
            return false;
        } else {
            return true;
        }
    }
}