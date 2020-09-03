package service;

import javafx.util.Pair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pojo.Info;
import sun.rmi.server.InactiveGroupException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlClientService {

    public void getInfo(Integer page) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            String url = String.format("%s/index_%s.html", HOST, page);
            HttpGet request = new HttpGet(url);

            CloseableHttpResponse response = httpClient.execute(request);

            List<Info> infos = new LinkedList<>();
            if (response.getStatusLine().getStatusCode() == 200) {
                //需要设置编码，这里主要看抓取的页面的编码，编码不一致会使结果乱码
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                infos = getCodeMap(content);
            }

            for (Info info : infos) {
                url = String.format("%s/%s.html", HOST, info.getId());

                request = new HttpGet(url);
                System.out.printf("%10s\n", info.getCode());

                List<String> error = Arrays.asList("GAH106","JUY549");
                if (error.contains(info.getCode())) {
                    System.out.println("call url fail: " + url);
                    continue;
                }
                response = httpClient.execute(request);
                if (response.getStatusLine().getStatusCode() == 200) {
                    //需要设置编码，这里主要看抓取的页面的编码，编码不一致会使结果乱码
                    String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                    info.setImage(getImageUrl(content));
                }
                System.out.printf("%10s  %s \n", info.getCode(), info.getImage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final static String HOST = "https://www.gaoxiaoba.com/jav";

    private String getImageUrl(String content) {
        //匹配所有a标签
        String regexStr = "<img src=\"https://file.*";
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(content);
        String result = null;
        while (matcher.find()) {
            result = matcher.group().split("\"")[1];
        }
        return result;
    }

    private List<Info> getCodeMap(String content) {
        //匹配所有a标签
        String regexStr = "<li><a href=\"/jav/.*_";
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(content);
        List<Info> infos = new LinkedList<>();
        while (matcher.find()) {
            String text = matcher.group().split("/")[2];
            String id = text.split("\\.")[0];
            String value = text.split("\"")[2].replace("-", "");
            Info info = new Info(value, id);
            infos.add(info);
        }
        return infos;
    }
}
