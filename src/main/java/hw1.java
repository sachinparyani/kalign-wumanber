import model.Alignment;
import model.Sequence;
import util.AlignmentComparator;
import util.FileHelper;

import java.util.*;

/**
 * Created by nammi on 20/10/17.
 */
public class hw1 {
    static int alignType;
    static String queryFile;
    static String dataBaseFile;
    static String alphabetsFile;
    static String scoringMatrixFile;
    static int k;
    static int gap;

    static HashMap<String,Integer> queryList = new HashMap<String, Integer>();
    static HashMap<String,Integer> databaseList = new HashMap<String, Integer>();
    static HashMap<Character, Integer> letters = new HashMap<Character, Integer>();
    static int[][] scoringMatrix;
    static Comparator<Alignment> alignmentComparator =  new AlignmentComparator();
    static PriorityQueue<Alignment> alignmentPriorityQueue;
    static ArrayList<Alignment> alignmentArrayList = new ArrayList<Alignment>();

    public static void main(String[] args) {

        if(args.length < 7){
            throw new IllegalArgumentException("\nThe Usage of the application is as below \n" + ">hw1 typeOfAlign(1|2|3) queryfile datafile alphabet scorematrix k(nearest neighbor) gapPenalty(integer)");
        }
        try {
            alignType = Integer.parseInt(args[0]);
            if(alignType!=1&&alignType!=2&&alignType!=3){
                throw new IllegalArgumentException("Align Type as to be number \n" +"1 - Global\n2 - Local\n3 - Dovetail");
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Align Type as to be number \n" +"1 - Global\n2 - Local\n3 - Dovetail");
        }
        queryFile = args[1];
        dataBaseFile = args[2];
        alphabetsFile = args[3];
        scoringMatrixFile = args[4];
        try{
            k = Integer.parseInt(args[5]);
        }catch (NumberFormatException e) {
          throw new IllegalArgumentException("K has to be an integer not " + k);
        }
        try {
            gap = Integer.parseInt(args[6]);
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("Gap penalty has to be an integer not " + gap);
        }

        queryList= FileHelper.readSequenceFile(queryFile,queryList);
       // System.out.println(queryList);
        databaseList= FileHelper.readSequenceFile(dataBaseFile,databaseList);
        //System.out.println(databaseList);
        letters = FileHelper.readAlphaFile(alphabetsFile,letters);
        //System.out.println(letters);
        scoringMatrix = new int[letters.size()][letters.size()];
        scoringMatrix = FileHelper.ReadScoringMatrix(scoringMatrixFile,scoringMatrix);
        //MatrixHelper.printMatrix(scoringMatrix);

        //alignmentPriorityQueue = new PriorityQueue<Alignment>();//queryList.size()*databaseList.size(),alignmentComparator);
            int i=0;
            for (HashMap.Entry<String,Integer> queries : queryList.entrySet()
             ) {
            Sequence sq2 = new Sequence(queries.getKey(),queries.getValue().toString());
                //long start = System.currentTimeMillis();
            for (HashMap.Entry<String,Integer> data: databaseList.entrySet()
                 ) {
                Sequence sq1 = new Sequence(data.getKey(),data.getValue().toString());
                switch (alignType){
                    case 1:
                        alignmentArrayList.add(NeedlemanWunsch.Align(sq1,sq2,letters,scoringMatrix, gap));
                        break;
                    case 2:
                        alignmentArrayList.add(SmithWaterman.Align(sq1,sq2,letters,scoringMatrix, gap));
                        break;
                    case 3:
                        alignmentArrayList.add(Dovetail.Align(sq1,sq2,letters,scoringMatrix, gap));
                        i++;
                        break;
                }

//                 if(i==10){
//                    break;
            }
               // long end = System.currentTimeMillis();
               // System.out.println(sq2.getSequence().length());
               // System.out.println(end-start);
//
//                if(i==10){
//                    break;
//                }
        }
        Iterator iterator = null;
        if(alignType ==1 || alignType==2 || alignType ==3) {
            Collections.sort(alignmentArrayList);

            // create ascending iterator
            iterator = alignmentArrayList.iterator();
        }
       // else iterator = alignmentPriorityQueue.iterator();
        i=0;
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            i++;
            if(i==k) {
                break;
            }
        }


//        //Temporary Matrix
//        for (int i=0;i< letters.size();i++){
//            for (int j=0;j<letters.size();j++){
//                if(i==j) {
//                    scoringMatrix[i][j] = 1;
//                }else
//                scoringMatrix[i][j] = -1;
//            }
//        }

//        Alignment alignment = NeedlemanWunsch.Align(new Sequence("ACGCTTTG","36"), new Sequence("CATGTAT","37"),letters,scoringMatrix, gap);
//        System.out.println(alignment);
       // alignment = NeedlemanWunsch.Align(new Sequence(queryList.))

//        alignment = NeedlemanWunsch.Align(new Sequence("TGGTG","36"), new Sequence("ATCGT","37"),letters,scoringMatrix,-2);
//        System.out.println(alignment);
//        alignment = NeedlemanWunsch.Align(new Sequence("GCGCAATG","36"), new Sequence("GCCCTAGCG","37"),letters,scoringMatrix,-2);
//        System.out.println(alignment);
//        alignment = NeedlemanWunsch.Align(new Sequence("GCATGCT","36"), new Sequence("GATTACA","37"),letters,scoringMatrix, gap);
//
//        System.out.println("dovetail begins");
//        Alignment alignment = Dovetail.Align(new Sequence("CCATGAC","36"), new Sequence("TTCCAGTG","37"),letters,scoringMatrix, gap);
//        System.out.println(alignment);
//        alignment = Dovetail.Align(new Sequence("CGGAC","36"), new Sequence("ATCG","37"),letters,scoringMatrix, gap);
//        System.out.println(alignment);
//        alignment = Dovetail.Align(new Sequence("TCT","36"), new Sequence("TACT","37"),letters,scoringMatrix, gap);
//        System.out.println(alignment);
//        alignment = Dovetail.Align(new Sequence("CGGAC","36"), new Sequence("CGAGCT","37"),letters,scoringMatrix, gap);
//        System.out.println(alignment);
//        alignment = Dovetail.Align(new Sequence("AATC","36"), new Sequence("CGGAC","37"),letters,scoringMatrix, gap);
//        System.out.println(alignment);

//
//        for (int i=0;i< letters.size();i++){
//            for (int j=0;j<letters.size();j++){
//                if(i==j) {
//                    scoringMatrix[i][j] = 5;
//                }else
//                    scoringMatrix[i][j] = -3;
//            }
//        }
//       alignment= SmithWaterman.Align(new Sequence("CGTGAATTCAT", "36"),new Sequence("GACTTAC", "39"), letters, scoringMatrix, gap);
//        System.out.println(alignment);
    }
}
