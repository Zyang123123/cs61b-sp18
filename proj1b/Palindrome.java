public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

/*    public boolean isPalindrome(String word) {
        //using iteration.
        Deque<Character> d = wordToDeque(word);
        if (d.size() == 0 || d.size() == 1) {
            return true;
        }
        while (d.size() != 0 && d.size() != 1) {
            if (d.removeLast() != d.removeFirst()) {
                return false;
            }
        }
        return true;
    }*/
    public boolean isPalindrome(String word) {
        //using recursion.
        return isPalindromehelper(wordToDeque(word));
    }
    private boolean isPalindromehelper(Deque<Character> d) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        }
        if (d.removeLast() == d.removeFirst()) {
            return isPalindromehelper(d);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        //using iteration.
        Deque<Character> d = wordToDeque(word);
        if (d.size() == 0 || d.size() == 1) {
            return true;
        }
        while (d.size() != 0 && d.size() != 1) {
            if (!cc.equalChars(d.removeLast(), d.removeFirst())) {
                return false;
            }
        }
        return true;

    }

}
