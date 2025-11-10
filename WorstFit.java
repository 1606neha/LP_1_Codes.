import java.util.Scanner;

public class WorstFit {

    static void worstFit(int blockSize[], int processSize[]) {
        int n = blockSize.length;  
        int m = processSize.length; 

        int allocation[] = new int[m];
        boolean[] blockAllocated = new boolean[n];

        for (int i = 0; i < m; i++) {
            allocation[i] = -1;
        }

        for (int i = 0; i < m; i++) {
            int worstIndex = -1;
            for (int j = 0; j < n; j++) {
                if (!blockAllocated[j] && blockSize[j] >= processSize[i]) {
                    if (worstIndex == -1 || blockSize[j] > blockSize[worstIndex]) {
                        worstIndex = j;
                    }
                }
            }

            if (worstIndex != -1) {
                allocation[i] = worstIndex;
                blockAllocated[worstIndex] = true;
            }
        }

        System.out.println("\nProcess No.\tProcess Size\tBlock No.");
        for (int i = 0; i < m; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1) {
                System.out.println((allocation[i] + 1));
            } else {
                System.out.println("Not Allocated");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of memory blocks: ");
        int n = sc.nextInt();
        int blockSize[] = new int[n];
        System.out.println("Enter sizes of memory blocks:");
        for (int i = 0; i < n; i++) {
            blockSize[i] = sc.nextInt();
        }

        System.out.print("Enter number of processes: ");
        int m = sc.nextInt();
        int processSize[] = new int[m];
        System.out.println("Enter sizes of processes:");
        for (int i = 0; i < m; i++) {
            processSize[i] = sc.nextInt();
        }

        worstFit(blockSize, processSize);
        sc.close();
    }
}

