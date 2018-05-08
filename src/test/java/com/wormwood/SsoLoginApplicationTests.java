package com.wormwood;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.CorpMessageCorpconversationAsyncsendRequest;
import com.dingtalk.api.response.CorpMessageCorpconversationAsyncsendResponse;
import com.taobao.api.ApiException;
import com.wormwood.client.DingDingClient;
import com.wormwood.helper.HttpHelper;
import com.wormwood.vo.DDToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SsoLoginApplication.class)
@WebAppConfiguration
public class SsoLoginApplicationTests {

    @Autowired
    private DingDingClient dingDingClient;

    @Value("${dingding.corpid}")
    private String corpid;

    @Value("${dignding.corpsecret}")
    private String corpsecret;

    @Test
    public void testSendMsg() throws ApiException {

        DDToken token = dingDingClient.getToken(corpid, corpsecret);
        DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
        CorpMessageCorpconversationAsyncsendRequest req = new CorpMessageCorpconversationAsyncsendRequest();
        req.setMsgtype("oa");
        req.setAgentId(129141145L);
        //req.setUseridList("45370562-1711009811,11111");
        req.setDeptIdList("46251090");
        req.setToAllUser(false);
        req.setMsgcontentString("{\n" +
                "    \"message_url\": \"http://wormwood.com.sg\",\n" +
                "    \"head\": {\n" +
                "        \"bgcolor\": \"FFBBBBBB\",\n" +
                "        \"text\": \"国庆放假通知\"\n" +
                "    },\n" +
                "    \"body\": {\n" +
                "        \"title\": \"国庆放假通知\",\n" +
                "        \"form\": [\n" +
                "            {\n" +
                "                \"key\": \"放假区域:\",\n" +
                "                \"value\": \"中国\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"放假时间:\",\n" +
                "                \"value\": \"2017-10-1 到 2017-10-8\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"content\": \"Happy Chinese National Day!\n \",\n" +
                "        \"image\": \"@lADPBY0V4pL2s93NARTNAiY\",\n" +
                //  "        \"file_count\": \"3\",\n" +
                "        \"author\": \"wormwood admin \"\n" +
                "    }\n" +
                "}");
        CorpMessageCorpconversationAsyncsendResponse rsp = client.execute(req, token.getAccess_token());
        System.out.println(rsp.getBody());
    }


    @Test
    public void testUploadFile() throws Exception {
        DDToken token = dingDingClient.getToken(corpid, corpsecret);
        String url = "https://oapi.dingtalk.com/media/upload?access_token=" + token.getAccess_token() + "&type=image";

        File file = new File("C:\\Users\\kasimodo\\Desktop\\1.jpg");
        HttpHelper.uploadMedia(url,file);
    }
}
