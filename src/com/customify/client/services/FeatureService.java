/**
 * @description
 * server-side feature service for handling operations related to the billing feature
 *
 * @author fiston nshimiyandinze
 * @since Wednesday, 5 February 2021
 * */
package com.customify.client.services;

import com.customify.client.Colors;
import com.customify.client.Keys;
import com.customify.client.SendToServer;
import com.customify.client.data_format.BillingFeature.GetFeatureFormat;

import com.customify.client.data_format.billing.GetFeaturesFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;



public class FeatureService {
    private final Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;
    public FeatureService(Socket socket){
        this.socket = socket;

    }

    /**
     * @author fiston nshimiyandinze
     * @role
     * send request to the server for registering given feature into database
     * */

//    public  void RegisterFeature(FeatureFormat featureFormat) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(featureFormat);
//        SendToServer sendToServer = new SendToServer(json, this.socket);
//        if (sendToServer.send()) {
//            System.out.println("The feature is successfully created .... ");
//        }else {
//            System.out.println("Failed to send the request on the server ....");
//        }
//    }

    /**
     * @author fiston nshimiyandinze
     * send request to the server for updating  feature in database
     * */
//    public void update(FeatureFormat featureFormat) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(featureFormat);
//        SendToServer sendToServer = new SendToServer(json, this.socket);
//        if (sendToServer.send()) {
//            System.out.println("The feature is successfully updated .... ");
//        }else {
//            System.out.println("The request is not sent to the server ....");
//        }
//    }


    /**
     * @author fiston nshimiyandinze
     * @role
     * send request to the server for  get all features from the database
     * and sending back the response
     * */
    public void getFeatures() throws IOException, ClassNotFoundException{
        GetFeaturesFormat format = new GetFeaturesFormat(Keys.GET_FEATURES);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleGetFeaturesRes();
        }
    }

//    /**
//     * @author fiston nshimiyandinze
//     * @role
//     * send request to the server for getting feature matching given  id  from the database
//     * */
//    public void getFeaturesById(FeatureCode format) throws IOException, ClassNotFoundException{
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(format);
//        SendToServer serverSend = new SendToServer(json, this.socket);
//        if(serverSend.send()){
//            this.handleResponse("getById");
//        }
//        else {
//            System.out.println("Request failed...");
//        }
//    }
//
//    /**
//     * @author fiston nshimiyandinze
//     * @role
//     * send request to the server for  deleting feature matching given id from the database
//     * */
//    public void deleteFeature(FeatureCode format) throws IOException, ClassNotFoundException{
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(format);
//        SendToServer serverSend = new SendToServer(json, this.socket);
//        if(serverSend.send()){
//            System.out.println("--------feature deleted--------");
//        }
//        else {
//            System.out.println("Request failed...");
//        }
//    }
//
//    /**
//     * @author fiston nshimiyandinze
//     * @role
//     * this function has switch case that is used  to handle  Response  from the server depending on resultt
//     * and sending back the response
//     * */
//    public void handleResponse(String func_name) throws IOException,ClassNotFoundException{
//        try {
//            this.input = this.socket.getInputStream();
//            this.objectInput = new ObjectInputStream(this.input);
//            this.json_data = (String)this.objectInput.readObject();
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.readTree(json_data);
//            switch (func_name){
//                case "getAll":
//                    this.handleGetResponse(jsonNode);
//                    break;
//                case "getById":
//                    this.handleGetBYId(jsonNode);
//                    break;
//                default:
//                    System.out.println("Invalid option");
//                    break;
//            }
//        } catch (IOException e) {
//            System.out.println("Error in reading Object " + e.getMessage());
//        }
//
//            }
//
//    /**
//     * @author fiston nshimiyandinze
//     * @role
//     * this function is used to handle    handle handleGetBYId response
//     * and sending back the response
//     * */
//
//    public void handleGetBYId(JsonNode jsonNode)throws IOException{
//        ObjectMapper objectMapper = new ObjectMapper();
//        FeatureFormat data = objectMapper.treeToValue(jsonNode, FeatureFormat.class);
//        System.out.println("-------------------Feature------------------------------");
//        System.out.format("%5s%20s%20s%25s%20s\n","FeatureCode","Name","Description");
//        System.out.println();
//        System.out.format("%5d%20s%20s%25s%20s\n",data.getFeatureCode(),data.getFeatureName(),data.getFeatureDescription());
//    }
//
//    /**
//     * @author fiston nshimiyandinze
//     * @role
//     * this function is used to handle    handle handleGetall features response
//     * and sending back the response
//     * */
//
//    public void handleGetResponse(JsonNode jsonNode) throws IOException,ClassNotFoundException{
//        ObjectMapper objectMapper = new ObjectMapper();
//        FeatureReadFormat featureReadFormat = objectMapper.treeToValue(jsonNode, FeatureReadFormat.class);
//        System.out.println("-------------------Feature------------------------------");
//        System.out.format("%5s%20s%20s%25s%20s\n","FeatureCode","Name","Description");
//        System.out.println();
//        for(FeatureFormat bs:featureReadFormat.getData()){
//            System.out.format("%5d%20s%20s%25s%20s\n",bs.getFeatureCode(),bs.getFeatureName(),bs.getFeatureDescription());
//        }
//    }



    public void handleGetFeaturesRes(){
        /**
         * @author Patrick Niyogitare
         * @role
         * handling the get features response
         * */
        try{
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            String json_response = response.get(0);
            System.out.println(Colors.ANSI_CYAN);
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tALL SALES");
            System.out.println(Colors.ANSI_RESET);
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+------------+------------+------------+%n");
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t| Feature code     | Feature Name |  Feature description | %n");
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+------------+------------+------------+%n");

            for(int i=0; i < response.size(); i++) {
                String responseData = response.get(i);
                JsonNode jsonNode = new ObjectMapper().readTree(responseData);
                String leftAlignFormat = "| %-10s | %-10s | %-10s |%n";

                System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t" + leftAlignFormat,jsonNode.get("featureCode").asText() ,jsonNode.get("featureName").asText() ,jsonNode.get("featureDescription").asText());
                System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+------------+------------+------------+%n");
                System.out.println("\n");
            }
        }catch(Exception e) {
            System.out.println("RESPONSE ERROR =>"+e.getMessage());
        }
    }


}
