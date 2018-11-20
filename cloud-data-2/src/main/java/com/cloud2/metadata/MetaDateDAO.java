/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud2.metadata;

import com.cloud2.pojo.FileMetaData;
import java.util.List;


/**
 *
 * @author kerch
 */
public interface MetaDateDAO {
    
    
    public void createFile(String name, String type, String size, long createdDate, long currentDate, String user);
    public void updateFile(String name, String type, String size, long currentDate, String user);
    public void deleteFile(int id);
    public boolean fileExist(String fileName, String fileMask, String userName);
    public boolean bucketExist(String userName);
    public FileMetaData getFileMetadataByFileId(int id);
    public List<FileMetaData> getAll(String userName);
    
}
