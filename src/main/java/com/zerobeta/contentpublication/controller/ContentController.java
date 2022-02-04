package com.zerobeta.contentpublication.controller;

import com.zerobeta.contentpublication.domain.ContentDomain;
import com.zerobeta.contentpublication.domain.Response;
import com.zerobeta.contentpublication.service.ContentService;
import com.zerobeta.contentpublication.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Controller for content resource access")
@RestController
@RequestMapping("/api/content")
@CrossOrigin("http://localhost:3000")
public class ContentController {

    private  final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private ContentService contentService;

    @ApiOperation(value = "getAllContent", response = Response.class, tags = "getAllContent")
    @GetMapping(value = "/allcontent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getAllContent(){

        logger.info("Entering into getAllContent");
        return ResponseEntity.ok().body(contentService.getAllContent());
    }

    @ApiOperation(value = "getContent", response = Response.class, tags = "getContent")
    @GetMapping(value = "/getcontent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getContent(@RequestParam(name = "contentId") Integer contentId){

        logger.info("Entering into getContent");
        return ResponseEntity.ok().body(contentService.getContent(contentId));
    }

    @ApiOperation(value = "getContentByUserId", response = Response.class, tags = "getContentByUserId")
    @GetMapping(value = "/getcontentbyuserid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getContentByUserId(){

        logger.info("Entering into getContentByUserId");
        return ResponseEntity.ok().body(contentService.getContentByUserId(Util.getUserDetails().getId()));
    }

    @ApiOperation(value = "addContent", response = Response.class, tags = "addContent")
    @PostMapping(value = "/addcontent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> addContent(@RequestBody ContentDomain contentDomain){

        logger.info("Entering into addContent");
        return ResponseEntity.ok().body(contentService.addContent(Util.getUserDetails().getId(), contentDomain));
    }

    @ApiOperation(value = "editContent", response = Response.class, tags = "editContent")
    @PostMapping(value = "/editcontent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> editContent(@RequestBody ContentDomain contentDomain){

        logger.info("Entering into editContent");
        return ResponseEntity.ok().body(contentService.editContent(contentDomain));
    }

    @ApiOperation(value = "deleteContent", response = Response.class, tags = "deleteContent")
    @GetMapping(value = "/deletecontent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteContent(@RequestParam(name = "contentId") Integer contentId){

        logger.info("Entering into deleteContent");
        return ResponseEntity.ok().body(contentService.deleteContent(contentId));
    }

    @ApiOperation(value = "publishContent", response = Response.class, tags = "publishContent")
    @GetMapping(value = "/publishcontent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> publishContent(@RequestParam(name = "contentId") Integer contentId,
                                                   @RequestParam(name = "status") String status){

        logger.info("Entering into publishContent");
        return ResponseEntity.ok().body(contentService.publishContent(contentId, status));
    }

    @ApiOperation(value = "addComment", response = Response.class, tags = "addComment")
    @PostMapping(value = "/addcomment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> addComment(@RequestBody ContentDomain contentDomain){

        logger.info("Entering into addComment");
        return ResponseEntity.ok().body(contentService.addComment(Util.getUserDetails().getId(),contentDomain ));
    }
}
