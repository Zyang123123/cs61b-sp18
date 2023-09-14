public class LinkedListDeque<T> implements Deque<T> {
    private class StuffNode {
        private StuffNode prev;
        private T item;
        private StuffNode next;
        public StuffNode() {
        }
        public StuffNode(StuffNode m, T i, StuffNode n) {
            prev = m;
            item = i;
            next = n;
        }

        public T getRecursive(int index) {
            StuffNode p;
            int i = 0;
            if (index >= size) {
                return null;
            }
            if (i == index) {
                return this.next.item;
            } else {
                p = this.next;
                return p.getRecursive(index - 1);
            }
        }
    }
    private StuffNode sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new StuffNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        //sentinel = new StuffNode(sentinel,null,sentinel);
        size = 0;
    }
    /*public LinkedListDeque(T x){
        //sentinel = new StuffNode(sentinel,null,sentinel);
        sentinel = new StuffNode();
        sentinel.next= new StuffNode(sentinel,x,sentinel);
        sentinel.prev= sentinel.next;
        size=1;
    }*/
    @Override
    public void addFirst(T x) {
        StuffNode p = sentinel.next;
        sentinel.next = new StuffNode(sentinel, x, p);
        p.prev = sentinel.next;
        size += 1;
    }
    @Override
    public void addLast(T x) {
        size += 1;
        StuffNode p = sentinel.prev;
        p.next = new StuffNode(p, x, sentinel);
        sentinel.prev = p.next;
        /*using loop:
        while (p.next != sentinel) {
            p = p.next;
        }
        p.next = new StuffNode(p, x, sentinel);
        sentinel.prev=p.next;
        * */
    }
    @Override
    public int size() {
        /*if (size<0){
            size=0;
        }*/
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public void printDeque() {
        StuffNode p = sentinel;
        while (p.next != sentinel) {
            p = p.next;
            System.out.print(p.item + " ");
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        size -= 1;
        if (size < 0) {
            size = 0;
        }
        T x = sentinel.next.item;
        StuffNode p = sentinel.next.next;
        sentinel.next = p;
        p.prev = sentinel;
        return x;
    }

    @Override
    public T removeLast() {
        size -= 1;
        if (size < 0) {
            size = 0;
        }
        T x = sentinel.prev.item;
        StuffNode p = sentinel.prev.prev;
        sentinel.prev = p;
        p.next = sentinel;
        return x;
    }
    @Override
    public T get(int index) {
        StuffNode p = sentinel;
        int i = 0;
        /*for (i=0;i<index;i+=1){
            p=p.next;
        }
        return p.item;*/
        while (p.next != sentinel) {
            if (i < index) {
                p = p.next;
                i += 1;
            }
            if (i == index) {
                return p.next.item;
            }
        }
        return null;
    }
    public T getRecursive(int index) {
        StuffNode p = sentinel;
        /*int i=0;
        if (index>size){
            return null;
        }
        if (i==index){
            return p.item;
        }
        else{
            p=p.next;
            return p.getRecursive(index-1);
        }*/
        return p.getRecursive(index);
    }
    /*public static void main(String[] args) {
        System.out.println("Running tests.\n");
        LinkedListDeque<Integer> s1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> s2 = new LinkedListDeque<>();
        s1.addLast(15);
        s1.addFirst(10);
        s1.removeFirst();
        s1.removeFirst();
        s1.removeFirst();
        s1.removeFirst();
        s1.printDeque();
        System.out.println(s1.removeFirst());
        System.out.println(s1.removeLast());
        s1.printDeque();
        System.out.println(s1.size());
        s2.addLast(15);
        s2.addFirst(10);
        s2.printDeque();
        System.out.println(s2.isEmpty());
        System.out.println(s2.get(0));
        System.out.println(s2.getRecursive(1));
    }*/

}
