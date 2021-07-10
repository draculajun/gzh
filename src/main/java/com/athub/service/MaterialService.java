package com.athub.service;

import com.athub.dto.MaterialQueryDto;

import java.io.File;

/**
 * @Author Wang wenjun
 */
public interface MaterialService {

    boolean add(File file, String type);

    byte[] get(String mediaId);

    Object page(MaterialQueryDto materialQueryDto);
}
