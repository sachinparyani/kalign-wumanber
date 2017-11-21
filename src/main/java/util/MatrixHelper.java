package util;

/**
 * Created by nammi on 22/10/17.
 */
public class MatrixHelper {

    public static void printMatrix(int [][] matrix){
        for(int i =0;i<matrix.length;i++){
            // System.out.println("lenght " + matrix.length);
            for (int j= 0 ;j<matrix[0].length;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
}
