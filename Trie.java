import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by turingwy-PC on 2017/1/6.
 */
public class Trie {
    private class TrieNode {
        char value;
        TrieNode[] childs = new TrieNode[26];
        TrieNode failPointer;
        boolean isFinish;
    }

    private TrieNode root = new TrieNode();

    private String[] patterns;

    public void setPatterns(String[] patterns) {
        this.patterns = patterns;
    }

    private void generateTrie() {
        for(int i = 0; i < patterns.length; i++) {
            insertPattern(i);
        }
    }

    private void insertPattern(int k) {
        TrieNode tn = root;
        for (int i = 0; i < patterns[k].length(); i++) {
            int c = patterns[k].charAt(i) - 'a';
            if (tn.childs[c] == null) {
                tn.childs[c] = new TrieNode();
                tn.childs[c].value = patterns[k].charAt(i);
            }

            tn = tn.childs[c];
        }
        tn.isFinish = true;
    }

    public Trie(String[] patterns) {
        setPatterns(patterns);
        generateTrie();
        generateFailPointer();
    }

    public void traversal() {
        _traversal(root);
    }

    private void _traversal(TrieNode root) {
        System.out.print(root.value);
        for(int i = 0; i < 26; i++) {
            if(root.childs[i] != null) {
                _traversal(root.childs[i]);
            }
        }
    }

    private void generateFailPointer() {
        TrieNode[] bfsQueue = new TrieNode[100];        // hardcode 100
        int queueFront = 0, queueRear = 0;                      // front and rear of the bfsQueue
        for(int i = 0; i < 26; i++) {
            if(root.childs[i] != null) {
                bfsQueue[queueRear++] = root.childs[i];
                root.childs[i].failPointer = root;
            }
        }
        root.failPointer = root;
        while(queueFront < queueRear) {
            TrieNode trie = bfsQueue[queueFront++];
            for(int i = 0; i < 26; i++) {
                if(trie.childs[i] != null) {
                    bfsQueue[queueRear++] = trie.childs[i];
                    trie.childs[i].failPointer = findFailPointer(trie, trie.childs[i].value);
                }
            }
        }

    }

    private TrieNode findFailPointer(TrieNode parent, char childValue) {
        TrieNode failPointer = parent.failPointer;
        while(failPointer != root) {
            for(int i = 0; i < 26; i++) {
                if(failPointer.childs[i] != null && failPointer.childs[i].value == childValue) {
                    return failPointer.childs[i];
                }
            }
            failPointer = failPointer.failPointer;
        }

        for(int i = 0; i < 26; i++) {
            if(root.childs[i] != null && root.childs[i].value == childValue) {
                return root.childs[i];
            }
        }
        return root;
    }


    public static void main(String[] args) {
        Trie t = new Trie(new String[]{"say", "she", "shr","he","her"});
        String str = "yasherhs";
        TrieNode tn = t.root;
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            if(tn.childs[str.charAt(i) - 'a'] != null) {
                tn = tn.childs[str.charAt(i) - 'a'];
                TrieNode temp = tn;
                while(temp != t.root) {
                    if(temp.isFinish) {
                        count++;
                    }
                    temp = temp.failPointer;
                }
            }
            else {
                if(tn == t.root)
                    continue;
                tn = tn.failPointer;
                i--;
            }

        }
        System.out.println(count);

}
