package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryStrings {
  private List<QueryString> queryString=new ArrayList<>();
  // "operand1=11&operator&operand2=55"
  public QueryStrings(String queryStringLine){
    String[] queryStringTokens=queryStringLine.split("&");
    Arrays.stream(queryStringTokens)
        .forEach(queryString->{
          String[] values=queryString.split("=");
          if(values.length!=2){
            throw new IllegalArgumentException("잘못된 QueryString 포맷을가진 문자열입니다");
          }
        });
  }
}
