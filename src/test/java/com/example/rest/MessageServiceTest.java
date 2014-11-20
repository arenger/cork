package com.example.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.example.dal.DalException;
import com.example.dal.MessageKeeper;
import com.example.dto.Message;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageServiceTest {

   @Test
   public void testMd5from() throws DalException {
      MessageKeeper mmk = mock(MessageKeeper.class);
      List<Message> list = new ArrayList<Message>();
      list.add(newMsgWithContent("s"));
      list.add(newMsgWithContent("t"));
      list.add(newMsgWithContent("p"));
      when(mmk.getMessagesFrom(23)).thenReturn(list);
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
