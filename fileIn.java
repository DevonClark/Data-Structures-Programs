import java.util.Scanner;
import java.io.*;

public class fileIn {
    int condition;
    String fname;

    public fileIn() {
        System.out.println("Constructor");
        getFileName();
        readFileContents();
    }

    public void readFileContents()
    {
        boolean looping;
        DataInputStream in;
        String line;
        int j, len;
        char ch;

        /* Read input from file and process. */
        try {
            in = new DataInputStream(new FileInputStream(fname));

            looping = true;
            while(looping) {
                /* Get a line of input from the file. */
                if (null == (line = in.readLine())) {
                    looping = false;
                    /* Close and free up system resource. */
                    in.close();
                }
                else {
                    //System.out.println("line = "+line);
                    switch(line){
                        case "parentheses tests" :
                            line = in.readLine();
                            condition=1;
                            break;
                        case "postfix equation solving" :
                            line = in.readLine();
                            condition = 2;
                            break;
                        case "infix to postfix conversion" :
                            line = in.readLine();
                            condition=3;
                            break;
                        case "Parentheses check, infix to postfix conversion, postfix evaluation" :
                            line = in.readLine();
                            condition = 4;
                            break;
                        default:
                            //empty on purpose
                    }

                    switch(condition){
                        case 1 :
                            System.out.println("line = "+line);
                            if(verify(line)){
                                System.out.println(line + " is valid.");
                            }else{
                                System.out.println(line + " is NOT valid.");
                            }
                            break;
                        case 2 :
                            System.out.println("line = "+line);
                            System.out.print(line + " = ");
                            evaluatePostfix(line);
                            break;
                        case 3 :
                            System.out.println("line = "+line);
                            System.out.println("the postfix of " + line + " = " + InToPost(line));
                            break;
                        case 4 :
                            System.out.println("line = "+line);
                            if(verify(line)){
                                System.out.println("the postfix of " + line + " = " + InToPost(line));
                                evaluatePostfix(InToPost(line));
                            }else{
                                System.out.println(line + " is NOT valid.");
                            }
                            break;
                        default:
                            System.out.println("Your default is showing");
                    }
                }
            } /* End while. */

        } /* End try. */

        catch(IOException e) {
            System.out.println("Error " + e);
        } /* End catch. */
    }


    class CharStack {
        char[] stack = new char[40];
        int top;

        public void Init(){
            top = -1;
        }

        public void push(char i) {
            top++;
            stack[top] = i;
        }

        public char pop() {
            char i;
            i = stack[top];
            top--;
            return i;
        }

        public boolean isStackEmpty() {
            boolean isEmpty = false;

            if (top == -1) {
                isEmpty = true;
            }

            return isEmpty;
        }

        public String printStack() {
            String st = "";
            for (int i = 0; i <= top; i++) {
                st += stack[i];
            }
            return st;
        }

    }

    private boolean verify(String str){
        CharStack object = new CharStack();
        object.Init();
        for(int i = 0; i <str.length(); i++){
            char a = str.charAt(i);
            if(a == '[' || a == '(' || a == '{'){
                object.push(a);
            } else if(a == ')'){
                if(object.isStackEmpty() || object.pop() != '(') {
                    return false;
                }
            } else if(a == ']'){
                if(object.isStackEmpty() || object.pop() != '[') {
                    return false;
                }
            } else if(a == '}'){
                if(object.isStackEmpty() || object.pop() != '{') {
                    return false;
                }
            }
        }

        return object.isStackEmpty();
    }

    public void evaluatePostfix(String str)
    {
        CharStack hell = new CharStack();
        int a;
        double b;
        double c;
        double d = 0;
        char e;
        int f;
        hell.Init();

        for(a=0; a< str.length(); a++) {
            e = str.charAt(a);
            str = str.replaceAll("\\s", "");

            if((e>='a')&&(e<='z')) {

            }else if((e>='0')&&(e<='9')) {
                f = e - '0';
                hell.push((char) f);
            }else{
                b = hell.pop();
                if(hell.isStackEmpty()){
                    break;//tutor found typo suggested this
                }
                c = hell.pop();

                switch(e){
                    case '+':
                        d = c+b;
                    break;

                    case '-':
                        d= c-b;
                    break;

                    case '/':
                        d = c/b;
                    break;

                    case '*':
                        d = c*b;
                    break;
                    default: System.out.println("Try again.");
                }
                hell.push((char)d);
            }
        }
        if(d == 0){
            System.out.println("syntax error");
        }else {
            System.out.println("The postfix = " + d);
        }
    }
    public String InToPost(String str){
        CharStack post = new CharStack();
        CharStack op = new CharStack();
        char a;
        str = str.replaceAll("\\s", "");

        post.Init();
        op.Init();

        for(int i = 0; i < str.length(); i++){
            a = str.charAt(i);
            if(a == '('){

            }else if((a>='a')&&(a<='z')){
                post.push(a);
            }else if(a == ')'){
                if(!op.isStackEmpty()){
                    a = op.pop();
                    post.push(a);
                }
            }else if((a >= '0')&&(a <= '9')){
                post.push(a);
            }else if(a == '+' || a == '-' || a == '*' || a == '/' || a == ')'){
                op.push(a);
            }
        }//close for

        while(!op.isStackEmpty()){
            a = op.pop();
            post.push(a);
        }

        return post.printStack();
    }


    public void getFileName()
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter file name please.");
        fname = in.nextLine();
        System.out.println("You entered "+fname);
    }

    public static void main(String[] args)
    {

        System.out.println("Hello TV land!");

        fileIn f = new fileIn();

        System.out.println("Bye-bye!");
    }
}
