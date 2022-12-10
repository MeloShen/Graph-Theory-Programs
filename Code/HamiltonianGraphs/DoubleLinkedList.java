package Code.HamiltonianGraphs;

public class DoubleLinkedList<T> {
    private final Node<T> head;
    private final Node<T> tail;
    //this is the Constructor of the double linked list
    //every node has two pin connect to the next and the prev
    public DoubleLinkedList(){
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    //add a node after head
    public void addHead(T key){
        //creat a new node
        Node<T> node = new Node<>(key);
        //save the odd node after head
        Node<T> prevNode = this.head.next;
        //connect new node before odd node
        prevNode.prev = node;
        //connect head with new node
        node.prev = head;
        this.head.next = node;
        //connect the odd node after new node
        node.next = prevNode;
    }
    //add node before tail
    public void addTail(T key){
        //creat a new node
        Node<T> node = new Node<>(key);
        //save the odd node before tail
        Node<T> prevNode = this.tail.prev;
        //connect new node after odd node
        prevNode.next = node;
        //connect new node with tail
        node.next = this.tail;
        this.tail.prev = node;
        //connect odd node before new node
        node.prev = prevNode;
    }
    //find the node after head
    public T findHead(){
        Node<T> current = this.head.next;
        return current.key;
    }
    //find the node before tail
    public T findTail(){
        Node<T> current = this.tail.prev;
        return current.key;
    }
    //find the node before the node inputed
    public T findPrev(T key){
        Node<T> current = this.tail.prev;
        while(current.key != key && current.key != null) {
            current = current.prev;
        }
        return current.key;
    }
    //print the node in the link list
    public void print(){
        Node<T> current = this.head.next;
        while (current.key != null ){
            System.out.print(current.key);
            System.out.print(" ");
            current = current.next;
        }
        System.out.println();
    }
    //check is the input node already in the list
    public boolean has(T key){
        Node<T> current = this.head.next;
        while(current.key != null){
            if(current.key.equals(key)){
                return false;
            }
            current = current.next;
        }
        return true;
    }
    //after find the key need connect to the head
    public void changeHead(T key){
        Node<T> headNode = this.head.next;
        Node<T> keyNode = this.tail.prev;
        System.out.print(keyNode.key);
        //find the node same to inputted key
        while(keyNode.key.equals(key)){
            keyNode = keyNode.prev;
        }
        //remember the node before key node
        Node<T> prevKeyNode = keyNode.prev;
        //let head connect the node before key node
        this.head.next = prevKeyNode;
        prevKeyNode.next = head;
        //connect original head node with key node
        headNode.prev = keyNode;
        keyNode.prev = head;
        //the code below is used to print the list
        Node<T> current = this.head.next;
        //because of disconnect the key node and prev key node
        //then prev key node has a next pin connect to head and
        //head has a next pin connect to prev key node and
        //headnode has a prev pin connected to key node
        //key node has a prev pin connected to headnode
        //beacuse of that when print the list it will have a problem
        //in do...while part print as normal put is just go to prev
        // and it will end at unusuall place
       do{
           System.out.print(current.key);
           System.out.print(" ");
           current = current.prev;

       }while (current.prev != current.prev.prev);
       //force to go to prev
        current = current.prev;
        //this part is normal print
        while (current.key != null){
            System.out.print(current.key);
            System.out.print(" ");
            current = current.next;
        }
        System.out.println();
    }
    //Constructor for the node
    private class Node<T>{
        private T key;
        private DoubleLinkedList<T>.Node<T> next;
        private DoubleLinkedList<T>.Node<T> prev;
        private Node(T key){
            this.key = key;
        }
    }
}
