package meli.challenge.mutante.helper;

import java.util.ArrayList;
import java.util.List;

public class MatrixHelper {

    //recieve a string list and returns a char array
    public static char[][] stringListToMatrix(List<String> stringList){
        if(stringList.isEmpty()){
            return new char[][]{};
        }
        char[][] chars = new char[stringList.get(0).length()][stringList.size()];
        for (int i=0; i < stringList.size(); i++) {
            for(int j = 0; j < stringList.get(i).length() ; j++){
                chars[i][j] = stringList.get(i).charAt(j);
            }
        }
        return chars;
    }
    //transpose a char matrix
    public static List<String> transponeMatrix(char[][] chars){
        List<String> result = new ArrayList<>();



        for (int i = 0; i < chars.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<chars[i].length;j++){
                sb.append(chars[j][i]);
            }
            result.add(sb.toString());
        }
        return result;
    }

  //return the strings that are the diagonals from a char matrix
  public static List<String> getDiagonals(char[][] matrix){
      List<String> result = new ArrayList<>();
      result.add(getPrincipalDiagonal(matrix));
      result.addAll(getSecondaryDiagonal(matrix));
      return result;

  }
    // Function to get the principal Diagonal
    private static  String getPrincipalDiagonal(char matrix[][])
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // Condition for principal diagonal
                if (i == j) {
                    sb.append(matrix[i][j]);
                }
            }
        }
        return sb.toString();
    }

    // Function to get the Secondary Diagonal
    private static   List<String> getSecondaryDiagonal(char mat[][])
    {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {

                // Condition for secondary diagonal
                if ((i + j) == (mat.length - 1)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(mat[i][j]);
                    result.add(sb.toString());
                }
            }
        }

        return result;

    }
}
