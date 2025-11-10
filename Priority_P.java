import java.util.*;

class Priority_P {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter no. of processes: ");
        int n=sc.nextInt();

        int pid[]=new int[n];
        int at[]=new int[n];
        int bt[]=new int[n];
        int pr[]=new int[n];
        int rt[]=new int[n];
        int ct[]=new int[n];
        int tat[]=new int[n];
        int wt[]=new int[n];

        for(int i=0;i<n;i++){
            pid[i]=i+1;
            System.out.print("Enter AT, BT, Priority for P"+pid[i]+": \n");
            at[i]=sc.nextInt(); 
            bt[i]=sc.nextInt(); 
            pr[i]=sc.nextInt();
            rt[i]=bt[i];
        }

        int time=0,completed=0;
        while(completed<n){
            int idx=-1,minPr=Integer.MAX_VALUE;
            for(int i=0;i<n;i++){
                if(at[i]<=time && rt[i]>0 && pr[i]<minPr){
                    minPr=pr[i]; 
                    idx=i;
                }
            }
            if(idx==-1){time++; continue;}
            rt[idx]--; time++;
            if(rt[idx]==0){
                ct[idx]=time;
                tat[idx]=ct[idx]-at[idx];
                wt[idx]=tat[idx]-bt[idx];
                completed++;
            }
        }

        System.out.println("PID\tAT\tBT\tPRIO\tCT\tTAT\tWT");
        double avgTAT=0,avgWT=0;
        for(int i=0;i<n;i++){
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\t%d\n",pid[i],at[i],bt[i],pr[i],ct[i],tat[i],wt[i]);
            avgTAT+=tat[i]; avgWT+=wt[i];
        }
        System.out.printf("Avg TAT=%.2f Avg WT=%.2f\n",avgTAT/n,avgWT/n);
    }
}
