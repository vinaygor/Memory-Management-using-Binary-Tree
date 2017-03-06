/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorytreeusingbinarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author vinay
 */
public class MemoryTreeUsingBinaryTree {
    
   
    /**
     * @param args the command line arguments
     */
    public static ArrayList<Integer> jobIdArray =new ArrayList<Integer>();
    public static int jobIdCount=0; 
   public static ArrayList<Node> nodeList = new ArrayList<Node>();
   public static Map<Integer,ArrayList<Node>> memoryMap = new HashMap<Integer,ArrayList<Node>>();
   static boolean globalFlag=false;
    static Node mainRoot;
    static int mergeFunctionCount=0;
    static int mergeSuccessCount=0;

   
    MemoryTreeUsingBinaryTree(int size)
    {
        mainRoot= new Node(size);
        mainRoot.startAddr=0;
        mainRoot.endAddr=size-1;
    }
    
    MemoryTreeUsingBinaryTree()
    {
        mainRoot=null;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        MemoryTreeUsingBinaryTree binaryTree= new MemoryTreeUsingBinaryTree(32);
        initializeTree(mainRoot);
        intializeHashMap(mainRoot);
        System.out.println("Completed");
        //display(root);
        //availablilityCount(root);
        //calculate(root);
        
       // System.out.println("Level 3- Left-Start "+root.left.left.startAddr);
       // System.out.println("Level 3- Left-End"+root.left.endAddr);
       //System.out.println("Level 3- Right-Start"+root.right.startAddr);
       //System.out.println("L and R value = "+root.right.rightAvailableSize+" : "+root.right.leftAvailableSize);
       
     //  root.left.left.setAvailable(false);
       //root.left.right.setAvailable(false);
       
       calculateLeftRightAvailableSize(mainRoot);
     //  System.out.println("Root :"+root.size);
     
        //System.out.println("Root availablility :"+root.left.left.isAvailable());
//        System.out.println("L and R value = "+root.leftAvailableSize+" : "+root.rightAvailableSize);
////        
//        allocateMemory(2,mainRoot);
//       calculateLeftRightAvailableSize(mainRoot);
//       displayMap(memoryMap);
////       System.out.println("L and R value = "+root.leftAvailableSize+" : "+root.rightAvailableSize);
////        
//        allocateMemory(4,mainRoot);
//       calculateLeftRightAvailableSize(mainRoot);
//       displayMap(memoryMap);
////        System.out.println("L and R value = "+root.leftAvailableSize+" : "+root.rightAvailableSize);
////        
//     allocateMemory(4,mainRoot);
//       calculateLeftRightAvailableSize(mainRoot);
//       displayMap(memoryMap);
//        System.out.println("L and R value = "+root.leftAvailableSize+" : "+root.rightAvailableSize);

int random[] = {2,2,4,2,4,8,16,8,16,2,2,2,2};
int input[]={8,2,2,16,2,2,16,4};
int del[]={2,5};

int count=0;
//while(count!=3)
//{
//for(int i=1;i<=5;i++)
//    {
//    if(i<8)
//    {
//        for(int j=1;j<=50;j++)
//        {
//            random[count++]=(int) Math.pow(2, i);
//        }
//    }
//    else
//    random[count++]=(int) Math.pow(2, i);    
//    }
//}
Random rand = new Random();

 long startTime = System.nanoTime();
 //displayMap(memoryMap);
for(int i=0;i<100;i++)
{
   
    int requestedSize = rand.nextInt(random.length);
    globalFlag=false;
    System.out.println("Size "+random[requestedSize]);
   // System.out.println("Size "+input[i]);
     boolean flag =allocateMemory(random[requestedSize],mainRoot);
     
     if(flag)
     {
         System.out.println("Memory Full---------");
     }
     else if(!globalFlag)
         mergeCase(random[requestedSize],mainRoot);
     
    calculateLeftRightAvailableSize(mainRoot);
   // displayMap(memoryMap);
   
   if(i!=0 && i%3==0)
   {
       
       Random randomIndex = new Random();
        int randomNumber = randomIndex.nextInt(jobIdArray.size());
       displayMap(memoryMap);
        int jobToDeallocate = jobIdArray.get(randomNumber);
       deallocateMemory(jobToDeallocate, mainRoot);
       calculateLeftRightAvailableSize(mainRoot);
        for (Map.Entry<Integer, ArrayList<Node>> entry : memoryMap.entrySet()) 
           {
            entry.getValue().clear();
       
            }
       updateHashMap(memoryMap,mainRoot);
       jobIdArray.remove(randomNumber);
       System.out.println("After Deletion:");
       displayMap(memoryMap);
   }
} 
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        
        System.out.println(elapsedTime+" NanoSeconds");
//displayMap(memoryMap);
for(int i=0;i<jobIdArray.size();i++)
{
    System.out.println("Job Id "+jobIdArray.get(i));
}
System.out.println("Merge Function Count :"+mergeFunctionCount);
System.out.println("Merge Success Count :"+mergeSuccessCount);
//displayMap(memoryMap);

//
//    mainRoot.right.right.right.setAvailable(false);
//    updateSubtree(mainRoot.right.right.right);
//    updateHashMap(memoryMap,mainRoot);
//    calculateLeftRightAvailableSize(mainRoot);
//    
//    allocateMemory(2,mainRoot);
//    calculateLeftRightAvailableSize(mainRoot);
//    
//    allocateMemory(2,mainRoot);
//    calculateLeftRightAvailableSize(mainRoot);
//    
//    allocateMemory(2,mainRoot);
//    calculateLeftRightAvailableSize(mainRoot);
//    
//   boolean flag =allocateMemory(8,mainRoot);
//    if(!flag)
//        mergeCase(8,mainRoot);
//    calculateLeftRightAvailableSize(mainRoot);
//    displayMap(memoryMap);
//    
//    display(mainRoot);
    }
    
    public static void display(Node root)
    {
        if(root!=null)
        {
            display(root.left);
            System.out.println("Root.size :"+root.size+"Root.AS:"+root.availableSize());           
            display(root.right);            
        }        
    }
        
    public static void displayMap(Map<Integer,ArrayList<Node>> map){
        for (Map.Entry<Integer, ArrayList<Node>> entry : map.entrySet()) {
        System.out.print(entry.getKey()+" | ");
        for(Node n : entry.getValue()){
            System.out.print(+n.startAddr+"-"+n.endAddr+"\t");
        }
        System.out.println();
    }

    }
    
    public static void intializeHashMap(Node root)
    {
        int temp =0;
        int rootSize = root.size-1;
        int size = root.size;
        int startAddr=0, endAddr=0;
        while(size>1)
        {
            ArrayList<Node> nodelist = new ArrayList<Node>();
            {
                for(int i= 0;i<Math.pow(2, temp);i++)
                {  
                    Node n = new Node(root.size);
                    if(i==0)
                    { 
                        startAddr =0;
                        endAddr = root.size -1;
                       
                    }
                    else
                    {
                        startAddr = endAddr + 1;
                        endAddr = startAddr + root.size -1;
                    }
                    
                    n.startAddr = startAddr;
                    n.endAddr = endAddr;
                    nodelist.add(n);
                                        
                }
            }
            memoryMap.put(size,nodelist);
            temp++;
            size = size/2;
        }
        //displayMap(memoryMap);
    }
    
    public static void updateHashMap(Map<Integer,ArrayList<Node>> map,Node root){
        if(root!=null)
        {
            updateHashMap(map,root.left);
            updateHashMap(map,root.right);
            if(root.size == 2)
            {
                if(root.isAvailable())
                {
                        ArrayList<Node> list = map.get(root.size);
                    Node temp = new Node(root.size);
                    temp.setStartAddr(root.getStartAddr());
                    temp.setEndAddr(root.getEndAddr());
                    list.add(temp);
                    map.put(root.size,list);
                    root.mapFlag = true;
                }
                
            }
                else
                {
                    if(root.isAvailable() && root.left.isMapFlag() && root.right.isMapFlag())
                    {
                        ArrayList<Node> list = map.get(root.size);
                        Node temp = new Node(root.size);
                        temp.setStartAddr(root.getStartAddr());
                        temp.setEndAddr(root.getEndAddr());
                        list.add(temp);
                        map.put(root.size,list);
                        root.mapFlag = true;
                        
                    }
                    else
                    {
                        root.mapFlag = false;
                    }
                
            }
            
        }
        
             
         
    }
    
    public static void initializeTree(Node root)
    {
        int size=root.size;
        
        int newSize=size/2;
        if(newSize>1)
        {
            //creating new nodes Left and right
            Node newLeftNode=new Node(newSize);
            Node newRightNode=new Node(newSize);
            
            //Attaching new nodes on left and right
            root.left=newLeftNode;
            root.right=newRightNode;
            
            //assigning parents to the new nodes created
            newLeftNode.parent=root;
            newRightNode.parent=root;
            
            //setting up the addresses 
            newLeftNode.startAddr=root.startAddr;
            newLeftNode.endAddr=((root.endAddr+root.startAddr)/2);
            newRightNode.startAddr=((root.endAddr+root.startAddr)/2) + 1;
            newRightNode.endAddr=root.endAddr;
            
            //recursion call to create subtrees
            initializeTree(newLeftNode);       
            initializeTree(newRightNode);
            
        }
    }
    
    public static void calculateLeftRightAvailableSize(Node root)
    {
       
         int size=root.size;
        int newSize=size/2;
        
        if(newSize>1)
        {
            calculateLeftRightAvailableSize(root.left);
            calculateLeftRightAvailableSize(root.right);
           
            if(root.left.isAvailable())
            {
                if(root.left.size==2)
                    root.setLeftAvailableSize(2);
                else
                    root.setLeftAvailableSize(root.left.getLeftAvailableSize()+root.left.getRightAvailableSize());
            }
            else
                root.setLeftAvailableSize(0);
            
            if(root.right.isAvailable())
            {
                if(root.right.size==2)
                    root.setRightAvailableSize(2);
                else
                    root.setRightAvailableSize(root.right.getLeftAvailableSize()+root.right.getRightAvailableSize());
            } 
            else
                root.setRightAvailableSize(0);
            
            if(root.left.size!=2)
                if((root.left.availableSize()+root.right.availableSize())==0){
                    root.setAvailable(false);
                     for (Map.Entry<Integer, ArrayList<Node>> entry : memoryMap.entrySet()) 
           {
            entry.getValue().clear();
       
            }
                    updateHashMap(memoryMap,mainRoot);
                }                    
        }
    }
    
    public static void mergeCase(int sizeRequired,Node root)
    {
        mergeFunctionCount++;
        int sizeRoot=root.size;
       // while(sizeRoot>1)
        {
            
       // if(root.isAvailable() && root.availableSize()<sizeRequired)
        {
            int size = sizeRequired;
            ArrayList<Node> startNode = new ArrayList<Node>();
            while(size>1)
            {
                int count = 0;
                ArrayList<Node> list =memoryMap.get(size);
                startNode.clear();
                if(list.size()*size == sizeRequired)
                {
                    for(int i=0;i<list.size()-1;i++)
                    {
                        if(count == sizeRequired)
                        {
                            updateTreeNodesAvailability(startNode);
                            mergeSuccessCount++;
                            return;
                        }
                        int endaAddr = list.get(i).endAddr;
                        int startAddr = list.get(i+1).startAddr;
                        if(startAddr - endaAddr ==1)
                        {
                            if(startNode!=null)
                            {
                                if(!startNode.contains(list.get(i)))
                                {
                                    startNode.add(list.get(i));
                                    count = count + list.get(i).size;
                                }
                                if(!startNode.contains(list.get(i+1)))
                                {
                                    startNode.add(list.get(i+1));
                                    count = count + list.get(i+1).size;
                                }

                            }
                            else
                            {
                                if(list.size()>0)
                                {
                                startNode.add(list.get(i));
                                count = count + list.get(i).size;
                                startNode.add(list.get(i+1));
                                count = count + list.get(i+1).size;}
                            }
                           
                        }
                        else
                        {
                            count =0;
                            if(startNode!=null)
                            startNode.clear();
                        }
                            
                    }
                    if(count == sizeRequired)
                        {
                            updateTreeNodesAvailability(startNode);
                            mergeSuccessCount++;
                            return;
                        }
                    else
                        size=size/2;
                }
                else
                    size =size/2;
            }
            
            System.out.println("Memory Full");
            return;
        }
        }
    }
    
    public static boolean allocateMemory(int sizeRequired,Node root)
    {
        if(root.availableSize()==0)
            return true;
       if(!globalFlag)
       if( root.isAvailable() && root.availableSize()==sizeRequired && root.size ==sizeRequired)
       {
           root.setJobId(++jobIdCount);
           System.out.println("Memory Assigned. Job Id is :"+jobIdCount);
           jobIdArray.add(jobIdCount);
           root.setAvailable(false);
           root.setMapFlag(false);
           
           //updating the subtree flags to set them unavailable
           updateSubtree(root);
           
           for (Map.Entry<Integer, ArrayList<Node>> entry : memoryMap.entrySet()) 
           {
            entry.getValue().clear();
       
            }
           updateHashMap(memoryMap,mainRoot);
           globalFlag=true;
           return false;
       }
       else
       {
           if((root.size/2)>1)
           {
           if(root.getLeftAvailableSize()>=sizeRequired)
               allocateMemory(sizeRequired, root.left);
           else if(root.getRightAvailableSize()>=sizeRequired)
               allocateMemory(sizeRequired, root.right);
           }
       }       
        return false;
    }
        
    public static void updateTreeNodesAvailability(ArrayList<Node> nodeList){
        ++jobIdCount;
        for(Node n:nodeList){
            searchAndUpdateNodeInTree(n,mainRoot);
        }
        System.out.println("Memory Allocated. Job id assigned is: "+jobIdCount);
    }
    
    public static void searchAndUpdateNodeInTree(Node n,Node root){
        if(root==null){
            return;
        }else{
            if(root.size==n.size && root.startAddr==n.startAddr && root.endAddr==n.endAddr){
                root.setJobId(jobIdCount);
                jobIdArray.add(jobIdCount);
                root.setAvailable(false);
                updateSubtree(root);
                for (Map.Entry<Integer, ArrayList<Node>> entry : memoryMap.entrySet()) 
           {
            entry.getValue().clear();
       
            }
                updateHashMap(memoryMap, root);
                return;
            }
            searchAndUpdateNodeInTree(n,root.left);
            searchAndUpdateNodeInTree(n,root.right);
        }
    }
    
    public static void updateSubtree(Node root)
    {
        if(root!=null)
        {
            updateSubtree(root.left);
            updateSubtree(root.right);
            root.setAvailable(false);
            root.setMapFlag(false);
        }
    }
    
    public static void deallocateMemory(int jobToDelete, Node root)
    {
           if(root!=null)
        {
        deallocateMemory(jobToDelete, root.left);
                deallocateMemory(jobToDelete, root.right);
        
     
            if(root.jobId==jobToDelete)
            {
                System.out.println("Size:"+root.size+" | Root Starting addr "+root.startAddr);
                root.setJobId(-1);
                root.setAvailable(true);
                root.setMapFlag(true);
                updateDeallocateSubTree(root);
                updateParentNodes(root,root.size);
                return;
            }
            
        }
        
    }
    
    public static void updateParentNodes(Node root, int sizeToAdd)
    {
        if(root.parent!=null)
        {
            Node parent= root.parent;
            if(parent.left==root)
            {
                parent.leftAvailableSize=parent.leftAvailableSize+sizeToAdd;
            }
            else
            {
                parent.rightAvailableSize=parent.rightAvailableSize+sizeToAdd;
            }
            if(parent.leftAvailableSize+parent.rightAvailableSize==parent.size)
            {
                parent.mapFlag=true;
            }
            parent.setAvailable(true);
            updateParentNodes(parent, sizeToAdd);
            
        }
    }
    
    public static void updateDeallocateSubTree(Node root)
    {
        if(root!=null)
        {
            updateDeallocateSubTree(root.left);
            updateDeallocateSubTree(root.right);
            root.setAvailable(true);
            root.setMapFlag(true);
        }
    }
}
