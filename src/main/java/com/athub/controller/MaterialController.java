package com.athub.controller;

import com.athub.dto.MaterialQueryDto;
import com.athub.dto.NewsArticleDto;
import com.athub.dto.Result;
import com.athub.service.MaterialService;
import com.athub.utils.FileUtils;
import com.athub.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author Wang wenjun
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    MaterialService materialService;

    /**
     * 新增临时素材
     *
     * @param file
     * @param type
     * @return
     * @throws Exception
     */
    @PostMapping("/temporary/media")
    public Result addTemporaryMedia(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) throws Exception {
        if (materialService.addTemporaryMedia(FileUtils.multipartFileToFile(file), type)) {
            return ResultUtils.success("新增临时素材成功");
        } else {
            return ResultUtils.error(500, "新增临时素材失败");
        }
    }

    /**
     * 获取临时素材
     *
     * @param mediaId
     * @return
     * @throws Exception
     */
    @GetMapping("/temporary/media_id/{media_id}")
    public ResponseEntity<byte[]> getTemporaryMedia(@PathVariable("media_id") String mediaId) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + mediaId + ".jpg" + "\"");
        return new ResponseEntity<byte[]>(materialService.getTemporaryMedia(mediaId), headers, HttpStatus.OK);
    }

    /**
     * 新增其他永久素材
     *
     * @param file
     * @param type
     * @return
     * @throws Exception
     */
    @PostMapping("/forever/others")
    public Result addForeverOthers(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) throws Exception {
        if (materialService.addForeverOthers(FileUtils.multipartFileToFile(file), type)) {
            return ResultUtils.success("新增永久素材成功");
        } else {
            return ResultUtils.error(500, "新增永久素材失败");
        }
    }

    /**
     * 新增永久图文素材
     */
    @PostMapping("/news")
    public Result addNews(@RequestBody List<NewsArticleDto> articlesList) {
        return ResultUtils.success(materialService.addNews(articlesList), "新增永久图文素材成功");
    }

    /**
     * 下载永久素材
     *
     * @param mediaId
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/forever/media_id/{media_id}")
    public ResponseEntity<byte[]> getForeverMedia(@PathVariable("media_id") String mediaId) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + mediaId + ".jpg" + "\"");
        return new ResponseEntity<byte[]>(materialService.getForeverMedia(mediaId), headers, HttpStatus.OK);
    }

    /**
     * 获取素材总数
     * @return
     */
    @GetMapping("/count")
    public Result count(){
        return ResultUtils.success(materialService.count(), "");
    }

    /**
     * 获取素材列表
     *
     * @param materialQueryDto
     * @return
     */
    @PostMapping(value = "/page")
    public Result page(@RequestBody MaterialQueryDto materialQueryDto) {
        return ResultUtils.success(materialService.page(materialQueryDto), "");
    }

}
