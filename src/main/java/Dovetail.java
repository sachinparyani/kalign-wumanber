import model.Alignment;
import model.Direction;
import model.Sequence;
import util.Backtracking;

import java.util.HashMap;

/**
 * Created by nammi on 22/10/17.
 */
public class Dovetail {
    public static Alignment Align(Sequence s1, Sequence s2, HashMap<Character, Integer> letters, int smatrix[][], int gap) {

        int seq1Length = s1.getSequence().length();
        int seq2Length = s2.getSequence().length();
        char[] seq1 = s1.getSequence().toCharArray();
        char[] seq2 = s2.getSequence().toCharArray();

        int scores[][] = new int[seq2Length + 1][seq1Length + 1];
        int pointers[][] = new int[seq2Length + 1][seq1Length + 1];

        int i = 0;
        for (int j = 0; j <= seq1Length; j++) {
            scores[i][j] = 0;
        }

        int j = 0;
        for (i = 1; i <= seq2Length; i++) {
            scores[i][j] = 0;
        }

        pointers[0][0] = Direction.STOP;
        // Initializes the boundaries of the traceback matrix.
        for (i = 1; i <= seq2Length; i++) {
            pointers[i][0] = Direction.STOP;
        }
        for (j = 1; j <= seq1Length; j++) {
            pointers[0][j] = Direction.STOP;
        }

        int maximum;
        int direction;
        for (i = 1; i <= seq2Length; i++) {
            for (j = 1; j <= seq1Length; j++) {

                maximum = scores[i - 1][j - 1] + smatrix[letters.get(seq2[i-1])][letters.get(seq1[j-1])];
                direction = 3;

                // vertical
                if ((scores[i - 1][j] + gap) > maximum) {
                    maximum = scores[i - 1][j] + gap;
                    direction = 1;
                }

                // Horizontal
                if ((scores[i][j-1] + gap) > maximum) {
                    maximum = scores[i][j - 1] + gap;
                    direction = 2;
                }

                scores[i][j] = maximum;

                switch (direction) {
                    case 1:
                        pointers[i][j] = Direction.UP;
                        break;
                    case 2:
                        pointers[i][j] = Direction.LEFT;
                        break;
                    case 3:
                        pointers[i][j] = Direction.DIAG;
                }
            }
        }

        int max = 0;
        int max1 = 0;
        int row = 0;
        int row1 = 0;
        int col = 0;
        int col1 =0;
        Alignment alignment;
       for (i=0;i<=seq2Length;i++){
           if(scores[i][seq1Length] >= max){
               max = scores[i][seq1Length];
               row = i;
               col = seq1Length;
           }
       }
        for (j=0;j<=seq1Length;j++){
            if(scores[seq2Length][j] >= max1){
                max1 = scores[seq2Length][j];
                row1 = seq2Length;
                col1 = j;
            }
        }

//        System.out.println("Max " + max + " max1 : " + max1);
//        MatrixHelper.printMatrix(scores);
        if(max<max1){
            row = row1;
            col = col1;
        }
            alignment = Backtracking.backTrace(s1, s2, pointers, row, col);

//        System.out.println("scores");
//        MatrixHelper.printMatrix(scores);
//        System.out.println("pointers");
//        MatrixHelper.printMatrix(pointers);
       // int score = Backtracking.calculateFinalScore(alignment.getaSeq1(),alignment.getaseq2(),letters,smatrix,gap);
        alignment.setScore(scores[row][col]);
        return alignment;
    }
}
