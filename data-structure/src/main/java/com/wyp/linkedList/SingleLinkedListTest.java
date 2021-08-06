package com.wyp.linkedList;

public class SingleLinkedListTest {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        // singleLinkedList.add(hero1);
        // singleLinkedList.add(hero2);
        // singleLinkedList.add(hero3);
        // singleLinkedList.add(hero4);

        // 按照编号顺序 插入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        System.out.println("---------update前---------");
        singleLinkedList.show();

        // HeroNode updateNode = new HeroNode(4, "鲁智深", "花和尚");
        // singleLinkedList.update(updateNode);
        // System.out.println("---------update后---------");
        // singleLinkedList.show();

        // singleLinkedList.delete(hero1);
        // singleLinkedList.delete(hero2);
        // singleLinkedList.delete(hero3);
        // singleLinkedList.delete(hero4);
        // System.out.println("---------delete---------");
        // singleLinkedList.show();
    }

}

/**
 * 带有头节点的链表
 */
class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到链表尾部
     */
    public void add(HeroNode heroNode) {
        HeroNode temp = head; //临时节点，相当于指针
        while (true){
            if (temp.next == null){
                // temp节点已经是尾部节点
                break;
            }
            // 未找到尾部节点，指针后移
            temp = temp.next;
        }
        // 此时temp为尾部节点,将尾部节点的next指向新增节点
        temp.next = heroNode;
    }

    /**
     * 按照   heroNode.no 顺序插入
     */
    public void addByOrder(HeroNode heroNode) {
        boolean flag = false; //是否找到待修改节点
        HeroNode temp = head; //临时节点，相当于指针
        while (true){
            if (temp.next == null){
                // temp节点已经是尾部节点
                break;
            }
            if (temp.next.no == heroNode.no){
                // 待插入节点的编号在链表中已存在
                flag = true;
                break;
            }else if (temp.next.no > heroNode.no){
                // 指针的下一个节点编号大于待插入节点编号，在temp后面插入新节点
                break;
            }
            // 未找到尾部节点，指针后移
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("待插入节点在链表中已存在，编号为：【%d】", heroNode.no);
        }else {
            // 插入新节点
            heroNode.next = temp.next; // 新节点的下一个节点为原temp的下个节点
            temp.next = heroNode; // temp的下个节点指向新节点
        }

    }

    public void update(HeroNode heroNode) {
        if(head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        HeroNode temp = head.next; //临时节点，相当于指针
        boolean flag = false; // 是否找到待修改节点
        while (true){
            if (temp == null){
                // temp节点已经是尾部节点
                break;
            }
            if (temp.no == heroNode.no){
                // 找到待修改节点
                flag = true;
                break;
            }

            // 未找到尾部节点，指针后移
            temp = temp.next;
        }
        if (flag) {
            temp.name = head.name;
            temp.nickname = heroNode.nickname;
        }else {
            // 未找到待修改节点
            System.out.printf("未找到编号为【%d】的英雄！\n", heroNode.no);
        }

    }

    public void delete(HeroNode heroNode) {
        if(head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        boolean flag = false; //是否找到待修改节点
        HeroNode temp = head; //临时节点，相当于指针
        while (true){
            if (temp.next == null){
                // temp节点已经是尾部节点
                break;
            }
            if (temp.next.no == heroNode.no){
                //找到待修改节点，temp为待修改节点的前一个节点
                flag = true;
                break;
            }
            // 未找到尾部节点，指针后移
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        }else {
            System.out.printf("未找到标号为【%d】的节点", heroNode.no);
        }
    }

    public void show() {
        if (head.next == null){
            System.out.println("链表为空~~~");
            return;
        }
        HeroNode temp = head.next; //临时节点，相当于指针
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            // 指针后移
            temp = temp.next;
        }
    }
}

class HeroNode {
    int no;
    String name;
    String nickname;
    HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}




