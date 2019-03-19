package aa;

import aa.Application;
import aa.JMSProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class Test {


  @Autowired
  private JMSProducer jmsProducer;

  @org.junit.Test
  public void testJms() {
    Destination destination = new ActiveMQQueue("springboot.queue.test");
    String text = "";
    for (Integer i = 0; i < 1024; i++) {
      text += "b";
    }
    System.out.println(text.getBytes().length);

    final String aaa =text;
    for (int i = 0; i < 1; i++) {
      Thread t = new Thread(new Runnable(){
        @Override
        public void run() {
          jmsProducer.sendMessage(destination, aaa);
        }
      });
      t.start();
    }

  }
}
