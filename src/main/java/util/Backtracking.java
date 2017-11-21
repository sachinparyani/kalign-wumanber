package util;

import model.Alignment;
import model.Direction;
import model.Sequence;

/**
 * Created by nammi on 22/10/17.
 */
public class Backtracking {

    public static Alignment backTrace(Sequence s1, Sequence s2, int[][] pointers, int row, int col) {

        StringBuffer stringBuilder1 = new StringBuffer();
        StringBuffer stringBuilder2 = new StringBuffer();
        char[] seq1 = s1.getSequence().toCharArray();
        char[] seq2 = s2.getSequence().toCharArray();

        int i = row;
        int j = col;

        while (pointers[i][j] != Direction.STOP) {
            switch (pointers[i][j]){
                //Diag
                case 3:
                    stringBuilder1.append(seq1[j-1]);
                    stringBuilder2.append(seq2[i-1]);
                    i--;
                    j--;
                    break;
                //Horizontal
                case 2:
                    stringBuilder1.append(seq1[j-1]);
                    stringBuilder2.append(".");
                    j--;
                    break;
                //Vertical
                case 1:
                    stringBuilder1.append(".");
                    stringBuilder2.append(seq2[i-1]);
                    i--;
                    break;
            }
        }

        stringBuilder1 = stringBuilder1.reverse();
        stringBuilder2 = stringBuilder2.reverse();
        Alignment alignment = new Alignment(s1.getSequence(),s2.getSequence(),stringBuilder1.toString(),j+1,stringBuilder2.toString(),i+1,Integer.parseInt(s1.getId()),Integer.parseInt(s2.getId()));
        return alignment;
    }

//    public static int calculateFinalScore(String aseq1, String aseq2, HashMap<Character,Integer> letters , int[][] smatrix, int gap) {
//        char[] seq1 = aseq1.toCharArray();
//        char[] seq2 = aseq2.toCharArray();
//        int score =0;
//        for(int i=0;i<aseq1.length();i++){
//            if((seq1[i] == '.') || seq2[i] == '.'){
//                score += gap;
//            }
//            else
//                score+= smatrix[letters.get(seq1[i])][letters.get(seq2[i])];
//        }
//        return score;
//    }
}
