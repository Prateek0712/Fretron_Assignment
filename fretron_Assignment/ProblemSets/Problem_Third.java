package ProblemSets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Problem_Third {

    public static void helper(int castleRow,int  castleCol,char currDir,String ans,int currR,int currC, int[][]grid)
    {
        //base case
        if(currR==castleRow && currC==castleCol)
        {
            ans+="Return Home. \n";
            System.out.println("New Path:- \n=========\n"+ ans);
            return ;
        }
        //invalid coordinate
        if(currR<1 ||currR>=grid.length || currC>=grid[0].length
                || currC<0)
        {
            return ;
        }
        /* I need to go left after each killig that means
        i need  to turn 90 degree in anti clock direction to the currentDirection that what is decided by
         currDir */
        if(grid[currR][currC]==0) //soldier region
        {
            /* At  this Stage I will have two possiblities either kill the soldier or jump over it*/

            if(currDir=='D')
            {
                grid[currR][currC]=1;
                //kill soldier
                helper(castleRow, castleCol, 'R',ans+"kill ("+currC+","+currR+") turn left, \n"
                        ,currR, currC+1,grid);
                grid[currR][currC]=0; //backtracking

                //jump over it
                helper(castleRow, castleCol, 'D',ans+"Jump("+currC+","+currR+"), \n"
                        ,currR+1, currC,grid);
            }
            else if(currDir=='R')
            {
                grid[currR][currC]=1;
                helper(castleRow, castleCol, 'U',ans+"kill ("+currC+","+currR+") turn left,\n"
                        ,currR-1, currC,grid);
                grid[currR][currC]=0;
                helper(castleRow, castleCol, 'R',ans+"Jump("+currC+","+currR+"), \n"
                        ,currR, currC+1,grid);
            }
            else if(currDir=='U')
            {
                grid[currR][currC]=1;
                helper(castleRow, castleCol, 'L',ans+"kill ("+currC+","+currR+") turn left,\n"
                        ,currR, currC-1,grid);
                grid[currR][currC]=0;
                helper(castleRow, castleCol, 'U',ans+"Jump ("+currC+","+currR+"), \n"
                        ,currR-1, currC,grid);
            }
            else
            {
                grid[currR][currC]=1;
                helper(castleRow, castleCol, 'D',ans+"kill ("+currC+","+currR+") turn left, \n"
                        ,currR+1, currC,grid);
                grid[currR][currC]=0;
                helper(castleRow, castleCol, 'L',ans+"Jump ("+currC+","+currR+"), \n"
                        ,currR, currC-1,grid);

            }
        }
        //When region is not soldier a need to move in currDirection always
        else {
            if(currDir=='D')
            {

                helper(castleRow, castleCol, 'D',ans,currR+1, currC,grid);
            }
            else if(currDir=='R')
            {

                helper(castleRow, castleCol, 'R',ans,currR, currC+1,grid);
            }
            else if(currDir=='U')
            {
                helper(castleRow, castleCol, 'U',ans,currR-1, currC,grid);
            }
            else
            {
                helper(castleRow, castleCol, 'L',ans,currR, currC-1,grid);
            }
        }
    }
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter Number Of Soldier - ");
        int solderCount=sc.nextInt();
        sc.nextLine();
        //System.out.println();
        int[][]arr=new int[solderCount][2];
        int maxi=Integer.MIN_VALUE;
        System.out.println("Enter coordinate in Column Row format as given in sample example by you ");
        //System.out.println();
        for(int i=0;i<solderCount;i++)
        {
            System.out.print("Enter coordinates for soldier "+(i+1)+" - ");
            //String ip=sc.nextLine();
            String[] inp=sc.nextLine().split(",");
            int x= Integer.parseInt(inp[0].trim());
            int y=Integer.parseInt(inp[1].trim());
            maxi=Math.max(maxi,Math.max(x,y));
            arr[i][0]=x;
            arr[i][1]=y;

        }
        System.out.print("Enter coordinates for castle - ");
        String[] inp=sc.nextLine().split(",");
        int castleC= Integer.parseInt(inp[0].trim());
        int castleR=Integer.parseInt(inp[1].trim());
        System.out.println();
        int[][]grid=new int[maxi+1][maxi+1];
        for(int[] g:grid)
        {
            Arrays.fill(g,1);
        }
        for(int[] a:arr)
        {
            grid[a[1]][a[0]]=0;
        }
        grid[castleR][castleC]=2;
        helper(castleR,castleC,'D',"Start ("+castleC+","+castleR+"), \n",
                castleR+1,castleC,grid);
    }
}
