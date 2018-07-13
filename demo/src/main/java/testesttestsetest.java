import java.net.InetAddress;
import java.net.UnknownHostException;

public class testesttestsetest {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = java.net.InetAddress.getLocalHost();
		System.out.println(addr.getHostName());
	}
}
