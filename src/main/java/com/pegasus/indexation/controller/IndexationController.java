package com.pegasus.indexation.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pegasus.indexation.model.DeleteResponse;
import com.pegasus.indexation.model.GetResponse;
import com.pegasus.indexation.model.PostRequest;
import com.pegasus.indexation.model.PostResponse;

/**
 * 
 * @author hongyu.ma
 *
 */
@RestController
public class IndexationController {

    @PostMapping("/api/content/check")
    public ResponseEntity<PostResponse> postCheck(@RequestBody PostRequest request) {
        
        PostResponse response = new PostResponse();
        
        try {

            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("indexation.properties"));
            
            final String myProxy = prop.getProperty("indexation.proxy.host");
            final Integer myPort = Integer.parseInt(prop.getProperty("indexation.proxy.port"));
            
            if (myProxy == null || "".equals(myProxy) || myPort == null) {
                response.setState("Proxy confuguration problem");
                return new ResponseEntity<PostResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(myProxy, myPort));
            URL url = new URL(request.getUrl());
            URLConnection urlConnection = url.openConnection(proxy);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(request.getWord())) {
                    bufferedReader.close();
                    response.setState("rejected");
                    // TODO add to database
                    return new ResponseEntity<PostResponse>(response, HttpStatus.OK);
                }
            }
            bufferedReader.close();
            response.setState("accepted");
        } catch (Exception e) {
            response.setState(e.toString());
            return new ResponseEntity<PostResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PostResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/api/content")
    public ResponseEntity<GetResponse> getContent() {

        GetResponse response = new GetResponse();
        return new ResponseEntity<GetResponse>(response, HttpStatus.OK);
    }

    @DeleteMapping("/api/content")
    public ResponseEntity<DeleteResponse> deleteContent() {

        DeleteResponse response = new DeleteResponse();
        return new ResponseEntity<DeleteResponse>(response, HttpStatus.OK);
    }
}