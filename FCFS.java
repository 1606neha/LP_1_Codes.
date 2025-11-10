import java.util.*;

class FCFS {
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

        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter AT and BT for P" + pid[i] + ": \n");
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (at[i] > at[j]) {
                    int tmp;
                    tmp = at[i]; at[i] = at[j]; at[j] = tmp;
                    tmp = bt[i]; bt[i] = bt[j]; bt[j] = tmp;
                    tmp = pid[i]; pid[i] = pid[j]; pid[j] = tmp;
                }
            }
        }

        int time = 0;
        for (int i = 0; i < n; i++) {
            if (time < at[i]) time = at[i];
            time += bt[i];
            ct[i] = time;
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        double avgTAT = 0, avgWT = 0;
        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", pid[i], at[i], bt[i], ct[i], tat[i], wt[i]);
            avgTAT += tat[i]; avgWT += wt[i];
        }
        System.out.printf("Avg TAT=%.2f Avg WT=%.2f\n", avgTAT/n, avgWT/n);
    }
}
