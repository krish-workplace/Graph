package DSU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DSUByRank{
    
    private int parent[],rank[];
    int components;
    
    DSUByRank(int components)
    {
        parent = new int[components];
        rank = new int[components];
        this.components = components;
        for(int i=0;i<components;i++)
        {
            parent[i] = i;
        }
    }

    void union(int u,int v)
    {
        //*1 : find the root parent of both
        //*2 : find the rank fo both rootparent 
        //*3 : merge smaller rank to bigger rank

        int rootParentU = getRootParent(u);
        int rootParentV = getRootParent(v);

        if(rootParentU == rootParentV)
            return;

        if(rank[rootParentU] > rank[rootParentV])
        {
            parent[rootParentV] = rootParentU;
            // rank[rootParentU]++;
        }
        else if(rank[rootParentV] > rank[rootParentU])
        {
            parent[rootParentU] = rootParentV;
            // rank[rootParentV]++;
        }
        else
        {
            parent[rootParentV] = rootParentU;
            rank[rootParentU]++;
        }
        this.components--;

    }

    int getRootParent(int u)
    {
        if(u == parent[u])
            return u;

        parent[u] = getRootParent(parent[u]); // path compression 
        return parent[u];
    }


}

public class AccountMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        ArrayList<List<String>> ans = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap<>();

        DSUByRank dsuByRank = new DSUByRank(accounts.size());

        //new find where a email are overlapping 
        for(int i=0;i<accounts.size();i++)
        {
            for(int j=1;j<accounts.get(i).size();j++)
            {
                String email = accounts.get(i).get(j);

                //check if map contains email so there is overlapping email
                if(map.containsKey(email))
                {
                    //apply union operation 
                    dsuByRank.union(map.get(email), i);
                }
                else
                {
                    //create new entry
                    map.put(email, i);
                }
            }
        }

        //^now we have an union component 
        //^now we have a hashMap that contains email ->  its component 
        //^so we add email to rootParent(component)

        ArrayList<ArrayList<String>> mergedEmail = new ArrayList<>(accounts.size());
        
        for(int i=0;i<accounts.size();i++)
        {
            mergedEmail.add(new ArrayList<>());
        }

        for(Map.Entry<String,Integer> entry : map.entrySet())
        {
            int component = entry.getValue();
            String email = entry.getKey();
            int rootParent = dsuByRank.getRootParent(component);

            //add email at rootParent index in terms of List
            mergedEmail.get(rootParent).add(email);
        }

        for(int i=0;i<mergedEmail.size();i++)
        {
            if(mergedEmail.get(i).size() == 0)
                continue;

            Collections.sort(mergedEmail.get(i));
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            for(String it : mergedEmail.get(i))
            {
                temp.add(it);
            }
            ans.add(temp);
        }

        return ans;
    }
}
