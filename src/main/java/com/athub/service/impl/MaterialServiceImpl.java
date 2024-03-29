package com.athub.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.athub.dto.MaterialCountDto;
import com.athub.dto.MaterialQueryDto;
import com.athub.dto.NewsArticleDto;
import com.athub.exception.BusinessException;
import com.athub.service.MaterialService;
import com.athub.utils.GzhUploadUtils;
import com.athub.utils.RequestHeaderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wang wenjun
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    private final static Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);

    @Value("${gzhInfo.materialTemporaryMediaAddFormat}")
    private String materialTemporaryMediaAddFormat;

    @Value("{gzhInfo.materialTemporaryMediaGetFormat}")
    private String materialTemporaryMediaGetFormat;

    @Value("${gzhInfo.materialForeverAddFormat}")
    private String materialForeverAddFormat;

    @Value("${gzhInfo.materialForeverGetFormat}")
    private String materialForeverGetFormat;

    @Value("${gzhInfo.materialBatchGetFormat}")
    private String materialBatchGetFormat;

    @Value("${gzhInfo.materialNewsForeverAddFormat}")
    private String materialNewsForeverAddFormat;

    @Value("${gzhInfo.meterialCountFormat}")
    private String meterialCountFormat;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean addForeverOthers(File file, String type) {
        try {
            String url = String.format(materialForeverAddFormat, RequestHeaderContext.getInstance().getAccessToken(), type);
            ResponseEntity responseEntity = GzhUploadUtils.uploadFile(url, file, type);
            logger.info("addForeverOthers:" + responseEntity.getBody().toString());
            return true;
        } catch (Exception e) {
            logger.error(e.toString());
            throw new BusinessException("500", "新增其他类型永久素材失败：" + e.toString());
        }
    }

    @Override
    public String addNews(List<NewsArticleDto> articlesList) {
        try {
            String url = String.format(materialNewsForeverAddFormat, RequestHeaderContext.getInstance().getAccessToken());
            Map map = new HashMap<>();
            map.put("articles", articlesList);
            String str = restTemplate.postForObject(url, map, String.class);
            JSONObject jo = JSON.parseObject(str);
            return jo.get("media_id").toString();
        } catch (Exception e) {
            logger.error(e.toString());
            throw new BusinessException("500", "上传永久图文素材失败");
        }
    }

    @Override
    public byte[] getForeverMedia(String mediaId) {
        String url = String.format(materialForeverGetFormat, RequestHeaderContext.getInstance().getAccessToken());
        Map map = new HashMap<>();
        map.put("media_id", mediaId);
        return restTemplate.postForObject(url, map, byte[].class);
    }

    @Override
    public Object page(MaterialQueryDto materialQueryDto) {
        String url = String.format(materialBatchGetFormat, RequestHeaderContext.getInstance().getAccessToken());
        String result = restTemplate.postForObject(url, materialQueryDto, String.class);
        return result;
    }

    @Override
    public MaterialCountDto count() {
        try {
            String url = String.format(meterialCountFormat, RequestHeaderContext.getInstance().getAccessToken());
            String str = restTemplate.getForObject(url, String.class);
            return JSONObject.parseObject(str, MaterialCountDto.class);
        } catch (Exception e) {
            logger.error(e.toString());
            throw new BusinessException("500", "获取素材总数失败");
        }
    }

    @Override
    public boolean addTemporaryMedia(File file, String type) {
        try {
            String url = String.format(materialTemporaryMediaAddFormat, RequestHeaderContext.getInstance().getAccessToken(), type);
            ResponseEntity responseEntity = GzhUploadUtils.uploadFile(url, file, type);
            logger.info("addTemporaryMedia:" + responseEntity.getBody().toString());
            return true;
        } catch (Exception e) {
            logger.error(e.toString());
            throw new BusinessException("500", "新增临时素材失败：" + e.toString());
        }
    }

    @Override
    public byte[] getTemporaryMedia(String mediaId) {
        String url = String.format(materialForeverGetFormat, RequestHeaderContext.getInstance().getAccessToken());
        Map map = new HashMap<>();
        map.put("media_id", mediaId);
        return restTemplate.postForObject(url, map, byte[].class);
    }

    @Deprecated
    public String connectHttpsByPost(String url, File file) throws Exception {
        URL urlObj = new URL(url);
        //连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        String result = null;
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false); // post方式不能使用缓存

        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type",
                "multipart/form-data; boundary="
                        + BOUNDARY);

        // 请求正文信息
        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // 必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length() + "\";filename=\""
                + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        // 获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        // 输出表头
        out.write(head);

        // 文件正文部分
        // 把文件以流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();

        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
        out.write(foot);
        out.flush();
        out.close();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            logger.error(e.toString());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        logger.info(result);

        return result;
    }
}
