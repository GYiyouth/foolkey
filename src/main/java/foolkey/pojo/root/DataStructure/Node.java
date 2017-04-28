package foolkey.pojo.root.DataStructure;

/**
 * Created by admin on 2017/4/26.
 */
public class Node {

    //存储的数据
    private Object data;
    //前一个节点
    private Node prev;
    //后一个节点
    private Node next;


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    //定义构造函数
    public Node(){

    }

    public Node(Object data, Node prev, Node next) {
        super();
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

}

