/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package election;


public class Candidate {

    private String name;
    private int votes;

    public Candidate(String name, int votes) {
        this.name = name;
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addVotes(int v) {
        votes += v;
    }

    public void setVote(int v) {
        votes = v;
    }

    public void removeVotes(int v) {
        votes -= v;
    }

    public int getVotes() {
        return votes;
    }
}
