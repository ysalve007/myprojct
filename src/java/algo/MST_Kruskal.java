/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

/**
 *
 * @author user6
 */
public class MST_Kruskal
{
    private int [][] data;
    private int [][] mstkdata;
    private int [] state;
    private int [] d;
    private int [] pi;
    private char c;

    MST_Kruskal(int[][] data,char c)
    {
        this.data =new int[data.length][data.length];
        this.data=data;
        this.d=new int[data.length];
        this.pi=new int[data.length];
        this.state=new int [data.length];
        this.mstkdata =new int [data.length][data.length];
        this.c=c;
    }
    public void MST_Kruskal()
    {
        java.util.LinkedList<Edge1> l =new java.util.LinkedList<Edge1>();
        for(int i=0;i<data.length;i++)
        {
            for(int j=0;j<data[i].length;j++)
            {
                if(i==j || data[i][j]==0)
                {
                    continue;
                }
                l.add(new Edge1(i,j,data[i][j]));                
            }
        }
        for(int i=l.size()-1;i>=0;i--)
        {
            for(int j=0;j<i;j++)
            {
                if(l.get(j).weight>l.get(j+1).weight)
                {
                    Edge1 hold =new Edge1();
                    hold= l.remove(j);
                    l.add(j+1,hold);
                }
            }
        }
        while(l.size()!=0)
        {
            Edge1 hold =new Edge1();
            hold = l.removeFirst();
            if(!isPathBetween(hold.u,hold.v))
            {
                mstkdata[hold.u][hold.v]=hold.weight;
                if(c!='d')
                {
                    mstkdata[hold.v][hold.u]=hold.weight;
                }

                System.out.println(hold.u+" "+hold.v+" "+hold.weight);
            }
        }
    }
    public int getcost()
    {
        int answer=0;
        MST_Kruskal();
        for(int i=0;i<mstkdata.length;i++)
        {
            for(int j=0;j<data[i].length;j++)
            {
               answer+=mstkdata[i][j]; 
            }
        }
        if(c=='d')
        {
            return answer;
        }
        else
        {
            return answer/2;
        }
    }
    private void breadthFirstSearch(int s)
    {

        java.util.LinkedList<Integer>  q=new java.util.LinkedList<Integer>();
        for(int i=0;i<mstkdata.length;i++)
        {
            state[i]=-1;
            d[i]=0;
        }
        q.add(s);
        state[s]=0;

        while(q.size()!=0)
        {
            int hold= q.removeFirst();

            for(int i=0;i<mstkdata.length;i++)
            {
                if(mstkdata[hold][i]!=0 && state[i]==-1)
                {
                    q.add(i);
                    state[i]=0;
                    d[i]=d[hold]+1;
                    pi[i]=hold;
                }
            }
            state[hold]=1;
        }
    }
    private boolean isPathBetween(int u,int v)
    {
        breadthFirstSearch(u);
        if(d[v]!=0)
        {
            return true;
        }
        return false;
    }
    class Edge1
    {
        int weight,u,v;
        Edge1(int u,int v,int weight)
        {
            this.u=u;
            this.v=v;
            this.weight=weight;
        }
        Edge1(){}   
    }
}
