package com.example.rest;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.example.dal.DalException;
import com.example.dal.MessageKeeper;
import com.example.dto.Message;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestMessageService {

   @Test
   public void testMd5from() throws DalException {
      MessageKeeper mmk = mock(MessageKeeper.class);
      Set<Message> set = new HashSet<Message>();
      set.add(newMsgWithContent("s"));
      set.add(newMsgWithContent("t"));
      set.add(newMsgWithContent("p"));
      when(mmk.getMessagesFrom(23)).thenReturn(set);
      MessageService ms = new MessageService(mmk);
      Assert.assertEquals("67a063aecbd1fc4701ba184b92f695b0\n",
         ms.md5from(23));
   }

   private Message newMsgWithContent(String content) {
      Message m = new Message();
      m.setContent(content);
      return m;
   }
}
