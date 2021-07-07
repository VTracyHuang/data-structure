package org.example.linkedlist;

/**
 * created by huangyating
 *
 * @Date 2021/7/7
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        HeroNode2 heroNode = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");
        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);

        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表的情况");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.delete(3);
        System.out.println("删除后的链表的情况");
        doubleLinkedList.list();

        //按顺序添加
        HeroNode2 heroNode5 = new HeroNode2(5, "测试", "哈哈哈");
        doubleLinkedList.addByOrder(heroNode5);
        System.out.println("顺序添加后的链表的情况");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //修改一个节点的内容
    public void update(HeroNode2 heroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        //找到需要修改的节点，根据No编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.printf("没有找到编号%d的节点。不能修改 \n", heroNode.no);
        }
    }


    //删除节点
    //思路
    //1. 对于双向链表，我们可以直接找到要删除的节点
    //2. 找到后，自我删除即可
    public void delete(int no) {
        //判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) { //已经找到链表的最后的next
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //删除
            temp.pre.next = temp.next;
            //如果是最后一个节点 temp.next = null
            if (temp.next != null)
                temp.next.pre = temp.pre;
        } else {
            System.out.println("要删除的 %d 节点不存在");
        }
    }
    public void addByOrder(HeroNode2 node) {
        // 头节点不能动，通过一个辅助指针（变量）帮助找到需要添加的位置
        HeroNode2 temp = head;
        boolean flag = false;	// flag标志添加的编号是否存在，默认为false
        while(true) {
            if(temp.next == null) {
                break;
            }
            if(temp.next.no > node.no) {
                break;
            }
            if(temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;	// 遍历链表
        }
        if(flag) {
            System.out.printf("输入的编号%d已经存在,不能加入\n", node.no);
        }
        else {
            // 为防止出现空指针的情况，需要对temp节点位置进行判断
            // 若双向链表尚未到达尾端，则需要将node节点与其相邻的后面的节点进行连接
            if(temp.next != null) {
                node.next = temp.next;
                temp.next.pre = node;
            }
            // 无论双向链表是否到达尾端，都需要将node节点与其相邻的前面的节点进行连接
            temp.next = node;
            node.pre = temp;
        }
    }
    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环的时候，temp就指向了链表的最后
        //将最后的这个节点的Next指向新的节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //显示链表【遍历】
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否到链表的最后
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}

//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 pre; //指向前一个节点
    public HeroNode2 next; //指向下一个节点

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}