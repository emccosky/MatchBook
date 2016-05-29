package com.viperbots.matchbook2;


import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;


/**
 * Created by mccosky_890578 on 5/18/2016.
 */
public class awsDBHandler {

    AWSCredentials credentials;
    AmazonDynamoDBClient dbClient;

    List<Map<String, AttributeValue>> items;
    boolean isTaskComplete = false;

    public awsDBHandler() {
        this.credentials = new BasicAWSCredentials("AKIAJNKU7SGDAAJXUIZA","b0ViXpDHHAL/LoGIX4G2XwmJZ+4EJUZBkOQRRU21");
        this.dbClient = new AmazonDynamoDBClient(credentials);
    }

    public void populateMatch(Match match){
        new dbWrite().execute(match);
    }

    public ArrayList<Match> getAllMatches(int compID){
        ArrayList<Match> matches = new ArrayList<Match>();
        new dbRetrieveAll().execute(compID);
        while(!isTaskComplete){
            //Log.d("DB", "waiting for task completion");
        }
        //reset value for future calls
        isTaskComplete = false;
        for(Map item : items){
            matches.add(new Match(Integer.parseInt(((AttributeValue)item.get("matchNum")).getN()), Integer.parseInt(((AttributeValue)item.get("compID")).getS()),
                    Integer.parseInt(((AttributeValue)item.get("red1")).getS()), Integer.parseInt(((AttributeValue)item.get("red2")).getS()),
                    Integer.parseInt(((AttributeValue)item.get("blue1")).getS()), Integer.parseInt(((AttributeValue)item.get("blue2")).getS())));
        }
        return matches;
    }

    /* REST OF CRUD GOES HERE */

    /*=============== ASYNC TASK DEFINITIONS ===================*/

    private class dbWrite extends AsyncTask<Match,Void,String> {

        @Override
        protected String doInBackground(Match... params) {
            try{
                Match match = params[0];
                Map item = new HashMap<>();
                item.put("compID", new AttributeValue().withN(Integer.toString(match.getCompID())));
                item.put("matchNum", new AttributeValue().withS(Integer.toString(match.getMatchNum())));
                item.put("red1", new AttributeValue().withS(Integer.toString(match.getRed1())));
                item.put("red2", new AttributeValue().withS(Integer.toString(match.getRed2())));
                item.put("blue1", new AttributeValue().withS(Integer.toString(match.getBlue1())));
                item.put("blue2", new AttributeValue().withS(Integer.toString(match.getBlue2())));

                PutItemRequest putItemRequest = new PutItemRequest("matchTable", item);
                PutItemResult putItemResult = dbClient.putItem(putItemRequest);

                Log.i("DB", "Match Successfully written");
                return "Written";
            } catch(Exception e) {
                Log.d("DB", e.toString());
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }

    private class dbRetrieveAll extends AsyncTask<Integer, Void, Object> {

        @Override
        protected Object doInBackground(Integer... params) {
            Integer compID = params[0];
            HashMap scanFilter = new HashMap();

            Log.d("Background Task", "Completed Step 1");

            /*Condition keyCondition = new Condition().withComparisonOperator(
                    ComparisonOperator.EQ.toString()).withAttributeValueList(new AttributeValue().withN(compID.toString()));

            Log.d("Background Task", "Completed Step 2");

            scanFilter.put("compID", keyCondition);

            Log.d("Background Task", "Completed Step 3");*/

            ScanRequest scanRequest = new ScanRequest("matchTable");

            Log.d("Background Task", "Completed Step 4");

            ScanResult scanResult = dbClient.scan(scanRequest);

            Log.d("Background Task", "Completed Step 5");

            Log.i("DB", "Matches Successfully Retreived");
            items = scanResult.getItems();
            isTaskComplete = true;
            return "Retrieved";
        }

        @Override
        protected void onPostExecute(Object result){
            Log.d("DB", "Post Execute");
        }

    }

}
