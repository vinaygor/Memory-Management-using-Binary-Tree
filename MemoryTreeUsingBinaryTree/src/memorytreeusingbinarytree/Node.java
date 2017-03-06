/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorytreeusingbinarytree;

/**
 *
 * @author vinay
 */
public class Node {
    int jobId;
    int size;

    Node left;
    Node right;
    Node parent;
    boolean available;
    boolean mapFlag;
    int startAddr;
    int endAddr;
    int leftAvailableSize;
    int rightAvailableSize;
    
    Node(int size)
    {
        this.size=size;
        left=right=parent=null;
        available=true;
        leftAvailableSize=0;
        rightAvailableSize=0;
        mapFlag = false;
    }
    Node()
    {}

    
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
    
    public boolean isMapFlag() {
        return mapFlag;
    }

    public void setMapFlag(boolean mapFlag) {
        this.mapFlag = mapFlag;
    }
    
    

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

  
  

    public int getStartAddr() {
        return startAddr;
    }

    public void setStartAddr(int startAddr) {
        this.startAddr = startAddr;
    }

    public int getEndAddr() {
        return endAddr;
    }

    public void setEndAddr(int endAddr) {
        this.endAddr = endAddr;
    }

    public int getLeftAvailableSize() {
        return leftAvailableSize;
    }

    public void setLeftAvailableSize(int leftAvailableSize) {
        this.leftAvailableSize = leftAvailableSize;
    }

    public int getRightAvailableSize() {
        return rightAvailableSize;
    }

    public void setRightAvailableSize(int rightAvailableSize) {
        this.rightAvailableSize = rightAvailableSize;
    }

    public int availableSize()
    {
        if(size==2 && isAvailable())
            return 2;
        else
            return leftAvailableSize+rightAvailableSize;
    }
    
}
