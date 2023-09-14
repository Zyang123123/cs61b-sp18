public class OffByN implements CharacterComparator {
    private int n;
    public OffByN(int N) {
        n = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int diff = y - x;
        if (diff == n || diff == -n){
            return true;
        }
        return false;
    }
}
