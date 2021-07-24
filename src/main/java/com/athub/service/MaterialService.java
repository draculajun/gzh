package com.athub.service;

import com.athub.dto.MaterialQueryDto;
import com.athub.dto.MaterialCountDto;
import com.athub.dto.NewsArticleDto;

import java.io.File;
import java.util.List;

/**
 * @Author Wang wenjun
 */
public interface MaterialService {

    boolean addForeverOthers(File file, String type);

    String addNews(List<NewsArticleDto> articlesList);

    byte[] getForeverMedia(String mediaId);

    Object page(MaterialQueryDto materialQueryDto);

    MaterialCountDto count();

    boolean addTemporaryMedia(File multipartFileToFile, String type);

    byte[] getTemporaryMedia(String mediaId);
}
