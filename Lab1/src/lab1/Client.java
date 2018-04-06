package lab1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	private static UserAdmin stub = null;
    private Client() {}
    
    public static void main(String[] args) throws RemoteException, Exception {
        String username;
        String password;
        try {
            Registry reg = LocateRegistry.getRegistry();
            stub = (UserAdmin) reg.lookup("UserAdmin");
        } catch (Exception e) {
            System.err.println("Client exception thrown: " + e.toString());
            e.printStackTrace();
        }
        int choose = -1;
        Scanner input=new Scanner(System.in);
        Scanner input2=new Scanner(System.in);
        while(choose!=3) {
            System.out.println("Please choose the option:\n1.Registration\n2.Login\n3.Exit");
            choose = input.nextInt();
            switch (choose) {
                case 1: //case of register
                    System.out.println("New User Registration");
                    System.out.println("Please input a username:");
                    username = input2.next();
                    System.out.println("Please input a password:");
                    password = input2.next();
                    System.out.println("Please re-input the password");
                    String re_password = input2.next();
                    while (!re_password.equals(password)) {
                        System.out.println("Please input a password");
                        password = input2.next();
                        System.out.println("Please re-input the password");
                        re_password = input2.next();
                    }
                    if (stub.register(username, password)) {
                        System.out.println("Registration success.");
                    } else {
                        System.out.println("Registration failure since username exists.");
                    }
                    break;
                case 2: //case of login
                    System.out.println("User Login");
                    System.out.println("Please input your username:");
                    username = input2.next();
                    System.out.println("Please input a password:");
                    password = input2.next();
                    if(stub.login(username, password)) {
                        System.out.println("Login successfully.");
                    }else{
                        System.out.println("invalid username or wrong password.");
                    }
                    break;
                case 3: //over
                    System.out.println("Bye.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        input.close();
        input2.close();
    }
}	

