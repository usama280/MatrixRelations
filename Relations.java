import java.util.*;
public class Relation{
    //declaring variables
    private static final int CARDINALITY;
    private int matrix [][];

    //static block to initialize constant 
    static{
        CARDINALITY = 7;
    }

    //default constructor
    public Relation(){
        this.matrix = new int[CARDINALITY][CARDINALITY];
        this.clearRelation();
    }

    //alternate contructor
    public Relation(Boolean x){
        this.matrix = new int[CARDINALITY][CARDINALITY];

        if (x == true){
            this.clearRelation();
            this.populateRelation();
        }else{
            this.clearRelation();
        }
    }

    //sets all matrix elements to 0
    public void clearRelation(){
        for(int i=0; i<CARDINALITY; i++){
            for(int j=0; j<CARDINALITY; j++){
                matrix[i][j] = 0;
            }
        }
    }

    //gets user input to populate matrix
    public void populateRelation(){
        Scanner sc = new Scanner(System.in);
        int x; //holds user numerical val
        char y; //holds input letter

        for(int i=0; i<CARDINALITY; i++){
            //make sure val is between 0 and 7
            do{
                System.out.print("How many elements is " + convertNumberToLetter(i) + " related to: ");
                x = sc.nextInt();
            }while((x>CARDINALITY) || (x<0)); 

            for(int j=0; j<x; j++){
                if(j==0){
                    //make sure input is between a and g    
                    do{
                        System.out.print("Enter the first element " + convertNumberToLetter(i) + " is related to: ");
                        y = sc.next().charAt(0);
                    }while((this.convertLetterToNumber(y)>CARDINALITY-1) || (this.convertLetterToNumber(y) < 0));

                    }else{
                    //make sure input is between a and g    
                    do{
                        System.out.print("Enter the next element " + convertNumberToLetter(i) + " is related to: ");
                        y = sc.next().charAt(0);
                    }while((this.convertLetterToNumber(y)>CARDINALITY-1));
                    }

                matrix[i][this.convertLetterToNumber(y)] = 1; //sets matrix position
            }
        }
        
    }

    //converts given char to a number correspondgly (0-6)
    private int convertLetterToNumber(char x){
        return ((int)x -97);    //casting used to change into num val
    }

    //converts given number to a char correspondgly (a-g)
    private char convertNumberToLetter(int y){
        return (char)(y+97);    //casting used to change into char
    }

    //neatly displays the matrix with its relations
    public void displayMatrixOfRelation(){
        System.out.println("\nMatrix of relation\n------------------\n");
        for(int i=0; i<CARDINALITY; i++){
            for(int j=0; j<CARDINALITY; j++){
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    //checks if matrix is reflexive
    public boolean isReflexive(){
        int j = 0;
        for(int i=0; i<CARDINALITY; i++){
            if(this.matrix[i][j] == 1){ //checks if the diagonal is all 1's
                j++;
            }else{
                return false;
            }
        }
        return true;
    }

    //checks if matrix is irreflexive
    public boolean isIrreflexive(){
        int j = 0;
        for(int i=0; i<CARDINALITY; i++){
            if(this.matrix[i][j] == 0){ //checks if the diagonal is all 0's
                j++;
            }else{
                return false;
            }
        }
        return true;
    }

    //checks if matrix is transitive
    public boolean isTransitive(){
        int[][] boolMatrix = new int[CARDINALITY][CARDINALITY];
        boolMatrix = this.booleanProduct(this.matrix,this.matrix); //does boolean product

        for (int i=0; i<CARDINALITY; i++){                                                        
            for (int j=0; j<CARDINALITY; j++){
                if ((boolMatrix[i][j] == 1) && !(this.matrix[i][j] == 1)){  //checks if matrix has a 1
                    return false;                                     //everywhere as boolMatrix does
                }
            }
        }
        return true;
    }

    //-----------------------------------------------------
    //another method that checks if matrix is transitive
    //-----------------------------------------------------

    /**
    public boolean isTransitive2(){
        for (int i=0; i<CARDINALITY; i++){
            for (int j=0; j<CARDINALITY; j++){
                if (matrix[i][j] == 1){
                    for (int k=0; k<CARDINALITY; k++){
                        if ((matrix[j][k] == 1) && !(matrix[i][k] == 1)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    **/

    //checks if matrix is symmetric
    public boolean isSymmetric(){
        for(int i=0; i<CARDINALITY; i++){ 
            for (int j=0; j<CARDINALITY; j++){
                if (this.matrix[i][j] != this.matrix[j][i])   //checks if each element is still the same
                    return false; 
                }
            }
        return true; 
    }

    //checks if matrix is antisymmetric
    public boolean isAntisymmetric(){
        for(int i=0; i<CARDINALITY; i++){ 
            for (int j=0; j<CARDINALITY; j++){
                if ((this.matrix[i][j] == 1) && (this.matrix[j][i] == 1)) //checks if same elements
                    if(i!=j){ //also makes sure its not a reflexive relation
                        return false;
                    }
                }
            }
        return true;
    }

    //checks if matrix is asymmetric
    public boolean isAsymmetric(){
        for(int i=0; i<CARDINALITY; i++){ 
            for (int j=0; j<CARDINALITY; j++){
                if ((this.matrix[i][j] == 1) && (this.matrix[j][i] == 1)) //checks if same elements
                    return false;
                }
            }
        return true;
    }

    //makes a copy of orig relation
    public int[][] copyRelation(int[][] arr){
        for(int i=0; i<CARDINALITY; i++){ 
            for (int j=0; j<CARDINALITY; j++){
                arr[i][j] = this.matrix[i][j]; //copies the orig relation into arr
            }
        }
        return arr;
    }

    //makes a copy of input matrix
    public int[][] copyMatrix(int[][] arr, int[][] arr2){
        for(int i=0; i<CARDINALITY; i++){ 
            for (int j=0; j<CARDINALITY; j++){
                arr2[i][j] = arr[i][j];  //copies matrix arr into matrix arr2
            }
        }
        return arr2;
    }

    //performs boolean product on matrix
    public int[][] booleanProduct(int[][] arr, int[][] arr2){
        int[][] prod = new int[CARDINALITY][CARDINALITY];
        int sum = 0;

        for(int i=0; i<CARDINALITY; i++){ 
            for (int j=0; j<CARDINALITY; j++){
                sum = 0;                            //resets sum val when starting next loop
                for (int k = 0; k<CARDINALITY; k++){
                        sum += arr[i][k] * arr2[k][j]; //basically checking if there is a match of 1's
                    }
                    if(sum > 0){ 
                        prod[i][j] = 1; // sets that position in the matrix
                    }
                }
            }
        return prod;
    }
        


    //displayes the matrix as a relation of ordered pairs
    public void displayOrderedPairs(){
        char x;
        char y;

        System.out.print("\nR = {");

        for(int i=0; i<CARDINALITY; i++){ 
            x = this.convertNumberToLetter(i); //converting position to letter
            for (int j=0; j<CARDINALITY; j++){
                y = this.convertNumberToLetter(j); //converting position to letter
                if(this.matrix[i][j] == 1){ 
                    //this is here to make the output look better
                    if(i == (CARDINALITY-1)){
                        System.out.print("(" + x + "," + y + ")");
                    }else{
                        System.out.print("(" + x + "," + y + ") , ");
                    }
                }
            } 
        }
        System.out.print("}\n");
    }

}