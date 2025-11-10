import java.util.*;

public class BankersAlgorithm {

    static boolean isSafe(int processes[], int avail[], int max[][], int allot[][]) {
        int n = processes.length; 
        int m = avail.length;     

        int need[][] = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                need[i][j] = max[i][j] - allot[i][j];

        boolean finish[] = new boolean[n];
        int safeSeq[] = new int[n];
        int work[] = Arrays.copyOf(avail, m);

        int count = 0;
        while (count < n) {
            boolean found = false;
            for (int p = 0; p < n; p++) {
                if (!finish[p]) {
                    int j;
                    for (j = 0; j < m; j++)
                        if (need[p][j] > work[j])
                            break;

                    if (j == m) { // If all resources can be allocated
                        for (int k = 0; k < m; k++)
                            work[k] += allot[p][k];
                        safeSeq[count++] = p;
                        finish[p] = true;
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("System is NOT in a safe state!");
                return false;
            }
        }

        System.out.print("System is in a SAFE state.\nSafe sequence: ");
        for (int i = 0; i < n; i++)
            System.out.print("P" + safeSeq[i] + " ");
        System.out.println();
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter number of resources: ");
        int m = sc.nextInt();

        int processes[] = new int[n];
        for (int i = 0; i < n; i++) processes[i] = i;

        int max[][] = new int[n][m];
        int allot[][] = new int[n][m];
        int avail[] = new int[m];

        System.out.println("Enter Maximum Matrix (n x m):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                max[i][j] = sc.nextInt();

        System.out.println("Enter Allocation Matrix (n x m):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                allot[i][j] = sc.nextInt();

        System.out.println("Enter Available Resources (m):");
        for (int j = 0; j < m; j++)
            avail[j] = sc.nextInt();

        isSafe(processes, avail, max, allot);

        sc.close();
    }
}

