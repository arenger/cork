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
      m.setContent("pJlmkY.btid.");
      ml.add(m);
      m = new Message();
      m.setContent("pmmabmfah.");
      ml.add(m);
      Assert.assertEquals("80b05863c6ad3ec1b0ef4bc29ed2a843",
         new Digester().md5(ml));
   }
}
