package com.athub.service.impl;

import com.athub.exception.BusinessException;
import com.athub.service.MaterialService;
import com.athub.utils.RequestHeaderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wang wenjun
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    private final static Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);

    @Value("${gzhInfo.materialAddFormat}")
    private String materialAddFormat;

    @Value("${gzhInfo.materialGetFormat}")
    private String materialGetFormat;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean add(File file, String type) {
        String url = String.format(materialAddFormat, RequestHeaderContext.getInstance().getAccessToken(), type);
        try {
            String result = this.connectHttpsByPost(url, file);
            if (result == null) {
                return false;
            }
        } catch (Exception e) {
            throw new BusinessException("500", "新增其他类型永久素材失败：" + e.toString());
        }
        return true;
    }

    @Override
    public byte[] get(String mediaId) {
        String url = String.format(materialGetFormat, RequestHeaderContext.getInstance().getAccessToken());
        Map map = new HashMap<>();
        map.put("media_id", mediaId);
        byte[] resultByteArr = restTemplate.postForObject(url, map, byte[].class);
        return resultByteArr;
    }

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
