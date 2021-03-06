package serveur;
import java.io.IOException;
import java.net.ServerSocket;

import services.ServiceRetour;

public class ServeurRetour implements Runnable{
	private ServerSocket listen_socket;
	
	public ServeurRetour(int port) throws IOException {
		listen_socket = new ServerSocket(port);
	}

	public void run() {
		try {
			System.err.println("Lancement du serveur au port "+this.listen_socket.getLocalPort());
			while(true)
				new Thread(new ServiceRetour(listen_socket.accept())).start();
		}
		catch (IOException e) { 
			try {this.listen_socket.close();} catch (IOException e1) {}
			System.err.println("Arr�t du serveur au port "+this.listen_socket.getLocalPort());
		}
	}

	 // restituer les ressources --> finalize
	protected void finalize() throws Throwable {
		try {this.listen_socket.close();} catch (IOException e1) {}
	}
}
