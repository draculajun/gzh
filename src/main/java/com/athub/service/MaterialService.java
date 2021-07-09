package com.athub.service;

import java.io.File;

/**
 * @Author Wang wenjun
 */
public interface MaterialService {

    boolean add(File file, String type);

    byte[] get(String mediaId);

}
