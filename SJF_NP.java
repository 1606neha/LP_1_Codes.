import java.util.*;

class SJF_NP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int ct[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        int done[] = new int[n];

        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter AT and BT for P" + pid[i] + ": \n");
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            done[i] = 0;
        }

        int time = 0, completed = 0;
        while (completed < n) {
            int idx = -1, minBT = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (at[i] <= time && done[i] == 0 && bt[i] < minBT) {
                    minBT = bt[i];
                    idx = i;
                }
            }
            if (idx == -1) { time++; continue; }
            time += bt[idx];
            ct[idx] = time;
            tat[idx] = ct[idx] - at[idx];
            wt[idx] = tat[idx] - bt[idx];
            done[idx] = 1;
            completed++;
        }

        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        double avgTAT=0, avgWT=0;
        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", pid[i], at[i], bt[i], ct[i], tat[i], wt[i]);
            avgTAT+=tat[i]; avgWT+=wt[i];
        }
        System.out.printf("Avg TAT=%.2f Avg WT=%.2f\n", avgTAT/n, avgWT/n);
    }
}
