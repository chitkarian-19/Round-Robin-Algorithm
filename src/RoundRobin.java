import java.util.*;
class Process{
	String name;
	int bt;
	int at;
	Process next;
}
public class RoundRobin {
    
	static void Schedule(Process p1,Process p2,Process p3,int quant,int lf) {
		
		String gantt="";
		Queue<Process> rq= new LinkedList<Process>();
		AddE(rq,0,p1,p1);//Initially adding the processes into the ready queue
		int time=0;
		while(p1.bt!=0||p2.bt!=0||p3.bt!=0) {
		   
		   if(rq.isEmpty()) {
			   gantt+=time+" * ";
			   time++;  //Allow process to come into the ready queue
			   AddE(rq,time,p1,p1);
		   }
		   else {
			   //Exceution of process
			   Process p= rq.poll();
			   int t=p.bt>quant?quant:(p.bt);
			   for(int i=1;i<=t;i++) {
				   AddE(rq,time+i,p1,p);//Add processes while the main p is executing
			   }
			  
			   p.bt-=t;
			  gantt+=(time)+" "+p.name+" ";
			   time=time+t;
			   if(time>lf) {
				   System.out.println(gantt);
				   System.out.print("Abort");
				   return;
			   }
		   }
		}
		gantt+=time;
		System.out.println(gantt);System.out.print("Successful");
		
	}
	 static void AddE(Queue<Process> rq, int time, Process p1,Process mp) {
		 Process temp=p1;
		  do {
			   
			if(temp!=mp&&(!rq.contains(temp))&&temp.at<=time&&temp.bt>0) {
				rq.add(temp);
				
			}
			temp=temp.next;
		}while(temp!=p1);
		//If queue empty but mp is still left with some burst time
		  if(rq.isEmpty()&&mp.at<=time&&mp.bt>0) {
			  rq.add(mp);
		  }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	     Scanner sc= new Scanner(System.in);
         System.out.println("Welcome to the RoundRobin Scheduler\n");
         Process P1 = new Process();
         
         Process P2 = new Process();
         
         Process P3 = new Process();
         P1.next=P2;
         P2.next=P3;
         P3.next=P1;
         System.out.println("Enter the CPU Lifetime: ");
         int lf=sc.nextInt();
         System.out.println("Entered Lifetime: "+lf);
         System.out.println("Enter the time quantum: ");
         int quant=sc.nextInt();
         System.out.println("Entered quant: "+quant);
         
         System.out.println("Enter the Arrival time and Burst time one by one");
         System.out.println("Process 1");
         System.out.println("Arrival Time:");
         P1.name="P1";
         P1.at=sc.nextInt();
         System.out.println("BURST Time:");
         P1.bt=sc.nextInt();
         System.out.println();
         System.out.println("Process 2");
         System.out.println("Arrival Time:");
         P2.name="P2";
         P2.at=sc.nextInt();
         System.out.println("BURST Time:");
         P2.bt=sc.nextInt();
         System.out.println();
         System.out.println("Process 3");
         System.out.println("Arrival Time:");
         P3.name="P3";
         P3.at=sc.nextInt();
         System.out.println("BURST Time:");
         P3.bt=sc.nextInt();
         System.out.println("Process "+ "Arrival  "+"Burst ");
         System.out.println("  P1       "+ P1.at+"      "+ P1.bt);
         System.out.println("  P2       "+ P2.at+"      "+ P2.bt);
         System.out.println("  P3       "+ P3.at+"      "+ P3.bt);
         System.out.println();
         System.out.println();
         System.out.println();
         Schedule(P1,P2,P3,quant,lf);
	}

}
