package com.lrm.web;

import com.lrm.po.Type;
import com.lrm.service.BlogService;
import com.lrm.service.TypeService;
import com.lrm.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by limi on 2017/10/23.
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model) {
        //相当于查询全表，拿到所有的分类
        List<Type> types = typeService.listTypeTop(10000);
        //如果是从首页点击分类按钮跳转到这个页面的话，则id=-1，因为在前端页面写死了跳转链接为/types/-1，默认指定显示第一个分类下的所有博客
        if (id == -1) {
           id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));//分页显示该分类下的所有博客
        model.addAttribute("activeTypeId", id);//把当前点击的分类的id传回前端，这样就可以在前端渲染出激活的按钮，改变颜色
        return "types";
    }
}
