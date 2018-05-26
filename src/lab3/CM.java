import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Remote;

public class CM {
	static RORtbl rortable = new RORtbl();
	public static void export(RemoteObjectRef ror, Remote obj)throws Exception{
		rortable.addObj(ror, obj);
	}
	public static void start() throws IOException {
		int port = 1100;
		// create a socket.

		ServerSocket serverSoc = new ServerSocket(port);
		System.out.println("cm server socket created.\n");

		while (true) {
			// create new connections.
			Socket newsoc = serverSoc.accept();

			System.out.println("accepted the stub request.");

			ObjectInputStream input = new ObjectInputStream(newsoc.getInputStream());
			try {
				// 发布服务的方法名
				String  interfacename = input.readUTF();
				System.out.println(interfacename);

				String methodName = input.readUTF();
				// 入参
				Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
				Object[] arguments = (Object[]) input.readObject();
				ObjectOutputStream output = new ObjectOutputStream(newsoc.getOutputStream());
				System.out.println(methodName);
				System.out.println(parameterTypes.toString());
				System.out.println(arguments.toString());
				try {
					Object o = rortable.findObjByname(interfacename);
					Method method = o.getClass().getMethod(methodName, parameterTypes);
					Object result = method.invoke(o, arguments);
					System.out.println(result.toString());
					
					output.writeObject(result);
				} catch (Throwable t) {
					output.writeObject(t);
				} finally {
					output.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				input.close();
			}

			newsoc.close();
		}
	}

}
