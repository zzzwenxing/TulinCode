package com.tuling.control;

import com.tuling.mvc.FreemarkeView;
import com.tuling.mvc.MvcMapping;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BlogControl {
    List<BlogDoc> docs = new ArrayList<>();

    @MvcMapping("/edit")
    public FreemarkeView openEditPage(String user) {
        FreemarkeView freemarkeView = new FreemarkeView("edit.ftl");
        freemarkeView.setModel("authorName", user);
        freemarkeView.setModel("user", user);
        return freemarkeView;
    }

    @MvcMapping("/list")
    public FreemarkeView openDocList(String author) {
        List<BlogDoc> result = new ArrayList<>();
        if (author != null) {
            for (BlogDoc doc : docs) {
                if (author.equals(doc.getAuthor())) {
                    result.add(doc);
                }
            }
        } else {
            result.addAll(docs);
        }
        FreemarkeView freemarkeView = new FreemarkeView("docList.ftl");
        freemarkeView.setModel("authorName", author);
        freemarkeView.setModel("docs", result);
        return freemarkeView;
    }

    @MvcMapping("/save")
    public void openEditPage(String title, String author, String content, HttpServletResponse res) {
        BlogDoc doc = new BlogDoc(title, author, content, new Date());
        docs.add(doc);
        try {
            res.sendRedirect("/list");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
