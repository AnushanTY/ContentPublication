package com.zerobeta.contentpublication.serviceimpl;

import com.zerobeta.contentpublication.domain.ContentDomain;
import com.zerobeta.contentpublication.domain.Response;
import com.zerobeta.contentpublication.entity.Content;
import com.zerobeta.contentpublication.entity.ContentCategory;
import com.zerobeta.contentpublication.entity.ContentComment;
import com.zerobeta.contentpublication.entity.User;
import com.zerobeta.contentpublication.respository.ContentCategoryRepository;
import com.zerobeta.contentpublication.respository.ContentRepository;
import com.zerobeta.contentpublication.respository.UserRepository;
import com.zerobeta.contentpublication.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentCategoryRepository contentCategoryRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public Response addContent(Integer userId,ContentDomain contentDomain) {

        Content content = new Content();
        content.setTitle(contentDomain.getTitle());
        content.setPublishedDate(new Date());
        content.setSummary(contentDomain.getSummary());
        content.setDetail(contentDomain.getDetail());
        content.setIsPublished(false);

        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(content::setUser);

        Optional<ContentCategory> contentCategory = contentCategoryRepository.findById(contentDomain.getCategoryId());
        contentCategory.ifPresent(content::setContentCategory);

        contentRepository.save(content);

        contentRepository.save(content);
        return Response.success(true);
    }

    @Override
    public Response editContent(ContentDomain contentDomain) {

        Optional<Content> content = contentRepository.findById(contentDomain.getContentId());

        if (content.isPresent()){
            Content editContent = content.get();
            editContent.setTitle(contentDomain.getTitle());
            editContent.setSummary(contentDomain.getSummary());
            editContent.setDetail(contentDomain.getDetail());
            editContent.setEditDate(new Date());

            return Response.success(contentRepository.save(editContent));
        } else {
            return Response.failure("Content not found");
        }
    }

    @Override
    public Response deleteContent(Integer contentId) {
        Optional<Content> content = contentRepository.findById(contentId);

        if (content.isPresent()){
            contentRepository.deleteById(contentId);
            return Response.success("Content Deleted successfully");
        } else
            return Response.failure("Content not found");

    }

    @Override
    public Response publishContent(Integer contentId, String status) {
        Optional<Content> content = contentRepository.findById(contentId);

        if (content.isPresent()){
            content.get().setIsPublished("true".equals(status));

            if("true".equals(status)){
                content.get().setIsPublished(true);
                String categoryTypeAsTopic = content.get().getContentCategory().getCategoryName();

                String topicName = null;
                if(categoryTypeAsTopic.equals("ML/AL")){
                    topicName = "ML-AL";
                } else if(categoryTypeAsTopic.equals("Big Data")){
                    topicName = "Big-Data";
                } else {
                    topicName = categoryTypeAsTopic;
                }
                System.out.println(topicName);

                kafkaTemplate.send(topicName, content.get().getTitle() + " Published" );
            }
            contentRepository.save(content.get());
            return Response.success("Content publish status changed successfully");
        } else
            return Response.failure("Content not found");
    }

    @Override
    public Response getAllContent() {
        return Response.success(contentRepository.findAll());
    }

    @Override
    public Response getContent(Integer contentId) {
        Optional<Content> content = contentRepository.findById(contentId);
        return content.map(Response::success).orElseGet(() -> Response.success(null));
    }

    @Override
    public Response getContentByUserId(Integer userId) {

        Optional<User> user = userRepository.findById(userId);
        return user.map(value -> Response.success(contentRepository.findByUser(value))).orElse(null);
    }

    @Override
    public Response addComment(Integer userId, ContentDomain contentDomain) {

        ContentComment contentComment = new ContentComment();
        contentComment.setComment(contentDomain.getComment());

        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(contentComment::setUser);

        Optional<Content> content = contentRepository.findById(contentDomain.getContentId());

        if (content.isPresent()){
            contentComment.setContent(content.get());
            content.get().getContentComments().add(contentComment);

            contentRepository.save(content.get());

            return Response.success("Content comment successfully");
        } else
            return Response.failure("Content not found");

    }
}
