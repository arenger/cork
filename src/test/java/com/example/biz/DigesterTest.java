package com.example.biz;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.example.dto.Message;

public class DigesterTest {

   @Test
   public void testMd5() {
      List<Message> ml = new ArrayList<Message>();
      Message m = new Message();
      m.setContent("make me a sandwich.");
      ml.add(m);
      m = new Message();
      m.setContent("sudo make me a sandwich.");
      ml.add(m);
      Assert.assertEquals("55afdd56f6cc7e6e4f13312eaae9f7dc",
         new Digester().md5(ml));
   }
}
