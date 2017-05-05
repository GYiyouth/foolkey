package foolkey.pojo.root.DataStructure;

import foolkey.tool.StaticVariable;

import java.util.ArrayList;

/**
 * Created by admin on 2017/4/26.
 */
public class DoubleLink {

    public DoubleLink() {
    }

    //记录链表的节点数
    private int length = 0;
    //定义上一个节点
    private Node header;
    //定义下一个节点
    private Node tail;

    //获取链表的节点值
    public int getLength() {
        return length;
    }

    //尾插法，查到头的位置
    //向链表中添加值
    public void addTail(Object data) {
        //判断链表是否是空的哈
        if (header == null) {
            header = new Node(data, null, null);
            tail = header;
            length++;
        } else if (length < StaticVariable.cacheSize) {
            //非空，非满
            Node tempNode = new Node(data, tail, null);
            tail.setNext(tempNode);
            tail = tempNode;
            length++;
        } else {
            //满了
            //不做操作

        }
    }

    //向链表中添加值
    public void addHead(Object data) {
        //判断链表是否是空的哈
        if (header == null) {
            header = new Node(data, null, null);
            length++;
            tail = header;
        } else if (length < StaticVariable.cacheSize) {
            //非空，非满

            Node tempNode = new Node(data, null, header);
            header.setPrev(tempNode);
            header = tempNode;
            length++;
        } else {
            //满了
            //插头去尾
            Node tempNode = new Node(data, null, header);
            header.setPrev(tempNode);
            header = tempNode;

            Node tempNode2 = tail.getPrev();
            tempNode2.setNext(tail);
            tail.setPrev(tempNode2);
            tail = tempNode2;
        }
    }


    //获取特定的index的节点
    public Node getNodeByIndex(int index) {
        //根据索引的值不同是选择是从后向前查找还是
        //选择从前往后查找
        if (index < 0 || index > length - 1) {
            throw new IndexOutOfBoundsException("索引超出了边界！");
        } else if (index <= length / 2) {
            Node current = header;
            for (int i = 0; i < length & current != null; i++, current = current.getNext()) {
                if (index == i) {
                    return current;
                }

            }


        } else if (index > length / 2) {
            Node current = tail;
            for (int i = length - 1; i >= 0 & current != null; i--, current = current.getPrev()) {
                if (index == i) {
                    return current;
                }

            }

        }

        return null;
    }


    public Node getNodeByIndex_new(int index) {
        if (index < 0 || index > length - 1) {
            throw new IndexOutOfBoundsException("索引超出了边界！");
        } else {
            Node current = header;
            for (int i = 0; i < length & current != null; i++, current = current.getNext()) {
                if (index == i) {
                    return current;
                }

            }


        }
        return null;
    }

    //根据节点获取节点所在的值哈
    public Object getDataByIndex(int index) {

        return getNodeByIndex(index).getData();
    }

    //遍历节点的值哈
    public void allPrint() {
        if (header == null) {
            throw new NullPointerException("链表为空");
        } else {
            for (int i = 0; i < length; i++) {
                Node current = getNodeByIndex_new(i);
                System.out.println(current.getData());
            }
        }

    }

    //遍历节点的方法2
    public void allPrint_New() {
        if (header == null) {
            throw new NullPointerException("链表为空");
        } else {

            Node current = tail;
            while (current != null) {
                System.out.println(current.getData());
                current = current.getPrev();
            }

        }

    }

    //删除某一个特定的index的节点
    public void delNodeByIndex(int index) {
        if (index > length - 1 || index < 0) {
            throw new IndexOutOfBoundsException("索引超出了边界！");
        } else if (header == null) {
            throw new NullPointerException("链表为空！");
        } else if (index == 0) {
            //如果删除的是头节点时候
            Node current = header.getNext();
            current.setPrev(null);
            header = current;
            length--;
        } else if (index == length - 1) {
            //如果删除的是最后一个节点的话。
            //获取前一个节点
            Node prevNode = getNodeByIndex(index - 1);
            //修改前一个节点的next节点
            Node del = prevNode.getNext();
            prevNode.setNext(del.getNext());
            tail = prevNode;

            length--;
        } else {
            //删除一般的节点的
            //获取前一个节点
            Node prevNode = getNodeByIndex(index - 1);
            //修改前一个节点的next节点
            Node del = prevNode.getNext();
            prevNode.setNext(del.getNext());

            if (del.getNext() != null) {
                del.getNext().setPrev(prevNode);
            }
            del.setNext(null);
            del.setPrev(null);
            length--;
        }
    }

    //获得第i到j的所有节点内容
    public ArrayList<Object> getNode(Integer begin, Integer end) {
        if (begin > end) {
            throw new IndexOutOfBoundsException("开始节点在结束节点后面！");
        }else if(end >= length){
            end = length-1;
        }
        ArrayList<Object> results = new ArrayList<>();
        for (int i = begin; i <= end; i++) {
            results.add(getDataByIndex(i));
        }
        return results;

    }

    //获得所有节点内容
    public ArrayList<Object> getAllNode() {
        ArrayList<Object> results = new ArrayList<>();
        for(int i=0;i<length;i++){
            results.add(getDataByIndex(i));
        }
        return results;
    }

    //初始化
    public void InitMyDoubleLink(){
        length = 0;
        header = null;
        tail = null;
    }

    //修改节点内容
    public void updateNodeByIndex(int index,Object data){
        if (index > length - 1 || index < 0) {
            throw new IndexOutOfBoundsException("索引超出了边界！");
        } else if (header == null) {
            throw new NullPointerException("链表为空！");
        } else{
            Node node = getNodeByIndex(index);
            node.setData(data);
        }
    }

    public static void main(String[] args) {

        DoubleLink mytest = new DoubleLink();
        mytest.addHead("12334");
        mytest.addTail("wangpeili");
        mytest.addHead("123");
        mytest.addHead("test");
        mytest.addTail("test2");
        mytest.addHead("hahha");

        DoubleLink mytest2 = new DoubleLink();
        mytest2.addHead("gengrui");

//        mytest.insertDataByIndex("here", 3);
//        mytest.insertDataByIndex("here2", 3);
        System.out.println("++++++++++++删除前++++++++++++");
        mytest.allPrint();
//        mytest.delNodeByIndex(5);
        System.out.println("++++++++++++删除后++++++++++++");
//        mytest.allPrint();
        //mytest.allPrint_New();

        //System.out.println(mytest.getLength());
        System.out.println("++++++++++++取值+++++++++++++");
//        System.out.println(mytest.getDataByIndex(0));
        ArrayList<Object> results = mytest.getNode(1, 3);
        for (Object object : results) {
            System.out.println(object.toString());
        }

        for(int i = 0;i<mytest.getLength();i++){
            if(mytest.getNodeByIndex(i).getData().equals("123")){
                System.out.println("i"+i);
                mytest.delNodeByIndex(i);
                break;
            }
        }
        mytest.allPrint();

        System.out.println("=====");
        mytest2.allPrint();
    }


}
