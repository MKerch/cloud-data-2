/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud2.metadata;

/**
 *
 * @author kerch
 */
public class MetaDataServiseImpl implements MetaDataSelvice{

    @Override
    public String fileNameParser(String headerValue) {
        int startIndex = headerValue.indexOf("filename=\"");
        int endIndex = headerValue.lastIndexOf(".");
        return headerValue.substring(startIndex+10, endIndex);
    }
    
}
