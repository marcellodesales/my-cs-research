package br.ufal.tci.interpreter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


// JAVA DOC COMMENTING INCLUDED
/**
 * Models a stack data structure.  This data structure can
 * store any Object.  In this implementation, the students
 * must complete the push(), pop() and peek() methods and
 * the second constructor (not the default constructor).
 *
 * @author Martin Smith - Template structure of stack<BR>
 *         Martin Smith - Completed methods push, pop and peek
 * @see StackException
 * @see Calculator
 */
public class Stack
{
    /** This is stores the Objects in the stack */
    private Object[] data;
    /** This keeps track of the number of Object in the stack. */
    private int currentSize;

    /**
     * Default Constructor: builds an empty Stack with
     * space allocated for one Objects.
     *
     */
    public Stack()
    {
        data = new Object[1]; // default size
        currentSize = 0;  // initially stack is empty
    }

    /**
     * Construct an empty Stack with space allocated
     * for the specified number of Objects.  If an
     * inappropriate value is given (any value less than 1),
     * then the constructor should build a stack with an
     * initial capacity of 1.
     *
     * @param initCapacity space initially allocated for
     *        Stack.
     */
    public Stack(int initCapacity)
    {
        // validate capacity
        if (initCapacity < 1) {
            initCapacity = 1;
        }
        data = new Object[initCapacity];
        currentSize = 0;
    }


    /**
     * Pushes Object o onto stack.
     *
     * If internal array storing data if full,
     * this operation should double the size of the stack.
     *
     * @param o Object to be pushed on stack.
     */
    public void push(Object o)
    {
        // Check and possibly resize stack before adding
        if (data.length == currentSize) {
            Object [] temp = new Object[data.length * 2];
            System.arraycopy(data, 0, temp, 0, data.length);
            data = temp;
        }
        data[currentSize] = o;
        currentSize++;
    }

    /**
     * Performs pop operation on stack and returns the
     * Object that was "popped."
     *
     * @return Object that was popped off of the stack.
     * @throws StackException if the stack is empty and
     *         no Object can be popped.
     */
    public Object pop() throws StackException
    {
        if (currentSize == 0) {
            throw new StackException("Empty Stack");
        }
        currentSize--;
        return data[currentSize];
    }

    /**
     * Returns a reference to the Object that will
     * be popped off the stack.  Does not modify the
     * stack.
     *
     * @return Object that would be popped off of the stack.
     * @throws StackException if the stack is empty and
     *         no Object can be popped.
     */
    public Object peek() throws StackException
    {
        if (currentSize == 0) {
            throw new StackException("Empty Stack");
        }
        return data[currentSize - 1];
    }

    /**
     * Returns whether the Stack is currently empty.
     *
     * @return <code>false</code> if stack has Objects stored.
     */
    public boolean isEmpty()
    {
        return currentSize == 0;
    }

    /**
     * For debug purposes, I've defined a toString() method to print out my stack.
     *
     * @return Visual representation of the stack.
     */
    public String toString()
    {
        String newLine = System.getProperty("line.separator");
        String returnValue = "* TOP " + newLine;
        for (int i = currentSize - 1; i >= 0;  i--) {
            returnValue += "* " + data[i] + newLine;
        }
        returnValue += "* BOTTOM " + newLine;
        return returnValue;
        // On Windows, a line seperator is /r/n (carriage return, new line)
        // On Unix, a line seperator is /n
        // On Macintosh, a line seperator is /r
        // System.getProperty("line.seperator") allows us to get the specific
        // type of line sperator based on the system we're actually running
        // the program on.  Otherwise, we may occaisionally get strange results
        // For example, a text file with little boxes where the line seperator
        // would be.
    }

    /**
     * For debug purposes, I've defined a main() method to run and test
     * my stack.  It's important to know my stack works before I try
     * building my calculator.  To run the calculator, I'd type
     * <code>java Calculator</code>, but to test my Stack, I'd type
     * <code>java Stack</code>.
     */
    public static void main(String[] args)
    {
        Stack s = new Stack(-1);
        // test that it defaults to 1
        if (s.data.length != 1) {
            System.out.println("Error in size of constructor");
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in), 1);
        while (true) {
            // Print usage
            System.out.println();
            System.out.println("U - Push        O - Pop         K - Peek");
            System.out.println("P - Print       N - New         E - IsEmpty");
            System.out.println("S - Size Info   Q - Quit");
            System.out.print("Enter Command: ");
            System.out.flush();

            String input = null;
            try {
                input = in.readLine();
            } catch (IOException ioe) {
                System.err.println(ioe);
                System.exit(-1);
            }

            // Match command
            if (input.equalsIgnoreCase("U")) { // push
                System.out.print("Enter String to push: ");
                System.out.flush();
                try {
                    s.push(in.readLine());
                } catch (IOException ioe) {
                    System.err.println(ioe);
                    System.exit(-1);
                }
            } else if (input.equalsIgnoreCase("O")) { // pop
                try {
                    System.out.println(s.pop());
                } catch (StackException se) {
                    System.out.println(se);
                    se.printStackTrace();
                }
            } else if (input.equalsIgnoreCase("K")) { // peek
                try {
                    System.out.println(s.peek());
                } catch (StackException se) {
                    System.out.println(se);
                    se.printStackTrace();
                }
            } else if (input.equalsIgnoreCase("P")) { // print
                System.out.println(s);
            } else if (input.equalsIgnoreCase("N")) { // new
                try {
                    System.out.print("Size: ");
                    System.out.flush();
                    int size = Integer.parseInt(in.readLine());
                    s = new Stack(size);
                } catch (NumberFormatException nfe) {
                    System.err.println(nfe);
                } catch (IOException ioe) {
                    System.err.println(ioe);
                    System.exit(-1);
                }
            } else if (input.equalsIgnoreCase("E")) { // is Empty
                System.out.println(s.isEmpty());
            } else if (input.equalsIgnoreCase("S")) { // Size Info
                System.out.println("Size is " + s.currentSize + ".  Capacity is " + s.data.length + ".");
            } else if (input.equalsIgnoreCase("Q")) { // Quit
                break;
            } else { // Default
                System.out.println("Command not understood");
            }
        }
    }
}
