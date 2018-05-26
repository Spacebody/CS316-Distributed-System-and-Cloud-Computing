package lab4;

import java.util.Scanner;

public class Actions {


    public void receive_new_messages(String name){
        System.out.println("Receiving new messages...");
        Subscribe subscribe = new Subscribe();
        subscribe.subscribe_receive(name);
    }


    public void showMenu(String name) {
        System.out.println("Choose options:");
        System.out.println("1. Publish new contents\n2. Subscribe new topics\n3. Exit");
        int choice;
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();
        switch (choice){
            case 1:
                Publish publish = new Publish();
                publish.publish_context(name);
                break;
            case 2:
                Subscribe subscribe = new Subscribe();
                subscribe.subscribe_topic(name);
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
}
