package model;

/**
 * Created by nammi on 20/10/17.
 */
public class Sequence {
    private String sequence;
    private String id;
    private int alignStart;

    public Sequence(String sequence, String id) {
        this.sequence = sequence;
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAlignStart() {
        return alignStart;
    }

    public void setAlignStart(int alignStart) {
        this.alignStart = alignStart;
    }
}
