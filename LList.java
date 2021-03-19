//Write a program that creates a linked list and loads it with the numbers 0 to 9.  You should create a linked list class, a node class, etc
//Make a small text menu for your program so that you ask the user how many nodes they want in the list.  Use the show list function to display the list.
import java.util.Scanner;

public class LList {
    Node front;

    void init(){front = null;}

     void buildSimpleList(int len){
        Node end;
        init();
        int i = 0;
        front = makeNode(i);
        for(i = 1; i < len; i++){
            end = findTail();
            end.next = makeNode(i);
        }
        showList();
    }



    Node findTail(){
        Node curr = front;
        while(curr.next != null){
            curr = curr.next;
        }
        return curr;
    }

    Node makeNode(int num){
        Node isNode = new Node();

        //Allocate memory for a new node?
        isNode.iNum = num;
        isNode.next = null;

        return isNode;
    }

    void showList(){
        Node curr = front;
        while(curr != null){
            System.out.print(curr.iNum + ", ");
            curr = curr.next;
        }
        //System.out.print(curr.iNum);
    }

    void insert(Node in){
        Node end = findTail();
        end.next = in;

    }

    void insertAfter(Node in, int num){
        Node curr, prev;
        if(in == null){
            curr = makeNode(num);
            prev = findTail();
            prev.next = curr;
        }else{
            curr = makeNode(num);
            curr.next = in.next;
            in.next = curr;
        }
    }

    void insertAtFront(Node in){
        in.next = front;
        front = in;
    }

    void delete(Node in){
        //move nodes after deleting
        Node temp= front, prev = null;

        if(temp == null){

            return;
        }
        if(temp.iNum == in.iNum){

            front = temp.next;
            return;
        }
        while (temp.iNum != in.iNum){

            prev = temp;
            temp = temp.next;
        }

        prev.next = temp.next;
    }





    public static void main(String[] agrs){
        Scanner ohWord = new Scanner(System.in);
        LList babyWord = new LList();
        LList userWord = new LList();
        int i =0;

        babyWord.buildSimpleList(10);

        System.out.print("\nHow many nodes do you want? :");
        int listLen = ohWord.nextInt();
        userWord.buildSimpleList(listLen);

        int choice = 0;
        int inNode;

        while(choice != 5){
            System.out.println("Would you like to... \n1: Add a node with a certain value. \n2: Add a node to the front of the list. \n3: Add a node at the back of the list. \n4: Delete a certain node. \n 5: Leave menu");
            choice = ohWord.nextInt();

            switch(choice){
                case 1: System.out.print("What is the value in the node? ");
                        inNode = ohWord.nextInt();
                        userWord.insert(userWord.makeNode(inNode));
                        userWord.showList();
                    break;
                case 2: System.out.print("What is the value in the node? ");
                        inNode = ohWord.nextInt();
                        userWord.insertAtFront(userWord.makeNode(inNode));
                        userWord.showList();
                    break;
                case 3: System.out.print("What is the value in the node? ");
                        userWord.insertAfter(null,ohWord.nextInt());
                        userWord.showList();
                    break;
                case 4: System.out.print("What is the value in the node you would like to delete? ");
                        inNode = ohWord.nextInt();
                        userWord.delete(userWord.makeNode(inNode));
                        userWord.showList();
                    break;
                default: System.out.println("Sorry invalid number.");
            }
        }
    }
}
