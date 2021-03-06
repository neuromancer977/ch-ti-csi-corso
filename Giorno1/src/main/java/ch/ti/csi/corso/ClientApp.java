package ch.ti.csi.corso;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.ti.csi.corso.beans.Servizio1;
import ch.ti.csi.corso.beans.Servizio2;

public class ClientApp {  
	
	HelloInterface hi;
	
	public ClientApp(HelloImpl_AutoWiring hi) {
		super();
		this.hi = hi;
	}

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/beans.xml");
		ClientApp c = (ClientApp)ctx.getBean("client");
		Servizio1 s1 = (Servizio1)ctx.getBean("servizio1");
		s1.a();
		c.go();
		ctx.close();
	}
	
	void go() {
		hi.hello();
	}

//	public void setHi(HelloImpl_AutoWiring hi) {
//		this.hi = hi;
//	}

}
