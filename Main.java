/**
 * Created by Danish on 11/4/2016.
 */
import java.util.Scanner;
class WorkItem{
      public static int [][] result;
     int[]arr1,arr2;
     int i;
     int j;
    WorkItem(int[] arr1,int[] arr2,int i,int j){
        this.arr1=arr1;
        this.arr2=arr2;
        this.i=i;
        this.j=j;
    }
}
class Workerthread implements Runnable{
    WorkItem obj;
    Workerthread(WorkItem obj){
        this.obj=obj;
    }
    public  void run(){
        for (int k = 0; k <obj.arr1.length ; k++) {
        WorkItem.result[obj.i-1][obj.j-1]+=obj.arr1[k]*obj.arr2[k];
        }

    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        System.out.println("enter thread number ");
        int n=scn.nextInt();
        System.out.println("enter row of matrix A");
        int row=scn.nextInt();
        System.out.println("enter column of matrix A");
        int c1=scn.nextInt();
        System.out.println("enter row of matrix B");
        int r2=scn.nextInt();
        System.out.println("enter column of matrix B");
        int col=scn.nextInt();
        WorkItem.result=new int[row][col];

        int[][]A=new int[row][c1];
        int[][]B=new int[r2][col];
        int [][]C=new int[col][r2];
        System.out.println("enter matrix A");
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <c1 ; j++) {
                A[i][j]=scn.nextInt();
            }
        }
        System.out.println("enter matrix B");
        for (int i = 0; i <r2 ; i++) {
            for (int j = 0; j <col ; j++) {
                B[i][j]=scn.nextInt();
            }
        }
        for (int i = 0; i <col ; i++) {
            for (int j = 0; j <r2 ; j++) {
                C[i][j]=B[j][i];
            }
        }


        int threadcount=0;
        int k=0;//flag
        int avg=((row*col)/n);
        Thread []W=new Thread[n];
        WorkItem[]obj=new WorkItem[row*col];
       // while(k<(row*col)) {

            for (int i = 0; i< row; i++) {
                for (int j = 0; j < col; j++) {
                    if (k<(row*col)){
                        obj[k] = new WorkItem(A[i],C[j], i+1, j+1);

                        k++;
                    }
                }
            }



           k=0;

        try
        {
              // if(k<(row*col)) {
                  while(threadcount < n) {
                    for (int i = 0; i <avg ; i++) {
                       // if (k < (row * col)) {

                            // creating thread for multiplications
                            W[threadcount] = new Thread(new Workerthread(obj[k]));

                            W[threadcount].start(); //thread start

                            W[threadcount].join(); // joining threads wait until all thread complete their work

                            k++;

                        //}

                    }
                          threadcount++;

                  }

           // }

        }
        catch (InterruptedException ie){
            System.out.println("interrupted");
        }
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col ; j++) {
                System.out.print(WorkItem.result[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
