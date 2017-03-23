package com.digitalchina.common.apiTest;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * （一句话描述）
 * 作用：发送短信接口
 *
 * @author 王功文
 * @date 2017/3/8 14:36
 * @see
 */
public class SendMessageForJava {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMessageForJava.class);

    /**
     * 1.通过client方式进行接口调用  后期可以改造
     */
    public void sendMessage() {
        String SERVER_URL = "http://10.32.0.126:10010/MessageService.asmx?SendMessageForJava";
        try {
            HttpPost request = new HttpPost(SERVER_URL);
            List<BasicNameValuePair> params = new ArrayList();
            //发送手机号码
            params.add(new BasicNameValuePair("sendMobile", "18872893186"));
            //接受手机号码
            params.add(new BasicNameValuePair("strreciveMobiles", "17625036486"));
            //发送内容
            params.add(new BasicNameValuePair("content", "短息发送成功，请查收"));
            //发送部门编号
            params.add(new BasicNameValuePair("deptCode", "320500003028"));
            //发送人姓名
            params.add(new BasicNameValuePair("userName", "王功文"));
            request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = new DefaultHttpClient().execute(request);
            if (httpResponse.getStatusLine().getStatusCode() != 404) {
                String result = EntityUtils.toString(httpResponse.getEntity());
                System.out.println(result);
            }
        } catch (Exception e) {
            LOGGER.error("========发送短信接口失败=========", e);
            e.printStackTrace();
        }
    }

    /**
     * 第二种调用方式  后期可以改造
     */
//    public void sendMessageother() {
//        String url = "http://10.32.0.126:10010/MessageService.asmx";
//        String soapaction = "http://tempuri.org/SendMessageForJava";   //域名，这是在server定义的
//        String City = "北京";
//        Service service = new Service();
//        try {
//            Call call = (Call) service.createCall();
//            call.setTargetEndpointAddress(url);
//            call.setOperationName(new QName(soapaction, "getWeatherbyCityName")); //设置要调用哪个方法
//            // 该方法需要的参数
//            call.addParameter("CustNo", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
//            call.addParameter("passwd", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
//            call.addParameter("Jobno", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
//            call.setReturnType(new QName(soapaction, "getWeatherbyCityName"), Vector.class); //要返回的数据类型（自定义类型）
//            // 方法的返回值类型
////            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
//            call.setUseSOAPAction(true);
//            call.setSOAPActionURI(soapaction + "getWeatherbyCityName");
//            Vector v = (Vector) call.invoke(new Object[]{City});//调用方法并传递参数
////            String xmlStr = call.invoke(new Object[]{CustNo, passwd, Jobno}).toString();
//            for (int i = 0; i < v.size(); i++) {
//                System.out.println(v.get(i));
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }


    public static void main(String[] args) {
        SendMessageForJava sendMessageForJava = new SendMessageForJava();
        sendMessageForJava.sendMessage();
    }


}
