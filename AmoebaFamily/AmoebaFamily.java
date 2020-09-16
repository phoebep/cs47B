import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * AmoebaFamily: CS 47B
 *  @author Phoebe Pumilia
 *    @date 2/8/17
 * @project Iterator Exercises
 */
public class AmoebaFamily
{
    private Amoeba myHead;  // Head of the AmoebaFamily
    
    /**
     * Constructs a new AmoebaFamily containing a single Amoeba (the head of
     * the family).
     * @param name Name of the head Amoeba.
     */
    public AmoebaFamily(String name)
    {
        myHead = new Amoeba(name, null);
    }

    /**
     * Returns a new iterator for this AmoebaFamily.
     * @return New AmoebaIterator instance corresponding to this AmoebaFamily.
     */
    public AmoebaIterator iterator()
    {
        return new AmoebaIterator();
    }
    
    /**
     * Add a new Amoeba child with a given name to the specified Amoeba parent.
     * Precondition: The AmoebaFamily contains an Amoeba named parentName.
     * @param parentName Name of the parent Amoeba that we want to add a child
     *                   to.
     * @param childName Name of the new Amoeba child.
     */
    public void addChild(String parentName, String childName)
    {
        // TODO: You supply this code for Exercise 1.
    	AmoebaIterator it = new AmoebaIterator(myHead);
    	while (it.hasNext()){
    		String a = it.next().myName;
    		if (a.equals(parentName)){
    			addChild(childName);
    		}
    	}
    }
    
    /**
     * Print the names of all amoebas in the family. Names should appear in
     * preorder, with children's names printed oldest first. Members of the
     * family constructed with the main driver program below should be printed
     * in the following sequence:
     *
     *     Amos McCoy, mom/dad, me, Mike, Bart, Lisa, Homer, Marge, Bill,
     *     Hilary, Fred, Wilma
     */
    public void print()
    {
        // TODO: You supply this code for Exercise 2.
    	AmoebaIterator it = new AmoebaIterator(myHead);
    	while(it.hasNext()){
    		System.out.print(it.next().toString() + ", ");
    	}
    }
    
    /**
     * Construct a family of Amoebas, and then print the family tree using the
     * print() method as well as the AmoebaIterator.
     * @param args Command-line arguments.
     */
    public static void main(String[] args)
    {
        AmoebaFamily family = new AmoebaFamily("Amos McCoy");
        family.addChild("Amos McCoy", "mom/dad");
        family.addChild("mom/dad", "me");
        family.addChild("mom/dad", "Fred");
        family.addChild("mom/dad", "Wilma");
        family.addChild("me", "Mike");
        family.addChild("me", "Homer");
        family.addChild("me", "Marge");
        family.addChild("Mike", "Bart");
        family.addChild("Mike", "Lisa");
        family.addChild("Marge", "Bill");
        family.addChild("Marge", "Hilary");

        System.out.println("Here's the family:");
        family.print();

//        System.out.println("");
//        System.out.println("Here it is again!");
//        AmoebaIterator iter = family.iterator();
//        while (iter.hasNext())
//        {
//            System.out.println(((Amoeba)iter.next()));
//        }
    }

    /**
     * AmoebaIterator: Amoebas in the family are iterated over in preorder,
     * with oldest children first. Members of the family constructed with the
     * main program above should be iterated over in the following sequence:
     *
     *     Amos McCoy, mom/dad, me, Mike, Bart, Lisa, Homer, Marge, Bill,
     *     Hilary, Fred, Wilma
     *
     * Complete iteration of a family of N amoebas should take O(N) operations.
     */
    private class AmoebaIterator implements Iterator
    {
        // TODO: You supply the details of this class for Exercises 3 and 4.
    }

    /**
     * Amoeba: Defines an Amoeba with a name, a parent, and children.
     */
    private class Amoeba
    {
        public String myName;                   // Amoeba's Name
        public Amoeba myParent;                 // Amoeba's Parent
        public ArrayList<Amoeba> myChildren;    // Amoeba's Children

        /**
         * Constructs a new Amoeba with a given name and parent.
         * @param name Name of this Amoeba.
         * @param parent Parent of this Amoeba.
         */
        public Amoeba(String name, Amoeba parent)
        {
            myName = name;
            myParent = parent;
            myChildren = new ArrayList<Amoeba>();
        }

        /**
         * Returns the name of this Amoeba.
         * @return Amoeba name.
         */
        public String getName()
        {
            return myName;
        }

        /**
         * Returns the parent of this Amoeba.
         * @return Parent Amoeba.
         */
        public Amoeba getParent()
        {
            return myParent;
        }

        /**
         * Returns the children of this Amoeba.
         * @return ArrayList containing the children of this Amoeba.
         */
        public ArrayList<Amoeba> getChildren()
        {
            return myChildren;
        }

        /**
         * Constructs a new Amoeba with a given name, and adds it as the
         * youngest child of this current Amoeba.
         * @param childName Amoeba child name.
         */
        public void addChild(String childName)
        {
            Amoeba child = new Amoeba(childName, this);
            myChildren.add(child);
        }

        /**
         * Returns the String representation of this Amoeba.
         * @return Name of this Amoeba.
         */
        public String toString()
        {
            return myName;
        }
    }
}
