/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package election;

import java.util.Observable;

public class ElectionModel extends Observable {

    private int votesCast;
    static final int TOTAL_ALLOWED_VOTES = 100;
    static final int TOTAL_NUMBER_CANDIDATES = 5;
    Candidate[] candidates = new Candidate[TOTAL_NUMBER_CANDIDATES];

    public ElectionModel() {
        initialise();
        notifyObservers();
    }

    public void addVotes(int candidateID, int v) {
        votesCast += v;
        candidates[candidateID].addVotes(v);
        setChanged();
        notifyObservers();
    }
    
    public String getCandidateName(int candidateID) {
        return candidates[candidateID].getName();
    }

        public void removeVotes(int candidateID, int v) {
            votesCast -= v;
            candidates[candidateID].removeVotes(v);
            setChanged();
            notifyObservers();
        }

    public int getVotes(int candidateID) {
        return candidates[candidateID].getVotes();
    }

    private void initialise() {

        candidates[0] = new Candidate("Jeremy Bourgein", 20);
        candidates[1] = new Candidate("Paul Oliver", 25);
        candidates[2] = new Candidate("Olly Haine", 15);
        candidates[3] = new Candidate("Jon Worth", 10);
        candidates[4] = new Candidate("Marian Wade", 5);

        for (Candidate c : candidates) {
            votesCast += c.getVotes();
        }

    }

    void reset() {
        for (Candidate c : candidates) {
            c.setVote(0);
        }
        votesCast = 0;
        setChanged();
        notifyObservers();
    }

    public int getRemainingVotes() {
        return TOTAL_ALLOWED_VOTES - votesCast;
    }

    public int getCastVotes() {
        return votesCast;
    }
}
