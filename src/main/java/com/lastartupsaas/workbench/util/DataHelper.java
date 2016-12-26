package com.lastartupsaas.workbench.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mvel2.MVEL;

/**
 * @author shixin
 *
 */
public class DataHelper {

    private static SimpleDateFormat DATEFORMAT_MONTH_VALUE = new SimpleDateFormat("yyyyMM");
    private static SimpleDateFormat DATEFORMAT_DAY_VALUE = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat DATEFORMAT_HOUR_VALUE = new SimpleDateFormat("yyyyMMddHH");


    public static int getDaysBetween(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int)((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }







    public static List<String> toStringList(String content, String delimiters) {
        List<String> ls = new ArrayList<String>();
        if(content!=null){
            StringTokenizer stk = new StringTokenizer(content, delimiters);
            while(stk.hasMoreTokens()){
                String s = stk.nextToken().trim();
                if(s.length()>0){
                    ls.add(s);
                }
            }
        }
        return ls;
    }

    public static List<String> toLines(String content) {
        List<String> ls = new ArrayList<String>();
        if(content!=null)for(String line : content.split("[\\n\\r]+")){
            ls.add(line.trim());
        }
        return ls;
    }

    public static List<String> toStringList(String content) {
        return toStringList(content, ",;/|，；、 　\n\r\t");
    }

    public static String join(Object arr, String delimiter) {
        if(arr==null) return "";
        StringBuilder sbf = new StringBuilder();
        if(arr instanceof Collection){
            Collection list = (Collection) arr;
            Iterator iterator = list.iterator();
            int c = 0;
            while(iterator.hasNext()){
                if(c>0) sbf.append(delimiter);
                Object val = iterator.next();
                sbf.append(val);
                c++;
            }
        }else if(arr.getClass().isArray()){
            int len = Array.getLength(arr);
            for(int i=0;i<len;i++){
                if(i>0) sbf.append(delimiter);
                sbf.append(Array.get(arr, i));
            }
        }
        return sbf.toString();
    }

    public static List<String> mergeTags(String ... tags){
        List<String> ls = new ArrayList<String>();
        for(String tag : tags) {
            if(tag!=null) {
                String[] strings = tag.split(",;，； \\n\\r.。\"\'\\s、");
                for(String s : strings){
                    if(s!=null) s=s.trim();
                    if(s==null || s.length()==0) continue;
                    if(!ls.contains(s)){
                        ls.add(s);
                    }
                }
            }
        }
        return ls;
    }

    public static String smartCutByTemplate(String content, String template, String placeholder) {
        if(content==null) return null;

        int fidx = template.indexOf(placeholder);
        if(fidx==-1) return null;
        int fidx2 = fidx+placeholder.length();
        String start = template.substring(0, fidx);
        String end = template.substring(fidx2);
        if(start.length()==0 && end.length()==0) return null;
        String[] flags = new String[]{start, end};

        String val = null;
        if(flags[0].length()==0){
            int idx = content.indexOf(flags[1]);
            if(idx!=-1){
                val = content.substring(0, idx);
            }
        }else if(flags[1].length()==0){
            int idx = content.indexOf(flags[0]);
            if(idx!=-1){
                val = content.substring(idx+flags[0].length());
            }
        }else{
            int idx = content.indexOf(flags[0]);
            if(idx!=-1){
                int idx2 = content.indexOf(flags[1], idx+flags[0].length());
                if(idx!=-1){
                    val = content.substring(idx+flags[0].length(), idx2);
                }
            }
        }

        return val;
    }

    public static String prettyCutString(String str, int maxLength, int endMaxLength, String dftValue) {
        if(str==null) return dftValue;
        if(str.length()<=maxLength) return str;
        int startLen = maxLength - endMaxLength;

        return str.substring(0, startLen) + "..." + str.substring(str.length()-endMaxLength);
    }

    public static String safeString(String val, String dftValue){
        return val!=null?val:dftValue;
    }


    public static String toDateString(Date date, String formatString) {
        if(date==null) return "";
        return new SimpleDateFormat(formatString).format(date);
    }



    public static Date getOffsetTime(int field, int offset, Date time){
        Calendar t = Calendar.getInstance();
        t.setTime(time);
        t.add(field, offset);
        return t.getTime();
    }

    public static Date getZeroSecondDateByOffestMinutes(int minutes, Date referenceDate){
        Calendar now = Calendar.getInstance();
        now.setTime(referenceDate);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        now.add(Calendar.MINUTE, minutes);
        return now.getTime();
    }


    public static Date getZeroMinuteDateByOffestHours(int hours){
        Calendar now = Calendar.getInstance();
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        now.add(Calendar.HOUR_OF_DAY, hours);
        return now.getTime();
    }

    public static Date getZeroMinuteDateByOffestHours(int hours, Date referenceDate){
        Calendar now = Calendar.getInstance();
        now.setTime(referenceDate);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        now.add(Calendar.HOUR_OF_DAY, hours);
        return now.getTime();
    }

    public static Date getZeroHourDateByOffestDays(int days){
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        now.add(Calendar.DATE, days);
        return now.getTime();
    }

    public static Date getZeroHourDateByOffestDays(int days, Date referenceDate){
        Calendar now = Calendar.getInstance();
        now.setTime(referenceDate);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        now.add(Calendar.DATE, days);
        return now.getTime();
    }

    /**
     * 获得本周的第一天
     * @return
     */
    public static Date getThisWeekFirstDate(){
        Calendar now = Calendar.getInstance();
        int mondayPlus;
        int dayOfWeek = now.get(Calendar.DAY_OF_WEEK); // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek <=1) {
            mondayPlus = -6;
        } else {
            mondayPlus = 2 - dayOfWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DATE, mondayPlus);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        Date monday = currentDate.getTime();
        return monday;
    }

    public static Date getThisMonthFirstDate() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_MONTH, 1);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return now.getTime();
    }

    public static Date toDate(String dateString, String formatString, Date defaultDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            return format.parse(dateString);
        } catch (Exception e) {
            return defaultDate;
        }
    }

    public static int toInt(String intString, int defaultValue){
        try{
            return Integer.parseInt(intString);
        }catch(Exception ex){
            return defaultValue;
        }
    }

    public static long toLong(String intString, long defaultValue){
        try{
            return Long.parseLong(intString);
        }catch(Exception ex){
            return defaultValue;
        }
    }



    public static Map<String, String> parametersToMap(String parameters) {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        try{
            String query = parameters;
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(
                        URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
        }catch(Exception ex){

        }
        return query_pairs;
    }

    public static void setProperty(Object bean, String propertyName, Object value) {
        MVEL.setProperty(bean, propertyName, value);
    }

    public static Object getProperty(Object bean, String propertyName) {
        return MVEL.getProperty(propertyName, bean);
    }

    public static void copyProperties(Object fromBean, Object targetBean, boolean onlyNoNull){
        if(fromBean==null || targetBean==null) return;
        Field[] fields = fromBean.getClass().getDeclaredFields();
        for(Field f : fields){
            try{
                Object v = MVEL.getProperty(f.getName(), fromBean);
                if(onlyNoNull){
                    if(v!=null){
                        MVEL.setProperty(targetBean, f.getName(), v);
                    }
                }else{
                    MVEL.setProperty(targetBean, f.getName(), v);
                }
            }catch(Exception ex){
                //ex.printStackTrace();
            }
        }
    }



    private static final String[] domainNames = new String[]{
            ".com", ".edu", ".gov", ".int", ".mil", ".net", ".org", ".biz", ".info", ".pro", ".name", ".museum", ".coop", ".aero", ".xxx", ".idv",
            ".com.cn", ".cn", ".tw", ".hk", ".me", ".mo"
    };




    public static boolean isUrlString(String url) {
        try {
            new URL(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String encodeUrl(String text, String charset){
        try {
            return URLEncoder.encode(text, charset);
        } catch (UnsupportedEncodingException e) {
            return text;
        }
    }



    public static boolean isAllLetters(String string) {
        if(string==null) return false;
        return string.matches("[a-zA-Z0-9\\.&#]+");
    }



    private static String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>";
    private static Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
    private static String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>";
    private static Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
    private static String regEx_html = "<[^>]+>";
    private static Pattern p_html =  Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
    public static String clearHtmlTag(String HTMLStr)
    {
        if(HTMLStr==null) return null;
        String htmlStr = HTMLStr;
        String textStr = "";
        Matcher m_script;
        Matcher m_style;
        Matcher m_html;
        try
        {
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("");
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("");
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("");
            textStr = htmlStr.replaceAll("&nbsp;", "");
            textStr = htmlStr.replaceAll("<",  "<");
            textStr = htmlStr.replaceAll(">",  ">");
            textStr = htmlStr.replaceAll("®", "®");
            textStr = htmlStr.replaceAll("&", "&");
        }
        catch (Exception e)
        {
            System.err.println("Html2Text: " + e.getMessage());
        }
        return textStr;
    }

    private static Pattern P_TRIM = Pattern.compile("[\\s\\r　 =\\-－——_]+");
    public static String clearString(String content) {
        if(content==null) return null;
        String cnt = content.trim();
        Matcher m = P_TRIM.matcher(cnt);
        if(m.matches()) {
            return m.replaceAll("");
        }else{
            return cnt;
        }
    }


    private static Pattern cut_0 = Pattern.compile("\\&[a-zA-Z]{1,10};");
    private static Pattern cut_1 = Pattern.compile("<[^>]*>");
    private static Pattern cut_2 = Pattern.compile("[(/>)<]");

    public static String subString(String sourceString, int maxLength) {
        if(sourceString!=null){
            Matcher m1 = cut_0.matcher(sourceString);
            if(m1.find()){
                sourceString = m1.replaceAll("");
            }
            Matcher m2 = cut_1.matcher(sourceString);
            if(m2.find()){
                sourceString = m2.replaceAll("");
            }
            Matcher m3 = cut_2.matcher(sourceString);
            if(m3.find()){
                sourceString = m3.replaceAll("");
            }
        }
        String resultString = "";
        if (sourceString == null || sourceString.equals("") || maxLength < 1) {
            return resultString;
        } else if (sourceString.length() <= maxLength) {
            return sourceString;
        } else if (sourceString.length() > 2 * maxLength) {
            sourceString = sourceString.substring(0, 2 * maxLength);
        }

        if (sourceString.length() > maxLength) {
            char[] chr = sourceString.toCharArray();
            int strNum = 0;
            int strGBKNum = 0;
            boolean isHaveDot = false;

            for (int i = 0; i < sourceString.length(); i++) {
                if (chr[i] >= 0xa1) // 0xa1汉字最小位开始
                {
                    strNum = strNum + 2;
                    strGBKNum++;
                } else {
                    strNum++;
                }

                if (strNum == 2 * maxLength || strNum == 2 * maxLength + 1) {
                    if (i + 1 < sourceString.length()) {
                        isHaveDot = true;
                    }
                    break;
                }
            }
            resultString = sourceString.substring(0, strNum - strGBKNum);
//			if (!isHaveDot) {
//				resultString = resultString + "...";
//			}
        }

        return resultString;
    }

    public static List<String> multiplyWords(Set<String> ws1, Set<String> ws2, Set<String> ws3) {
        List<Object> list = new ArrayList<Object>();
        list.addAll(Arrays.asList(new Object[]{ws1, ws2, ws3}));

        Set<String> s1 = null;
        int rmIdx = -1;
        for(int i=0;i<list.size();i++) {
            Set<String> s = (Set<String>) list.get(i);
            if(s.size()>0) {
                s1 = s;
                rmIdx = i;
                break;
            }
        }
        if(rmIdx>=0) list.remove(rmIdx);

        rmIdx = -1;
        Set<String> s2 = null;
        for(int i=0;i<list.size();i++) {
            Set<String> s = (Set<String>) list.get(i);
            if(s.size()>0) {
                s2 = s;
                rmIdx = i;
                break;
            }
        }
        if(rmIdx>=0) list.remove(rmIdx);

        rmIdx = -1;
        Set<String> s3 = null;
        for(int i=0;i<list.size();i++) {
            Set<String> s = (Set<String>) list.get(i);
            if(s.size()>0) {
                s3 = s;
                rmIdx = i;
                break;
            }
        }
        if(rmIdx>=0) list.remove(rmIdx);

        List<String> ret = new ArrayList<String>();

        if(s1!=null)for(String v1 : s1) {
            if(s2!=null && s2.size()>0){
                for(String v2 : s2) {
                    if(s3!=null && s3.size()>0) {
                        for(String v3 : s3) {
                            ret.add(new StringBuilder(v1).append(" ").append(v2).append(" ").append(v3).toString());
                        }
                    }else{
                        ret.add(new StringBuilder(v1).append(" ").append(v2).toString());
                    }
                }
            }else{
                ret.add(new StringBuilder(v1).toString());
            }
        }
        return ret;
    }

    public static List<String> multiplyWords(String k1, String k2, String k3) {
        List<Object> list = new ArrayList<Object>();
        list.addAll(Arrays.asList(new Object[]{DataHelper.toStringList(k1), DataHelper.toStringList(k2), DataHelper.toStringList(k3)}));

        List<String> s1 = null;
        int rmIdx = -1;
        for(int i=0;i<list.size();i++) {
            List<String> s = (List<String>) list.get(i);
            if(s.size()>0) {
                s1 = s;
                rmIdx = i;
                break;
            }
        }
        if(rmIdx>=0) list.remove(rmIdx);

        rmIdx = -1;
        List<String> s2 = null;
        for(int i=0;i<list.size();i++) {
            List<String> s = (List<String>) list.get(i);
            if(s.size()>0) {
                s2 = s;
                rmIdx = i;
                break;
            }
        }
        if(rmIdx>=0) list.remove(rmIdx);

        rmIdx = -1;
        List<String> s3 = null;
        for(int i=0;i<list.size();i++) {
            List<String> s = (List<String>) list.get(i);
            if(s.size()>0) {
                s3 = s;
                rmIdx = i;
                break;
            }
        }
        if(rmIdx>=0) list.remove(rmIdx);

        List<String> ret = new ArrayList<String>();

        if(s1!=null)for(String v1 : s1) {
            if(s2!=null && s2.size()>0){
                for(String v2 : s2) {
                    if(s3!=null && s3.size()>0) {
                        for(String v3 : s3) {
                            ret.add(new StringBuilder(v1).append(" ").append(v2).append(" ").append(v3).toString());
                        }
                    }else{
                        ret.add(new StringBuilder(v1).append(" ").append(v2).toString());
                    }
                }
            }else{
                ret.add(new StringBuilder(v1).toString());
            }
        }
        return ret;
    }

    public static String resolveUrl(String contextUrl, String destUrl){
        String dUrl = destUrl;
        try{
            if(!dUrl.toLowerCase().startsWith("http://") && !dUrl.toLowerCase().startsWith("https://")){
                String upath = contextUrl;
                int qIdx = upath.indexOf("?");
                if(qIdx!=-1){
                    upath = upath.substring(0, qIdx);
                }
                URL urlPath = new URL(upath);

                if(!dUrl.startsWith("?")){
                    dUrl = new URL(urlPath, dUrl).toString();
                }else{
                    dUrl = urlPath.toString() + dUrl;
                }
            }
        }catch(Exception ex){

        }
        return dUrl;
    }


}