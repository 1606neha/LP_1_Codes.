import java.util.Scanner;

public class FirstFit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] blockSize = new int[m];
        boolean[] blockAllocated = new boolean[m]; // track if block is already used

        System.out.println("Enter sizes of " + m + " memory blocks:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = sc.nextInt();
            blockAllocated[i] = false; // initialize as free
        }

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        int[] allocation = new int[n]; // stores allocated block index

        System.out.println("Enter sizes of " + n + " processes:");
        for (int i = 0; i < n; i++) {
            processSize[i] = sc.nextInt();
            allocation[i] = -1; // initialize as not allocated
        }

        for (int i = 0; i < n; i++) { // for each process
            for (int j = 0; j < m; j++) { // find the first suitable block
                if (!blockAllocated[j] && blockSize[j] >= processSize[i]) {
                    allocation[i] = j;          // allocate block j to process i
                    blockAllocated[j] = true;   // mark block as used
                    break;                      // move to next process
                }
            }
        }

        System.out.println("\nProcess No.\tProcess Size\tBlock No.");
        for (int i = 0; i < n; i++) {
            if (allocation[i] != -1)
                System.out.println((i + 1) + "\t\t" + processSize[i] + "\t\t" + (allocation[i] + 1));
            else
                System.out.println((i + 1) + "\t\t" + processSize[i] + "\t\tNot Allocated");
        }

        sc.close();
    }
}

