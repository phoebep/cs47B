import java.util.*;

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
    private void addChild(String parentName, String childName, Amoeba am){
        if(am.getName().equals(parentName)){
            am.addChild(childName);
        }
        else{
            Iterator<Amoeba> it = am.myChildren.iterator();
            while(it.hasNext()){
                addChild(parentName, childName, it.next());
            }


        }

    }
    public void addChild(String parentName, String childName)
    {
        // TODO: You supply this code for Exercise 1.
        addChild(parentName, childName, myHead);
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
    private void print(Amoeba am){
        System.out.println(am.getName());
        Iterator<Amoeba> it = am.myChildren.iterator();
        while(it.hasNext()){
            print(it.next());
        }
    }
    public void print()
    {
        // TODO: You supply this code for Exercise 2.
        print(myHead);
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

        System.out.println("");
        System.out.println("Here it is again!");
        AmoebaIterator iter = family.iterator();
        while (iter.hasNext())
        {
            System.out.println(((Amoeba)iter.next()));
        }
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
    private class AmoebaIterator implements Iterator<Amoeba>
    {

//        Q3
        Stack<Amoeba> st = new Stack<Amoeba>();

//        Q4
//        Queue<Amoeba> st = new LinkedList<Amoeba>();

        public AmoebaIterator(){
            if (myHead != null) {
//                Q3
                st.push(myHead);

//                Q4
//                st.add(myHead);
            }
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            if(st.isEmpty()){
                return false;
            }
            return true;
        }

        @Override
        public Amoeba next() {
            // TODO Auto-generated method stub

            if(!hasNext()){
                throw new NoSuchElementException();
            }

//            Q3
            Amoeba current = st.pop();
            for(int i = current.myChildren.size()-1; i >= 0; i--){
                st.push(current.myChildren.get(i));
            }

//            Q4
//            Amoeba current = st.remove();
//            for(int i = 0; i < current.myChildren.size(); i++){
//                st.add(current.myChildren.get(i));
//            }

//return same for both q3 and q4
            return current;

        }
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