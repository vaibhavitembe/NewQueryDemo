//package com.zplus.configuration;
//
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//
//public class SmsPanel {
//    public static String sendSms(String mobileNo, String content) {
//
//        String message= URLEncoder.encode(content, "UTF-8");
//        // URL url2 = new URL("http://sms.zpluscybertech.com/app/smsapi/index.php?key=25F0023F2582B6&campaign=0&routeid=35&type=text&contacts="+mob+"&senderid=TECHIT&msg="+message );
////        URL url2 = new URL("http://msg.technolitesolution.com/vendorsms/pushsms.aspx?user=dealgrocery&password=123456&msisdn="+mob+"&sid=DEALGO&msg="+message+"&fl=0&gwid=2");
//        URL url2 = new URL("\n" +
//                "http://zpluscybertech.in/app/smsapi/index.php?key=460C98945E2441&campaign=11778&routeid=7&type=text&contacts="+mobileNo+"&senderid=PCKING&msg="+message+"&template_id=1207161684068756755");
//        HttpURLConnection con = (HttpURLConnection) url2.openConnection();
//        con.addRequestProperty("User-Agent", "Mozilla/4.76");
//        con.setRequestMethod("GET");
//        con.setDoOutput(true);
//        con.getOutputStream();
//        con.getInputStream();
//        BufferedReader rd;
//        String line;
//        String result = "";
//        rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        while ((line = rd.readLine()) != null)
//        {
//            result += line;
//        }
//        rd.close();
//        System.out.println("Server Response: " + result);
//        return result;
//
//    }
//    }
//
//}
//
//
