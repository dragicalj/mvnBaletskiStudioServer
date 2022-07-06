package rs.ac.bg.fon.nprog.niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerskaNit extends Thread {
	
	private ServerSocket serverSocket;
    private List<KlijentskaNit> klijenti;

    public ServerskaNit(ServerSocket serverSocket) throws IOException {
        this.serverSocket=serverSocket;
        klijenti=new ArrayList<>();
    }
    
    

    @Override
    public void run() {
        while(!serverSocket.isClosed()){
            try {
                System.out.println("Ceka se klijent...");
                Socket socket=serverSocket.accept();
                KlijentskaNit thread=new KlijentskaNit(socket,this);
                thread.start();
                klijenti.add(thread);
                System.out.println("Klijent je povezan!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        zaustaviSveNiti();
    }
    
    private void zaustaviSveNiti(){
        for (KlijentskaNit klijent : klijenti) {
            try {
                klijent.getSocket().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void zaustavi() throws IOException{
        serverSocket.close();
    }
    
   

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
