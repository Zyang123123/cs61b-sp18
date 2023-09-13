import java.util.Formatter;

/**
 * A naked recursive list of integers, similar to what we saw in lecture 3, but
 * with a large number of additional methods.
 *
 * @author P. N. Hilfinger, with some modifications by Josh Hug and melaniecebula
 *         [Do not modify this file.]
 */
public class IntList {
    /**
     * First element of list.
     */
    private int first;
    /**
     * Remaining elements of list.
     */
    private IntList rest;

    /**
     * A List with first FIRST0 and rest REST0.
     */
    public IntList(int first0, IntList rest0) {
        first = first0;
        rest = rest0;
    }

    /**
     * A List with null rest, and first = 0.
     */
    public IntList() {
        /* NOTE: public IntList () { }  would also work. */
        this(0, null);
    }

    /**
     * Returns a list equal to L with all elements squared. Destructive.
     */
    public static void dSquareList(IntList L) {

        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.first * L.first, null);
        IntList ptr = res;
        L = L.rest;
        while (L != null) {
            ptr.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            ptr = ptr.rest;
        }
        return res;
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, squareListRecursive(L.rest));
    }

    /** DO NOT MODIFY ANYTHING ABOVE THIS LINE! */


    /**
     * Returns a list consisting of the elements of A followed by the
     * *  elements of B.  May modify items of A. Don't use 'new'.
     */

    public static IntList dcatenate(IntList A, IntList B) {
        IntList p = A;
        //A=B;
        if (A == null && B == null) {
            return null;
        }
        if (A == null) {
            A = B;
            //p = A;
            //A = new IntList(B.first, B.rest);
            return A;
        }
        while (A.rest != null) {
            A = A.rest;
        }
        A.rest = B;
        //A = B;你遇到错误的原因是因为在循环结束后，A变成了null，
        // 然后你尝试将B赋值给A，但A已经不再指向任何有效的IntList对象了。
        //要正确地连接两个链表A和B，你需要在循环结束后保持对链表A的引用，
        // 然后将B连接到A的末尾
        //A.first= B.first;
        //A.rest = B.rest;
        return p;
    }

    /**
     * Returns a list consisting of the elements of A followed by the
     * * elements of B.  May NOT modify items of A.  Use 'new'.
     */
    public static IntList catenate(IntList A, IntList B) {
        if (A == null && B == null) {
            return null;
        }
        IntList res = new IntList(A.first, null);
        IntList ptr = res;
        A = A.rest;
        while (A != null) {
            ptr.rest = new IntList(A.first, null);
            A = A.rest;
            ptr = ptr.rest;
        }
        while (B != null) {
            ptr.rest = new IntList(B.first, null);
            B = B.rest;
            ptr = ptr.rest;
        }
        return res;

    }

    public static IntList ndreverse(IntList A) {
        //Non-destructive
        IntList result = null;
        while (A != null) {
            result = new IntList(A.first, result);
            A = A.rest;
        }
        return result;
    }

    /**
     * Returns the reverse of the given IntList.
     * This method is destructive. If given null
     * as an input, returns null.
     */
    public static IntList ddreverse(IntList A) {
        //无法做到因为A不是全局变量，是形参
        if (A == null || A.rest == null) {
            return A;
        }
        IntList reversed = reverse(A.rest);
        A.rest.rest = A;
        A.rest = null;
        return reversed;
    }

    public static IntList reverse(IntList A) {
        //destructive
        IntList R, L1;
        for (R = null; A != null; A = L1) {
            L1 = A.rest;
            A.rest = R;
            R = A;
        }
        return R;
    }

    public static IntList nondreverse(IntList A) {
        /*IntList frontOfReversed = null;
        IntList nextNodeToAdd = A;
        while (nextNodeToAdd != null) {
            IntList remainderOfOriginal = nextNodeToAdd.rest;
            nextNodeToAdd.rest = frontOfReversed;
            frontOfReversed = nextNodeToAdd;
            nextNodeToAdd = remainderOfOriginal;
        }
        A = frontOfReversed;
        return A;*/
        //SLList iteration写法，public static IntList nondreverse()
        //A.reverse() A是全局变量
        // 无法改变外部变量A并且A变成了无意义的值
        //SLList recursion写法

        if (A == null || A.rest == null) {
            return A;
        } else {
            IntList reversed = nondreverse(A.rest);
            A.rest.rest = A;
            A.rest = null;
            return reversed;
        }
    }



    /**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return first;
    }

    /**
     * Returns a new IntList containing the ints in ARGS. You are not
     * expected to read or understand this method.
     */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * doesn't get stuck in an infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}

