import model.Alignment;
import model.Direction;
import model.Sequence;
import util.Backtracking;

import java.util.HashMap;

/**
 * Created by nammi on 20/10/17.
 */
public class NeedlemanWunsch {

    private static int count = 0;

    public static Alignment Align(Sequence s1, Sequence s2, HashMap<Character, Integer> letters, int smatrix[][], int gap) {

        int seq1Length = s1.getSequence().length();
        int seq2Length = s2.getSequence().length();
        char[] seq1 = s1.getSequence().toCharArray();
        char[] seq2 = s2.getSequence().toCharArray();

//        System.out.println(s1.getId());
//        System.out.println(s2.getId());

        int scores[][] = new int[seq2Length + 1][seq1Length + 1];
        int pointers[][] = new int[seq2Length + 1][seq1Length + 1];

        int i = 0;
        for (int j = 0; j <= seq1Length; j++) {
            scores[i][j] = j * gap;
        }

        int j = 0;
        for (i = 1; i <= seq2Length; i++) {
            scores[i][j] = i * gap;
        }

        pointers[0][0] = Direction.STOP;
        // Initializes the boundaries of the traceback matrix.
        for (i = 1; i <= seq2Length; i++) {
            pointers[i][0] = Direction.UP;
        }
        for (j = 1; j <= seq1Length; j++) {
            pointers[0][j] = Direction.LEFT;
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
        Alignment alignment = Backtracking.backTrace(s1,s2,pointers,seq2Length,seq1Length);
       // int score = Backtracking.calculateFinalScore(alignment.getaSeq1(),alignment.getaseq2(),letters,smatrix,gap);
        alignment.setScore(scores[seq2Length][seq1Length]);
        return alignment;
    }
}
