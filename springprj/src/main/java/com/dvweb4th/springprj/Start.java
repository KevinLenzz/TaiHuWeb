package com.dvweb4th.springprj;

import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.GZIPInputStream;
@SpringBootApplication
public class Start {
    static HashSet<String> require=new HashSet<String>();
    public static String[] cityname=new String[]{
            "黄浦区",
            "徐汇区",
            "长宁区",
            "静安区",
            "普陀区",
            "虹口区",
            "杨浦区",
            "闵行区",
            "宝山区",
            "嘉定区",
            "浦东新区",
            "金山区",
            "松江区",
            "青浦区",
            "奉贤区",
            "虎丘区",
            "姑苏区",
            "相城区",
            "吴中区",
            "吴江区",
            "常熟市",
            "张家港市",
            "昆山市",
            "太仓市",
            "锡山区",
            "惠山区",
            "梁溪区",
            "新吴区",
            "滨湖区",
            "宜兴市",
            "江阴市",
            "新北区",
            "钟楼区",
            "天宁区",
            "武进区",
            "金坛区",
            "溧阳市",
            "丹阳市",
            "句容市",
            "丹徒区",
            "京口区",
            "润州区",
            "秀洲区",
            "南湖区",
            "平湖市",
            "海盐县",
            "嘉善县",
            "桐乡市",
            "海宁市",
            "吴兴区",
            "南浔区",
            "长兴县",
            "安吉县",
            "德清县",
            "钱塘区",
            "上城区",
            "临安区",
            "临平区",
            "拱墅区",
            "余杭区",
            "西湖区"
    };
    public static void main(String[] args){
        SpringApplication.run(Start.class,args);
        require.add("余杭");
        require.add("瓶窑");
        require.add("梅溪");
        require.add("溧阳(二)");
        require.add("宜兴（西）");
        require.add("坊前");
        require.add("丹阳");
        require.add("\t苏州（枫桥）");
        require.add("甘露");
        require.add("湘城");
        require.add("昆山");
        require.add("平望");
        TimerTask schedule = new TimerTask() {
            @Override
            public void run() {
                /*try{

                    Thread.sleep(1000L);
                    try {
                        GetRequest4precip();
                        GetRequest4wq();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                }*/
            }
        };
        Timer timer = new Timer();
        long period = 120;
        timer.schedule(schedule,0,period*60*1000);
    }
    public static void Crawler4Rivers() throws IOException {
        WebClient client=new WebClient(BrowserVersion.CHROME);
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setTimeout(5000);
        client.setAjaxController(new NicelyResynchronizingAjaxController());
        client.getOptions().setDoNotTrackEnabled(false);
        HtmlPage page=client.getPage("http://xxfb.mwr.cn/sq_djdh.html");
        client.waitForBackgroundJavaScript(10*1000);
        String pageXml=page.asXml();
        Document doc= Jsoup.parse(pageXml);
        Element element=doc.getElementById("DataContainer");
        Elements eles=element.children();
        JSONObject jo=new JSONObject();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        jo.put("time", LocalDate.now().toString()+' '+LocalTime.now().format(formatter));
        jo.put("names",new ArrayList<String>());
        jo.put("values",new ArrayList<String>());
        for(Element ele:eles){
            if(require.contains(ele.child(3).text())){
            //if(ele.child(0).text().equals("长江流域太湖区")||ele.child(0).text().equals("太湖")||ele.child(0).text().equals("太湖流域")){
                //System.out.println(ele.child(2).text());
                jo.getJSONArray("names").add(ele.child(2).text());
                jo.getJSONArray("values").add(ele.child(5).text());
            }
        }
        Writer fw = new FileWriter("src/main/jsons/results/result_sw.json");
        fw.write(jo.toString());
        fw.flush();
        fw.close();
        client.close();
    }
    public static void GetRequest4wq() throws IOException {
        String url = "http://api.moonapi.com/197?keyid=645&sign=p4kpg4k2b4d4c6vmpxktfqbm&river=太湖流域&rows=125";
        RestTemplate client = new RestTemplate();
        byte[] body = client.getForEntity(url, byte[].class).getBody();
        assert body != null;
        String jsonString = new String(body, "utf-8");
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Writer fw = new FileWriter("src/main/jsons/results/result_wq.json");
        fw.write(jsonObject.toString());
        fw.flush();
        fw.close();
    }
    public static void GetRequest4precip() throws IOException {
        Reader rd=new FileReader("src/main/jsons/data4requests/citylocation.json");
        StringBuilder sb = new StringBuilder();
        int n = rd.read();
        while (n != -1) {
            sb.append((char) n);
            n = rd.read();
        }
        JSONObject jsonOBJofcitylocation=JSONObject.parseObject(sb.toString());
        Writer fw = new FileWriter("src/main/jsons/results/result_precip.json");
        fw.write('[');
        fw.flush();
        for(int i=0;i<61;i++) {
            String E=jsonOBJofcitylocation.getJSONArray(cityname[i]).getString(0);
            String N=jsonOBJofcitylocation.getJSONArray(cityname[i]).getString(1);
            String url = "https://devapi.qweather.com/v7/minutely/5m?location="+E+","+N+"&key=05ee5c5c0fac4015a4daae8a4064d35b";
            RestTemplate client = new RestTemplate();
            byte[] body = client.getForEntity(url, byte[].class).getBody();
            assert body != null;
            byte[] decompressed = ungzip(body);
            String jsonString = new String(decompressed, "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            fw.write(jsonObject.toString());
            if(i!=60){
                fw.write(',');
            }
            fw.flush();
        }
        fw.write(']');
        fw.flush();
        fw.close();
    }
    public static byte[] ungzip(byte[] compressed) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
        GZIPInputStream gis = new GZIPInputStream(bis);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = gis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        gis.close();
        bis.close();
        bos.close();
        return bos.toByteArray();
    }
}
