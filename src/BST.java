
import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size = 0;

    public class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return val;
        }
    }

    public void put(K key, V val) {
        Node curr = root;
        Node parent = null;

        while (curr != null) {
            parent = curr;
            if (key.compareTo(curr.key) < 0) {
                curr = curr.left;
            } else if (key.compareTo(curr.key) > 0) {
                curr = curr.right;
            } else {
                curr.val = val;
                return;
            }
        }

        Node newNode = new Node(key, val);
        if (parent == null) {
            root = newNode;
        } else if (key.compareTo(parent.key) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
    }

    public V get(K key) {
        Node curr = root;
        while (curr != null) {
            if (key.compareTo(curr.key) < 0) {
                curr = curr.left;
            } else if (key.compareTo(curr.key) > 0) {
                curr = curr.right;
            } else {
                return curr.val;
            }
        }
        return null;
    }

    public void delete(K key) {
        root = deleteIterative(root, key);
    }

    private Node deleteIterative(Node root, K key) {
        Node parent = null;
        Node curr = root;

        while (curr != null && !curr.key.equals(key)) {
            parent = curr;
            if (key.compareTo(curr.key) < 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        if (curr == null) return root;

        if (curr.left == null || curr.right == null) {
            Node newCurr = (curr.left != null) ? curr.left : curr.right;

            if (parent == null) {
                return newCurr;
            }

            if (curr == parent.left) {
                parent.left = newCurr;
            } else {
                parent.right = newCurr;
            }
        } else {
            Node successorParent = curr;
            Node successor = curr.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            if (successorParent != curr) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }

            curr.key = successor.key;
            curr.val = successor.val;
        }
        size--;
        return root;
    }

    public Iterable<Node> iterator() {
        return () -> new Iterator<Node>() {
            Stack<Node> stack = new Stack<>();
            Node current = root;

            {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }

            public boolean hasNext() {
                return !stack.isEmpty();
            }

            public Node next() {
                Node node = stack.pop();
                Node temp = node.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
                return node;
            }
        };
    }

    public int size() {
        return size;
    }
} // End of BST with full iterator and size()
