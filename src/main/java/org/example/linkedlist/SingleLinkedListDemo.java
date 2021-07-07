package org.example.linkedlist;

import java.util.Stack;

/**
 * created by huangyating
 *
 * @Date 2021/7/5
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode heroNode = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(heroNode);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);
        singleLinkedList.list();

        //创建链表
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(heroNode);
        singleLinkedList2.addByOrder(heroNode4);
        singleLinkedList2.addByOrder(heroNode2);
        singleLinkedList2.addByOrder(heroNode3);
        singleLinkedList2.addByOrder(heroNode3);

        //显示
        singleLinkedList2.list();

        //测试修改节点的代码
        HeroNode heroNode1 = new HeroNode(2, "小卢", "小尾巴");
        singleLinkedList.update(heroNode1);
        System.out.println("修改后");
        singleLinkedList.list();
/*

        singleLinkedList.delete(1);
        singleLinkedList.delete(2);
*/


        System.out.println("删除后");
        singleLinkedList.list();
        System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));

        //测试是否得到倒数第k个节点
        HeroNode lastIndexNode = findLastIndexNode(singleLinkedList.getHead(), 2);
        System.out.println(lastIndexNode);

        //测试一下单链表的反转功能
        System.out.println("原来链表的情况");
        singleLinkedList.list();

        System.out.println("测试逆序打印链表,没有改变链表本身结构");
        reversePrint(singleLinkedList.getHead());
        System.out.println("反转单链表");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
    }
    //方式2：
    public  static void reversePrint(HeroNode head){
        if(head.next == null){
            return; //空链表，不能打印
        }
        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
    //将单链表翻转
    public static void reverseList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，无需翻转，直接返回
        if(head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助的指针，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while(cur != null){
            next = cur.next;  //先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next; //将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;
            cur = next;//让cur后移
        }
        //将head.next指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }
    //查找单链表的倒数第k个结点【新浪面试题】
    //思路
    //1.编写一个方法，接收head节点，同时接收一个index
    //2.index表示是倒数第Index个节点
    //3.先把链表从头到尾遍历，得到链表的总的长度getLength
    //4.得到size后，我们从链表的第一个开始遍历(size-index)个，就可以得到
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //判断如果链表为空，返回Null
        if(head.next == null){
            return null;
        }
        //第一次遍历得到链表的长度(节点个数)
        int size = getLength(head);

        //第二次遍历 size-index 位置，就是我们倒数的第k个节点
        //先做一个index的校验
        if(index <= 0 || index > size){
            return null;
        }

        //定义个辅助变量，for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }
    //方法：获取到单链表的节点的个数（如果是带头结点的链表，需求不统计头结点）
    public static int getLength(HeroNode head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        //定义个辅助的变量,这里我们没有统计头结点
        HeroNode cur = head.next;
        while(cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //
}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的Next指向新的节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true){
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环的时候，temp就指向了链表的最后
        //将最后的这个节点的Next指向新的节点
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并且给出提示)
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅助指针来帮助找到添加的位置
        HeroNode temp = head;
        boolean flag = false; //flag标志添加的编号是否存在，默认为false
        while(true){
            if(temp.next == null){
                //说明已经在链表的最后
                break;
            }
            if(temp.next.no > heroNode.no){  //位置找到，就在temp的后面插入
                break;
            }else if(temp.next.no == heroNode.no){
                flag = true; //编号存在
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.printf("不能添加  英雄编号为：%d \n",heroNode.no);
        }else{
            //当退出while循环的时候，temp就指向了链表的最后
            //将最后的这个节点的Next指向新的节点
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }
    //删除节点
    //思路
    //1. head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2.说明我们在比较时，是temp.next.no 和需要删除节点的no比较
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            //删除
            temp.next = temp.next.next;
        }else{
            System.out.println("要删除的 %d 节点不存在");
        }
    }
    //显示链表【遍历】
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while(true){
            //判断是否到链表的最后
            if(temp == null){
                break;
            }
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    public void update(HeroNode heroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        //找到需要修改的节点，根据No编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if(temp == null){
                break;
            }
            if(temp.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        }else {
            System.out.printf("没有找到编号%d的节点。不能修改 \n",heroNode.no);
        }
    }


}

//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; //指向下一个节点
    //构造器
    public HeroNode(int no,String name,String nickName){
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