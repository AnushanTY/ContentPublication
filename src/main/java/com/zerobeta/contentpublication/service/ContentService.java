package com.zerobeta.contentpublication.service;

import com.zerobeta.contentpublication.domain.ContentDomain;
import com.zerobeta.contentpublication.domain.Response;
import com.zerobeta.contentpublication.entity.Content;
import com.zerobeta.contentpublication.entity.ContentComment;

public interface ContentService {

    Response addContent(Integer userId, ContentDomain content);

    Response editContent(ContentDomain content);

    Response deleteContent(Integer contentId);

    Response publishContent(Integer contentId, String status);

    Response getAllContent();

    Response getContent(Integer contentId);

    Response getContentByUserId(Integer userId);

    Response addComment(Integer userId, ContentDomain contentComment);
}
