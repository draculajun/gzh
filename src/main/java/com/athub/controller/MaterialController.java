package com.athub.controller;

import com.athub.dto.MaterialQueryDto;
import com.athub.dto.Result;
import com.athub.service.MaterialService;
import com.athub.utils.FileUtils;
import com.athub.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author Wang wenjun
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/material")
public class MaterialController {

    private static Logger logger = LoggerFactory.getLogger(MaterialController.class);

    @Autowired
    MaterialService materialService;

    /**
     * 新增永久素材
     *
     * @param file
     * @param type
     * @return
     * @throws Exception
     */
    @PostMapping
    public Result add(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) throws Exception {
        if (materialService.add(FileUtils.multipartFileToFile(file), type)) {
            return ResultUtils.success("新增永久素材成功");
        } else {
            return ResultUtils.error(500, "新增永久素材失败");
        }
    }

    /**
     * 下载永久素材（jpg）
     *
     * @param mediaId
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/media_id/{media_id}")
    public ResponseEntity<byte[]> get(@PathVariable("media_id") String mediaId) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + mediaId + ".jpg" + "\"");
        return new ResponseEntity<byte[]>(materialService.get(mediaId), headers, HttpStatus.OK);
    }

    @PostMapping(value = "/page")
    public Result page(@RequestBody MaterialQueryDto materialQueryDto){
        return ResultUtils.success(materialService.page(materialQueryDto), "");
    }

}
