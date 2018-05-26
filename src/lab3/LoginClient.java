import java.util.Scanner;

public class LoginClient {
	private static Register stub_r = null;

	public LoginClient() {
	};

//	public static void main(String[] args) {
	public void LoginClient(){
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Please choose what you want to do:");
		System.out.println("1:Regist");
//		System.out.println("2:Login");

		String option;
		if (sc.hasNext()) {
			option = sc.next();
			if (option.equals("1")) {
				startRegister();
			} else if (option.equals("2")) {
				// startLogin();
			} else {
				System.out.println("Please choose a valid option!");
			}

		}

	}

	public static void startRegister() {
		String username = "";
		String password = "";
		System.out.println("Please input you name:");
		Scanner sc = new Scanner(System.in);
		if (sc.hasNext()) {
			username = sc.next();
		}
		System.out.println("Please input you password:");
		if (sc.hasNext()) {
			password = sc.next();
		}
		sc.close();

		try {
			// Registry reg =
			// LocateRegistry.getRegistry("zhaoyaodeMacBook-Pro.local",2004);
			// stub_r = (Register) reg.lookup("Register");
			// locate.
			SimpleRegistry sr = LocateSimpleRegistry.getRegistry("127.0.0.1", 1099);

			RemoteObjectRef ror = sr.lookup("RegisterServer");

			stub_r = (Register) ror.localise();
			
			if(stub_r.store(username, password)){
				System.out.println("Register Success!");
			} else {
				System.out.println("Register Fail!");
	
			}
		} catch (Exception e) {
			System.err.println("Client exception thrown: " + e.toString());
			e.printStackTrace();
		}

		// try {
		// if (stub_r.store(username, password)) {
		// System.out.println("Register Success!");
		// } else {
		// System.out.println("Register Fail!");
		//
		// }
		// } catch (RemoteException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
	public static void startLogin() {
	
	}
}
