import java.util.Random;

public class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + id;
        hash = 31 * hash + (name != null ? name.length() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return id == other.id && name.equals(other.name);
    }

    @Override
    public String toString() {
        return "[" + id + ", " + name + "]";
    }

    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(10000);
            String name = "name" + (char)(random.nextInt(26) + 'a');
            table.put(new MyTestingClass(id, name), "val" + i);
        }

        for (int i = 0; i < 11; i++) {
            int count = 0;
            MyHashTable.HashNode<MyTestingClass, String> node = table.chainArray[i];
            while (node != null) {
                count++;
                node = node.next;
            }
            System.out.println("Bucket " + i + " has " + count + " elements");
        }
    }
}
//changed some private to public