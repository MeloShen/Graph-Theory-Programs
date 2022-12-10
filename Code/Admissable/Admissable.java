package Code.Admissable;

public class Admissable {

    //input the ordered sequence
    public static void main(String[] args) {
        System.out.println("This is the first set:");
        int[] sequence1 = {5,1,1,1,0};
        check(sequence1);
        System.out.println("This is the second set:");
        int[] sequence2 = {1,1,1,1,1,1,1,0};
        check(sequence2);
        System.out.println("This is the third set:");
        int[] sequence3 = {5,4,2,2,2,2,2,1};
        check(sequence3);

    }
    //find the sequence is admissable or not
    public static void check(int[] sequence) {
        //print the sequence
        for (int i : sequence) {System.out.print(i);}
        System.out.print("\n");
        //if sequence are all zero vector then output is admissable
        if (sequence[0] == 0) {
            for (int i : sequence) {System.out.print(i);}
            System.out.println("\nHavel and hakiwi: sequence is admissable <=> out put is zero vector");
            return;
        }
        //the sequence does not match the degree
        if (sequence[0] > sequence.length - 1) {
            for (int i : sequence) {System.out.print(i);}
            System.out.println("\nAction stops no simple graph with that degree.");
            return;
        }
        //reduce the sequence
        sequence = reduce(sequence);
        //check the sequence dose have negative number
        if (!checkNegative(sequence)) {
            for (int i : sequence) {System.out.print(i);}
            System.out.println("\nAction stops we found to have negative number.");
            return;
        }
        //make the sequence reorded
        reOrder(sequence);
        //repeat the function
        check(sequence);
    }
    //this method is to remove the first item in the array and let the rest item reduce 1
    public static int[] reduce(int[] sequence) {
        int[] reduceSequence = new int[sequence.length - 1];
        System.arraycopy(sequence, 1, reduceSequence, 0, sequence.length - 1);
        for (int y = 0; y < sequence[0]; y++) {
            reduceSequence[y]--;
        }
        return reduceSequence;
    }
    //check the sequence have negative number or not
    public static boolean checkNegative(int[] sequence) {
        for (int i : sequence) {
            if (i == -1) {return false;}
        }
        return true;
    }
    //use the Bubble Sort to reorder the sequence let the sequence form big to small
    public static void reOrder(int[] sequence) {
        for (int i = 0; i < sequence.length - 1; i++) {
            for (int j = 0; j < sequence.length - 1 - i; j++) {
                if (sequence[j + 1] > sequence[j]) {
                    int temp = sequence[j];
                    sequence[j] = sequence[j + 1];
                    sequence[j + 1] = temp;
                }
            }
        }
    }
}