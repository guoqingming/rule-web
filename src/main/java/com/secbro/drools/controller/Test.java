//package com.secbro.drools.controller;
//
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @program: spring-boot-drools
// * @description:
// * @author: guoqingming
// * @create: 2018-12-21 18:44
// **/
//public class Test {
//    static Map<Integer,String> map = new HashMap<>();
//    static Map<Integer,String> fieldMap = new HashMap<>();
//       static {
//           map.put(1, "Integer");
//           map.put(2, "String");
//           map.put(3, "BigDecimal");
//           map.put(4, "Double");
//           map.put(5, "Boolean");
//
//           fieldMap.put(1, "age");
//           fieldMap.put(2, "name");
//           fieldMap.put(3, "price");
//       }
//    public static void main(String[] args) {
//
//
//        String condition = "1>18&&2==abc||3<2.0";
//        List<String> list = new ArrayList<>();
//        String[] split = StringUtils.split(condition,"&&");
//        for (String s : split) {
//            if(!s.contains("||")){
//                list.add(s);
//                continue;
//            }
//        }
//        for (String string : ) {
//            String regex = "\\$\\{" + string + "\\}";
//            String repacement = "this[\""+string+"\"]";
//            condition = condition.replaceAll(regex, repacement);
////            condition = ReUtil.replaceAll(condition, regex, repacement);
//        }
//
//
//    }
//
//    String wrap(String expression) {
//        StringBuffer sb = new StringBuffer();
//
//        if(expression.contains(">")){
//            String left = expression.split(">")[0];
//            String right = expression.split(">")[0];
//            Integer fid = Integer.valueOf(left);
//            String type = map.get(fid);
//            String fieldName = fieldMap.get(fid);
//
//            sb.append("this[\""+fieldName+"\"]");
//            sb.append(">");
//            sb.append(right);
//
//        }
//        else if(expression.contains("<")){
//            String left = expression.split("<")[0];
//            String right = expression.split("<")[0];
//            Integer fid = Integer.valueOf(left);
//            String type = map.get(fid);
//            String fieldName = fieldMap.get(fid);
//
//            sb.append("this[\""+fieldName+"\"]");
//            sb.append("<");
//            sb.append(right);
//        }
//        else if(expression.contains("<=")){
//            String left = expression.split("<=")[0];
//            String right = expression.split("<=")[0];
//            Integer fid = Integer.valueOf(left);
//            String type = map.get(fid);
//            String fieldName = fieldMap.get(fid);
//
//            sb.append("this[\""+fieldName+"\"]");
//            sb.append("<=");
//            sb.append(right);
//        }
//        else if(expression.contains(">=")){
//            String left = expression.split(">=")[0];
//            String right = expression.split(">=")[0];
//            Integer fid = Integer.valueOf(left);
//            String type = map.get(fid);
//            String fieldName = fieldMap.get(fid);
//
//            sb.append("this[\""+fieldName+"\"]");
//            sb.append(">=");
//            sb.append(right);
//        }
//        else if(expression.contains("==")){
//            String left = expression.split("==")[0];
//            String right = expression.split("==")[0];
//            Integer fid = Integer.valueOf(left);
//            String type = map.get(fid);
//            String fieldName = fieldMap.get(fid);
//            sb.append("this[\""+fieldName+"\"]");
//            sb.append("==");
//            sb.append(right);
//        }
//        else if(expression.contains("!=")){
//            String left = expression.split("!=")[0];
//            String right = expression.split("!=")[0];
//            Integer fid = Integer.valueOf(left);
//            String type = map.get(fid);
//            String fieldName = fieldMap.get(fid);
//            sb.append("this[\""+fieldName+"\"]");
//            sb.append("!=");
//            sb.append(right);
//        }else {
//            return "this[\""+expression+"\"]";
//        }
//        return sb.toString();
//    }
//
//    String wrapSingleExpresstion(String )
//}
