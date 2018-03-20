package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		
		try {
		
		System.out.println("[클라이언트가 연결되었습니다.]");
		 
		 //메시지 받기용 스트림
		 InputStream is;
		
			is = socket.getInputStream();
	
		 Reader isr = new InputStreamReader(is);
		 BufferedReader br = new BufferedReader(isr);
		 
		 
		 //메세지 보내기요 스트림
		 OutputStream os = socket.getOutputStream();
		 Writer osw = new OutputStreamWriter(os, "UTF-8");
		 BufferedWriter bw = new BufferedWriter(osw);
		 
		 
		 String msg;
		 
		 while(true) {
			 msg = br.readLine();
			 
			 if(msg==null) {
				 System.out.println("클라이언트 접속 종료");
				 break;
			 }
			 System.out.println("받은메세지: " + msg);
			 
			 bw.write(msg);
			 bw.newLine();
			 bw.flush();           //버퍼드는 일정 모아서 간다(꽉차야감), 플러시는 적어도 강제로 보냄!!!!!!!
		 
			 
			 
		 }
		 } catch(IOException e) {
			 e.printStackTrace();
		}
	}
}
