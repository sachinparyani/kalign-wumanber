package model;

/**
 * Created by nammi on 20/10/17.
 */
public class Alignment implements Comparable<Alignment>{
    String sequence1;

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public String getAseq2() {
        return aseq2;
    }

    int id1;
    String sequence2;
    int id2;
    String aSeq1;
    int start1;
    String aseq2;
    int start2;
    int score;

    public String getaSeq1() {
        return aSeq1;
    }

    public void setaSeq1(String aSeq1) {
        this.aSeq1 = aSeq1;
    }

    public int getStart1() {
        return start1;
    }

    public void setStart1(int start1) {
        this.start1 = start1;
    }

    public String getaseq2() {
        return aseq2;
    }

    public void setAseq2(String aseq2) {
        this.aseq2 = aseq2;
    }

    public int getStart2() {
        return start2;
    }

    public void setStart2(int start2) {
        this.start2 = start2;
    }

    public Alignment(String sequence1, String sequence2, String aSeq1,int start1, String aseq2, int start2,int id1,int id2) {
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        this.aSeq1= aSeq1;
        this.aseq2 = aseq2;
        this.start1 = start1;
        this.start2 = start2;
        this.id1 = id1;
        this.id2 = id2;

    }

    public String getSequence1() {
        return sequence1;
    }

    public void setSequence1(String sequence1) {
        this.sequence1 = sequence1;
    }

    public String getSequence2() {
        return sequence2;
    }

    public void setSequence2(String sequence2) {
        this.sequence2 = sequence2;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("The original sequences are \n Sequence1 : " + sequence1 + "\nSequence2 : " + sequence2);
        stringBuilder.append("score = " + score);
        stringBuilder.append("\n" + id1 +" " + start1 +" "+ aSeq1 + "\n" + id2 +" " + start2 +" "+ aseq2);
        //stringBuilder.append(score);
        return stringBuilder.toString();
    }

    public int compareTo(Alignment other)
    {
        if(this.score < other.score){
            return 1;
        }
        if(this.score > other.score){
            return -1;
        }
        return 0;
    }
}
