package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.Entity.CVEntity;
import com.example.happyprogramming.Entity.CommentAndRateEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.CVRepository;
import com.example.happyprogramming.repository.RateCommentRepository;
import com.example.happyprogramming.repository.UserRepository;
import com.example.happyprogramming.service.RateCommentService;
import com.example.happyprogramming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RateCommentServiceImpl implements RateCommentService {

    @Autowired
    private RateCommentRepository rateCommentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CVRepository cvRepository;

    @Override
    public String getRateComment(int mentorId, int menteeId) {
        UserEntity user = userRepository.findById(mentorId);
        CVEntity mentor = cvRepository.findByUser(user);
        ArrayList<CommentAndRateEntity> commentAndRateList = rateCommentRepository.findByMentor(mentor);
        CommentAndRateEntity commentAndRate = new CommentAndRateEntity();
        for (int i =0; i < commentAndRateList.size();i++){
            if(commentAndRateList.get(i).getMenteeId()==menteeId)
                commentAndRate = commentAndRateList.get(i);
        }
        String result="<form class=\"form\" action=\"\">\n" +
                "            <input id=\"mentor-id\" type=\"hidden\">\n" +
                "            <a class=\"icon-close\" onclick=\"hideRate()\">Close</a>\n" +
                "            <input id=\"ratings-hidden\" name=\"rating\" type=\"hidden\">\n" +
                "            <textarea class=\"form-control animated\" cols=\"50\" id=\"new-review\" name=\"comment\"\n" +
                "                      placeholder=\"Enter your review here...\" rows=\"5\">"+commentAndRate.getComment()+"</textarea>\n" +
                "            <div class=\"container d-flex justify-content-center\">\n" +
                "                <div class=\"row\">\n" +
                "                    <div class=\"col-md-12\">\n" +
                "                        <div class=\"stars\">\n" +
                "                            <input "+getRateNumber(5,commentAndRate.getRate())+" class=\"star star-5\" id=\"star-5\" type=\"radio\" name=\"star\" value=\"5\"/>\n" +
                "                            <label class=\"star star-5\" for=\"star-5\"></label>\n" +
                "                            <input "+getRateNumber(4,commentAndRate.getRate())+" class=\"star star-4\" id=\"star-4\" type=\"radio\" name=\"star\" value=\"4\"/>\n" +
                "                            <label class=\"star star-4\" for=\"star-4\"></label>\n" +
                "                            <input "+getRateNumber(3,commentAndRate.getRate())+" class=\"star star-3\" id=\"star-3\" type=\"radio\" name=\"star\" value=\"3\"/>\n" +
                "                            <label class=\"star star-3\" for=\"star-3\"></label>\n" +
                "                            <input "+getRateNumber(2,commentAndRate.getRate())+" class=\"star star-2\" id=\"star-2\" type=\"radio\" name=\"star\" value=\"2\"/>\n" +
                "                            <label class=\"star star-2\" for=\"star-2\"></label>\n" +
                "                            <input "+getRateNumber(1,commentAndRate.getRate())+" class=\"star star-1\" id=\"star-1\" type=\"radio\" name=\"star\" value=\"1\"/>\n" +
                "                            <label class=\"star star-1\" for=\"star-1\"></label>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <button class=\"btn btn-success btn-lg\" type=\"button\" onclick=\"saveReview()\">Save</button>\n" +
                "        </form>";

        return result;
    }
    public String getRateNumber(int star, int ratedStar){
        return star==ratedStar?"checked":"";
    }
}
