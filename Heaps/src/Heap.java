
import java.util.*;

/**
 * Heap: Maintain a binary max heap of ints that contains mySize elements. The
 * entries of the heap are stored sequentially in the myElements array; thus
 * the array represents a binary tree of mySize nodes whose depth is minimal
 * and in which the bottom level is as far "left" as possible. The parent of
 * the entry at position k is at position (k-1)/2; the two children are at
 * positions 2*k+1 and 2*k+2. Each entry in the heap has at least as large a
 * value as its children.
 */
public class Heap
{
    private final boolean DEBUGGING = true;

    private int mySize;
    private Node root;
    private int capacity;
    
    private class Node{
    	Node LeftChild;
    	Node RightChild;
    	Node parent;
    	int data;
    	private void removeChild(Node n){
    	    	if(LeftChild==n){
    	    		LeftChild=null;
    	    	}
    	    	else if(RightChild==n){
    	    		RightChild=null;
    	    	}
    	    }
    }

    /**
     * Initializes an empty heap to hold up to the given number of elements.
     */
    public Heap(int capacity)
    {
        mySize = 0;
        this.capacity = capacity;
        //myElements = new int[capacity];
        /*if (DEBUGGING)
        {
            display();
            check();
        }*/
    }
    
    /**
     * Return true exactly when the heap is empty.
     */
    public boolean isEmpty()
    {
        return mySize == 0;
    }

    /**
     * Return the top of the heap.
     */
    public int top() throws NoSuchElementException
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("attempt to access top of empty heap");
        }

        //return myElements[0];
        return root.data;
    }
    
    /**
     * Remove the top of the heap.
     */
    public void remove() throws NoSuchElementException{
        if (isEmpty()){
            throw new NoSuchElementException("attempt to remove from empty heap");
        }
        /*if (mySize == 1)
        {
            mySize = 0;
        }
        else
        {
            myElements[0] = myElements[mySize - 1];
            mySize--;
            bubbleDown(0);
        }*/
        if(mySize==1){
        	root = null;
        }
        else{
        swap(root, findNode(mySize));
        findNode(mySize).parent.removeChild(findNode(mySize));
        bubbleDown(root);
        }
        mySize--;
        /*if (DEBUGGING){
            display();
            check();
        }*/
    }
   
    
    /**
     * Add an element to the heap.
     */
    public void add(int i){
        /*myElements[mySize] = newElement;
        mySize++;
        bubbleUp(mySize - 1);*/
    	if(mySize>=capacity){
    		throw new RuntimeException();
    	}
    	Node n = new Node();
    	n.data = i;
    	if(mySize==0){
    		root = n;
    	}
    	else{
    		int j = (mySize+1)/2;
    		n.parent = findNode(j);
    		if(n.parent.LeftChild==null){
    			n.parent.LeftChild = n;
    		}
    		else{
    			n.parent.RightChild = n;
    		}
    		bubbleUp(n);
    	}
    	mySize++;
       /* if (DEBUGGING){
        	display();
            check();
        }*/
    }
    //returns last node
    private Node findNode(int i){
    	int mask = 1;
    	while((mask*2)<= i){
    		mask= mask*2;
    	}
    	Node n = root;
    	mask = mask/2;
    	while(mask>0){
    	if((mask & i)==0){
    		n = n.LeftChild;
    	}
    	else{
    		n = n.RightChild;
    	}
    	mask = mask/2;
    	}
    	return n;
    }

    /**
     * Move the heap entry indexed by k down the heap until it's larger than
     * both its children. Each move exchanges the entry with its larger child.
     */
    private void bubbleDown(Node n){
        /*assert k >= 0 && k < mySize : "bubbling " + k + " down";

        while (2 * k + 1 < mySize - 1)
        {
            int maxChildIndex = isGreater(2 * k + 1, 2 * k + 2) ? (2 * k + 1)
                : (2 * k + 2);
            if (!isGreater(maxChildIndex, k))
            {
                return;
            }
            exchange(maxChildIndex, k);
            k = maxChildIndex;
        }

        if (2 * k + 1 == mySize - 1)    // Maybe only one child
        {
            if (isGreater(mySize - 1, k))
            {
                exchange(mySize - 1, k);
            }
        }*/
    	if((n.LeftChild != null && n.data<n.LeftChild.data)||(n.RightChild != null && n.data<n.RightChild.data)){
    		if(n.RightChild == null){
    			swap(n, n.LeftChild);
    			bubbleDown(n.LeftChild);
    		}
    		else if(n.LeftChild.data<n.RightChild.data){
    			swap(n, n.RightChild);
    			bubbleDown(n.RightChild);
    		}
    		else{
    			swap(n, n.LeftChild);
    			bubbleDown(n.LeftChild);
    		}
    	}
    }
    
    private void swap(Node n, Node m){
    	int i = n.data;
    	int j = m.data;
    	n.data = j;
    	m.data = i;
    }

    /**
     * Move the heap entry indexed by k up the heap until it's smaller than its
     * parent.
     */
    private void bubbleUp(Node n){
        /*assert k >= 0 && k < mySize : "bubbling " + k + " up";
        while (k > 0 && isGreater(k, (k - 1) / 2))
        {
            exchange(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }*/
        if(n.parent != null && n.parent.data < n.data){
        	swap(n, n.parent);
        	bubbleUp(n.parent);
        }
    }
    
    /**
     * Exchange the two heap elements at the given positions.
     */
    /*private void exchange(int k1, int k2)
    {
        assert k1 >= 0 && k2 >= 0 && k1 < mySize && k2 < mySize :
            "exchanging " + k1 + " with " + k2;
        int temp = myElements[k1];
        myElements[k1] = myElements[k2];
        myElements[k2] = temp;
    }*/

    /**
     * Return true exactly when the heap entry with index k1 is equal to heap
     * entry with index k2.
     */
   /* private boolean isEqual(int k1, int k2)
    {
        assert k1 >= 0 && k2 >= 0 && k1 < mySize && k2 < mySize :
            "checking equality of " + k1 + " and " + k2;
        return myElements[k1] == myElements[k2];
    }

    /**
     * Return true exactly when the heap entry with index k1 is greater than
     * the heap entry with index k2.
     */
   /* private boolean isGreater(int k1, int k2)
    {
        assert k1 >= 0 && k2 >= 0 && k1 < mySize && k2 < mySize :
            "comparing " + k1 + " and " + k2;
        return myElements[k1] > myElements[k2];
    }*/

    /**
     *  Display the heap.
     */
    public void display()
    {
        System.out.println("Heap (" + mySize + " elements):");
        //displayHelper(0, 0);
        System.out.println();
    }
    
    /**
     * Display the subheap rooted at element k, indenting each element
     * according to the indent level argument.
     */
    /*private void displayHelper(int k, int indentLevel)
    {
        if (k >= mySize)
        {
            return;
        }

        displayHelper(2 * k + 1, indentLevel + 1);
        for (int j = 0; j < indentLevel; j++)
        {
            System.out.print("  ");
        }
        System.out.println(myElements[k]);
        displayHelper(2 * k + 2, indentLevel + 1);
    }*/

    /**
     * Check the heap for consistency. Each element must be at least as large
     * as its children.
     */
    private void check()
    {
        /*int k;

        for (k = 0; 2 * k + 1 < mySize - 1; k++)
        {
            assert !isGreater(2 * k + 1, k) : "checking left child of " + k;
            assert !isGreater(2 * k + 2, k) : "checking right child of " + k;
        }

        if (2 * k + 1 == mySize - 1)
        {
            assert !isGreater(2 * k + 1, k) :
                   "checking left (only) child of " + k;
        }*/
    }
    
    public static void main(String[] args)
    {
        Heap h = new Heap(9);
        for (int i = 1; ; i++)
        {
            try
            {
                h.add(i);
            }
            catch (Exception e)
            {
                System.out.println("Heap filled up at insertion " + i);
                break;
            }
        }
        for (int i = 1; ; i++)
        {
            try
            {
                h.remove();
            }
            catch (Exception e)
            {
                System.out.println("Heap emptied at removal " + i);
                break;
            }
        }

        Random r = new Random();
        for (int i = 1; ; i++)
        {
            try
            {
                int newElement = r.nextInt();
                newElement = newElement < 0 ? ((-newElement) % 10)
                    : (newElement % 10);
                h.add(newElement);
                System.out.println("Heap added " + newElement);
            }
            catch (Exception e)
            {
                System.out.println("Heap filled up at insertion " + i);
                break;
            }
        }
        for (int i = 1; ; i++)
        {
            try
            {
            	System.out.println("Heap is removing " + h.top());
                h.remove();
            }
            catch (Exception e)
            {
                System.out.println("Heap emptied at removal " + i);
                break;
            }
        }
    }
    
}