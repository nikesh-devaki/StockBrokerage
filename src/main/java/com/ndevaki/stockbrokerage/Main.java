package com.ndevaki.stockbrokerage;

import com.ndevaki.stockbrokerage.client.HttpClient;
import com.ndevaki.stockbrokerage.model.DataManager;
import com.ndevaki.stockbrokerage.model.Security;
import com.ndevaki.stockbrokerage.utils.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        String portfolio= HttpClient.getrequest(Constants.api2);
        String stocksTrend=HttpClient.getrequest(Constants.api1);
        JSONParser parser = new JSONParser();
        try {
            JSONObject portfolioObject = (JSONObject) parser.parse(portfolio);
            JSONObject trendObject= (JSONObject) parser.parse(stocksTrend);
            HashMap<String, HashMap<String, Security>>  result= DataManager.processData(portfolioObject,trendObject);
            Scanner scanner=new Scanner(System.in);
            System.out.println("Enter dates seperated by commas (,)");
            String input=scanner.nextLine();
            String[] inputs=input.split(",");
            double sum=0.0;
            for(int i=0;i<inputs.length;i++){
                HashMap<String,Security> details=result.get(inputs[i]);
                for(String key: details.keySet()){
                    sum+=details.get(key).getRate();
                }
            }
            System.out.println("Valuation: "+sum);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
