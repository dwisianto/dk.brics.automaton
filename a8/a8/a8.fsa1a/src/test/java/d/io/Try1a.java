package d.io;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Try1a {
	
	static Logger logger = Logger.getLogger(Try1a.class);
	

    @Test
	public void t1a() {
        System.out.println("t1a");
    }

    @Test
	public void t1b() {
        System.out.println("t1b");
    }

    @Test
	public void t1c() {
        System.out.println("t1c");
    }
    
}