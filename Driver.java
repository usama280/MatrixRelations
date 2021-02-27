import java.util.*;
public class Driver{
    public static void main(String[] args){
        //declaring and or initializing objects
        Relation obj = new Relation(true);
        Scanner sc = new Scanner(System.in);
        int length;

        //displaying the matrix and ordered pair relation
        obj.displayMatrixOfRelation();
        obj.displayOrderedPairs();

        System.out.println("\nIs the relation reflexive? " + obj.isReflexive());
        System.out.println("Is the relation irreflexive? " + obj.isIrreflexive());
        System.out.println("Is the relation symmetric? " + obj.isSymmetric());
        System.out.println("Is the relation Asymmetric? " + obj.isAsymmetric());
        System.out.println("Is the relation anti-symmetric? " + obj.isAntisymmetric());
        System.out.println("Is the relation transitive? " + obj.isTransitive());
        //System.out.println("Is the relation transitive2? " + obj.isTransitive2());
        //Just an additional method I was using before making one that fits the criteria^ 

    }
}