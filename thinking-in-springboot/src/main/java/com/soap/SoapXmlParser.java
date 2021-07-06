package com.soap;

/**
 * @description:
 * @author: H.K
 * @create: 2021-07-06 15:35
 */

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.StringUtils;

import java.util.*;


public class SoapXmlParser {

    public static void main(String[] args) throws DocumentException {
        String soap = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns1:apiEntryResponse xmlns:ns1=\"http://i.api.ei.scnczxh.iih/\"><return xmlns=\"http://i.api.ei.scnczxh.iih/\"><result><resultmsg><code>0</code><message/></resultmsg><datas><data><mzcount>2</mzcount><name_org>南充市中心医院</name_org><bservationno>--</bservationno><mzno>1900077472</mzno><diseasecode>Z34.900</diseasecode><zytotalcost>--</zytotalcost><revisit>1</revisit><code_org>1001</code_org><majordescribe>--</majordescribe><diagnose>Z34.900</diagnose><outdoctorid>01520</outdoctorid><healthcheck>--</healthcheck><hzno>001174578800</hzno><illnow>--</illnow><regisway>--</regisway><illhistory>--</illhistory><age>27岁</age><outdeptid>0101010033</outdeptid><diseasename>正常妊娠监督</diseasename><registype>--</registype><mztime>2019-11-01 08:24:14</mztime><assitcheck>--</assitcheck><disposition>--</disposition></data><data><mzcount>6</mzcount><name_org>南充市中心医院</name_org><bservationno>--</bservationno><mzno>1900077479</mzno><diseasecode>K04.500</diseasecode><zytotalcost>--</zytotalcost><revisit>1</revisit><code_org>1001</code_org><majordescribe>--</majordescribe><diagnose>K04.500</diagnose><outdoctorid>01027</outdoctorid><healthcheck>--</healthcheck><hzno>000571414200</hzno><illnow>--</illnow><regisway>--</regisway><illhistory>--</illhistory><age>77岁</age><outdeptid>0101010018</outdeptid><diseasename>慢性根尖牙周炎</diseasename><registype>--</registype><mztime>2019-11-01 08:14:05</mztime><assitcheck>--</assitcheck><disposition>--</disposition></data></datas></result></return></ns1:apiEntryResponse></soap:Body></soap:Envelope>";

        Map map = new HashMap();
        List<Data> dataList = new ArrayList<>();
        Document doc = DocumentHelper.parseText(soap);
        //获取根元素，准备递归解析这个XML树
        Element root = doc.getRootElement();
        //获取到data的集合
        List<Element> mzList = root.element("Body").element("apiEntryResponse").element("return").element("result").element("datas").elements("data");
        //遍历data集合
        for (Element e : mzList) {
            List<Element> elements = e.elements();
            //遍历将元素中的key和value存到map中
            for (Element item : elements) {
                if (!StringUtils.isEmpty(item.getText())) {
                    map.put(item.getName(), item.getText());
                }
            }
            //将map转换成对象
//            Data data = BeanUtil.mapToBean(map, Data.class, true);
            Data data = (Data) ReflectBean.mapConvertBean(map, Data.class);
            //对象存到集合中
            //对象存到集合中
            dataList.add(data);
        }
        //最后可以将取到的数据返回及其他等等操作
    }
}
