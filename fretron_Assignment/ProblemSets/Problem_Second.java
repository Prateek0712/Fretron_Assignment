package ProblemSets;


import java.util.*;

/* this example did not explicitely mention what we have to  do when there are apples that can not
* be distribute in proper proportion of 5: 3: 2. So in that case i will attempt to
* distribute extra apples on basis of person who paid more will get more apples */
public class Problem_Second {


    public static boolean helper( int i,List<Integer>apples,List<String>results, String ans,
                                  int currShare,boolean[]vis,int[]persons,int p)
    {
        if(currShare==0)
        {
            System.out.println(ans);
            if(p+1>=3){
                results.add(ans);
                return  true;
            }
            boolean result =helper(0,apples,results,"",persons[p+1],vis,persons, p+1);
            if(result) results.add(ans);
            return result;
        }
        if(i>=apples.size() || currShare<0)
        {
            return false;
        }
        if(vis[i]!=true)
        {
            vis[i]=true;
            boolean resp=helper(i+1,apples,results,ans+apples.get(i)+", ",currShare-apples.get(i),
                    vis,persons,p);
            vis[i]=false;
            if(resp) return true;
        }
        return helper(i+1,apples,results,ans,currShare, vis,persons,p);
    }
    public static List<List<Integer>> failureHandle(List<Integer> apples,int totalWeight) {
        // Sort apples by weight in descending order
        Collections.sort(apples, (a, b) -> Integer.compare(b, a));

        // Calculate proportion of payment for each person
        double[] proportions = new double[3];
        proportions[0]= (double) 5/10;
        proportions[1]= (double) 3/10;
        proportions[2]= (double) 2/10;

        // Initialize lists to store distributed apples
        List<List<Integer>> distributedApples = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            distributedApples.add(new ArrayList<>());
        }
        // Distribute apples
        for (Integer apple : apples) {
            // Find the person who should get the apple based on proportion
            double maxProportion = 0;
            int personIndex = 0;
            for (int i = 0; i < proportions.length; i++) {
                if (proportions[i] > maxProportion) {
                    maxProportion = proportions[i];
                    personIndex = i;
                }
            }
            // Add apple to person's list
            distributedApples.get(personIndex).add(apple);
            // Update proportion
            proportions[personIndex] -= (double) apple / totalWeight;
        }
        return distributedApples;
    }
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc=new Scanner(System.in);
        List<Integer>apples=new ArrayList<>();
        int weight=0;
        int totalWeight=0;
        while(weight!=-1){
            System.out.print("Enter apple weight in grams (-1 to stop): ");
            weight=sc.nextInt();
            if(weight!=-1)
            {
                apples.add(weight);
                totalWeight+=weight;
            }
        }
        if(apples.size()<3)
        {
            System.out.println("distribution is not possible");
            System.exit(404);
        }
        List<String>result= new ArrayList<>();
        boolean[]vis=new boolean[apples.size()];
        int[]persons={(totalWeight*5)/10,(totalWeight*3)/10,(totalWeight*2)/10};
        boolean resp=helper(0,apples,result,"",persons[0],vis,persons,0);
        if(resp)
        {
            System.out.println("Ram : "+ result.get(2));
            System.out.println("Sham : "+ result.get(1));
            System.out.println("Rahim : "+ result.get(0));
        }
        //In false case scenerio where apples cant be distribute with proper proportion i will try to use algorithm
        //which will try to distribute apples in such a way that person paid more will receive more
        //but proportion of distribution will not be fix in that case
        else {
            System.out.println("Proper distribution not possible. So trying to distribute as good as possible");
            List<List<Integer>>answer=failureHandle(apples,totalWeight);
            System.out.println("Ram : "+ result.get(0).toString());
            System.out.println("Sham : "+ result.get(1).toString());
            System.out.println("Rahim : "+ result.get(2).toString());
        }
    }
}

