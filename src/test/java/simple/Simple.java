package simple;

import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.THsHaServer.Args;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import simple.UserService.Iface;
import simple.UserService.Processor;

public class Simple {
		
	@Test
	public void configurationTest() {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class);
				
		try {
			UserService.Iface handler  = (UserService.Iface) context.getBean("userHandler");
			UserService.Processor<UserService.Iface> processor = new Processor<Iface>(handler);
			
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(9090);
			TServer server = new THsHaServer(new Args(serverTransport).workerThreads(10).processor(processor));
			System.out.println("Server is ready!!!");
			server.serve();
			
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
