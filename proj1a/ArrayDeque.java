public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst=4;
        nextLast=5;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        int i;
        for (i=0;i<size;i+=1){
            if (nextFirst+1+i<=size-1){
                a[i]=items[nextFirst+1+i];
            }
            else{
                a[i]=items[nextFirst+1+i-size];
            }
        }
        //System.arraycopy(items, 0, a, 0, size);
        items = a;
        nextFirst=capacity-1;
        nextLast=size;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (nextLast==items.length-1){
            items[nextLast] = x;
            nextLast=0;
        }
        else {
            items[nextLast] = x;
            nextLast += 1;
        }
        size = size + 1;
    }

    public void addFirst(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (nextFirst==0){
            items[nextFirst] = x;
            nextFirst=items.length-1;
        }
        else {
            items[nextFirst] = x;
            nextFirst -= 1;
        }
        size = size + 1;
    }

    public boolean isEmpty(){
        return size==0;
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    public void printDeque(){
        int i;
        for (i=0;i<size;i+=1){
            if (nextFirst+1+i<= items.length-1){
                System.out.print(items[nextFirst+1+i] + " ");
            }
            else{
                System.out.print(items[nextFirst+1+i- items.length] + " ");
            }
        }
        System.out.println();
    }

    public Item removeFirst(){
        nextFirst+=1;
        Item T=items[nextFirst];
        items[nextFirst]=null;
        size-=1;
        return T;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public Item removeLast() {
        nextLast-=1;
        Item T=items[nextLast];
        items[nextLast]=null;
        size-=1;
        return T;
    }

    /** Returns the item from the back of the list.
    public Item getLast() {
        return items[size - 1];
    }*/
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        if (nextFirst+1+i<= items.length-1){
            return items[nextFirst+1+i];
        }
        else{
            return items[nextFirst+1+i- items.length];
        }
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        ArrayDeque<Integer> s1=new ArrayDeque<>();
        s1.addLast(11);
        s1.addLast(12);
        s1.addFirst(10);
        s1.addLast(1);
        s1.addLast(14);
        s1.addFirst(13);
        s1.removeLast();
        s1.addLast(4);
        s1.removeFirst();
        s1.addLast(0);
        s1.addLast(7);
        System.out.println(s1.removeFirst());
        System.out.println(s1.removeLast());
        s1.printDeque();
        System.out.println(s1.size());
        System.out.println(s1.get(5));
    }
}
